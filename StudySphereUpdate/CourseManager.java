package com.mycompany.studysphere;

import java.util.Scanner;

public class CourseManager {

    static Course[] courses = new Course[100];
    static int count = 0;

    int[][] enrollment = new int[100][100];

    // Persists across different method calls
    static String[] cachedCourseSearch = new String[50];
    static int courseCacheIndex = 0;

    // ================= CACHE SYSTEM =================

    static void cacheCourseSearch(String courseCode) {
        // Prevent duplicate suggestions in the list
        for (String c : cachedCourseSearch) {
            if (c != null && c.equalsIgnoreCase(courseCode)) return;
        }
        
        cachedCourseSearch[courseCacheIndex] = courseCode.toUpperCase();
        courseCacheIndex = (courseCacheIndex + 1) % cachedCourseSearch.length;
    }

    public static void suggestCourse(String input) {
        if (input.isEmpty()) return;
        
        System.out.println("\n[System Suggestions]:");
        boolean found = false;
        for (String c : cachedCourseSearch) {
            if (c != null && c.startsWith(input.toUpperCase())) {
                System.out.println(" - " + c);
                found = true;
            }
        }
        if (!found) System.out.println(" > No matching registered courses.");
    }

    // ================= MAIN MENU =================

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

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1 -> addCourse(scanner);
                    case 2 -> searchCourse(scanner);
                    case 3 -> editCourse(scanner);
                    case 4 -> deleteCourse(scanner);
                    case 5 -> displayCourses();
                    case 6 -> System.out.println("Exiting Course Module...");
                    default -> System.out.println("\nInvalid choice. Please enter 1-6.");
                }
            } else {
                System.out.println("Invalid input! Please enter a number (1-6).");
                scanner.nextLine(); 
                choice = 0; 
            }
        } while (choice != 6);
    }

    // ================= ADD COURSE =================
    public static void addCourse(Scanner scanner) {
        if (count >= courses.length) {
            System.out.println("Course list full!");
            return;
        }

        System.out.print("\nCourse Name: ");
        String name = scanner.nextLine();

        System.out.print("Course Code: ");
        String code = scanner.nextLine();

        for (int i = 0; i < count; i++) {
            if (courses[i] != null && courses[i].getCourseCode().equalsIgnoreCase(code)) {
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
        
        // SUCCESS: Add to CACHE only when course is successfully created
        cacheCourseSearch(code);
        
        count++;
        System.out.println("\nCourse added successfully!");
    }

    // ================= SEARCH COURSE =================
    public static void searchCourse(Scanner scanner) {
    boolean found = false;

    while (!found) {
        System.out.print("\nEnter Course Code to search: ");
        String searchCode = scanner.nextLine();

        for (int i = 0; i < count; i++) {
            if (courses[i] != null && courses[i].getCourseCode().equalsIgnoreCase(searchCode)) {
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
            System.out.println("\n[!] Course not found.");
            suggestCourse(searchCode);
            System.out.println("Please try again.");
        }
    }
}

    // ================= EDIT =================
    public static void editCourse(Scanner scanner) {
        if (count == 0) {
            System.out.println("\n[!] No courses registered in the system.");
            return;
        }

        boolean found = false;
        int i = -1; // To store the index once found

        // LOOP starts here
        while (!found) {
            System.out.print("Enter Course Code to edit: ");
            String searchCode = scanner.nextLine();

            for (int j = 0; j < count; j++) {
                if (courses[j] != null && courses[j].getCourseCode().equalsIgnoreCase(searchCode)) {
                    i = j; // Found the student at this index
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("\nCourse not found.");
                suggestCourse(searchCode); // Suggest based on current input
                System.out.println("Please key in the correct Course Code.");
            }
        }

        cacheCourseSearch(courses[i].getCourseCode());

        System.out.println("\n===== COURSE FOUND =====");
        System.out.println("Course Name    : " + courses[i].getCourseName());
        System.out.println("Credit Hour    : " + courses[i].getCreditHour());
        System.out.println("Summary        : " + courses[i].getSummary());
        System.out.println("MS Teams Link  : " + courses[i].getTeamsLink());
        System.out.println("Type           : " + courses[i].getCourseType());

        System.out.println("\nEnter new values (leave blank to keep current)");

        System.out.print("New Course Name: ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) courses[i].setCourseName(name);

        System.out.print("New Credit Hour: ");
        String creditInput = scanner.nextLine();
        if (!creditInput.isEmpty()) {
            try {
                courses[i].setCreditHour(Integer.parseInt(creditInput));
            } catch (NumberFormatException e) {
                System.out.println("Invalid format, keeping previous.");
            }
        }

        System.out.print("New Summary: ");
        String summary = scanner.nextLine();
        if (!summary.isEmpty()) courses[i].setSummary(summary);

        System.out.print("New MS Teams Link: ");
        String link = scanner.nextLine();
        if (!link.isEmpty()) courses[i].setTeamsLink(link);
        
        System.out.print("New Course Type: ");
        String type = scanner.nextLine();
        if (!type.isEmpty()) courses[i].setCourseType(type);

        System.out.println("\n===== COURSE UPDATED =====");
        System.out.println("Course Name    : " + courses[i].getCourseName());
        System.out.println("Course Code    : " + courses[i].getCourseCode());
        System.out.println("Credit Hour    : " + courses[i].getCreditHour());
        System.out.println("Summary        : " + courses[i].getSummary());
        System.out.println("MS Teams Link  : " + courses[i].getTeamsLink());
        System.out.println("Type           : " + courses[i].getCourseType());

        displayCourses();
    }

    // ================= DELETE =================
    public static void deleteCourse(Scanner scanner) {
        if (count == 0) {
            System.out.println("\n[!] No courses registered to delete.");
            return;
        }

        boolean found = false;
        int i = -1;

        while (!found) {
            System.out.print("Enter Course Code to delete: ");
            String code = scanner.nextLine();

            for (int j = 0; j < count; j++) {
                if (courses[j] != null && courses[j].getCourseCode().equalsIgnoreCase(code)) {
                    i = j;
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("\nCourse not found.");
                suggestCourse(code);
                System.out.println("Please key in the correct Course Code.");
            }
        }
        
        //Original detail display before confirmation
        System.out.println("\n===== COURSE FOUND =====");
        System.out.println("Course Name    : " + courses[i].getCourseName());
        System.out.println("Course Code    : " + courses[i].getCourseCode());
        System.out.println("Credit Hour    : " + courses[i].getCreditHour());
        System.out.println("Summary        : " + courses[i].getSummary());
        System.out.println("MS Teams Link  : " + courses[i].getTeamsLink());
        System.out.println("Type           : " + courses[i].getCourseType());

        System.out.print("\nConfirm deletion? (Y/N): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("Y")) {
            for (int k = i; k < count - 1; k++) {
                courses[k] = courses[k + 1];
            }
            courses[count - 1] = null;
            count--;
            System.out.println("Course deleted successfully!");
            System.out.println("--- Remaining Courses ---");
        }
        displayCourses();
    }

    // ================= DISPLAY =================
    public static void displayCourses() {
        if (count == 0) {
            System.out.println("\nNo courses available.");
            return;
        }
        System.out.println("\n===== COURSE LIST =====");
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

    // ================= ENROLLMENT HELPERS =================
    public boolean enrollStudentInCourse(int studentIndex, int courseIndex) {
    // If already 1, return false
    if (enrollment[studentIndex][courseIndex] == 1) return false;
    
    enrollment[studentIndex][courseIndex] = 1;
    return true; // Successfully updated
    }

    public void listCoursesByStudentId(String studentId) {
        int sIdx = StudentManager.getStudentIndexById(studentId);
        if (sIdx == -1) {
            System.out.println("\nError: Student ID not found.");
            return;
        }
        boolean hasCourse = false;
        System.out.println("\nCourses enrolled for " + studentId + ":");
        for (int i = 0; i < count; i++) {
            if (enrollment[sIdx][i] == 1 && courses[i] != null) {
                System.out.println("- " + courses[i].getCourseCode() + ": " + courses[i].getCourseName());
                hasCourse = true;
            }
        }
        if (!hasCourse) System.out.println("\nNo courses enrolled.");
    }
    
    public static int getCourseIndexByCode(String code) {
        for (int i = 0; i < count; i++) {
            if (courses[i] != null && courses[i].getCourseCode().equalsIgnoreCase(code)) return i;
        }
        return -1;
    }
}