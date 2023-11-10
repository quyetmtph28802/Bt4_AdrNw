package quyetmtph28802.fpoly.bt4_adrnw.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;


import java.util.List;

import quyetmtph28802.fpoly.bt4_adrnw.DTO.Albums;
import quyetmtph28802.fpoly.bt4_adrnw.R;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {
    private List<Albums> albums;

    public AlbumAdapter(List<Albums> albums) {
        this.albums = albums;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item, parent, false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        Albums album = albums.get(position);
        holder.id.setText(String.valueOf(album.getId()));
        holder.titleTextView.setText(album.getTitle());

        // Load image using Picasso
        Picasso.get().load(album.getThumbnailUrl()).into(holder.thumbnailImageView);
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView, id;
        public ImageView thumbnailImageView;

        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.txt_Id);
            titleTextView = itemView.findViewById(R.id.txt_title);
            thumbnailImageView = itemView.findViewById(R.id.imageView);
        }
    }
}
