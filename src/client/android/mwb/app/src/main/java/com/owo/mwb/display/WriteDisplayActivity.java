package com.owo.mwb.display;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.owo.app.common.OwoActivity;
import com.owo.app.common.OwoBackOkActivity;

/**
 * Created by wangli on 16-6-9.
 */
public class WriteDisplayActivity extends OwoBackOkActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WriteDisplayPage page = new WriteDisplayPage(this);
        setContentView(page);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
