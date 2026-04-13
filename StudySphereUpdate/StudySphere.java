package com.mycompany.studysphere;

import java.util.Scanner;

public class StudySphere {

    public static void main(String[] args) {
        Scanner mainScanner = new Scanner(System.in);
        
        CourseManager courseManager = new CourseManager();
        StudentManager studentManager = new StudentManager();
        
        int mainChoice = 0;

        // Changed to 4 to include the Enrollment module
        while (mainChoice != 4) {
            System.out.println("\n==================================");
            System.out.println("   WELCOME TO STUDYSPHERE SLMS   ");
            System.out.println("==================================");
            System.out.println("1. Manage Course Profiles");
            System.out.println("2. Manage Student Profiles");
            System.out.println("3. Manage Enrollments");
            System.out.println("4. Exit System");
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
                        handleEnrollmentMenu(mainScanner, courseManager, studentManager);
                    } else if (mainChoice == 4) {
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

    // New helper method to keep the main menu clean
    private static void handleEnrollmentMenu(Scanner sc, CourseManager cm, StudentManager sm) {
    int sub = 0;
    // The loop inside the Enrollment menu
    do {
        if (StudentManager.count == 0 || CourseManager.count == 0) {
            System.out.println("\n[!] Error: System requires at least one Student and one Course.");
            return; // Goes back to main menu because action is impossible
        }

        System.out.println("\n--- ENROLLMENT MANAGEMENT ---");
        System.out.println("1. Enroll Student in Course");
        System.out.println("2. View Student's Courses");
        System.out.println("3. View Course's Students");
        System.out.println("4. Back to Main Menu");
        System.out.print("Select: ");

        try {
            sub = Integer.parseInt(sc.nextLine());
            switch (sub) {
                case 1 -> {
                    // Display existing data so you know what IDs to type
                    System.out.println("\nAvailable Students:"); sm.displayStudent();
                    System.out.println("Available Courses:"); cm.displayCourses();

                    System.out.print("\nEnter Student ID: "); String sId = sc.nextLine();
                    System.out.print("Enter Course Code: "); String cCode = sc.nextLine();

                    int sIdx = StudentManager.getStudentIndexById(sId);
                    int cIdx = CourseManager.getCourseIndexByCode(cCode);

                    if (sIdx != -1 && cIdx != -1) {
                        cm.enrollStudentInCourse(sIdx, cIdx);
                        sm.addStudent(cIdx, sIdx);
                        System.out.println(">> Relationship Successfully Initiated.");
                    } else {
                        if (sIdx == -1) System.out.println("Error: Student ID [" + sId + "] not found.");
                        if (cIdx == -1) System.out.println("Error: Course Code [" + cCode + "] not found.");
                    }
                }
                case 2 -> {
                    System.out.print("Enter Student ID: ");
                    cm.listCoursesByStudentId(sc.nextLine());
                }
                case 3 -> {
                    System.out.print("Enter Course Code: ");
                    String code = sc.nextLine();
                    int cIdx = CourseManager.getCourseIndexByCode(code);
                    if (cIdx != -1) sm.listStudents(cIdx); 
                    else System.out.println("Error: Course not found.");
                }
                case 4 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid selection.");
            }
        } catch (Exception e) { System.out.println("Invalid input."); }
        
    } while (sub != 4); // Loop continues until "4" is pressed
}
}
