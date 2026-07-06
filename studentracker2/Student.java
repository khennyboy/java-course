package studentracker2;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentName;
    private short studentId;
    private String department;
    private short level;
    private List<Course> courses;
    private double cgpa;

    public Student(String studentName, short studentId, String department, short level) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.department = department;
        this.level = level;
        this.courses = new ArrayList<>();
        this.cgpa = 0.0;
    }

    public String getStudentName() {
        return studentName;
    }

    public short getId() {
        return studentId;
    }

    public String getDepartment() {
        return department;
    }

    public short getLevel() {
        return level;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public void setDepartment(String department) { this.department = department; }
    public void setLevel(short level) { this.level = level; }

    @Override
    public String toString() {
        return String.format("[%d] %s - %d level, %s (CGPA: %.2f)",
                studentId, studentName, level, department, cgpa);
    }
}
