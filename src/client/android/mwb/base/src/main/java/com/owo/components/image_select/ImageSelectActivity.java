package com.owo.components.image_select;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.owo.app.common.OwoActivity;
import com.owo.app.common.OwoBackOkActivity;
import com.owo.base.R;

public class ImageSelectActivity extends OwoBackOkActivity {
    private ImageSelectPage page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean isSingleSelect = getIntent().getBooleanExtra("is_single_select", false);
        boolean isWHEqual = getIntent().getBooleanExtra("is_wh_equal", false);
        boolean needCrop = getIntent().getBooleanExtra("need_crop", false);

        page = new ImageSelectPage(this, isWHEqual, isSingleSelect, needCrop);
        setContentView(page);
        setTitle(R.string.imge_select_title);
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putStringArrayListExtra("image_path", page.getSelectedImages());
                setResult(100, intent);
                finish();
            }
        });
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
