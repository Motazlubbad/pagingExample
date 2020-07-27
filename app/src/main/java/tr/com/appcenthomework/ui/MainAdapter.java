package tr.com.appcenthomework.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import tr.com.appcenthomework.R;
import tr.com.appcenthomework.entity.Photo;

public class MainAdapter extends PagedListAdapter<Photo, MainAdapter.ItemViewHolder> {

    private static DiffUtil.ItemCallback<Photo> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Photo>() {
                @Override
                public boolean areItemsTheSame(Photo oldItem, Photo newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }

                @Override
                public boolean areContentsTheSame(Photo oldItem, Photo newItem) {
                    return oldItem.equals(newItem);
                }
            };
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    protected MainAdapter(Context mContext, OnItemClickListener onItemClickListener) {
        super(DIFF_CALLBACK);
        this.mContext = mContext;
        this.mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        Photo item = getItem(position);

        assert item != null;
        Glide.with(mContext)
                .load("https://farm" + item.getFarm() + ".staticflickr.com/" + item.getServer() + "/" + item.getId() + "_" + item.getSecret() + "_m.jpg")
                .into(holder.imageView);

        holder.textView.setText(item.getTitle());

        holder.itemView.setOnClickListener(v -> mOnItemClickListener.onItemClick(item));


    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public ItemViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textViewName);
        }
    }

}
