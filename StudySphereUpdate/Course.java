package com.mycompany.studysphere;

public class Course {
    
    private String courseName;
    private String courseCode;
    private int creditHour ;
    private String summary;
    private String teamsLink;
    private String courseType;

    public Course (String courseName, String courseCode, int creditHour , String summary, String teamsLink, String courseType)
    {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.creditHour  = creditHour;
        this.summary = summary;
        this.teamsLink = teamsLink;
        this.courseType = courseType;
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

    public int getCreditHour()
    {
        return creditHour ;
    }

    public void setCreditHour (int creditHour )
    {
        this.creditHour  = creditHour ;
    }

    public String getSummary()
    {
        return summary;
    }

    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    public String getTeamsLink()
    {
        return teamsLink;
    }

    public void setTeamsLink(String teamsLink)
    {
        this.teamsLink = teamsLink;
    }

    public String getCourseType()
    {
        return courseType;
    }

    public void setCourseType(String courseType)
    {
        this.courseType = courseType;
    }
}