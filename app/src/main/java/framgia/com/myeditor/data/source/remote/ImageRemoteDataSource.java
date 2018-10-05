package framgia.com.myeditor.data.source.remote;

import framgia.com.myeditor.BuildConfig;
import framgia.com.myeditor.data.api.ApiClient;
import framgia.com.myeditor.data.api.ApiInterface;
import framgia.com.myeditor.data.model.Collection;
import framgia.com.myeditor.data.model.Image;
import framgia.com.myeditor.data.source.ImageDataSource;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by CuD HniM on 18/09/25.
 */
public class ImageRemoteDataSource implements ImageDataSource.ImageRemoteDataSource {

    private static final int NUMBER_RANDOM = 10;
    private static ImageRemoteDataSource sInstance;

    public static synchronized ImageRemoteDataSource getsInstance() {
        if (sInstance == null) {
            synchronized (ImageRemoteDataSource.class) {
                if (sInstance == null) {
                    sInstance = new ImageRemoteDataSource();
                }
            }
        }
        return sInstance;
    }

    @Override
    public Single<List<Image>> getRandomImages() {
        return createApiInterface().getRandomsImage(BuildConfig.API_KEY, NUMBER_RANDOM);
    }

    @Override
    public Single<List<Collection>> getCollections(int page) {
        return createApiInterface().getCollections(page, BuildConfig.API_KEY);
    }

    @Override
    public Single<List<Image>> getNewImages(int page, String apiKey) {
        return createApiInterface().getNewImage(page, apiKey);
    }

    private ApiInterface createApiInterface() {
        return ApiClient.getInstance().create(ApiInterface.class);
    }
}
