package studentracker2;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
        System.out.printf("Student %s added successfully!%n", student.getStudentName());
    }

    public void listAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students yet.");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    // NEW: exposes the raw list so the GUI can populate its table.
    // Doesn't change any existing behavior.
    public List<Student> getAllStudents() {
        return students;
    }

    // CHANGED: was "private", now "public" so the GUI can look up a
    // Student object directly (instead of only printing to console).
    public Student findStudentById(short studentId) {
        for (Student s : students) {
            if (s.getId() == studentId) return s;
        }
        return null;
    }

    public void findStudent(short studentId) {
        Student s = findStudentById(studentId);
        System.out.println(s != null ? s : "No student found with ID " + studentId);
    }

    public void addCourse(short studentId, Course course) {
        Student s = findStudentById(studentId);
        if (s != null) {
            s.addCourse(course);
            System.out.println("Course added successfully!");
        } else {
            System.out.println("No student found with ID " + studentId);
        }
    }

    private int gradePoint(double score) {
        if (score >= 70) return 5;
        if (score >= 60) return 4;
        if (score >= 50) return 3;
        if (score >= 45) return 2;
        if (score >= 40) return 1;
        return 0;
    }

    public void calculateCGPA(short studentId) {
        Student s = findStudentById(studentId);
        if (s == null) {
            System.out.println("No student found with ID " + studentId);
            return;
        }
        if (s.getCourses().isEmpty()) {
            System.out.println(s.getStudentName() + " has no courses yet.");
            return;
        }

        int totalPoints = 0;
        int totalUnits = 0;
        for (Course course : s.getCourses()) {
            totalPoints += gradePoint(course.getScore()) * course.getUnit();
            totalUnits += course.getUnit();
        }

        double cgpa = (double) totalPoints / totalUnits;
        s.setCgpa(cgpa);
        System.out.printf("Dear %s, your CGPA is %.2f%n", s.getStudentName(), cgpa);
    }

    public void deleteStudent(short studentId) {
        Student s = findStudentById(studentId);
        if (s != null) {
            students.remove(s);
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("No student found with ID " + studentId);
        }
    }

    public void updateStudentName(short studentId, String newName) {
        Student s = findStudentById(studentId);
        if (s == null) {
            System.out.println("No student found with ID " + studentId);
            return;
        }
        s.setStudentName(newName);
        System.out.println("Name updated successfully.");
    }

    public void updateStudentDepartment(short studentId, String newDept) {
        Student s = findStudentById(studentId);
        if (s == null) {
            System.out.println("No student found with ID " + studentId);
            return;
        }
        s.setDepartment(newDept);
        System.out.println("Department updated successfully.");
    }

    public void updateStudentLevel(short studentId, short newLevel) {
        Student s = findStudentById(studentId);
        if (s == null) {
            System.out.println("No student found with ID " + studentId);
            return;
        }
        s.setLevel(newLevel);
        System.out.println("Level updated successfully.");
    }

    public void updateCourseScore(short studentId, String courseCode, double newScore) {
        Student s = findStudentById(studentId);
        if (s == null) {
            System.out.println("No student found with ID " + studentId);
            return;
        }
        for (Course c : s.getCourses()) {
            if (c.getCourseCode().equalsIgnoreCase(courseCode)) {
                c.setScore(newScore);
                System.out.println("Score updated successfully. Recalculate CGPA to see the change.");
                return;
            }
        }
        System.out.println("No course with code " + courseCode + " found for this student.");
    }
}
