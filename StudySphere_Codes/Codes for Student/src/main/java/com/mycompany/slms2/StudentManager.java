package com.mycompany.slms2;

import java.util.Scanner;

public class StudentManager {

    static Student[] student = new Student[100];
    static int count = 0;

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

                case 1:
                    addStudent(scanner);
                    break;

                case 2:
                    searchStudent(scanner);
                    break;

                case 3:
                    editStudent(scanner);
                    break;

                case 4:
                    deleteStudent(scanner);
                    break;

                case 5:
                    displayStudent();
                    break;

                case 6:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 6);

        scanner.close();
    }

    public static void addStudent(Scanner scanner) {

        if (count >= student.length) {
            System.out.println("Student list is full!");
            return;
        }
        
        // Declare variables outside the loops so they can be used later
        String firstName, lastName, studentPhone;

        while (true) {
            System.out.print("Student First Name: ");
            firstName = scanner.nextLine();
            if (firstName.matches("[a-zA-Z ]+")) {
                break; // Valid input, exit the while loop
            }
            System.out.println("Invalid format! Please use alphabets only.");
        }
        
        while (true) {
            System.out.print("Student Last Name: ");
            lastName = scanner.nextLine();
            if (lastName.matches("[a-zA-Z ]+")) {
                break; // Valid input, exit the while loop
            }
            System.out.println("Invalid format! Please use alphabets only.");
        }

        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("Student Email: ");
        String studentEmail = scanner.nextLine();

        while (true){
            System.out.print("Student Phone Number: ");
            studentPhone = scanner.nextLine();
            if (studentPhone.matches("[0-9]+")) {
                break;
            }
            System.out.println("Invalid phone number! Phone number must contain only digits.");
        }

        student[count] = new Student(firstName, lastName, studentId, studentEmail, studentPhone);
        count++;

        System.out.println("Student added successfully!");
    }

    public static void searchStudent(Scanner scanner) {

        System.out.print("Enter Student ID to search: ");
        String searchStudentId = scanner.nextLine();

        boolean found = false;

        for (int i = 0; i < count; i++) {

            if (student[i].getStudentId().equalsIgnoreCase(searchStudentId)) {

                System.out.println("\n===== STUDENT FOUND =====");
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
            displayStudent();
        }
    }

    public static void editStudent(Scanner scanner) {

    System.out.print("Enter student ID to edit: ");
    String searchStudentId = scanner.nextLine();

    boolean found = false;

    for (int i = 0; i < count; i++) {
        if (student[i].getStudentId().equalsIgnoreCase(searchStudentId)) {

            System.out.println("\n===== STUDENT FOUND =====");
            System.out.println("Student Name          : " + student[i].getFirstName() + " " + student[i].getLastName());
            System.out.println("Student ID            : " + student[i].getStudentId());
            System.out.println("Student Email         : " + student[i].getStudentEmail());
            System.out.println("Student Phone Number  : " + student[i].getStudentPhone());

            System.out.println("\nEnter new values (leave blank to keep current)");

            while (true) {
                System.out.print("New Student First Name: ");
                String firstName = scanner.nextLine();
                if (firstName.isEmpty()) {
                    break; 
                }
                
                //Validates that input contains only alphabets and spaces
                if (firstName.matches("[a-zA-Z ]+")) {
                    student[i].setFirstName(firstName);
                    break;
                }
                System.out.println("Invalid format! Use alphabets only.");
            }
            
            while (true){
                System.out.print("New Student Last Name: ");
                String lastName = scanner.nextLine();
                if (lastName.isEmpty()) {
                    break;
                }
                
                // Validates that input contains only alphabets and spaces
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

            while (true){
                System.out.print("New Student Phone Number: ");
                String studentPhone = scanner.nextLine();
                if (studentPhone.isEmpty()) {
                    break;
                }
                
                //Check if the input contains only numbers
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
}

    public static void deleteStudent(Scanner scanner) {

        System.out.print("Enter Student ID to delete: ");
        String studentId = scanner.nextLine();

        boolean found = false;

        for (int i = 0; i < count; i++) {

            if (student[i].getStudentId().equalsIgnoreCase(studentId)) {

                System.out.println("\nStudent Found:");
                System.out.println("Student Name          : " + student[i].getFirstName() + " " + student[i].getLastName());
                System.out.println("Student ID            : " + student[i].getStudentId());
                System.out.println("Student Email         : " + student[i].getStudentEmail());
                System.out.println("Student Phone Number  : " + student[i].getStudentPhone());

                System.out.print("\nConfirm deletion? (Y/N): ");
                String confirm = scanner.nextLine();

                if (confirm.equalsIgnoreCase("Y")) {

                    for (int j = i; j < count - 1; j++) {
                        student[j] = student[j + 1];
                    }

                    student[count - 1] = null;
                    count--;

                    System.out.println("Student deleted successfully!");
                    displayStudent();
                }

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    public static void displayStudent() {

    if (count == 0) {
        System.out.println("No student available.");
        return;
    }

    System.out.println("\n===== STUDENT LIST =====");

    for (int i = 0; i < count; i++) {

        System.out.println("\nStudent " + (i + 1));
        System.out.println("Student Name          : " + student[i].getFirstName() + " " + student[i].getLastName());
        System.out.println("Student ID            : " + student[i].getStudentId());
        System.out.println("Student Email         : " + student[i].getStudentEmail());
        System.out.println("Student Phone Number  : " + student[i].getStudentPhone());
    }
}
}