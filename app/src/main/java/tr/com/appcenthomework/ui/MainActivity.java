package tr.com.appcenthomework.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import tr.com.appcenthomework.R;
import tr.com.appcenthomework.entity.Photo;
import tr.com.appcenthomework.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        final MainAdapter adapter = new MainAdapter(this, new OnItemClickListener() {
            @Override
            public void onItemClick(Photo photo) {
                Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                intent.putExtra("photo", photo);
                startActivity(intent);
            }
        });

        mainViewModel.getItems().observe(this, new Observer<PagedList<Photo>>() {
            @Override
            public void onChanged(@Nullable PagedList<Photo> items) {
                adapter.submitList(items);
            }
        });

        recyclerView.setAdapter(adapter);
    }
}
