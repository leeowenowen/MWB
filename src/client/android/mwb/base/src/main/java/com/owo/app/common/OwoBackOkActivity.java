package com.owo.app.common;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.owo.android.util.log.Logger;
import com.owo.base.R;


public class OwoBackOkActivity extends OwoActivity {
    private static final String TAG = OwoBackOkActivity.class.getSimpleName();
    protected ImageView ivBack;
    protected TextView tvTitle;
    protected TextView tvOk;


    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

    protected void initActionBar() {
        super.initActionBar();
        ivBack = (ImageView) actionBarView.findViewById(R.id.iv_back);
        tvTitle = (TextView) actionBarView.findViewById(R.id.tv_title);
        tvOk = (TextView) actionBarView.findViewById(R.id.tv_right);
        try {
            ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } catch (Exception e) {
            Logger.e(TAG, "This actionbar custom view doesn't have a view which id is iv_actionbar_back");
        }
    }

    protected int getActionBarResId() {
        return R.layout.actionbar_back_title_ok;
    }

    protected void setActionBarTitle(int id) {
        try {
            tvTitle.setText(id);
        } catch (Exception e) {
            Logger.e(TAG, "This Custom ActionBar doesn't have a TextView which Id is tv_actionbar_title");
        }
    }

    protected void setActionBarBackButtonEnable(boolean enable) {
        try {
            if (enable) {
                ivBack.setVisibility(View.VISIBLE);
            } else {
                ivBack.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            Logger.e(TAG, "This Custom ActionBar doesn't have a View which Id is iv_back");
        }
    }
}
