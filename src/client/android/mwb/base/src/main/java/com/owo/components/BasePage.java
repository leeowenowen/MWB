package com.owo.components;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.owo.android.util.ContextManager;
import com.owo.android.util.ui.DimensionUtil;
import com.owo.android.util.ui.LayoutParamUtil;
import com.owo.android.util.ui.LayoutUtil;

public class BasePage extends LinearLayout{
    protected ImageView mBack;
    protected TextView mTitle;
    private FrameLayout mTitleLeftExtensionContainer;
    private LinearLayout mTitleLayout;
    private FrameLayout mTitleLayoutContainer;
    private FrameLayout mContentView;
    private FrameLayout mTitleRightExtensionContainer;
    private boolean mScroll = false;

    public BasePage(Context context) {
        this(context, true);
    }

    public BasePage(Context context, boolean scroll) {
        super(context);
        mScroll = scroll;
        initComponents(context);
        setupListeners();
    }

    protected void setupListeners() {
        mBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = ContextManager.activity();
                if (activity != null) {
                    activity.finish();
                }
            }
        });
    }

    public void setTitleLeftExtension(View extension) {
        mTitleLeftExtensionContainer.addView(extension, LayoutParamUtil.FMM());
    }

    public void setTitleRightExtension(View extension) {
        mTitleRightExtensionContainer.addView(extension, LayoutParamUtil.FMM());
    }

    protected void initComponents(Context context) {
        mBack = new ImageView(context);
        mTitle = new TextView(context);
        mTitle.setGravity(Gravity.CENTER);
        mTitleLayout = new LinearLayout(context);
        mTitleLayout.addView(mBack);
        mTitleLeftExtensionContainer = new FrameLayout(context);
        mTitleLayout.addView(mTitleLeftExtensionContainer);
        mTitleLayout.setGravity(Gravity.CENTER_VERTICAL);

        mTitleRightExtensionContainer = new FrameLayout(context);

        mContentView = mScroll ? new ScrollView(context) : new FrameLayout(context);
        mTitleLayoutContainer = new FrameLayout(context);
        mTitleLayoutContainer.addView(mTitleLayout, LayoutParamUtil.FMM);
        mTitleLayoutContainer.addView(mTitle, LayoutParamUtil.FWWC);
        mTitleLayoutContainer.addView(mTitleRightExtensionContainer, LayoutParamUtil.FWWRCV);
        LayoutUtil.setMargin(mTitleRightExtensionContainer, 0, 0, 30, 0);
        LayoutUtil.setMargin(mBack, 20, 0, 0, 0);

        setOrientation(VERTICAL);
        addView(mTitleLayoutContainer,
                LayoutParamUtil.lp(LinearLayout.LayoutParams.MATCH_PARENT, DimensionUtil.h(100)));
        addView(mContentView, LayoutParamUtil.LMM());
    }

    public void setBackListener(OnClickListener listener) {
        mBack.setOnClickListener(listener);
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    public void setContentView(View contentView) {
        if (mScroll) {
            mContentView.addView(contentView);
        } else {
            mContentView.addView(contentView, LayoutParamUtil.FMM);
        }
    }
}