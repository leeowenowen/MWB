package com.owo.components.image_select;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.owo.android.util.ContextManager;
import com.owo.android.util.ui.DimensionUtil;
import com.owo.android.util.ui.LayoutParamUtil;
import com.owo.android.util.ui.LayoutUtil;
import com.owo.base.R;
import com.owo.components.image_crop.ImageCropActivity;

import java.util.ArrayList;
import java.util.BitSet;

import butterknife.ButterKnife;

/**
 * Created by wangli on 15-6-2.
 */
public class ImageSelectPage extends LinearLayout {
    GridView gridviewImgs;
    ListView listviewFolders;

    private boolean mIsWHEqual;
    private boolean mIsSingleSelectMode;
    private boolean mNeedCrop;

    public ImageSelectPage(Context context, boolean isWHEqual, boolean isSingleSelectMode, boolean needCrop) {
        super(context);
        mIsWHEqual = isWHEqual;
        mIsSingleSelectMode = isSingleSelectMode;
        mNeedCrop = needCrop;
        LayoutInflater.from(context).inflate(R.layout.image_select_page, this, true);
        ButterKnife.bind(this);
        gridviewImgs = (GridView) findViewById(R.id.gridview_imgs);
        listviewFolders = (ListView) findViewById(R.id.listview_folders);
        gridviewImgs.setAdapter(new ItemViewAdapter(queryAllImage()));
    }

    public ArrayList<String> getSelectedImages() {
        ArrayList<String> paths = new ArrayList<>();
        for (int i = 0; i < mSelectedFlags.size(); ++i) {
            if (mSelectedFlags.get(i)) {
                mCursor.moveToPosition(i);
                String pathString = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));
                paths.add(pathString);
            }
        }
        return paths;
    }

    private class SelectImageItemView extends FrameLayout {
        ImageView mImageView;
        CheckBox mSelectView;

        public SelectImageItemView(Context context) {
            super(context);

            mImageView = new ImageView(context);
            mSelectView = new CheckBox(context);
            addView(mImageView, LayoutParamUtil.FMM);
            addView(mSelectView, LayoutParamUtil.FWWTR);
            LayoutUtil.setMargin(mSelectView, 0, 20, 20, 0);
            if (mIsSingleSelectMode) {
                mSelectView.setVisibility(INVISIBLE);
            }

        }
    }

    private Cursor mCursor;

    private Cursor queryAllImage() {
        String[] imageColumns = new String[]{MediaStore.Images.Media._ID,//
                MediaStore.Images.Media.DATA, //
                MediaStore.Images.Media.SIZE, //
                MediaStore.Images.Media.TITLE,//
                MediaStore.Images.Media.WIDTH,//
                MediaStore.Images.Media.HEIGHT,//
                MediaStore.Images.Media.MIME_TYPE};
        mCursor = ContextManager.contentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, imageColumns, null, null, null);
        return mCursor;
    }

    private BitSet mSelectedFlags = new BitSet();


    private class ItemViewAdapter extends BaseAdapter {
        private Cursor mCursor;

        public ItemViewAdapter(Cursor cursor) {
            mCursor = cursor;
        }

        @Override
        public int getCount() {
            return mCursor == null ? 0 : mCursor.getCount();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            SelectImageItemView selectImageItemView = null;
            if (view == null) {
                selectImageItemView = new SelectImageItemView(getContext());
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(DimensionUtil.w(240), DimensionUtil.h(320));
                selectImageItemView.setLayoutParams(lp);
            } else {
                selectImageItemView = (SelectImageItemView) view;
            }
            if (mCursor.moveToPosition(i)) {
                String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));
                path = "file://" + path;
                final String selectPath = path;
                final ImageLoader imageLoader = ImageLoader.getInstance();
                selectImageItemView.mImageView.setImageBitmap(null);
                selectImageItemView.mSelectView.setChecked(mSelectedFlags.get(i));
                selectImageItemView.setTag(i);
                final SelectImageItemView selectImageItemViewMark = selectImageItemView;
                imageLoader.loadImage(path, new ImageSize(DimensionUtil.w(mIsWHEqual ? 320 : 240), DimensionUtil.w(320)), new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        if ((int) (selectImageItemViewMark.getTag()) == i) {
                            selectImageItemViewMark.mImageView.setImageBitmap(loadedImage);
                        }
                    }
                });
                if (mNeedCrop) {
                    selectImageItemView.mImageView.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(ContextManager.activity(), ImageCropActivity.class);
                            intent.putExtra("image_path", selectPath);
                            intent.putExtra("is_wh_equal", mIsWHEqual);
                            ContextManager.activity().startActivityForResult(intent, 1000);
                        }
                    });
                }
                selectImageItemView.mSelectView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        mSelectedFlags.set(i);
                    }
                });
            }
            return selectImageItemView;
        }
    }
}
