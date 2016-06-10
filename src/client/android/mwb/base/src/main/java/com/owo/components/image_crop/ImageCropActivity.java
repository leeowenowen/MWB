package com.owo.components.image_crop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.owo.app.common.OwoActivity;
import com.owo.app.common.OwoBackOkActivity;


public class ImageCropActivity extends OwoBackOkActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String imagePath = getIntent().getStringExtra("image_path");
        boolean isWHEqual = getIntent().getBooleanExtra("is_wh_equal", false);
        int width = getIntent().getIntExtra("width", 0);
        int height = getIntent().getIntExtra("height", 0);
        setContentView(new ImageCropPage(this, imagePath, isWHEqual, width, height));
    }
}
