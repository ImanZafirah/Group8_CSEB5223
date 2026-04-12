package com.mycompany.slms;

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

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n==== COURSE PROFILE SYSTEM ====");
            System.out.println("1. Add Course");
            System.out.println("2. Search Course");
            System.out.println("3. Edit Course");
            System.out.println("4. Delete Course");
            System.out.println("5. View All Course");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addCourse(scanner);
                case 2 -> searchCourse(scanner);
                case 3 -> editCourse(scanner);
                case 4 -> deleteCourse(scanner);
                case 5 -> displayCourses();
                case 6 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 6);

        scanner.close();
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
        }
    }

    // ================= EDIT =================

    public static void editCourse(Scanner scanner) {

        System.out.print("Enter Course Code: ");
        String code = scanner.nextLine();

        CourseManager manager = new CourseManager();
        manager.suggestCourse(code);

        for (int i = 0; i < count; i++) {

            if (courses[i] != null &&
                courses[i].getCourseCode().equalsIgnoreCase(code)) {

                manager.cacheCourseSearch(code);

                System.out.println("Course found.");

                System.out.print("New Name (enter skip): ");
                String name = scanner.nextLine();
                if (!name.isEmpty()) courses[i].setCourseName(name);

                System.out.print("New Credit Hour: ");
                String creditInput = scanner.nextLine();
                if (!creditInput.isEmpty()) {
                    try {
                        courses[i].setCreditHour(Integer.parseInt(creditInput));
                    } catch (Exception e) {
                        System.out.println("Invalid credit hour.");
                    }
                }

                System.out.print("New Summary: ");
                String summary = scanner.nextLine();
                if (!summary.isEmpty()) courses[i].setSummary(summary);

                System.out.print("New Link: ");
                String link = scanner.nextLine();
                if (!link.isEmpty()) courses[i].setTeamsLink(link);

                System.out.println("Course updated!");
                return;
            }
        }

        System.out.println("Course not found.");
    }

    // ================= DELETE =================

    public static void deleteCourse(Scanner scanner) {

        System.out.print("Enter Course Code: ");
        String code = scanner.nextLine();

        for (int i = 0; i < count; i++) {

            if (courses[i] != null &&
                courses[i].getCourseCode().equalsIgnoreCase(code)) {

                System.out.print("Confirm delete? (Y/N): ");
                String confirm = scanner.nextLine();

                if (confirm.equalsIgnoreCase("Y")) {

                    for (int j = i; j < count - 1; j++) {
                        courses[j] = courses[j + 1];
                    }

                    courses[count - 1] = null;
                    count--;

                    System.out.println("Deleted successfully!");
                }

                return;
            }
        }

        System.out.println("Course not found.");
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

    void listCourses(int studentIndex) {

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
    }
}
