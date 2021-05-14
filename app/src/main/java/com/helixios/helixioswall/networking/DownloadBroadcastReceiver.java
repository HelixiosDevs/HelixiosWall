package com.helixios.helixioswall.networking;

import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

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
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                    Intent myCrop =new Intent(context, CropActivity.class);
                    myCrop.putExtra(CropActivity.TAG_URISTR, uri.toString());
                    context.startActivity(myCrop);
                }
                else{
                    context.startActivity(wallMan);
                }
            }
            catch (IllegalArgumentException e) {
                Intent myCrop =new Intent(context, CropActivity.class);
                myCrop.putExtra(CropActivity.TAG_URISTR, uri.toString());
                context.startActivity(myCrop);
            }
        }
    }
}