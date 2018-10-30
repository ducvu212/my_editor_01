package framgia.com.myeditor.screen.home;

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import framgia.com.myeditor.R;
import java.io.File;

/**
 * Created by CuD HniM on 18/10/03.
 */
public class BindingHome {

    public static final int ONE_HUNDRED = 100;
    public static final int NUMBER_CONVERT = 0.01;
    private static int mWidth;
    private static float mRatio;
    private static float mHeight;
    private static float mRatioView;
    private static String mType;

    public BindingHome(int width, int height, float ratio, @ImageType String type) {
        mWidth = width;
        mRatio = ratio;
        mHeight = height;
        mType = type;
        mRatioView = (float) (NUMBER_CONVERT * ((ONE_HUNDRED * height) / width));
    }

    @BindingAdapter({ "loadImg" })
    public static void loadImage(ImageView imageView, String url) {
        switch (mType) {
            case ImageType.REMOTE:
                createCreator(mWidth, (int) (mWidth * mRatio), url).networkPolicy(
                        NetworkPolicy.OFFLINE).into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        createCreator(mWidth, (int) (mWidth * mRatio), url).into(imageView);
                    }
                });
                break;
            case ImageType.LOCAL:
                Picasso.get()
                        .load(new File(url))
                        .placeholder(R.drawable.placeholder)
                        .into(imageView);
                break;
            default:
                break;
        }
    }

    private static RequestCreator createCreator(int width, int height, String url) {
        return Picasso.get()
                .load(url).placeholder(R.drawable.placeholder)
                .resize(width, height).onlyScaleDown();
    }

    @BindingAdapter({ "loadImgRandom" })
    public static void loadImageRandom(ImageView imageView, String url) {
        createCreator(mWidth, (int) (mWidth * mRatioView), url).networkPolicy(NetworkPolicy.OFFLINE)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        createCreator(mWidth, (int) (mWidth * mRatioView), url).centerCrop()
                                .into(imageView);
                    }
                });
    }

    public static String getmType() {
        return mType;
    }

    public static void setmType(String mType) {
        BindingHome.mType = mType;
    }
}
