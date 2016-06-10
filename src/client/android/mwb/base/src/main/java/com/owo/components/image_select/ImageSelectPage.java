package com.owo.components.image_select;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.owo.android.util.ContextManager;
import com.owo.android.util.ui.DimensionUtil;
import com.owo.android.util.ui.LayoutParamUtil;
import com.owo.android.util.ui.LayoutUtil;
import com.owo.components.image_crop.ImageCropActivity;

import java.util.BitSet;

/**
 * Created by wangli on 15-6-2.
 */
public class ImageSelectPage extends FrameLayout {
    private GridView mImageGridView;

    private boolean mIsWHEqual;
    private boolean mIsSingleSelectMode;
    private boolean mNeedCrop;

    public ImageSelectPage(Context context, boolean isWHEqual, boolean isSingleSelectMode, boolean needCrop) {
        super(context);
        mIsWHEqual = isWHEqual;
        mIsSingleSelectMode = isSingleSelectMode;
        mNeedCrop = needCrop;

        mImageGridView = new GridView(context);
        mImageGridView.setNumColumns(3);
        mImageGridView.setAdapter(new ItemViewAdapter(queryAllImage()));
        mImageGridView.setHorizontalSpacing(0);
        mImageGridView.setVerticalSpacing(0);
        addView(mImageGridView);
//        mBtnSelect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ArrayList<String> paths = new ArrayList<>();
//                for (int i = 0; i < mSelectedFlags.size(); ++i) {
//                    if (mSelectedFlags.get(i)) {
//                        mCursor.moveToPosition(i);
//                        String pathString = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));
//                        paths.add(pathString);
//                    }
//                }
//                Intent data = new Intent();
//                data.putStringArrayListExtra("image_path", paths);
//                ContextManager.activity().setResult(100, data);
//                ContextManager.activity().finish();
//            }
//        });
//        mBtnSelect.setText("确定");
    }

    private class ItemView extends FrameLayout {
        ImageView mImageView;
        CheckBox mSelectView;

        public ItemView(Context context) {
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
            ItemView itemView = null;
            if (view == null) {
                itemView = new ItemView(getContext());
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(DimensionUtil.w(240), DimensionUtil.h(320));
                itemView.setLayoutParams(lp);
            } else {
                itemView = (ItemView) view;
            }
            if (mCursor.moveToPosition(i)) {
                String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));
                path = "file://" + path;
                final String selectPath = path;
                final ImageLoader imageLoader = ImageLoader.getInstance();
                itemView.mImageView.setImageBitmap(null);
                itemView.mSelectView.setChecked(mSelectedFlags.get(i));
                itemView.setTag(i);
                final ItemView itemViewMark = itemView;
                imageLoader.loadImage(path, new ImageSize(DimensionUtil.w(mIsWHEqual ? 320 : 240), DimensionUtil.w(320)), new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        if ((int) (itemViewMark.getTag()) == i) {
                            itemViewMark.mImageView.setImageBitmap(loadedImage);
                        }
                    }
                });
                if (mNeedCrop) {
                    itemView.mImageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(ContextManager.activity(), ImageCropActivity.class);
                            intent.putExtra("image_path", selectPath);
                            intent.putExtra("is_wh_equal", mIsWHEqual);
                            ContextManager.activity().startActivityForResult(intent, 1000);
                        }
                    });
                }
                itemView.mSelectView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        mSelectedFlags.set(i);
                    }
                });
            }
            return itemView;
        }
    }
}
