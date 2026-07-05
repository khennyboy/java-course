package studentracker;

import java.util.Scanner;

public class StudentTrackerApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        boolean running = true;
        while (running) {

            System.out.println("\n--- STUDENT TRACKER ---");
            System.out.println("1. Add student");
            System.out.println("2. List all students");
            System.out.println("3. Find student");
            System.out.println("4. Add course/score to student");
            System.out.println("5. Calculate GPA");
            System.out.println("6. Update student");
            System.out.println("7. Delete student");
            System.out.println("8. Quit");
            System.out.print("Choose: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter student ID: ");
                    short id = Short.parseShort(scanner.nextLine());
                    System.out.print("Enter department: ");
                    String dept = scanner.nextLine();
                    System.out.print("Enter level: ");
                    short level = Short.parseShort(scanner.nextLine());
                    manager.addStudent(new Student(name, id, dept, level));
                    break;

                case "2":
                    manager.listAllStudents();
                    break;

                case "3":
                    System.out.print("Enter student ID to find: ");
                    String searchId = scanner.nextLine();
                    manager.findStudent(searchId);
                    break;

                case "4":
                    // you'll prompt for student ID, course name, score here
                    break;

                case "5":
                    System.out.print("Enter student ID: ");
                    String gpaId = scanner.nextLine();
                    manager.calculateGPA(gpaId);
                    break;

                case "6":
                    // update logic
                    break;

                case "7":
                    System.out.print("Enter student ID to delete: ");
                    String delId = scanner.nextLine();
                    manager.deleteStudent(delId);
                    break;

                case "8":
                    System.out.println("Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }
}