package com.mycompany.slms;

import java.util.Scanner;

public class CourseManager {

    public CourseManager() {
    enrollment = new int[100][100];
    }
    static Course[] courses = new Course[100];
    int[][] enrollment;
    static int count = 0;

    String[] cachedCourseSearch = new String[50];
    int courseCacheIndex = 0;

    //Courses Cache
    void cacheCourseSearch(String courseCode){
    cachedCourseSearch[courseCacheIndex] = courseCode;
    courseCacheIndex++;
    if(courseCacheIndex >= cachedCourseSearch.length){
        courseCacheIndex = 0;
    }
}

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
            //System.out.println("6. Manage Course"); //Core, Elective, University
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addCourse(scanner);
                    break;

                case 2:
                    searchCourse(scanner);
                    break;

                case 3:
                    editCourse(scanner);
                    break;

                case 4:
                    deleteCourse(scanner);
                    break;

                case 5:
                    displayCourses();
                    break;

                /*case 6:
                    manageCourses();
                    break;
*/
                case 6:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 6);

        scanner.close();
    }

    public static void addCourse(Scanner scanner) {

        if (count >= courses.length) {
            System.out.println("Course list is full!");
            return;
        }

        System.out.print("Course Name: ");
        String name = scanner.nextLine();

        System.out.print("Course Code: ");
        String code = scanner.nextLine();
        for (int i = 0; i < count; i++) {
            if (courses[i].getCourseCode().equalsIgnoreCase(code)) {
                System.out.println("Error: Course already exists!");
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

    public void enrollStudentInCourse(int studentIndex, int courseIndex) {
        if (enrollment[studentIndex][courseIndex] == 1) {
            System.out.println("Student already enrolled");
            return;
        }
        enrollment[studentIndex][courseIndex] = 1;
        System.out.println("Course added to student");
    }

    public static void searchCourse(Scanner scanner) {

        System.out.print("Enter Course Code to search: ");
        String searchCode = scanner.nextLine();

        boolean found = false;

        for (int i = 0; i < count; i++) {

            if (courses[i].getCourseCode().equalsIgnoreCase(searchCode)) {

                System.out.println("\n===== COURSE FOUND =====");
                System.out.println("Course Name    : " + courses[i].getCourseName());
                System.out.println("Course Code    : " + courses[i].getCourseCode());
                System.out.println("Credit Hour    : " + courses[i].getCreditHour());
                System.out.println("Summary        : " + courses[i].getSummary());
                System.out.println("MS Teams Link  : " + courses[i].getTeamsLink());
                System.out.println("Course Type : " + courses[i].getCourseType());

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Error: Course not found.");
            displayCourses();
        }
    }

    void findCourse(int studentIndex) {

        for(int i=0; i<courses.length; i++) {
            if(enrollment[studentIndex][i] == 1) {
                System.out.println(courses[i].getCourseName());
            }
        }
    }

    public static void editCourse(Scanner scanner) {

    System.out.print("Enter Course Code to edit: ");
    String searchCode = scanner.nextLine();
    CourseManager manager = new CourseManager();
    manager.suggestCourse(searchCode);

    boolean found = false;

    for (int i = 0; i < count; i++) {
        if (courses[i].getCourseCode().equalsIgnoreCase(searchCode)) {

            CourseManager manager = new CourseManager();
            manager.cacheCourseSearch(searchCode);
            
            System.out.println("\n===== COURSE FOUND =====");
            System.out.println("Course Name    : " + courses[i].getCourseName());
            System.out.println("Credit Hour    : " + courses[i].getCreditHour());
            System.out.println("Summary        : " + courses[i].getSummary());
            System.out.println("MS Teams Link  : " + courses[i].getTeamsLink());
            System.out.println("Course Type : " + courses[i].getCourseType());

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

            System.out.println("\n===== COURSE UPDATED =====");
            System.out.println("Course Name    : " + courses[i].getCourseName());
            System.out.println("Course Code    : " + courses[i].getCourseCode());
            System.out.println("Credit Hour    : " + courses[i].getCreditHour());
            System.out.println("Summary        : " + courses[i].getSummary());
            System.out.println("MS Teams Link  : " + courses[i].getTeamsLink());
            System.out.println("Course Type : " + courses[i].getCourseType());

            found = true;
            break;
        }
    }

    if (!found) {
        System.out.println("Course not found.");
    }
}

    public static void deleteCourse(Scanner scanner) {

        System.out.print("Enter Course Code to delete: ");
        String code = scanner.nextLine();

        boolean found = false;

        for (int i = 0; i < count; i++) {

            if (courses[i].getCourseCode().equalsIgnoreCase(code)) {

                System.out.println("\nCourse Found:");
                System.out.println("Course Name   : " + courses[i].getCourseName());
                System.out.println("Course Code   : " + courses[i].getCourseCode());
                System.out.println("Credit Hour   : " + courses[i].getCreditHour());
                System.out.println("Summary       : " + courses[i].getSummary());
                System.out.println("MS Teams Link : " + courses[i].getTeamsLink());
                System.out.println("Course Type : " + courses[i].getCourseType());

                System.out.print("\nConfirm deletion? (Y/N): ");
                String confirm = scanner.nextLine();

                if (confirm.equalsIgnoreCase("Y")) {

                    for (int j = i; j < count - 1; j++) {
                        courses[j] = courses[j + 1];
                    }

                    courses[count - 1] = null;
                    count--;

                    System.out.println("Course deleted successfully!");
                    displayCourses();
                }

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Course not found.");
        }
    }

    public static void displayCourses() {

    if (count == 0) {
        System.out.println("No courses available.");
        return;
    }

    System.out.println("\n===== COURSE LIST =====");

    for (int i = 0; i < count; i++) {

        System.out.println("\nCourse " + (i + 1));
        System.out.println("Course Name   : " + courses[i].getCourseName());
        System.out.println("Course Code   : " + courses[i].getCourseCode());
        System.out.println("Credit Hour   : " + courses[i].getCreditHour());
        System.out.println("Summary       : " + courses[i].getSummary());
        System.out.println("MS Teams Link : " + courses[i].getTeamsLink());
        System.out.println("Course Type : " + courses[i].getCourseType());
    }
        void suggestCourse(String input){
            System.out.println("Suggestions:");
            for(String c : cachedCourseSearch){
                if(c != null && c.startsWith(input)){
                    System.out.println(" - " + c);
                }
            }
        }
}

void listCourses(int studentIndex){

        System.out.println("Courses enrolled:");

        boolean hasCourse = false;

        for(int i=0;i<count;i++){
            if(enrollment[studentIndex][i] == 1){
                System.out.println(courses[i].getCourseCode());
                hasCourse = true;
            }
        }
        
        if(!hasCourse){
            System.out.println("This student has no enrolled courses.");
        }
    }
}
