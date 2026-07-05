package studentracker;

import java.util.List;

public class Student {
    private String name;
    private short id;
    private String department;
    private short level;
    private List<Course> courses;
    private double CGPA

    public Student(String name, short id, String department, short level) {
        this.name = name;
        this.id = id;
        this.department = department;
        this.level = level;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    public String toString() {
        return String.format("%s, [%d level] student studying %s", name, level, department);
    }

    public double CalculateCGPA() {
        for (Course course: courses){
            if (course.getScore()>=70){
                this.CGPA += course.getUnit()*5;
            }
            else if (course.getScore()>=60 && course.getScore()<70) {
                this.CGPA += course.getUnit()*4;
            } 
            else if (course.getScore()>=50 && course.getScore()<60) {
                this.CGPA += course.getUnit()*3
            }
            else if(course.getScore()>=45 && course.getScore()<50){
                this.CGPA += course.getUnit()*2
            }
            else if(course.getScore()>=40 && course.getScore()<45){
                this.CGPA += course.getUnit()*1
            }
            else {
                this.CGPA += course.getUnit()*0;
            }
        }
        this.CGPA /= this.courses.size();
        System.out.printf("Dear %s, your CGPA is %f", this.name, this.CGPA)
    };
}
