package com.example.campusconnect;

public class LibraryRequest {
    private String bookTitle;
    private String requester;
    private String status; // "Pending", "Approved", "Issued"

    public LibraryRequest() {}

    public LibraryRequest(String bookTitle, String requester, String status) {
        this.bookTitle = bookTitle; this.requester = requester; this.status = status;
    }

    public String getBookTitle() { return bookTitle; }
    public String getRequester() { return requester; }
    public String getStatus() { return status; }

    @Override public String toString() {
        return bookTitle + " • " + requester + " • " + status;
    }
}
