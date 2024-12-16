package com.example.social_network_friendy;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "posts")
public class Post {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String content;
    private String imageUri; // Store imageUri as a String

    public Post(String content, String imageUri) {
        this.content = content;
        this.imageUri = imageUri;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
