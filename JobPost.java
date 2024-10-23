package com.example.social_network_friendy;
import java.util.ArrayList;
public class JobPost {
    private String title;
    private String description;
    private String time;
    private String salary;
    private String contact;
    private ArrayList<String> comments;
    private boolean isLiked; // Thêm biến isLiked để lưu trạng thái "thích"

    public JobPost(String title, String description, String time, String salary, String contact) {
        this.title = title;
        this.description = description;
        this.time = time;
        this.salary = salary;
        this.contact = contact;
        this.isLiked = false; // Mặc định là chưa "thích"
        this.comments = new ArrayList<>();
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getTime() { return time; }
    public String getSalary() { return salary; }
    public String getContact() { return contact; }
    //Trạng thái like
    public boolean isLiked() { return isLiked; }
    public void setLiked(boolean liked) { isLiked = liked; }
    public ArrayList<String> getComments() { return comments; } // Phương thức lấy bình luận
//    public void addComment(String comment) { comments.add(comment); } // Phương thức thêm bình luận

}
