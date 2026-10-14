package com.helixios.helixioswall;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.canhub.cropper.CropImageView;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.io.IOException;

public class CropActivity extends AppCompatActivity {

    public static final String TAG_URISTR = "tag_uri_str";
    private static final String STATE_MODE = "crop_mode";
    private static final String STATE_RECT = "crop_rect";

    //Which screen orientation(s) the wallpaper is cropped for
    private static final int MODE_PORTRAIT = 0;
    private static final int MODE_LANDSCAPE = 1;
    private static final int MODE_BOTH = 2;

    private CropImageView cropView;
    private ImageView previewPortrait;
    private ImageView previewLandscape;
    private TextView modeHint;
    private Bitmap source;
    private int shortSide;
    private int longSide;
    private int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.crop_root), (v, windowInsets) -> {
            Insets systemBars = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return windowInsets;
        });

        final String uristr = this.getIntent().getStringExtra(TAG_URISTR);
        Uri sourceUri = Uri.parse(uristr);
        cropView = findViewById(R.id.cropView);
        previewPortrait = findViewById(R.id.preview_portrait);
        previewLandscape = findViewById(R.id.preview_landscape);
        modeHint = findViewById(R.id.crop_mode_hint);
        MaterialButtonToggleGroup modeGroup = findViewById(R.id.crop_mode_group);
        Button setButton = findViewById(R.id.crop_set);

        //Full screen size, including system bars, since the wallpaper is drawn behind them
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        shortSide = Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
        longSide = Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels);

        Bitmap bit = null;
        try {
            bit = MediaStore.Images.Media.getBitmap(this.getContentResolver(), sourceUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (bit == null) {
            Toast.makeText(this, "Couldn't open the downloaded image", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        if(bit.getWidth()>6000 || bit.getHeight()>6000) {
            int wid2 = (int) (bit.getWidth()/1.5);
            int h2 = (int) (bit.getHeight()/1.5);
            bit = Bitmap.createScaledBitmap(bit,wid2,h2,true);
        }
        source = bit;
        cropView.setImageBitmap(source);

        Rect savedRect = null;
        if (savedInstanceState != null) {
            mode = savedInstanceState.getInt(STATE_MODE, MODE_PORTRAIT);
            savedRect = savedInstanceState.getParcelable(STATE_RECT);
        }
        else {
            //Rotating screens (tablets, foldables) default to a crop that works both ways
            boolean rotatesOften = getResources().getConfiguration().smallestScreenWidthDp >= 600;
            mode = rotatesOften ? MODE_BOTH : MODE_PORTRAIT;
        }
        modeGroup.check(buttonForMode(mode));
        applyMode(mode, savedRect);
        modeGroup.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (isChecked) {
                applyMode(modeForButton(checkedId), null);
            }
        });

        cropView.setOnSetCropOverlayReleasedListener(rect -> updatePreviews());
        setButton.setOnClickListener(v -> setWallpaper(setButton));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (cropView != null && source != null) {
            outState.putInt(STATE_MODE, mode);
            outState.putParcelable(STATE_RECT, cropView.getCropRect());
        }
    }

    private void applyMode(int newMode, Rect restoreRect) {
        mode = newMode;
        if (mode == MODE_PORTRAIT) {
            cropView.setAspectRatio(shortSide, longSide);
            modeHint.setText("Fits the screen held upright");
        }
        else if (mode == MODE_LANDSCAPE) {
            cropView.setAspectRatio(longSide, shortSide);
            modeHint.setText("Fits the screen held sideways");
        }
        else {
            //A square as wide as the long screen side contains both views: the launcher shows
            //its centre column in portrait and its centre band in landscape
            cropView.setAspectRatio(1, 1);
            modeHint.setText("One crop for both orientations, like the system picker");
        }
        if (restoreRect != null) {
            cropView.setCropRect(restoreRect);
        }
        cropView.post(this::updatePreviews);
    }

    //Shows what the home screen displays in each orientation for the current crop
    private void updatePreviews() {
        Rect crop = currentCrop();
        if (crop == null) {
            return;
        }
        int thumbPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 96,
                getResources().getDisplayMetrics());
        previewPortrait.setImageBitmap(centreThumbnail(crop, shortSide, longSide, thumbPx));
        previewLandscape.setImageBitmap(centreThumbnail(crop, longSide, shortSide, thumbPx));
    }

    //Largest aspectW:aspectH region centred in crop, scaled so its long edge is maxPx
    private Bitmap centreThumbnail(Rect crop, int aspectW, int aspectH, int maxPx) {
        int w = crop.width();
        int h = crop.height();
        if ((long) w * aspectH > (long) h * aspectW) {
            w = (int) ((long) h * aspectW / aspectH);
        }
        else {
            h = (int) ((long) w * aspectH / aspectW);
        }
        int x = crop.left + (crop.width() - w) / 2;
        int y = crop.top + (crop.height() - h) / 2;
        float scale = Math.min(1f, (float) maxPx / Math.max(w, h));
        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);
        return Bitmap.createBitmap(source, x, y, Math.max(1, w), Math.max(1, h), matrix, true);
    }

    //Crop window in source-bitmap pixels, clamped to the image
    private Rect currentCrop() {
        Rect crop = cropView.getCropRect();
        if (crop == null || !crop.intersect(0, 0, source.getWidth(), source.getHeight()) || crop.isEmpty()) {
            return null;
        }
        return crop;
    }

    private void setWallpaper(Button setButton) {
        Rect crop = currentCrop();
        if (crop == null) {
            return;
        }
        setButton.setEnabled(false);
        Toast.makeText(this, "Setting wallpaper", Toast.LENGTH_SHORT).show();
        new Thread(() -> {
            try {
                Bitmap bitmap = Bitmap.createBitmap(source, crop.left, crop.top, crop.width(), crop.height());
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    wallpaperManager.setBitmap(bitmap, null, true,
                            WallpaperManager.FLAG_SYSTEM|WallpaperManager.FLAG_LOCK);
                } else {
                    wallpaperManager.setBitmap(bitmap);
                }
                runOnUiThread(() -> {
                    Toast.makeText(CropActivity.this, "Wallpaper set", Toast.LENGTH_SHORT).show();
                    finish();
                });
            }
            catch (Exception e) {
                runOnUiThread(() -> {
                    Toast.makeText(CropActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    setButton.setEnabled(true);
                });
            }
        }).start();
    }

    private static int buttonForMode(int mode) {
        if (mode == MODE_LANDSCAPE) return R.id.crop_landscape;
        if (mode == MODE_BOTH) return R.id.crop_both;
        return R.id.crop_portrait;
    }

    private static int modeForButton(int buttonId) {
        if (buttonId == R.id.crop_landscape) return MODE_LANDSCAPE;
        if (buttonId == R.id.crop_both) return MODE_BOTH;
        return MODE_PORTRAIT;
    }
}
