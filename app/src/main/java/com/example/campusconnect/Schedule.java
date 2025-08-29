package com.example.campusconnect;

public class Schedule {
    private String time;
    private String subject;

    public Schedule(String time, String subject) {
        this.time = time;
        this.subject = subject;
    }

    public String getTime() {
        return time;
    }

    public String getSubject() {
        return subject;
    }
}
