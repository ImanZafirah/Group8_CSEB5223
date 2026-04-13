package com.mycompany.studysphere;

import java.util.Scanner;

public class StudySphere {

    public static void main(String[] args) {
        Scanner mainScanner = new Scanner(System.in);
        
        CourseManager courseManager = new CourseManager();
        StudentManager studentManager = new StudentManager();
        
        int mainChoice = 0;

        while (mainChoice != 3) {
            System.out.println("\n==================================");
            System.out.println("   WELCOME TO STUDY SPHERE SLMS   ");
            System.out.println("==================================");
            System.out.println("1. Manage Course Profiles");
            System.out.println("2. Manage Student Profiles");
            System.out.println("3. Exit System");
            System.out.print("Please select a module: ");

            try {
                if (mainScanner.hasNextInt()) {
                    mainChoice = mainScanner.nextInt();
                    mainScanner.nextLine(); 

                    if (mainChoice == 1) {
                        courseManager.displayCourseMenu(mainScanner); 
                    } else if (mainChoice == 2) {
                        studentManager.displayStudentMenu(mainScanner); 
                    } else if (mainChoice == 3) {
                        System.out.println("Exiting StudySphere...");
                    } else {
                        System.out.println("Invalid selection.");
                    }
                } else {
                    System.out.println("Invalid input! Please enter a number.");
                    mainScanner.nextLine(); 
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                mainScanner.nextLine(); 
            }
        }
        mainScanner.close();
    }
}