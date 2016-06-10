package com.owo.app.common;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.owo.android.util.ContextManager;
import com.owo.android.util.ui.DimensionUtil;
import com.owo.base.R;


public class OwoDialog extends Dialog {
    public OwoDialog(Context context, boolean hasTitle) {
        super(context);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        if (!hasTitle) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
    }

    private View mInterceptedContentView;
    
    @Override
    public void setContentView(View contentView) {
        if (contentView == null) {
            return;
        }

        mInterceptedContentView = contentView;
        Drawable d = contentView.getBackground();
        if (d == null) {
            contentView.setBackgroundResource(R.drawable.popup_bg);
        }
        super.setContentView(contentView);
    }

    public void setBgDrawableId(int id) {
        mInterceptedContentView.setBackgroundResource(id);
    }


    public static void show(int width, int height, View contentView) {
        OwoDialog dialog = new OwoDialog(ContextManager.context(), false);
        dialog.setContentView(contentView);
        WindowManager.LayoutParams param = dialog.getWindow().getAttributes();
        param.width = DimensionUtil.w(width);
        param.height = DimensionUtil.h(height);
        dialog.getWindow().setAttributes(param);
        dialog.show();
    }

}