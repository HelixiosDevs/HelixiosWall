package com.helixios.helixioswall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.helixios.helixioswall.model.Photo;
import com.helixios.helixioswall.networking.DownloadBroadcastReceiver;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;

public class PhotoActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 100;
    String imageName = null;
    String down_url = null;
    DownloadBroadcastReceiver downReceiver = null;

    enum Action{
        WALLPAPER,
        DOWNLOAD_ONLY
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        // Postpone the shared element enter transition.
        postponeEnterTransition();

        ImageView imageView = findViewById(R.id.photo_full);
        TextView textView = findViewById(R.id.creator_photo_full);
        FloatingActionButton setWallFab = findViewById(R.id.fab_wall);
        FloatingActionButton downloadFab = findViewById(R.id.fab_download);
        FloatingActionButton FaveFab = findViewById(R.id.fab_fave);
        FloatingActionButton shareFab = findViewById(R.id.fab_share);
        Photo photo = null;
        String url;

        if(getIntent().hasExtra("photo")) {
            photo = getIntent().getParcelableExtra("photo");
        }
        else {
            finish();
        }
        imageView.setTransitionName("sharedPhotoTransition"+photo.getId());
        Log.d("foto8", imageView.getTransitionName());

//        Explode explode = new Explode();
//        View decor = getWindow().getDecorView();
//        explode.excludeTarget(decor.findViewById(R.id.action_bar_container), true);
//        explode.excludeTarget(android.R.id.statusBarBackground, true);
//        explode.excludeTarget(android.R.id.navigationBarBackground, true);
//        explode.excludeTarget(imageView,true);
//        explode.setDuration(500);
//        getWindow().setEnterTransition(explode);
//        getWindow().setExitTransition(explode);

        getWindow().setSharedElementEnterTransition(new ChangeBounds().setDuration(1000));

        if (photo.getUrl_o()==null) {
            //b type img is 1024px long edge(unrestricted best quality)
            url = "https://farm" + photo.getFarm() + ".staticflickr.com/" + photo.getServer() + "/" + photo.getId() + "_" + photo.getSecret() + "_b.jpg";
        }
        else {
            url = photo.getUrl_o();
        }
        imageName = photo.getTitle()+photo.getId()+".jpg";
        Picasso.get().load(url).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                //View
                ActivityCompat.startPostponedEnterTransition(PhotoActivity.this);
            }

            @Override
            public void onError(Exception e) {
                ActivityCompat.startPostponedEnterTransition(PhotoActivity.this);
                Log.e("Error", e.getMessage() );
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
        }


        //getWindow().setSharedElementEnterTransition(new ChangeBounds().addTarget(imageView).setStartDelay(800).setDuration(1000));




        down_url = photo.getUrl_o();
        Log.d("foto", "onCreate:"+photo.getOwner_name());
        downloadFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadImage(url,imageName);
                Toast.makeText(PhotoActivity.this,"Download maybe started",Toast.LENGTH_LONG).show();
            }
        });

        setWallFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadImage(down_url,imageName);
                setWallServe();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(downReceiver != null){
            unregisterReceiver(downReceiver);
        }
    }

    public void setWallServe()
    {
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()+ File.separator + imageName;
        Toast.makeText(this,"This works right ?",Toast.LENGTH_SHORT).show();
//Moved this code to BroadcastReceiver to execute according to the download
//        Uri uri = FileProvider.getUriForFile(this, "com.helixios.helixioswall.fileprovider", new File(path));
//        Intent wallMan =WallpaperManager.getInstance(this).getCropAndSetWallpaperIntent(uri);
        try {
            downReceiver = new DownloadBroadcastReceiver(path);
            registerReceiver(downReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void downloadImage(String url, String outputFileName) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle(imageName);
        request.setDescription("Downloading " + imageName);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.allowScanningByMediaScanner();
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, outputFileName);
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }
}