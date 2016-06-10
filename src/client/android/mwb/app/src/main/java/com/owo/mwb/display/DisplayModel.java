package com.owo.mwb.display;

import com.owo.mwb.common.entity.Comment;
import com.owo.android.common.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by wangli on 16-6-5.
 */
public class DisplayModel extends ArrayList<DisplayItem> {
    private static DisplayModel instance = new DisplayModel();
    private Observable mObservable = new Observable();

    public static DisplayModel instance() {
        return instance;
    }

    public Observable asObservable() {
        return mObservable;
    }

    private DisplayModel() {
        for (int i = 0; i < 10; ++i) {
            DisplayItem item = new DisplayItem();
            item.setId("");
            item.setTitle("title    " + i);
            item.setContent("content   " + i);
            List<Image> imgs = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                Image img = new Image("http://e.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=59f0fd994b540923be646b2cf331ba6c/b21bb051f8198618e23bec3e48ed2e738bd4e608.jpg");
                imgs.add(img);
            }
            item.setImgs(imgs);
            List<Comment> comments = new ArrayList<>();
            for (int k = 0; k < 10; k++) {
                Comment comment = new Comment();
                comment.setId("idxxx" + k);
                comment.setContent("comment    " + k);
                comment.setCreateTime(System.currentTimeMillis());
                comments.add(comment);
            }
            item.setComments(comments);
            item.setLike(100);
            item.setReaded(false);


            add(item);
        }

    }
}
