package com.example.campusconnect;

public class Notice {
    private String title;
    private String description;

    public Notice(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
