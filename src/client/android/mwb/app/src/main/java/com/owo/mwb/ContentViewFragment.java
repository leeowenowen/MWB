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
public class ContentViewFragment extends Fragment {
    private View v;

    public ContentViewFragment() {
    }

    public ContentViewFragment withContentView(View v) {
        this.v = v;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return v;
    }
}
