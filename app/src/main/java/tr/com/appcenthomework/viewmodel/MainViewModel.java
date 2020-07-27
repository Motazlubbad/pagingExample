package tr.com.appcenthomework.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;
import tr.com.appcenthomework.data.FlickerDataSource;
import tr.com.appcenthomework.data.FlickerDataSourceFactory;
import tr.com.appcenthomework.entity.Photo;

public class MainViewModel extends ViewModel {

    LiveData<PagedList<Photo>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, Photo>> liveDataSource;

    public MainViewModel() {

        FlickerDataSourceFactory flickerDataSourceFactory = new FlickerDataSourceFactory();
        liveDataSource = flickerDataSourceFactory.getItemLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(FlickerDataSource.PAGE_SIZE)
                        .build();

        itemPagedList = (new LivePagedListBuilder(flickerDataSourceFactory, config)).build();

    }

    public LiveData<PagedList<Photo>> getItems() {
        return itemPagedList;
    }
}
