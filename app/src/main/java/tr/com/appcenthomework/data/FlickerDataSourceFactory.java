package tr.com.appcenthomework.data;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import tr.com.appcenthomework.entity.Photo;

public class FlickerDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Photo>> itemLiveDataSource = new MutableLiveData<>();


    @Override
    public DataSource create() {
        FlickerDataSource flickerDataSource = new FlickerDataSource();
        itemLiveDataSource.postValue(flickerDataSource);
        return flickerDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Photo>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
