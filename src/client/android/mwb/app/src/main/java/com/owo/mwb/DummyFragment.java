package com.owo.mwb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by wangli on 16-6-5.
 */
public class DummyFragment extends Fragment {
    private String text;
    public int color;

    public DummyFragment(String text) {
        super();
        this.text = text;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView tv = new TextView(container.getContext());
        tv.setText(text);
        tv.setBackgroundColor(color);
        return tv;
    }
}
