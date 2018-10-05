package framgia.com.myeditor.data.source.local;

import framgia.com.myeditor.data.source.ImageDataSource;

/**
 * Created by CuD HniM on 18/10/03.
 */
public class ImageLocalDataSource implements ImageDataSource.ImageLocalDataSource {
    private static ImageLocalDataSource sInstance;

    public static synchronized ImageLocalDataSource getsInstance() {
        if (sInstance == null) {
            synchronized (ImageLocalDataSource.class) {
                if (sInstance == null) {
                    sInstance = new ImageLocalDataSource();
                }
            }
        }
        return sInstance;
    }
}
