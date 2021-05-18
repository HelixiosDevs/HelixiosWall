package com.helixios.helixioswall.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.helixios.helixioswall.R;
import com.helixios.helixioswall.model.Photo;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapterFavourite extends RecyclerView.Adapter<RecyclerViewAdapterFavourite.FaveViewHolder> {
    private Context mContext;
    private ArrayList<Photo> mPhotos;
    private RecyclerViewAdapter.OnPhotoListener mOnPhotoListener;


    public RecyclerViewAdapterFavourite(Context context, ArrayList<Photo> photos, RecyclerViewAdapter.OnPhotoListener onPhotoListener) {
        mContext = context;
        mPhotos = photos;
        mOnPhotoListener = onPhotoListener;
    }

    @NonNull
    @Override
    public FaveViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_fave_photos,parent,false);

        return new FaveViewHolder(view, mOnPhotoListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FaveViewHolder holder, int position) {
        Photo currentPhoto = mPhotos.get(position);

        String id = currentPhoto.getId();
        String title = currentPhoto.getTitle();
        String url_z = currentPhoto.getUrl_z();

        Log.d("pix",id+"- is ID -"+title+"-is Title.");
        Log.d("pix",url_z);
        Picasso.get().load(url_z).placeholder(R.drawable.preloader_logo).fit().centerCrop().into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    public static class FaveViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView mImageView;
        public RecyclerViewAdapter.OnPhotoListener onPhotoListener;

        public FaveViewHolder(@NonNull @NotNull View itemView, RecyclerViewAdapter.OnPhotoListener onPhotoListener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.item_fave_imageView);
            this.onPhotoListener = onPhotoListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onPhotoListener.onPhotoClick(getAdapterPosition());
        }
    }
}
