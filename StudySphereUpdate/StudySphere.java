package com.mycompany.studysphere;

import java.util.Scanner;

public class StudySphere {

    public static void main(String[] args) {
        Scanner mainScanner = new Scanner(System.in);
        
        // Use these shared instances for the menus
        CourseManager courseManager = new CourseManager();
        StudentManager studentManager = new StudentManager();
        
        int mainChoice = 0;

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

    // ===================== ENROLLMENT =====================
    private static void handleEnrollmentMenu(Scanner sc, CourseManager cm, StudentManager sm) {
        int sub = 0;
        do {
            if (StudentManager.count == 0 || CourseManager.count == 0) {
                System.out.println("\n[!] Error: System requires at least one Student and one Course.");
                return;
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
                        sm.displayStudent();
                        cm.displayCourses();

                        int sIdx = -1;
                        int cIdx = -1;

                        while (sIdx == -1) {
                            System.out.print("\nEnter Student ID: "); 
                            String sId = sc.nextLine();
                            sIdx = StudentManager.getStudentIndexById(sId);
                            if (sIdx == -1) {
                                System.out.println("Error: Student ID not found.");
                                StudentManager.suggestStudent(sId);
                            }
                        }

                        while (cIdx == -1) {
                            System.out.print("Enter Course Code: "); 
                            String cCode = sc.nextLine();
                            cIdx = CourseManager.getCourseIndexByCode(cCode);
                            if (cIdx == -1) {
                                System.out.println("Error: Course Code not found.");
                                CourseManager.suggestCourse(cCode);
                            }
                        }

                        // Capture the result of the enrollment
                        boolean newlyEnrolled = cm.enrollStudentInCourse(sIdx, cIdx);
                        sm.addStudent(cIdx, sIdx); // Keep them in sync

                        if (newlyEnrolled) {
                            System.out.println(">> Student [" + StudentManager.student[sIdx].getStudentId() + 
                                               "] Successfully Enrolled in [" + CourseManager.courses[cIdx].getCourseCode() + "].");
                        } else {
                            System.out.println("\n[!] Notice: This student is already enrolled in this course.");
                        }
                    }
                    case 2 -> {
                        int sIdx = -1;
                        String id = "";
                    
                        // RETRY LOOP FOR VIEWING BY STUDENT
                        while (sIdx == -1) {
                            System.out.print("Enter Student ID: ");
                            id = sc.nextLine();
                            sIdx = StudentManager.getStudentIndexById(id);
                        
                            if (sIdx == -1) {
                                System.out.println("Error: Student ID not found.");
                                StudentManager.suggestStudent(id);
                                System.out.println("Please key in the correct Student ID.");
                            }
                        }
                        cm.listCoursesByStudentId(id);
                    }
                    case 3 -> {
                        int cIdx = -1;
                        String code = "";

                        // RETRY LOOP FOR VIEWING BY COURSE
                        while (cIdx == -1) {
                            System.out.print("Enter Course Code: ");
                            code = sc.nextLine();
                            cIdx = CourseManager.getCourseIndexByCode(code);

                            if (cIdx == -1) {
                                System.out.println("Error: Course not found.");
                                CourseManager.suggestCourse(code);
                                System.out.println("Please key in the correct Course Code.");
                            }
                        }
                        sm.listStudents(cIdx); 
                    }
                    case 4 -> System.out.println("Returning to main menu...");
                    default -> System.out.println("Invalid selection.");
                }
            } catch (Exception e) { 
                System.out.println("Invalid input."); 
            }
        
        } while (sub != 4);
    }
}