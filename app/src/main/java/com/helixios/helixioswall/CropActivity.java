package com.helixios.helixioswall;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.helixios.helixioswall.model.Photo;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;
import java.util.function.DoubleToIntFunction;

public class CropActivity extends AppCompatActivity {

    public static final String TAG_URISTR = "tag_uri_str";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);
        final String uristr = this.getIntent().getStringExtra(TAG_URISTR);
        Uri sourceUri = Uri.parse(uristr);
        CropImageView cropView = findViewById(R.id.cropView);
        TextView cropText = findViewById(R.id.text_crop);

        int width = WallpaperManager.getInstance(this).getDesiredMinimumWidth();
        int height = WallpaperManager.getInstance(this).getDesiredMinimumHeight();
        Log.d("crop", String.valueOf(width)+" set and support"+String.valueOf(height));
        Log.d("crop", uristr);

        Bitmap bit = null;
        try {
            bit = MediaStore.Images.Media.getBitmap(this.getContentResolver(), sourceUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int wid2 = (int) (bit.getWidth()/1.5);
        int h2 = (int) (bit.getHeight()/1.5);
        Bitmap bit2 = Bitmap.createScaledBitmap(bit,wid2,h2,true);
        cropView.setImageBitmap(bit2);
       // cropView.setImageUriAsync(sourceUri);
        cropView.setAspectRatio(width, height);

        cropText.setOnClickListener(v -> {
            cropView.getCroppedImageAsync();
            Log.d("crop", "clicked crop");
        });

        cropView.setOnCropImageCompleteListener((view, result) -> {
            Log.d("crop","Click");
            if(result.getError() == null) {
                try{
                    Bitmap bitmap = result.getBitmap();
                    Log.d("crop", String.valueOf(bitmap.getHeight()+"height"));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                        WallpaperManager.getInstance(this).setBitmap(bitmap, null, true,
                                WallpaperManager.FLAG_SYSTEM|WallpaperManager.FLAG_LOCK);
                    } else {
                        WallpaperManager.getInstance(this).setBitmap(bitmap);
                    }
                    finish();
                }
                catch (Exception e) {
                    Log.d("crop","error"+e.toString());
                    Toast.makeText(CropActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Log.e("crop9", String.valueOf(result.isSuccessful()));
                Toast.makeText(CropActivity.this, result.getError().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}