package com.owo.mwb.service.display.entity;

import com.owo.mwb.common.entity.Comment;
import com.owo.mwb.common.entity.Image;

import java.util.List;

/**
 * Created by wangli on 16-6-5.
 */
public class DisplayItem {
    private String id;
    private String title;
    private List<Image> imgs;
    private List<Comment> comments;

    private int like;
    private boolean readed;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Image> getImgs() {
        return imgs;
    }

    public void setImgs(List<Image> imgs) {
        this.imgs = imgs;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public boolean isReaded() {
        return readed;
    }

    public void setReaded(boolean readed) {
        this.readed = readed;
    }
}
