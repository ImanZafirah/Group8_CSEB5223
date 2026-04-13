package com.mycompany.studysphere;


public class Student 
{
    
    private String firstName;
    private String lastName;
    private String studentId;
    private String studentEmail;
    private String studentPhone;
    
    
    public Student (String firstName, String lastName, String studentId, String studentEmail, String studentPhone)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId  = studentId;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
    }
    
    //Getters and Setters Section
    
    public String getFirstName() //firstName getter
    {
        return firstName;
    }

    public void setFirstName(String firstName) //firstName setter
    {
        this.firstName = firstName;
    }
    
    public String getLastName() //lastName getter
    {
        return lastName;
    }

    public void setLastName(String lastName) //lastName setter
    {
        this.lastName = lastName;
    }
    
    public String getStudentId() //studentId getter
    {
        return studentId;
    }

    public void setStudentId(String studentId) //studentId setter
    {
        this.studentId = studentId;
    }
    
    public String getStudentEmail() //studentEmail getter
    {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) //studentEmail setter
    {
        this.studentEmail = studentEmail;
    }
    
    public String getStudentPhone() //studentPhone getter
    {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) //studentPhone setter
    {
        this.studentPhone = studentPhone;
    }
}
