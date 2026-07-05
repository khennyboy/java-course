package studentracker;

import java.util.List;

public class StudentManager {
    private List<Student> students;

    void addStundent(Student x) {
        students.add(x);
        System.out.println('Student %s added successfully!', x.getName());
    }

    void listAllStudents() {
        for (Student x : students) {
            System.out.println(x);
        }
    }

    void findStudent(String searchId) {
        for (Student x : students) {
            if (x.getId() == searchId) {
                System.out.printf("Student with id %d is %s", x.getId(), x.getName());
                break;
            }
        }
    }
}
