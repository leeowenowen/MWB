package com.owo.mwb.display;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.owo.android.util.log.Logger;
import com.owo.mwb.R;
import com.owo.mwb.common.entity.Image;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangli on 16-6-5.
 */
public class DisplayItemView extends LinearLayout {
    private static final String TAG = DisplayItemView.class.getSimpleName();
    @BindView(R.id.id_title)
    TextView idTitle;
    @BindView(R.id.id_content)
    TextView idContent;
    @BindView(R.id.id_images)
    LinearLayout idImages;
    @BindView(R.id.id_time)
    TextView idTime;
    @BindView(R.id.id_photo)
    TextView idPhoto;

    public DisplayItemView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.display_list_item_layout, this, true);
        ButterKnife.bind(this);
    }

    public void update(DisplayItem item) {
        idTitle.setText(item.getTitle());
        idContent.setText(item.getContent());
        //images
        //idImages.removeAllViews();
        List<Image> imgs = item.getImgs();
        while (idImages.getChildCount() < imgs.size()) {
            final ImageView imgView = new ImageView(getContext());
            idImages.addView(imgView);
        }
        for (int i = 0; i < imgs.size(); ++i) {
            Image img = imgs.get(i);
            final Image image = img;
            final Object tag = new Object();
            final ImageView imgView = (ImageView) idImages.getChildAt(i);
            imgView.setTag(tag);
            if (img.isLoaded()) {
                imgView.setImageBitmap(img.getBmp());
            } else {
                imgView.setImageBitmap(null);
                ImageLoader.getInstance().loadImage(img.getUrl(), new ImageSize(300, 300), new SimpleImageLoadingListener() {
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        Logger.e(TAG, "Load image failed " + failReason.getCause().getMessage());

                    }

                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        image.setBmp(loadedImage);
                        if (imgView.getTag() == tag) {
                            imgView.setImageBitmap(loadedImage);
                        }
                    }
                });
            }
        }
        //comments should display in details view
    }
}
