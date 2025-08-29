package com.example.campusconnect;

public class LibraryBook {
    private String title;
    private String author;
    private String status;

    public LibraryBook(String androidDevelopment, String johnSmith) { }

    public LibraryBook(String title, String author, String status) {
        this.title = title;
        this.author = author;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getStatus() {
        return status;
    }
}
