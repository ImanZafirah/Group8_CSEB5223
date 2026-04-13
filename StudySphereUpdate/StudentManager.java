package com.mycompany.studysphere;

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

    public void displayStudentMenu(Scanner scanner) {

    int choice = 0;

    do {
        System.out.println("\n==== STUDENT PROFILE SYSTEM ====");
        System.out.println("1. Add Student");
        System.out.println("2. Search Student");
        System.out.println("3. Edit Student");
        System.out.println("4. Delete Student");
        System.out.println("5. View All Student");
        System.out.println("6. Exit");
        System.out.print("Choose option: ");

        // Check if the user entered an integer
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1 -> addStudent(scanner);
                case 2 -> searchStudent(scanner);
                case 3 -> editStudent(scanner);
                case 4 -> deleteStudent(scanner);
                case 5 -> displayStudent();
                case 6 -> System.out.println("Exiting Student Module...");
                default -> System.out.println("Invalid choice. Please enter 1-6.");
            }
        } else {
            // This handles non-numeric input like alphabets
            System.out.println("Invalid input! Please enter a number (1-6).");
            scanner.nextLine(); // Clear the invalid input from the buffer
            choice = 0; // Set choice to 0 to ensure the loop continues
        }

    } while (choice != 6);
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
            displayStudent();
        }
    }

    // ===================== EDIT STUDENT =====================

    public static void editStudent(Scanner scanner) {

    System.out.print("Enter student ID to edit: ");
    String searchStudentId = scanner.nextLine();

    boolean found = false;

    for (int i = 0; i < count; i++) {
        if (student[i] != null && student[i].getStudentId().equalsIgnoreCase(searchStudentId)) {

            System.out.println("\n===== STUDENT FOUND =====");
            System.out.println("Student Name          : " + student[i].getFirstName() + " " + student[i].getLastName());
            System.out.println("Student ID            : " + student[i].getStudentId());
            System.out.println("Student Email         : " + student[i].getStudentEmail());
            System.out.println("Student Phone Number  : " + student[i].getStudentPhone());

            System.out.println("\nEnter new values (leave blank to keep current)");

            while (true) {
                System.out.print("New Student First Name: ");
                String firstName = scanner.nextLine();
                if (firstName.isEmpty()) break; 
                
                if (firstName.matches("[a-zA-Z ]+")) {
                    student[i].setFirstName(firstName);
                    break;
                }
                System.out.println("Invalid format! Use alphabets only.");
            }
            
            while (true) {
                System.out.print("New Student Last Name: ");
                String lastName = scanner.nextLine();
                if (lastName.isEmpty()) break;
                
                if (lastName.matches("[a-zA-Z ]+")) {
                    student[i].setLastName(lastName);
                    break;
                }
                System.out.println("Invalid format! Use alphabets only.");
            }

            System.out.print("New Student Email: ");
            String studentEmail = scanner.nextLine();
            if (!studentEmail.isEmpty()) {
                student[i].setStudentEmail(studentEmail);
            }

            while (true) {
                System.out.print("New Student Phone Number: ");
                String studentPhone = scanner.nextLine();
                if (studentPhone.isEmpty()) break;
                
                if (studentPhone.matches("[0-9]+")) {
                    student[i].setStudentPhone(studentPhone);
                    break;
                }
                System.out.println("Invalid format! Phone number must contain only digits.");
            }

            System.out.println("\n===== STUDENT UPDATED =====");
            System.out.println("Student Name          : " + student[i].getFirstName() + " " + student[i].getLastName());
            System.out.println("Student ID            : " + student[i].getStudentId());
            System.out.println("Student Email         : " + student[i].getStudentEmail());
            System.out.println("Student Phone Number  : " + student[i].getStudentPhone());

            found = true;
            break;
        }
    }

    if (!found) {
        System.out.println("Student not found.");
    }
    displayStudent();
}

    // ===================== DELETE STUDENT =====================

    public static void deleteStudent(Scanner scanner) {

    System.out.print("Enter Student ID to delete: ");
    String studentId = scanner.nextLine();

    boolean found = false;

    for (int i = 0; i < count; i++) {
        // null check added for safety
        if (student[i] != null && student[i].getStudentId().equalsIgnoreCase(studentId)) {

            //Original detail display before confirmation
            System.out.println("\nStudent Found:");
            System.out.println("Student Name          : " + student[i].getFirstName() + " " + student[i].getLastName());
            System.out.println("Student ID            : " + student[i].getStudentId());
            System.out.println("Student Email         : " + student[i].getStudentEmail());
            System.out.println("Student Phone Number  : " + student[i].getStudentPhone());

            System.out.print("\nConfirm deletion? (Y/N): ");
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("Y")) {
                // Array shifting logic
                for (int j = i; j < count - 1; j++) {
                    student[j] = student[j + 1];
                }

                student[count - 1] = null;
                count--;

                System.out.println("Student deleted successfully!");
                // displayStudent() is called inside if they confirmed
            }

            found = true;
            break;
        }
    }

    if (!found) {
        System.out.println("Student not found.");
    }
    displayStudent();
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

        System.out.println("\nStudents in this course:");

        boolean hasStudent = false;

        for (int i = 0; i < count; i++) {
            if (enrollment[i][courseIndex] == 1 && student[i] != null) {
                System.out.println("- " + student[i].getStudentId());
                hasStudent = true;
            }
        }

        if (!hasStudent) {
            System.out.println("\nNo students assigned to this course.");
        }
    }
    
    public static int getStudentIndexById(String id) {
    for (int i = 0; i < count; i++) {
        if (student[i] != null && student[i].getStudentId().equalsIgnoreCase(id)) {
            return i;
        }
    }
    return -1; // Requirement 3a: Returns -1 if student not found
}
}
