package com.example.campusconnect.models;

public class Announcement {
    private String title;
    private String description;
    private String date;

    // Empty constructor for Firestore
    public Announcement() {}

    public Announcement(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
}
