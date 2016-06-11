package com.owo.mwb;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.owo.app.util.ActivityUtil;
import com.owo.mwb.display.DisplayListView;
import com.owo.mwb.map.MapFrame;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.content_main)
    FrameLayout contentMain;
    @BindView(R.id.id_display)
    ImageView idDisplay;
    @BindView(R.id.id_map)
    ImageView idMap;
    @BindView(R.id.id_me)
    ImageView idMe;
    @BindView(R.id.id_setting)
    ImageView idSetting;
    @BindView(R.id.id_home)
    ImageView idHome;
    @BindView(R.id.bottom_container)
    LinearLayout bottomContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtil.setFullScreen(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        initUI(savedInstanceState);
    }

    private DisplayListView displayListView;
    private MapFrame mapFrame;

    private void initUI(Bundle savedInstanceState) {
        displayListView = new DisplayListView(this);
        contentMain.addView(displayListView);
        mapFrame = new MapFrame(this);
        mapFrame.onCreate(savedInstanceState);
        contentMain.addView(mapFrame);
        {
            TextView t = new TextView(this);
            t.setText("home");
            contentMain.addView(t);
        }
        {
            TextView t = new TextView(this);
            t.setText("me");
            contentMain.addView(t);
        }
        {
            TextView t = new TextView(this);
            t.setText("setting");
            contentMain.addView(t);
        }
        switchToPage(0);
    }

    @OnClick({R.id.id_display, R.id.id_map, R.id.id_home, R.id.id_me, R.id.id_setting})
    public void onClick(View view) {
        int index = bottomContainer.indexOfChild(view);
        switchToPage(index);
    }

    private void switchToPage(int index) {
        for (int i = 0; i < contentMain.getChildCount(); i++) {
            View v = contentMain.getChildAt(i);
            int visibility = (i == index) ? View.VISIBLE : View.INVISIBLE;
            v.setVisibility(visibility);
        }
    }
}
