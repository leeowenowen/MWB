package com.owo.app.common;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ListView;


public class OwoListView extends ListView {

    public OwoListView(Context context) {
        super(context);
        setDivider(new ColorDrawable(Color.GRAY));
        setCacheColorHint(0);
        setDividerHeight(2);
    }

}
