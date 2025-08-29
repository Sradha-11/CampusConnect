package com.example.campusconnect;

public class LostFoundItem {
    private String title;
    private String description;
    private String date;
    private String location;
    private String status;
    private String contact;
    private String imageurl;

    // Empty constructor (required for Firestore)
    public LostFoundItem() {}

    public LostFoundItem(String title, String description, String date, String location,
                         String status, String contact, String imageurl) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.status = status;
        this.contact = contact;
        this.imageurl = imageurl;
    }

    // Getters
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public String getLocation() { return location; }
    public String getStatus() { return status; }
    public String getContact() { return contact; }
    public String getImageurl() { return imageurl; }
}
