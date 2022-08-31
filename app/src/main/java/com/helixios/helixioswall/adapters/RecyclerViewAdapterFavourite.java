package com.helixios.helixioswall.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.helixios.helixioswall.R;
import com.helixios.helixioswall.model.Photo;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
        holder.mImageView.setVisibility(View.INVISIBLE);
        LottieAnimationView anim_preloader = holder.itemView.findViewById(R.id.animation_preloader_fave);
        LinearLayout lin_anim = holder.itemView.findViewById(R.id.lin_anim_fave);
        anim_preloader.setVisibility(View.VISIBLE);
        Glide.with(mContext).load(url_z).fitCenter().centerCrop().listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                Log.e("foto6", String.valueOf(e));
                Log.d("foto6", url_z);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                anim_preloader.setVisibility(View.GONE);
                holder.mImageView.setVisibility(View.VISIBLE);
                lin_anim.setVisibility(View.GONE);
                Log.d("foto6", "onSuccess: animation"+String.valueOf(holder.mImageView.getVisibility()));
                return false;
            }
        }).into(holder.mImageView);

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
