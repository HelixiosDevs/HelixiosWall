package com.helixios.helixioswall.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.helixios.helixioswall.R;
import com.helixios.helixioswall.model.Photo;
import com.helixios.helixioswall.model.SearchPhotos;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
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
        anim_preloader.setVisibility(View.VISIBLE);
        Picasso.get().load(url_z).fit().centerCrop().into(holder.mImageView, new Callback() {
            @Override
            public void onSuccess() {
                anim_preloader.setVisibility(View.GONE);
                holder.mImageView.setVisibility(View.VISIBLE);
                Log.d("foto6", "onSuccess: animation"+String.valueOf(holder.mImageView.getVisibility()));
            }

            @Override
            public void onError(Exception e) {
                Log.e("foto6", String.valueOf(holder.mImageView.getVisibility()));
            }
        });
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