package com.helixios.helixioswall.networking;

import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;

import com.helixios.helixioswall.CropActivity;

import java.io.File;

import androidx.core.content.FileProvider;

public class DownloadBroadcastReceiver extends BroadcastReceiver {

    String path;

    public DownloadBroadcastReceiver(String path) {
        this.path = path;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        //Listens for the download complete broadcast

        if(DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
            //uri is the file path using which the downloaded images are located

            Uri uri = FileProvider.getUriForFile(context, "com.helixios.helixioswall.fileprovider", new File(path));
            //Wallpaper manager is built-in android to set an image as wallpaper
            //getCropAndSetWallpaperIntent takes the uri of an image and returns a runnable intent
            //that intent can be used to set wallpaper.
            try{
                Intent wallMan = WallpaperManager.getInstance(context).getCropAndSetWallpaperIntent(uri);
                wallMan.setDataAndType(uri, "image/*");
                wallMan.putExtra("mimeType", "image/*");
                wallMan.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                wallMan.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                wallMan.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

                Log.d("crop2", uri.toString());
                context.startActivity(wallMan);
            }
            catch (IllegalArgumentException e) {
                String[] arrOfStr = path.split("/", 100);
                String imageName = arrOfStr[arrOfStr.length-1];
                //Log.i("cop", String.valueOf(MediaStore.Images.Media.DISPLAY_NAME.));
                Uri u1 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                Uri u2 = Uri.parse(u1.toString()+ File.separator+imageName);
                Log.i("cop",u2.toString());
                
                Intent wall = WallpaperManager.getInstance(context).getCropAndSetWallpaperIntent(u2);
                wall.setDataAndType(u2, "image/*");
                wall.putExtra("mimeType", "image/*");
                context.startActivity(wall);
//                Log.e("crop99",e.toString()  );
//                Intent myCrop =new Intent(context, CropActivity.class);
//                myCrop.putExtra(CropActivity.TAG_URISTR, uri.toString());
//                context.startActivity(myCrop);
            }
        }
    }
}