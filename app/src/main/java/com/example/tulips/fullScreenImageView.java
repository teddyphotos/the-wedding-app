package com.example.tulips;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.jsibbold.zoomage.ZoomageView;

public class fullScreenImageView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image_view);
        if(getIntent().hasExtra(currentRecitingVerses.EXTRA_IMAGE_ID)) {
            Bitmap b = BitmapFactory.decodeByteArray(
                    getIntent().getByteArrayExtra(currentRecitingVerses.EXTRA_IMAGE_ID),0,getIntent()
                            .getByteArrayExtra(currentRecitingVerses.EXTRA_IMAGE_ID).length);
            ZoomageView zoomageView = findViewById(R.id.imageView2);
            zoomageView.setImageBitmap(b);
            zoomageView.setAutoCenter(true);

        }

    }
}
