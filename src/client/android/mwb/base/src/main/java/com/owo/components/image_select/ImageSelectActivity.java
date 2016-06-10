package com.owo.components.image_select;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.owo.app.common.OwoActivity;
import com.owo.app.common.OwoBackOkActivity;

public class ImageSelectActivity extends OwoBackOkActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean isSingleSelect = getIntent().getBooleanExtra("is_single_select", false);
        boolean isWHEqual = getIntent().getBooleanExtra("is_wh_equal", false);
        boolean needCrop = getIntent().getBooleanExtra("need_crop", false);

        setContentView(new ImageSelectPage(this, isWHEqual, isSingleSelect, needCrop));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != 100) {
            return;
        }
        setResult(requestCode, data);
        finish();
    }
}
