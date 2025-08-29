package com.example.campusconnect;

public class Teacher {
    private String subjectShort;
    private String subjectName;
    private String facultyName;
    private String id; // Firestore document ID

    public Teacher() {}

    public Teacher(String subjectShort, String subjectName, String facultyName) {
        this.subjectShort = subjectShort;
        this.subjectName = subjectName;
        this.facultyName = facultyName;
    }

    public String getSubjectShort() { return subjectShort; }
    public String getSubjectName() { return subjectName; }
    public String getFacultyName() { return facultyName; }
    public String getId() { return id; }

    public void setSubjectShort(String subjectShort) { this.subjectShort = subjectShort; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }
    public void setFacultyName(String facultyName) { this.facultyName = facultyName; }
    public void setId(String id) { this.id = id; }
}
