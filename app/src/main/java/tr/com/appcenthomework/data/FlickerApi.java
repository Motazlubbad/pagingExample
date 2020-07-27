package tr.com.appcenthomework.data;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import tr.com.appcenthomework.entity.FlickerRecentPhotosResponse;

public interface FlickerApi {

    @GET("?method=flickr.photos.getRecent&api_key=ed9a9bccda692eba2f2e460c9f846fe3&format=json&nojsoncallback=1")
    Call<FlickerRecentPhotosResponse> getPhotos(
            @Query("page") int page,
            @Query("per_page") int pageSize
    );

}
