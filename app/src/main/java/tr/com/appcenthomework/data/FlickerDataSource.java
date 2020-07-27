package tr.com.appcenthomework.data;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tr.com.appcenthomework.entity.FlickerRecentPhotosResponse;
import tr.com.appcenthomework.entity.Photo;


public class FlickerDataSource extends PageKeyedDataSource<Integer, Photo> {

    public static final int PAGE_SIZE = 20;
    private static final int FIRST_PAGE = 1;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Photo> callback) {

        RetrofitClient.getInstance()
                .getApi()
                .getPhotos(FIRST_PAGE, PAGE_SIZE)
                .enqueue(new Callback<FlickerRecentPhotosResponse>() {
                    @Override
                    public void onResponse(Call<FlickerRecentPhotosResponse> call, Response<FlickerRecentPhotosResponse> response) {

                        if (response.body() != null) {
                            callback.onResult(response.body().getPhotos().getPhoto(), null, FIRST_PAGE + 1);
                        }

                    }

                    @Override
                    public void onFailure(Call<FlickerRecentPhotosResponse> call, Throwable t) {

                    }
                });

    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Photo> callback) {

        RetrofitClient.getInstance()
                .getApi()
                .getPhotos(params.key, PAGE_SIZE)
                .enqueue(new Callback<FlickerRecentPhotosResponse>() {
                    @Override
                    public void onResponse(Call<FlickerRecentPhotosResponse> call, Response<FlickerRecentPhotosResponse> response) {
                        if (response.body() != null) {
                            Integer key = (params.key > 1) ? params.key - 1 : null;
                            callback.onResult(response.body().getPhotos().getPhoto(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<FlickerRecentPhotosResponse> call, Throwable t) {

                    }
                });

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Photo> callback) {

        RetrofitClient.getInstance()
                .getApi()
                .getPhotos(params.key, PAGE_SIZE)
                .enqueue(new Callback<FlickerRecentPhotosResponse>() {
                    @Override
                    public void onResponse(Call<FlickerRecentPhotosResponse> call, Response<FlickerRecentPhotosResponse> response) {
                        if (response.body() != null) {
                            Integer key = params.key < response.body().getPhotos().getPages() ? params.key + 1 : null;
                            callback.onResult(response.body().getPhotos().getPhoto(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<FlickerRecentPhotosResponse> call, Throwable t) {

                    }
                });


    }
}
