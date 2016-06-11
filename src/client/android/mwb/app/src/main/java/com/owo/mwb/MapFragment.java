package com.owo.mwb;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.owo.mwb.map.MapFrame;

/**
 * Created by wangli on 16-6-11.
 */
public class MapFragment extends Fragment {
    private MapFrame mapFrame;

    public MapFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mapFrame = new MapFrame(getContext());
        mapFrame.onCreate(savedInstanceState);
        return mapFrame;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapFrame.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapFrame.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapFrame.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapFrame.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        mapFrame.onRestoreInstanceState(savedInstanceState);
    }
}
