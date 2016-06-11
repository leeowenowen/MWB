package com.owo.mwb;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.amap.api.maps.SupportMapFragment;
import com.owo.mwb.display.DisplayListView;

/**
 * Created by wangli on 16-6-5.
 */
public class TabPageIndicatorAdapter extends FragmentPagerAdapter {

    private String[] titles = {"0", "1", "2"};
    private Context context;


    public TabPageIndicatorAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ContentViewFragment().withContentView(new DisplayListView(context));
            case 1:
                return new MapFragment();
            case 2:
                return new DummyFragment("2xxx").withColor(Color.YELLOW);
            default:
                break;
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return 3;
    }
}
