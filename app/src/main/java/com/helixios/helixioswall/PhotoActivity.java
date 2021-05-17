package com.helixios.helixioswall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.Manifest;
import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.helixios.helixioswall.database.HelixDatabase;
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
    long down_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        // Postpone the shared element enter transition.
        //postponeEnterTransition();

        ImageView imageView = findViewById(R.id.photo_full);
        ImageView back_button = findViewById(R.id.back_button);
        TextView textView = findViewById(R.id.creator_photo_full);
        TextView favText = findViewById(R.id.add_fav_text);
        ImageView add_fave = findViewById(R.id.add_favourite);
        LinearLayout setWallClick = findViewById(R.id.wall_click);
        LinearLayout downloadClick = findViewById(R.id.down_click);
        LinearLayout shareClick = findViewById(R.id.share_click);
        LinearLayout faveClick = findViewById(R.id.fave_click);
//        FloatingActionButton FaveFab = findViewById(R.id.fab_fave);
//        FloatingActionButton shareFab = findViewById(R.id.fab_share);
        Photo photo = null;
        String url;

        if(getIntent().hasExtra("photo")) {
            photo = getIntent().getParcelableExtra("photo");
        }
        else {
            finish();
        }

//        Explode explode = new Explode();
//        View decor = getWindow().getDecorView();
//        explode.excludeTarget(decor.findViewById(R.id.action_bar_container), true);
//        explode.excludeTarget(android.R.id.statusBarBackground, true);
//        explode.excludeTarget(android.R.id.navigationBarBackground, true);
//        explode.excludeTarget(imageView,true);
//        explode.setDuration(500);
//        getWindow().setEnterTransition(explode);
//        getWindow().setExitTransition(explode);
//        getWindow().setSharedElementEnterTransition(new ChangeBounds().setDuration(1000));

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

        textView.setText("By "+photo.getOwner_name());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
        }

        //getWindow().setSharedElementEnterTransition(new ChangeBounds().addTarget(imageView).setStartDelay(800).setDuration(1000));

        down_url = photo.getUrl_o();
        Log.d("foto", "onCreate:"+photo.getOwner_name());
        downloadClick.setOnClickListener(v -> {
            downloadImage(down_url,imageName);
            Toast.makeText(PhotoActivity.this,"Download maybe started",Toast.LENGTH_LONG).show();
        });

        setWallClick.setOnClickListener(v -> {
            downloadImage(down_url,imageName);
            setWallServe();
        });

        Photo finalPhoto = photo;
        final boolean[] clicked = {false};
        final HelixDatabase[] Db = new HelixDatabase[1];

        Thread thread = new Thread(() -> {
            Db[0] = HelixDatabase.getInstance(PhotoActivity.this);

            Photo foto2 = Db[0].favDao().getfaveStatus(finalPhoto.getId());
            if(foto2 != null ) {
                if(finalPhoto.getId().equals(foto2.getId())) {
                    clicked[0]=true;
                    add_fave.setImageResource(R.drawable.fav_icon_red);
                    favText.setText("Added To Favourites");
                }
            }

        });
        thread.start();

        faveClick.setOnClickListener(view -> {
            Thread thread2 = new Thread(() -> {
                if(clicked[0]) {
                    Db[0].favDao().removeFavourite(finalPhoto.getId());
                    clicked[0] = false;
                }
                else {
                    Db[0].favDao().insert(finalPhoto);
                    clicked[0] = true;
                }
            });
            thread2.start();
            while(thread2.isAlive()){
                continue;
            }
            if(clicked[0]) {
                add_fave.setImageResource(R.drawable.fav_icon_red);
                favText.setText("Added To Favourites");
            }
            else {
                add_fave.setImageResource(R.drawable.fav_icon);
                favText.setText("Add To Favourites");
            }
        });

        shareClick.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String shareUrl = "https://www.flickr.com/photos/"+finalPhoto.getOwner()+"/"+finalPhoto.getId();
            intent.putExtra(Intent.EXTRA_TEXT,shareUrl);
            startActivity(Intent.createChooser(intent,"Share photo"));
        });


        back_button.setOnClickListener(v -> finish());
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
        Log.i("cop", String.valueOf(MediaStore.Images.Media.getContentUri(imageName)));

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
        down_id = manager.enqueue(request);
    }
}