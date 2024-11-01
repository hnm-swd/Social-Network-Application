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
    private int id;
    private List<String> comments;
    private int commentCount;

    public Post_MyProfile(int id, String username, String content, String time, int imageResId) {
        this.id = id;
        this.username = username;
        this.content = content;
        this.time = time;
        this.imageResId = imageResId;
        this.likeCount = 0;
        this.liked = false;
        this.comments = new ArrayList<>();
        this.commentCount = 0;
    }

    public int getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }


    public String getContent() {
        return content;
    }


    public String getTime() {
        return time;
    }


    public int getImageResId() {
        return imageResId;
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

    public void addComment(String comment) {
        comments.add(comment);
        commentCount++;
    }

    public List<String> getComments() {
        return comments;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
