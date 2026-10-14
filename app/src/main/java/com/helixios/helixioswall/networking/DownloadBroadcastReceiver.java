package com.helixios.helixioswall.networking;

import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.helixios.helixioswall.CropActivity;

import java.io.File;

import androidx.core.content.FileProvider;

public class DownloadBroadcastReceiver extends BroadcastReceiver {

    String path;
    long downloadId;


    public DownloadBroadcastReceiver(String path, long downloadId) {
        this.path = path;
        this.downloadId = downloadId;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        //Listens for the download complete broadcast

        long completedId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
        //Ignore downloads that aren't the wallpaper this receiver was registered for
        if(DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action) && completedId == downloadId) {
            //uri is the file path using which the downloaded images are located

            Uri uri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", new File(path));
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
                //No system cropper accepts the image (e.g. Samsung), so use the in-app cropper
                Log.e("crop99",e.toString()  );
                Intent myCrop =new Intent(context, CropActivity.class);
                myCrop.putExtra(CropActivity.TAG_URISTR, uri.toString());
                context.startActivity(myCrop);
            }
        }
    }
}