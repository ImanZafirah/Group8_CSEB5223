package com.mycompany.studysphere;

import java.util.Scanner;

public class StudentManager {

    static Student[] student = new Student[100];
    static int count = 0;

    int[][] enrollment = new int[100][100];

    // Static variables ensure the "Data Bank" persists across the whole app
    static String[] cachedStudentSearch = new String[50];
    static int studentCacheIndex = 0;

    // ===================== CACHE SYSTEM =====================

    static void cacheStudentSearch(String studentId) {
        // Check if it already exists in cache to avoid duplicate suggestions
        for (String s : cachedStudentSearch) {
            if (s != null && s.equalsIgnoreCase(studentId)) return;
        }
        
        cachedStudentSearch[studentCacheIndex] = studentId.toUpperCase();
        studentCacheIndex = (studentCacheIndex + 1) % cachedStudentSearch.length;
    }

    public static void suggestStudent(String input) {
        if (input.isEmpty()) return;
        
        System.out.println("\nSuggestions:");
        boolean found = false;
        for (String s : cachedStudentSearch) {
            if (s != null && s.startsWith(input.toUpperCase())) {
                System.out.println(" - " + s);
                found = true;
            }
        }
        if (!found) System.out.println(" - No matching registered students.");
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

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1 -> addStudent(scanner);
                    case 2 -> searchStudent(scanner);
                    case 3 -> editStudent(scanner);
                    case 4 -> deleteStudent(scanner);
                    case 5 -> displayStudent();
                    case 6 -> System.out.println("Exiting Student Module...");
                    default -> System.out.println("\nInvalid choice. Please enter 1-6.");
                }
            } else {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
            }
        } while (choice != 6);
    }

    // ===================== ADD STUDENT =====================
    public static void addStudent(Scanner scanner) {
        if (count >= student.length) {
            System.out.println("Student list full!");
            return;
        }

        System.out.print("\nFirst Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();

        for (int i = 0; i < count; i++) {
            if (student[i] != null && student[i].getStudentId().equalsIgnoreCase(studentId)) {
                System.out.println("Error: Student already exists!");
                return;
            }
        }

        System.out.print("Student Email: ");
        String studentEmail = scanner.nextLine();
        System.out.print("Student Phone Number: ");
        String studentPhone = scanner.nextLine();

        student[count] = new Student(firstName, lastName, studentId, studentEmail, studentPhone);
        
        // NEW UPDATE: Add only valid created students to the suggestion bank
        cacheStudentSearch(studentId);
        
        count++;
        System.out.println("\nStudent added successfully!");
    }

    // ===================== SEARCH STUDENT =====================
    public static void searchStudent(Scanner scanner) {
    boolean found = false;

    // The loop will not stop until found is true
    while (!found) {
        System.out.print("\nEnter Student ID to search: ");
        String searchId = scanner.nextLine();

        for (int i = 0; i < count; i++) {
            if (student[i] != null && student[i].getStudentId().equalsIgnoreCase(searchId)) {
                System.out.println("\n===== STUDENT FOUND =====");
                System.out.println("Name   : " + student[i].getFirstName() + " " + student[i].getLastName());
                System.out.println("ID     : " + student[i].getStudentId());
                System.out.println("Email  : " + student[i].getStudentEmail());
                System.out.println("Phone  : " + student[i].getStudentPhone());
                
                found = true; // This will break the while loop
                break;       // This breaks the for loop
            }
        }

        if (!found) {
            System.out.println("\n[!] Student not found.");
            suggestStudent(searchId); // Show suggestions to help them find the right ID
            System.out.println("Please try again with a valid ID.");
        }
    }
}

    // ================= EDIT =================
    public static void editStudent(Scanner scanner) {
        if (count == 0) {
            System.out.println("\n[!] No students registered.");
            return;
        }

        boolean found = false;
        int i = -1;

        while (!found) {
            System.out.print("Enter student ID to edit: ");
            String searchId = scanner.nextLine();

            for (int j = 0; j < count; j++) {
                if (student[j] != null && student[j].getStudentId().equalsIgnoreCase(searchId)) {
                    i = j;
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("\nStudent not found.");
                suggestStudent(searchId);
                System.out.println("Please key in the correct Student ID.");
            }
        }

        cacheStudentSearch(student[i].getStudentId());

        //Original detail display before confirmation
        System.out.println("\n===== STUDENT FOUND =====");
        System.out.println("Student Name          : " + student[i].getFirstName() + " " + student[i].getLastName());
        System.out.println("Student ID            : " + student[i].getStudentId());
        System.out.println("Student Email         : " + student[i].getStudentEmail());
        System.out.println("Student Phone Number  : " + student[i].getStudentPhone());

        System.out.println("\nEnter new values (leave blank to keep current)");

        System.out.print("New First Name: ");
        String fName = scanner.nextLine();
        if (!fName.isEmpty()) student[i].setFirstName(fName);

        System.out.print("New Last Name: ");
        String lName = scanner.nextLine();
        if (!lName.isEmpty()) student[i].setLastName(lName);

        System.out.print("New Email: ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) student[i].setStudentEmail(email);

        System.out.print("New Phone Number: ");
        String phone = scanner.nextLine();
        if (!phone.isEmpty()) student[i].setStudentPhone(phone);

        System.out.println("\n===== STUDENT UPDATED SUCCESSFULLY =====");
        System.out.println("Name   : " + student[i].getFirstName() + " " + student[i].getLastName());
        System.out.println("ID     : " + student[i].getStudentId());
        System.out.println("Email  : " + student[i].getStudentEmail());
        System.out.println("Phone  : " + student[i].getStudentPhone());

        displayStudent();
    }

    // ================= DELETE =================
    public static void deleteStudent(Scanner scanner) {
        if (count == 0) {
            System.out.println("\n[!] No students registered.");
            return;
        }

        boolean found = false;
        int i = -1;

        while (!found) {
            System.out.print("Enter Student ID to delete: ");
            String studentId = scanner.nextLine();

            for (int j = 0; j < count; j++) {
                if (student[j] != null && student[j].getStudentId().equalsIgnoreCase(studentId)) {
                    i = j;
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("\nStudent not found.");
                suggestStudent(studentId);
                System.out.println("Please key in the correct Student ID.");
            }
        }

        System.out.println("\nStudent Found:");
        System.out.println("Name   : " + student[i].getFirstName() + " " + student[i].getLastName());
        System.out.println("ID     : " + student[i].getStudentId());

        System.out.print("\nConfirm deletion? (Y/N): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("Y")) {
            for (int k = i; k < count - 1; k++) {
                student[k] = student[k + 1];
            }
            student[count - 1] = null;
            count--;
            System.out.println("\nStudent deleted successfully!");
            System.out.println("--- Remaining Students ---");
        }
        displayStudent();
    }

    // ===================== DISPLAY STUDENT =====================
    public static void displayStudent() {
        if (count == 0) {
            System.out.println("\nNo students available.");
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

    // ===================== ENROLLMENT HELPERS =====================

    public boolean addStudent(int courseIndex, int studentIndex) {
    // If already 1, return false (indicating nothing was changed)
    if (enrollment[studentIndex][courseIndex] == 1) return false;
    
    enrollment[studentIndex][courseIndex] = 1;
    return true; // Successfully updated
}

    public void listStudents(int courseIndex) {
        System.out.println("\nStudents in this course:");
        boolean hasStudent = false;
        for (int i = 0; i < count; i++) {
            if (enrollment[i][courseIndex] == 1 && student[i] != null) {
                System.out.println("- " + student[i].getStudentId() + ": " + student[i].getFirstName() + " " + student[i].getLastName());
                hasStudent = true;
            }
        }
        if (!hasStudent) System.out.println("\nNo students assigned to this course.");
    }

    public static int getStudentIndexById(String studentId) {
        for (int i = 0; i < count; i++) {
            if (student[i] != null && student[i].getStudentId().equalsIgnoreCase(studentId)) return i;
        }
        return -1;
    }
}