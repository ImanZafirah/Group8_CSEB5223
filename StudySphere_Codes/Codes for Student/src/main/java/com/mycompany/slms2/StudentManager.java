package com.mycompany.slms2;

import java.util.Scanner;

public class StudentManager {

    static Student[] student = new Student[100];
    static int count = 0;

    int[][] enrollment = new int[100][100];

    String[] cachedStudentSearch = new String[50];
    int studentCacheIndex = 0;

    // ===================== CACHE SYSTEM =====================

    void cacheStudentSearch(String studentId) {
        cachedStudentSearch[studentCacheIndex] = studentId;
        studentCacheIndex++;

        if (studentCacheIndex >= cachedStudentSearch.length) {
            studentCacheIndex = 0;
        }
    }

    public void suggestStudent(String input) {
        System.out.println("Suggestions:");

        for (String s : cachedStudentSearch) {
            if (s != null && s.startsWith(input)) {
                System.out.println(" - " + s);
            }
        }
    }

    // ===================== MAIN MENU =====================

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n==== STUDENT PROFILE SYSTEM ====");
            System.out.println("1. Add Student");
            System.out.println("2. Search Student");
            System.out.println("3. Edit Student");
            System.out.println("4. Delete Student");
            System.out.println("5. View All Student");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1 -> addStudent(scanner);
                case 2 -> searchStudent(scanner);
                case 3 -> editStudent(scanner);
                case 4 -> deleteStudent(scanner);
                case 5 -> displayStudent();
                case 6 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 6);

        scanner.close();
    }

    // ===================== ADD STUDENT =====================

    public static void addStudent(Scanner scanner) {

        if (count >= student.length) {
            System.out.println("Student list is full!");
            return;
        }

        String firstName, lastName, studentPhone;

        while (true) {
            System.out.print("Student First Name: ");
            firstName = scanner.nextLine();
            if (firstName.matches("[a-zA-Z ]+")) break;
            System.out.println("Invalid format! Alphabets only.");
        }

        while (true) {
            System.out.print("Student Last Name: ");
            lastName = scanner.nextLine();
            if (lastName.matches("[a-zA-Z ]+")) break;
            System.out.println("Invalid format! Alphabets only.");
        }

        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();

        for (int i = 0; i < count; i++) {
            if (student[i] != null &&
                student[i].getStudentId().equalsIgnoreCase(studentId)) {
                System.out.println("Error: Student already exists!");
                return;
            }
        }

        System.out.print("Student Email: ");
        String studentEmail = scanner.nextLine();

        while (true) {
            System.out.print("Student Phone Number: ");
            studentPhone = scanner.nextLine();
            if (studentPhone.matches("[0-9]+")) break;
            System.out.println("Invalid phone number!");
        }

        student[count] = new Student(firstName, lastName, studentId, studentEmail, studentPhone);
        count++;

        System.out.println("Student added successfully!");
    }

    // ===================== SEARCH STUDENT =====================

    public static void searchStudent(Scanner scanner) {

        System.out.print("Enter Student ID to search: ");
        String searchStudentId = scanner.nextLine();

        StudentManager manager = new StudentManager();
        manager.suggestStudent(searchStudentId);

        boolean found = false;

        for (int i = 0; i < count; i++) {

            if (student[i] != null &&
                student[i].getStudentId().equalsIgnoreCase(searchStudentId)) {

                manager.cacheStudentSearch(searchStudentId);

                System.out.println("\n===== STUDENT FOUND =====");
                System.out.println("Name   : " + student[i].getFirstName() + " " + student[i].getLastName());
                System.out.println("ID     : " + student[i].getStudentId());
                System.out.println("Email  : " + student[i].getStudentEmail());
                System.out.println("Phone  : " + student[i].getStudentPhone());

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    // ===================== EDIT STUDENT =====================

    public static void editStudent(Scanner scanner) {

        System.out.print("Enter student ID to edit: ");
        String id = scanner.nextLine();

        for (int i = 0; i < count; i++) {

            if (student[i] != null &&
                student[i].getStudentId().equalsIgnoreCase(id)) {

                System.out.println("Student found.");

                System.out.print("New First Name (enter to skip): ");
                String firstName = scanner.nextLine();
                if (!firstName.isEmpty() && firstName.matches("[a-zA-Z ]+")) {
                    student[i].setFirstName(firstName);
                }

                System.out.print("New Last Name (enter to skip): ");
                String lastName = scanner.nextLine();
                if (!lastName.isEmpty() && lastName.matches("[a-zA-Z ]+")) {
                    student[i].setLastName(lastName);
                }

                System.out.print("New Email (enter to skip): ");
                String email = scanner.nextLine();
                if (!email.isEmpty()) {
                    student[i].setStudentEmail(email);
                }

                System.out.print("New Phone (enter to skip): ");
                String phone = scanner.nextLine();
                if (!phone.isEmpty() && phone.matches("[0-9]+")) {
                    student[i].setStudentPhone(phone);
                }

                System.out.println("Student updated successfully!");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    // ===================== DELETE STUDENT =====================

    public static void deleteStudent(Scanner scanner) {

        System.out.print("Enter Student ID to delete: ");
        String id = scanner.nextLine();

        for (int i = 0; i < count; i++) {

            if (student[i] != null &&
                student[i].getStudentId().equalsIgnoreCase(id)) {

                System.out.print("Confirm delete? (Y/N): ");
                String confirm = scanner.nextLine();

                if (confirm.equalsIgnoreCase("Y")) {

                    for (int j = i; j < count - 1; j++) {
                        student[j] = student[j + 1];
                    }

                    student[count - 1] = null;
                    count--;

                    System.out.println("Deleted successfully!");
                }

                return;
            }
        }

        System.out.println("Student not found.");
    }

    // ===================== DISPLAY =====================

    public static void displayStudent() {

        if (count == 0) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("\n===== STUDENT LIST =====");

        for (int i = 0; i < count; i++) {

            if (student[i] != null) {
                System.out.println("\nStudent " + (i + 1));
                System.out.println("Name  : " + student[i].getFirstName() + " " + student[i].getLastName());
                System.out.println("ID    : " + student[i].getStudentId());
                System.out.println("Email : " + student[i].getStudentEmail());
                System.out.println("Phone : " + student[i].getStudentPhone());
            }
        }
    }

    // ===================== COURSE METHODS =====================

    public void addStudent(int courseIndex, int studentIndex) {

        if (enrollment[studentIndex][courseIndex] == 1) {
            System.out.println("Already assigned");
            return;
        }

        enrollment[studentIndex][courseIndex] = 1;
    }

    void findStudent(int courseIndex) {

        for (int i = 0; i < count; i++) {
            if (enrollment[i][courseIndex] == 1 && student[i] != null) {
                System.out.println(student[i].getFirstName() + " " + student[i].getLastName());
            }
        }
    }

    void listStudents(int courseIndex) {

        System.out.println("Students in course:");

        boolean hasStudent = false;

        for (int i = 0; i < count; i++) {
            if (enrollment[i][courseIndex] == 1 && student[i] != null) {
                System.out.println(student[i].getStudentId());
                hasStudent = true;
            }
        }

        if (!hasStudent) {
            System.out.println("No students assigned to this course.");
        }
    }
}
