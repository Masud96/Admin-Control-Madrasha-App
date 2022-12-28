package com.masud.admincontrol_madrasha_app_practic;

public class NoticeData {
    String postTitle,postDate,postKey,postTime,postImage;

    public NoticeData() {
    }

    public NoticeData(String postTitle, String postDate, String postKey, String postTime, String postImage) {
        this.postTitle = postTitle;
        this.postDate = postDate;
        this.postKey = postKey;
        this.postTime = postTime;
        this.postImage = postImage;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getPostKey() {
        return postKey;
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }
}
