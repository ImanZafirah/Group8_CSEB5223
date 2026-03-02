package com.mycompany.scm;

public class Course {
    
    private String courseName;
    private String courseCode;
    private int credHour;
    private String courseSum;
    private String teamsLink;

    public Course (String courseName, String courseCode, int credHour, String courseSum, String teamsLink)
    {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credHour = credHour;
        this.courseSum = courseSum;
        this.teamsLink = teamsLink;
    }

//Getter and Setters Section

    public String getCourseName()
    {
        return courseName;
    }

    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

    public String getCourseCode()
    {
        return courseCode;
    }

    public void setCourseCode(String courseCode)
    {
        this.courseCode = courseCode;
    }

    public int getCredHour()
    {
        return credHour;
    }

    public void setCredHour(int credHour)
    {
        this.credHour = credHour;
    }

    public String getCourseSum()
    {
        return courseSum;
    }

    public void setCourseSum(String courseSum)
    {
        this.courseSum = courseSum;
    }

    public String getTeamsLink()
    {
        return teamsLink;
    }

    public void setTeamsLink(String teamsLink)
    {
        this.teamsLink = teamsLink;
    }
}