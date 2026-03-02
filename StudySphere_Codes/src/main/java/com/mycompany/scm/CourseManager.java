/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.scm;

import javax.swing.JOptionPane; 

public class CourseManager {
    private static final int MAX_COURSES = 5; 
    private Course[] courseList = new Course[MAX_COURSES];
    private int courseCount = 0;

    public void addCourseWithPopup() {
        //Check for array out-of-bounds before showing popups
        if (courseCount >= MAX_COURSES) {
            JOptionPane.showMessageDialog(null, "Error: System storage is full!", "Capacity Alert", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Input fields
        String name = JOptionPane.showInputDialog("Enter Course Name:");
        String code = JOptionPane.showInputDialog("Enter Course Code:");
        
        String hoursStr = JOptionPane.showInputDialog("Enter Credit Hours:");
        int hours = Integer.parseInt(hoursStr); // Convert string input to integer
        
        String summary = JOptionPane.showInputDialog("Enter Course Summary:");
        String link = JOptionPane.showInputDialog("Enter MS Teams Link:");

        //Submit logic
        Course newCourse = new Course(name, code, hours, summary, link);
        
        //Store in the array
        courseList[courseCount] = newCourse;
        courseCount++;

        //Success Feedback
        JOptionPane.showMessageDialog(null, "Course " + code + " submitted successfully!");
    }
}
