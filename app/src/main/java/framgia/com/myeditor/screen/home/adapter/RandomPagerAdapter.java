package framgia.com.myeditor.screen.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import framgia.com.myeditor.R;
import framgia.com.myeditor.data.model.Image;
import framgia.com.myeditor.data.model.ImageRandom;
import framgia.com.myeditor.databinding.ItemRandomImageBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CuD HniM on 18/10/03.
 */
public class RandomPagerAdapter extends PagerAdapter {

    public static final int NUMBER_ZERO = 0;
    public static final int NUMBER_ONE = 1;
    private List<Image> mRandomList;

    public RandomPagerAdapter() {
        mRandomList = new ArrayList<>();
    }

    public void setRandomList(List<Image> list) {
        if (list.size() > 0) {
            mRandomList.clear();
            mRandomList.addAll(list);
        }
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ItemRandomImageBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(container.getContext()),
                        R.layout.item_random_image, container, false);
        View view = binding.getRoot();
        Image image = mRandomList.get(position);
        binding.setItem(new ImageRandom.Builder().mPath(image.getUrls().getRegular())
                .mLikeByUser(image.getLikedByUser() ? NUMBER_ONE : NUMBER_ZERO)
                .mUserName(image.getUser().getUsername())
                .mImageId(image.getId())
                .mRawImage(image.getUrls().getRaw())
                .mType(ImageType.REMOTE)
                .build());
        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, NUMBER_ZERO);
        return view;
    }

    @Override
    public int getCount() {
        return mRandomList == null ? NUMBER_ZERO : mRandomList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull View container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
