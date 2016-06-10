package com.owo.components.image_crop;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.edmodo.cropper.CropImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.owo.android.util.ContextManager;
import com.owo.android.util.iva.BitmapHelper;
import com.owo.app.common.LoadingCtrl;
import com.owo.base.async.TaskRunner;

import java.util.ArrayList;
import java.util.UUID;

public class ImageCropPage extends FrameLayout {
    private Button mBtnCrop;
    private CropImageView mCropImageView;

    public ImageCropPage(Context context, String imagePath, boolean isWHEqual, int width, int height) {
        super(context);

        mBtnCrop = new Button(context);
        mCropImageView = new CropImageView(context);
        if (isWHEqual) {
            mCropImageView.setAspectRatio(1, 1);
        }
        mBtnCrop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                final Bitmap bmp = mCropImageView.getCroppedImage();
                LoadingCtrl.start("正在生成头像...");
                TaskRunner.run(new Runnable() {
                    @Override
                    public void run() {
                        final String path = ContextManager.context().getFilesDir().getPath() + UUID.randomUUID();
                        BitmapHelper.saveBitmap(bmp, path);
                        post(new Runnable() {
                            @Override
                            public void run() {
                                ArrayList<String> paths = new ArrayList<>();
                                paths.add(path);
                                Intent data = new Intent();
                                data.putStringArrayListExtra("image_path", paths);
                                ContextManager.activity().setResult(100, data);
                                ContextManager.activity().finish();
                            }
                        });
                    }
                });

            }
        });
        addView(mCropImageView);
        ImageLoader.getInstance().loadImage(imagePath, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                mCropImageView.setImageBitmap(loadedImage);
            }
        });
        mBtnCrop.setText("确定");
        //     setTitle("裁剪图片");
    }
}
