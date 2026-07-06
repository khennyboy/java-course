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
            System.out.println("5. Calculate CGPA");
            System.out.println("6. Update student");
            System.out.println("7. Delete student");
            System.out.println("8. Quit");
            System.out.print("Choose: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1": {
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
                }
                case "2":
                    manager.listAllStudents();
                    break;

                case "3": {
                    System.out.print("Enter student ID to find: ");
                    short id = Short.parseShort(scanner.nextLine());
                    manager.findStudent(id);
                    break;
                }
                case "4": {
                    System.out.print("Enter student id: ");
                    short id = Short.parseShort(scanner.nextLine());
                    System.out.print("Enter course code: ");
                    String courseCode = scanner.nextLine();
                    System.out.print("Enter unit: ");
                    int unit = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter score: ");
                    double score = Double.parseDouble(scanner.nextLine());
                    manager.addCourse(id, new Course(courseCode, unit, score));
                    break;
                }
                case "5": {
                    System.out.print("Enter student ID: ");
                    short id = Short.parseShort(scanner.nextLine());
                    manager.calculateCGPA(id);
                    break;
                }
                case "6": {
                    System.out.print("Enter student ID: ");
                    short id = Short.parseShort(scanner.nextLine());

                    System.out.println("What do you want to update?");
                    System.out.println("1. Name");
                    System.out.println("2. Department");
                    System.out.println("3. Level");
                    System.out.println("4. Course score");
                    System.out.print("Choose: ");
                    String updateChoice = scanner.nextLine();

                    switch (updateChoice) {
                        case "1":
                            System.out.print("Enter new name: ");
                            manager.updateStudentName(id, scanner.nextLine());
                            break;
                        case "2":
                            System.out.print("Enter new department: ");
                            manager.updateStudentDepartment(id, scanner.nextLine());
                            break;
                        case "3":
                            System.out.print("Enter new level: ");
                            manager.updateStudentLevel(id, Short.parseShort(scanner.nextLine()));
                            break;
                        case "4":
                            System.out.print("Enter course code to update: ");
                            String courseCode = scanner.nextLine();
                            System.out.print("Enter new score: ");
                            double newScore = Double.parseDouble(scanner.nextLine());
                            manager.updateCourseScore(id, courseCode, newScore);
                            break;
                        default:
                            System.out.println("Invalid option.");
                    }
                    break;
                }

                case "7": {
                    System.out.print("Enter student ID to delete: ");
                    short id = Short.parseShort(scanner.nextLine());
                    manager.deleteStudent(id);
                    break;
                }
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