package com.example.inflearn_anstgram_1.data;

/**
 * Created by g82 on 4/28/16.
 */
public class DataPostItem {
    int postIdx;

    String postImgUrl;
    String posttext;
    String username;

    int likes;
    boolean isUserLiked;

    public DataPostItem(int postIdx, String postImgUrl, String posttext, String username, int likes, boolean isUserLiked) {
        this.postIdx = postIdx;
        this.postImgUrl = postImgUrl;
        this.posttext = posttext;
        this.username = username;
        this.likes = likes;
        this.isUserLiked = isUserLiked;
    }

    public int getPostIdx() {
        return postIdx;
    }

    public String getPostImgUrl() {
        return postImgUrl;
    }

    public String getPostText() {
        return posttext;
    }

    public String getUserName() {
        return username;
    }

    public int getPost_likes_count() {
        return likes;
    }

    public boolean isUserLiked() {
        return isUserLiked;
    }
}
