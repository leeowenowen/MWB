package com.owo.mwb;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by wangli on 16-6-5.
 */
public class TabPageIndicatorAdapter extends FragmentPagerAdapter {

    private String[] titles = {"0", "1", "2"};


    public TabPageIndicatorAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        DummyFragment f = null;
        switch (position) {
            case 0:
                f = new DummyFragment("0xxx");
                f.color = Color.GREEN;
                break;
            case 1:
                f = new DummyFragment("1xxx");
                f.color = Color.BLUE;
                break;
            case 2:
                f = new DummyFragment("2xxx");
                f.color = Color.YELLOW;
                break;
            default:
                break;
        }
        return f;
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
