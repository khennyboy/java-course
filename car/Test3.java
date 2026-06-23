package car;

public class Test3 {

    static Car car = new Car();

    public void printCarDetails() {
        System.out.println(car.brand);
    }

    public static void main(String[] args) {
        Test3 test = new Test3();
        test.printCarDetails();
        System.out.println(car.brand);
    }
}
