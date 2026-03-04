import java.util.Scanner;

public class CourseProfileApp {

    static Course[] courses = new Course[100];
    static int count = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n==== COURSE PROFILE SYSTEM ====");
            System.out.println("1. Add Course");
            System.out.println("2. Search Course");
            System.out.println("3. Exit");
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
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 3);

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

        System.out.print("Credit Hour: ");
        int credit = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Summary: ");
        String summary = scanner.nextLine();

        System.out.print("MS Teams Link: ");
        String link = scanner.nextLine();

        courses[count] = new Course(name, code, credit, summary, link);
        count++;

        System.out.println("Course added successfully!");
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

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Course not found.");
        }
    }
}