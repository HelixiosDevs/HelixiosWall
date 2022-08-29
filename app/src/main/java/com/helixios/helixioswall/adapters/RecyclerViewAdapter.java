package com.helixios.helixioswall.adapters;

import android.content.Context;
import android.content.Intent;
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
import com.helixios.helixioswall.model.SearchPhotos;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<Photo> mPhotos;
    private OnPhotoListener mOnPhotoListener;

    public RecyclerViewAdapter(Context context, ArrayList<Photo> photoList, OnPhotoListener onPhotoListener) {
        mContext = context;
        mPhotos = photoList;
        this.mOnPhotoListener = onPhotoListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_photos,parent,false);

        return new MyViewHolder(view, mOnPhotoListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Photo currentPhoto = mPhotos.get(position);

        String id = currentPhoto.getId();
        String title = currentPhoto.getTitle();
        String url_z = currentPhoto.getUrl_z();

        Log.d("pix",id+"- is ID -"+title+"-is Title.");
        Log.d("pix",url_z);
        holder.mImageView.setVisibility(View.INVISIBLE);
        LottieAnimationView anim_preloader = holder.itemView.findViewById(R.id.animation_preloader);
        LinearLayout lin_anim = holder.itemView.findViewById(R.id.lin_anim);
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

//        Picasso.get().load(url_z).fit().centerCrop().into(holder.mImageView, new Callback() {
//            @Override
//            public void onSuccess() {
//                anim_preloader.setVisibility(View.GONE);
//                holder.mImageView.setVisibility(View.VISIBLE);
//                lin_anim.setVisibility(View.GONE);
//                Log.d("foto6", "onSuccess: animation"+String.valueOf(holder.mImageView.getVisibility()));
//            }
//
//            @Override
//            public void onError(Exception e) {
//                Log.e("foto6", String.valueOf(e));
//                Log.d("foto6", url_z);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        //Log.i("foto1", String.valueOf((mPhotos.size())));
        return mPhotos.size();
    }

/*
 Implement this method when total number of photos reach 500.
    private boolean hasExtraRow() {
        return false;
    }
*/

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView mImageView;
        public OnPhotoListener onPhotoListener;

        public MyViewHolder(@NonNull View itemView, OnPhotoListener onPhotoListener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.item_home_imageView);
            this.onPhotoListener = onPhotoListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onPhotoListener.onPhotoClick(getAdapterPosition());
        }
    }

    public interface OnPhotoListener {
        void onPhotoClick(int position);
    }
}