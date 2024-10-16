package com.example.social_network_friendy;
public class JobPost {
    private String title;
    private String description;
    private String time;
    private String salary;
    private String contact;

    public JobPost(String title, String description, String time, String salary, String contact) {
        this.title = title;
        this.description = description;
        this.time = time;
        this.salary = salary;
        this.contact = contact;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getTime() { return time; }
    public String getSalary() { return salary; }
    public String getContact() { return contact; }

}
