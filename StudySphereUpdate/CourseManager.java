package com.mycompany.studysphere;

import java.util.Scanner;

public class CourseManager {

    static Course[] courses = new Course[100];
    static int count = 0;

    int[][] enrollment = new int[100][100];

    String[] cachedCourseSearch = new String[50];
    int courseCacheIndex = 0;

    // ================= CACHE =================

    void cacheCourseSearch(String courseCode) {
        cachedCourseSearch[courseCacheIndex] = courseCode;
        courseCacheIndex++;

        if (courseCacheIndex >= cachedCourseSearch.length) {
            courseCacheIndex = 0;
        }
    }

    public void suggestCourse(String input) {
        System.out.println("Suggestions:");

        for (String c : cachedCourseSearch) {
            if (c != null && c.startsWith(input)) {
                System.out.println(" - " + c);
            }
        }
    }

    // ================= MAIN =================

    public void displayCourseMenu(Scanner scanner) {
    int choice = 0;

    do {
        System.out.println("\n==== COURSE PROFILE SYSTEM ====");
        System.out.println("1. Add Course");
        System.out.println("2. Search Course");
        System.out.println("3. Edit Course");
        System.out.println("4. Delete Course");
        System.out.println("5. View All Course");
        System.out.println("6. Exit");
        System.out.print("Choose option: ");

        // Check if the user entered a number
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1 -> addCourse(scanner);
                case 2 -> searchCourse(scanner);
                case 3 -> editCourse(scanner);
                case 4 -> deleteCourse(scanner);
                case 5 -> displayCourses();
                case 6 -> System.out.println("Exiting Course Module...");
                default -> System.out.println("Invalid choice. Please enter 1-6.");
            }
        } else {
            // This runs if the user enters an alphabet or symbol
            System.out.println("Invalid input! Please enter a number (1-6).");
            scanner.nextLine(); // CRITICAL: This clears the alphabet from the scanner
            choice = 0; // Set choice to 0 so the loop continues
        }

    } while (choice != 6);
}

    // ================= ADD =================

    public static void addCourse(Scanner scanner) {

        if (count >= courses.length) {
            System.out.println("Course list full!");
            return;
        }

        System.out.print("Course Name: ");
        String name = scanner.nextLine();

        System.out.print("Course Code: ");
        String code = scanner.nextLine();

        for (int i = 0; i < count; i++) {
            if (courses[i] != null &&
                courses[i].getCourseCode().equalsIgnoreCase(code)) {
                System.out.println("Course already exists!");
                return;
            }
        }

        System.out.print("Credit Hour: ");
        int credit = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Summary: ");
        String summary = scanner.nextLine();

        System.out.print("MS Teams Link: ");
        String link = scanner.nextLine();

        System.out.print("Course Type: ");
        String type = scanner.nextLine();

        courses[count] = new Course(name, code, credit, summary, link, type);
        count++;

        System.out.println("Course added successfully!");
    }

    // ================= SEARCH =================

    public static void searchCourse(Scanner scanner) {

        System.out.print("Enter Course Code: ");
        String code = scanner.nextLine();

        CourseManager manager = new CourseManager();
        manager.suggestCourse(code);

        boolean found = false;

        for (int i = 0; i < count; i++) {

            if (courses[i] != null &&
                courses[i].getCourseCode().equalsIgnoreCase(code)) {

                manager.cacheCourseSearch(code);

                System.out.println("\n===== COURSE FOUND =====");
                System.out.println("Name   : " + courses[i].getCourseName());
                System.out.println("Code   : " + courses[i].getCourseCode());
                System.out.println("Credit : " + courses[i].getCreditHour());
                System.out.println("Summary: " + courses[i].getSummary());
                System.out.println("Link   : " + courses[i].getTeamsLink());
                System.out.println("Type   : " + courses[i].getCourseType());

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Course not found.");
            displayCourses();
        }
    }

    // ================= EDIT =================

    public static void editCourse(Scanner scanner) {

        System.out.print("Enter Course Code to edit: ");
        String searchCode = scanner.nextLine();

        CourseManager manager = new CourseManager();
        manager.suggestCourse(searchCode);

        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (courses[i] != null && courses[i].getCourseCode().equalsIgnoreCase(searchCode)) {
            
                //Cache logic
                manager.cacheCourseSearch(searchCode);

                System.out.println("\n===== COURSE FOUND =====");
                System.out.println("Course Name    : " + courses[i].getCourseName());
                System.out.println("Credit Hour    : " + courses[i].getCreditHour());
                System.out.println("Summary        : " + courses[i].getSummary());
                System.out.println("MS Teams Link  : " + courses[i].getTeamsLink());
                System.out.println("Type           : " + courses[i].getCourseType());

                System.out.println("\nEnter new values (leave blank to keep current)");

                System.out.print("New Course Name: ");
                String name = scanner.nextLine();
                if (!name.isEmpty()) {
                    courses[i].setCourseName(name);
                }

                System.out.print("New Credit Hour: ");
                String creditInput = scanner.nextLine();
                if (!creditInput.isEmpty()) {
                    try {
                        int credit = Integer.parseInt(creditInput);
                        courses[i].setCreditHour(credit);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid credit hour, keeping previous value.");
                    }
                }

                System.out.print("New Summary: ");
                String summary = scanner.nextLine();
                if (!summary.isEmpty()) {
                    courses[i].setSummary(summary);
                }

                System.out.print("New MS Teams Link: ");
                String link = scanner.nextLine();
                if (!link.isEmpty()) {
                    courses[i].setTeamsLink(link);
                }
                
                System.out.print("New Course Type: ");
                String type = scanner.nextLine();
                if (!type.isEmpty()) {
                courses[i].setCourseType(type);
                }

                System.out.println("\n===== COURSE UPDATED =====");
                System.out.println("Course Name    : " + courses[i].getCourseName());
                System.out.println("Course Code    : " + courses[i].getCourseCode());
                System.out.println("Credit Hour    : " + courses[i].getCreditHour());
                System.out.println("Summary        : " + courses[i].getSummary());
                System.out.println("MS Teams Link  : " + courses[i].getTeamsLink());
                System.out.println("Type           : " + courses[i].getCourseType());

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Course not found.");
        }
        displayCourses();
    }

    // ================= DELETE =================

    public static void deleteCourse(Scanner scanner) {

    System.out.print("Enter Course Code to delete: ");
    String code = scanner.nextLine();

    boolean found = false;

    for (int i = 0; i < count; i++) {
        // null check added for safety
        if (courses[i] != null && courses[i].getCourseCode().equalsIgnoreCase(code)) {

            System.out.println("\nCourse Found:");
            System.out.println("Course Name    : " + courses[i].getCourseName());
            System.out.println("Course Code    : " + courses[i].getCourseCode());
            System.out.println("Credit Hour    : " + courses[i].getCreditHour());
            System.out.println("Summary        : " + courses[i].getSummary());
            System.out.println("MS Teams Link  : " + courses[i].getTeamsLink());
            System.out.println("Type           : " + courses[i].getCourseType());

            System.out.print("\nConfirm deletion? (Y/N): ");
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("Y")) {
                // Array shifting logic to remove the item
                for (int j = i; j < count - 1; j++) {
                    courses[j] = courses[j + 1];
                }

                courses[count - 1] = null;
                count--;

                System.out.println("Course deleted successfully!");
            }

            found = true;
            break;
        }
    }

    if (!found) {
        System.out.println("Course not found.");
    }
    displayCourses();
}

    // ================= DISPLAY =================

    public static void displayCourses() {

        if (count == 0) {
            System.out.println("No courses available.");
            return;
        }

        for (int i = 0; i < count; i++) {

            if (courses[i] != null) {
                System.out.println("\nCourse " + (i + 1));
                System.out.println("Name   : " + courses[i].getCourseName());
                System.out.println("Code   : " + courses[i].getCourseCode());
                System.out.println("Credit : " + courses[i].getCreditHour());
                System.out.println("Summary: " + courses[i].getSummary());
                System.out.println("Link   : " + courses[i].getTeamsLink());
                System.out.println("Type   : " + courses[i].getCourseType());
            }
        }
    }

    // ================= ENROLLMENT =================

    public void enrollStudentInCourse(int studentIndex, int courseIndex) {

        if (enrollment[studentIndex][courseIndex] == 1) {
            System.out.println("Already enrolled");
            return;
        }

        enrollment[studentIndex][courseIndex] = 1;
    }

    void findCourse(int studentIndex) {

        for (int i = 0; i < count; i++) {
            if (enrollment[studentIndex][i] == 1 && courses[i] != null) {
                System.out.println(courses[i].getCourseName());
            }
        }
    }

    /*void listCourses(int studentIndex) {

        boolean hasCourse = false;

        System.out.println("Courses enrolled:");

        for (int i = 0; i < count; i++) {
            if (enrollment[studentIndex][i] == 1 && courses[i] != null) {
                System.out.println(courses[i].getCourseCode());
                hasCourse = true;
            }
        }

        if (!hasCourse) {
            System.out.println("No courses enrolled.");
        }
    }*/
    public void listCoursesByStudentId(String studentId) {
    int sIdx = StudentManager.getStudentIndexById(studentId);
    if (sIdx == -1) {
        System.out.println("Error: Student ID not found.");
        return;
    }

    boolean hasCourse = false;
    System.out.println("\nCourses enrolled for " + studentId + ":");
    for (int i = 0; i < count; i++) {
        // Requirement 2d logic
        if (enrollment[sIdx][i] == 1 && courses[i] != null) {
            System.out.println("- " + courses[i].getCourseCode() + ": " + courses[i].getCourseName());
            hasCourse = true;
        }
    }
    
    // Requirement 3a: Check for student without an assigned course
    if (!hasCourse) System.out.println("\nNo courses enrolled.");
}
    
    public static int getCourseIndexByCode(String code) {
    for (int i = 0; i < count; i++) {
        if (courses[i] != null && courses[i].getCourseCode().equalsIgnoreCase(code)) {
            return i;
        }
    }
    return -1; // Requirement 3a: Returns -1 if course not found
}
}
