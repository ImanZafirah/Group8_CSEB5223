package com.mycompany.scm;

public class CourseMethods {

    private String courseName;
    private String courseCode;
    private int creditHour;
    private String summary;
    private String teamsLink;

    // Constructor
    public CourseMethods(String courseName, String courseCode, int creditHour, String summary, String teamsLink) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.creditHour = creditHour;
        this.summary = summary;
        this.teamsLink = teamsLink;
    }

    // Getters
    public String getCourseName() { return courseName; }
    public String getCourseCode() { return courseCode; }
    public int getCreditHour() { return creditHour; }
    public String getSummary() { return summary; }
    public String getTeamsLink() { return teamsLink; }

    // Setters
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public void setCreditHour(int creditHour) { this.creditHour = creditHour; }
    public void setSummary(String summary) { this.summary = summary; }
    public void setTeamsLink(String teamsLink) { this.teamsLink = teamsLink; }
}