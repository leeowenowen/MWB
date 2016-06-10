package com.owo.app.common;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.owo.android.util.ContextManager;
import com.owo.android.util.log.Logger;
import com.owo.base.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public abstract class OwoActivity extends AppCompatActivity {
    protected View actionBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContextManager.init(this);
        initActionBar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ContextManager.init(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

    protected void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_background));
            int actionBarResId = getActionBarResId();

            actionBar.setCustomView(actionBarResId);
            actionBarView = actionBar.getCustomView();
            ButterKnife.bind(actionBarView);
        }
    }

    protected abstract int getActionBarResId();
}
