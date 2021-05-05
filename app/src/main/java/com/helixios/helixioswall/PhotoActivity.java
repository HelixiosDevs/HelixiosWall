package com.helixios.helixioswall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.helixios.helixioswall.model.Photo;
import com.squareup.picasso.Picasso;

public class PhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ImageView imageView = findViewById(R.id.photo_full);
        TextView textView = findViewById(R.id.creator_photo_full);
        Photo photo = null;
        String url;
        if(getIntent().hasExtra("photo")) {
            photo = getIntent().getParcelableExtra("photo");
        }
        else {
            finish();
        }
        if (photo.getUrl_o()==null) {
            url = "https://farm" + photo.getFarm() + ".staticflickr.com/" + photo.getServer() + "/" + photo.getId() + "_" + photo.getSecret() + "_b.jpg";
        }
        else {
            url = photo.getUrl_o();
        }
        Picasso.get().load(url).into(imageView);
        textView.setText(photo.getOwner_name());
        Log.d("foto", "onCreate:"+photo.getOwner_name()+"-"+photo.getOwner());
    }
}