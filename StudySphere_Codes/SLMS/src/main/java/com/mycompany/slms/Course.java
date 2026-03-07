package com.mycompany.slms;

public class Course {
    
    private String courseName;
    private String courseCode;
    private int creditHour ;
    private String summary;
    private String teamsLink;

    public Course (String courseName, String courseCode, int creditHour , String summary, String teamsLink)
    {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.creditHour  = creditHour;
        this.summary = summary;
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
}
