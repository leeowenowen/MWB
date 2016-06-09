package com.owo.android.util.ui;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.owo.android.util.log.Logger;

/*
 * View UTIL
 */
public class ViewUtil {
    /**
     * 动态设置ListView的高度
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        if (listView == null)
            return;

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }


    @SuppressLint("NewApi")
    public static void setGridViewHeightBasedOnChildren(GridView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null || listAdapter.getCount() == 0) {
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = 0;
            listView.setLayoutParams(params);
            return;
        }
        int col = listView.getNumColumns();
        int count = listAdapter.getCount();

        View listItem = listAdapter.getView(0, null, listView);
        listItem.measure(0, 0);
        int totalHeight = listItem.getMeasuredHeight() * ((count % col == 0) ? (count / col) : count / col + 1);
        totalHeight += listView.getVerticalSpacing() * (count / col + 1);

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight;
        if (totalHeight == 0) {
            Logger.v("ViewUtil", "gridview height 0");
        }
        listView.setLayoutParams(params);
    }

    public static void setGridViewHeightBasedOnChildren(GridView listView, int itemHeight) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null || listAdapter.getCount() == 0) {
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = 0;
            listView.setLayoutParams(params);
            return;
        }
        int col = listView.getNumColumns();
        int count = listAdapter.getCount();

        int totalHeight = itemHeight * ((count % col == 0) ? (count / col) : count / col + 1);
        totalHeight += 5 * (count / col + 1);

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight;
        if (totalHeight == 0) {
            Logger.v("ViewUtil", "gridview height 0");
        }
        listView.setLayoutParams(params);
    }
}
