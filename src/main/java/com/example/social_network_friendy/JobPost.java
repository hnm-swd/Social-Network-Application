package com.example.social_network_friendy;

import java.util.ArrayList;

public class JobPost {
    private String tvContent; // Nội dung bài viết
    private String imgPost; // Đường dẫn hoặc URL hình ảnh bài viết
    private String tvTime; // Thời gian đăng bài
    private int tvLikeCount; // Số lượt thích
    private int tvCommentCount; // Số lượt bình luận
    private ArrayList<String> comments; // Danh sách bình luận
    private boolean isLiked; // Trạng thái đã thích bài viết hay chưa

    // Constructor
    public JobPost(String tvContent, String tvTime, String imgPost) {
        this.tvContent = tvContent;
        this.tvTime = tvTime;
        this.imgPost = imgPost;
        this.tvLikeCount = 0; // Mặc định 0 lượt thích
        this.tvCommentCount = 0; // Mặc định 0 bình luận
        this.isLiked = false; // Mặc định chưa thích
        this.comments = new ArrayList<>();
    }

    // Getter và Setter
    public String getTvContent() {
        return tvContent;
    }

    public void setTvContent(String tvContent) {
        this.tvContent = tvContent;
    }

    public String getImgPost() {
        return imgPost;
    }

    public void setImgPost(String imgPost) {
        this.imgPost = imgPost;
    }

    public String getTvTime() {
        return tvTime;
    }

    public void setTvTime(String tvTime) {
        this.tvTime = tvTime;
    }

    public int getTvLikeCount() {
        return tvLikeCount;
    }

    public void setTvLikeCount(int tvLikeCount) {
        this.tvLikeCount = tvLikeCount;
    }

    public int getTvCommentCount() {
        return tvCommentCount;
    }

    public void setTvCommentCount(int tvCommentCount) {
        this.tvCommentCount = tvCommentCount;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void addComment(String comment) {
        this.comments.add(comment);
        this.tvCommentCount = this.comments.size(); // Cập nhật số lượng bình luận
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
        if (liked) {
            tvLikeCount++;
        } else {
            tvLikeCount--;
        }
    }
}
