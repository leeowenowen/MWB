package com.owo.imageselect;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.util.ArrayList;


public class ImageSelector {

    public static final String EXTRA_RESULT = ImageSelecteActivity.EXTRA_RESULT;

    private boolean mShowCamera = true;
    private int mMaxCount = 9;
    private int mMode = ImageSelecteActivity.MODE_MULTI;
    private ArrayList<String> mOriginData;
    private static ImageSelector sSelector;
    private Context mContext;

    private ImageSelector(Context context) {
        mContext = context;
    }

    public static ImageSelector create(Context context) {
        if (sSelector == null) {
            sSelector = new ImageSelector(context);
        }
        return sSelector;
    }

    public ImageSelector showCamera(boolean show) {
        mShowCamera = show;
        return sSelector;
    }

    public ImageSelector count(int count) {
        mMaxCount = count;
        return sSelector;
    }

    public ImageSelector single() {
        mMode = ImageSelecteActivity.MODE_SINGLE;
        return sSelector;
    }

    public ImageSelector multi() {
        mMode = ImageSelecteActivity.MODE_MULTI;
        return sSelector;
    }

    public ImageSelector origin(ArrayList<String> images) {
        mOriginData = images;
        return sSelector;
    }

    public void start(Activity activity, int requestCode) {
        if (hasPermission()) {
            activity.startActivityForResult(createIntent(), requestCode);
        } else {
            Toast.makeText(mContext, R.string.error_no_permission, Toast.LENGTH_SHORT).show();
        }
    }

    public void start(Fragment fragment, int requestCode) {
        if (hasPermission()) {
            fragment.startActivityForResult(createIntent(), requestCode);
        } else {
            Toast.makeText(mContext, R.string.error_no_permission, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean hasPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            // Permission was added in API Level 16
            return ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    private Intent createIntent() {
        Intent intent = new Intent(mContext, ImageSelecteActivity.class);
        intent.putExtra(ImageSelecteActivity.EXTRA_SHOW_CAMERA, mShowCamera);
        intent.putExtra(ImageSelecteActivity.EXTRA_SELECT_COUNT, mMaxCount);
        if (mOriginData != null) {
            intent.putStringArrayListExtra(ImageSelecteActivity.EXTRA_DEFAULT_SELECTED_LIST, mOriginData);
        }
        intent.putExtra(ImageSelecteActivity.EXTRA_SELECT_MODE, mMode);
        return intent;
    }
}
