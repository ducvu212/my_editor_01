package framgia.com.myeditor.data.api;

import framgia.com.myeditor.data.model.Collection;
import framgia.com.myeditor.data.model.Image;
import io.reactivex.Single;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("photos/random")
    Single<List<Image>> getRandomsImage(@Query("client_id") String apiKey,
            @Query("count") int count);

    @GET("collections")
    Single<List<Collection>> getCollections(@Query("page") int page,
            @Query("client_id") String apiKey);

    @GET("photos")
    Single<List<Image>> getNewImage(@Query("page") int page, @Query("client_id") String apiKey);
}
