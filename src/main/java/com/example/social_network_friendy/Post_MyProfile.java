package com.example.social_network_friendy;

import java.util.ArrayList;
import java.util.List;

public class Post_MyProfile {
    private String username;
    private String content;
    private String time;
    private int imageResId;
    private int likeCount;
    private boolean liked;
    private int id; // Thêm thuộc tính id
    private List<String> comments; // Danh sách bình luận
    private int commentCount; // Số lượng bình luận

    public Post_MyProfile(int id, String username, String content, String time, int imageResId) {
        this.id = id; // Khởi tạo id
        this.username = username;
        this.content = content;
        this.time = time;
        this.imageResId = imageResId;
        this.likeCount = 0; // Bắt đầu với 0 lượt thích
        this.liked = false; // Mặc định là chưa thích
        this.comments = new ArrayList<>(); // Khởi tạo danh sách bình luận
        this.commentCount = 0; // Bắt đầu với 0 bình luận
    }

    // Phương thức getter cho id
    public int getId() {
        return id;
    }

    // Các phương thức getter và setter

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    // Phương thức để thêm bình luận
    public void addComment(String comment) {
        comments.add(comment);
        commentCount++; // Tăng số lượng bình luận
    }

    // Phương thức để lấy danh sách bình luận
    public List<String> getComments() {
        return comments;
    }

    // Phương thức để lấy số lượng bình luận
    public int getCommentCount() {
        return commentCount;
    }

    // Phương thức để thiết lập số lượng bình luận
    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
