package studentracker;

public class Course {
    private String name;
    private int unit;
    private int score;

    public Course(String name, int unit, int score) {
        this.name = name;
        this.unit = unit;
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

    public int getUnit() {
        return this.unit;
    }
}
