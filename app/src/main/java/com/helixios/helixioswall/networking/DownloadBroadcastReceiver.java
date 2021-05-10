package com.helixios.helixioswall.networking;

import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

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

        if(DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
            Uri uri = FileProvider.getUriForFile(context, "com.helixios.helixioswall.fileprovider", new File(path));
            Intent wallMan = WallpaperManager.getInstance(context).getCropAndSetWallpaperIntent(uri);
            context.startActivity(wallMan);
        }
    }
}