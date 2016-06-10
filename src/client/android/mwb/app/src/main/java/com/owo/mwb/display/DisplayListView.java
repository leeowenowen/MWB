package com.owo.mwb.display;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.owo.app.common.OwoListView;
import com.owo.mwb.R;

import java.util.Observable;
import java.util.Observer;


public class DisplayListView extends OwoListView {
    private DisplayAdapter mDisplayAdapter = new DisplayAdapter();
    private DisplayModel mDisplayModel = DisplayModel.instance();

    public DisplayListView(Context context) {
        super(context);
        mDisplayModel.asObservable().addObserver(new Observer() {
            @Override
            public void update(Observable observable, Object data) {
                mDisplayAdapter.notifyDataSetChanged();
            }
        });
        setAdapter(mDisplayAdapter);
    }

    private class DisplayAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mDisplayModel.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            DisplayItemView itemView = null;
            if (convertView == null) {
                itemView = new DisplayItemView(parent.getContext());
            } else {
                itemView = (DisplayItemView) convertView;
            }
            itemView.update(mDisplayModel.get(position));
            return itemView;
        }
    }
}
