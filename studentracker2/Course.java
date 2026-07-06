package studentracker2;

public class Course {
    private String courseCode;
    private int unit;
    private double score;

    public Course(String courseCode, int unit, double score) {
        this.courseCode = courseCode;
        this.unit = unit;
        this.score = score;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public double getScore() {
        return score;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
