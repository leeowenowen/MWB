package com.owo.mwb.display;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.owo.android.util.ContextManager;
import com.owo.android.util.ui.DimensionUtil;
import com.owo.android.util.ui.ViewUtil;
import com.owo.base.async.loadable.Loadable;
import com.owo.components.image_select.ImageSelectActivity;
import com.owo.mwb.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangli on 16-6-9.
 */
public class WriteDisplayPage extends ScrollView {
    @BindView(R.id.id_text)
    EditText idText;
    @BindView(R.id.id_imgs)
    GridView idImgs;
    @BindView(R.id.id_sep)
    View idSep;
    @BindView(R.id.id_add_img)
    ImageView idAddImg;

    private ImgAdapter mImgAdapter = new ImgAdapter();

    public WriteDisplayPage(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.write_display, this, true);
        ButterKnife.bind(this);
        idImgs.setNumColumns(2);
        idImgs.setAdapter(mImgAdapter);

        idAddImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContextManager.activity(), ImageSelectActivity.class);
                intent.putExtra("is_single_select", false);
                intent.putExtra("is_wh_equal", true);
                intent.putExtra("need_crop", false);
                ContextManager.activity().startActivityForResult(intent, 100);
            }
        });
    }

    public void notifyPhotoData(ArrayList<String> paths) {
        if (paths == null || (paths.size() == 0)) {
            return;
        }
        mImgAdapter.add(paths);
    }

    private class ImgAdapter extends BaseAdapter {
        private ArrayList<Bitmap> mBmpsArrayList = new ArrayList<Bitmap>();
        private ArrayList<String> mPathsArrayList = new ArrayList<String>();
        private ArrayList<Loadable.State> mBmpStateList = new ArrayList<>();

        public void add(ArrayList<String> paths) {
            for (String path : paths) {
                mPathsArrayList.add(path);
                mBmpsArrayList.add(null);
                mBmpStateList.add(Loadable.State.NONE);
            }
            notifyDataSetChanged();
        }

        public List<Bitmap> getImages() {
            return mBmpsArrayList;
        }

        @Override
        public int getCount() {
            return mPathsArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        private boolean mHasBmp = false;

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ImageView view = null;
            if (convertView == null) {
                view = new ImageView(getContext());

            } else {
                view = (ImageView) convertView;
            }
            view.setTag(position);
            final ImageView itemViewMark = view;

            Bitmap bmp = mBmpsArrayList.get(position);
            Loadable.State state = mBmpStateList.get(position);
            if (state == Loadable.State.NONE) {
                mBmpStateList.set(position, Loadable.State.LOADING);
                ImageLoader.getInstance().loadImage("file://" + mPathsArrayList.get(position), new ImageSize(DimensionUtil.w(300), DimensionUtil.w(300)), new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        super.onLoadingComplete(imageUri, view, loadedImage);
                        if ((int) (itemViewMark.getTag()) == position) {
                            itemViewMark.setImageBitmap(loadedImage);
                            mBmpsArrayList.set(position, loadedImage);
                            mBmpStateList.set(position, Loadable.State.LOADED_SUCCESS);
                            if (!mHasBmp) {
                                ViewUtil.setGridViewHeightBasedOnChildren(idImgs, DimensionUtil.h(300));
                                mHasBmp = true;
                            }

                        }
                    }
                });
            } else {
                view.setImageBitmap(bmp);
            }
            view.setLayoutParams(new AbsListView.LayoutParams(DimensionUtil.w(300), DimensionUtil
                    .h(300)));
            return view;
        }

    }
}
