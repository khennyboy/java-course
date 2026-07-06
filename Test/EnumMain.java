import java.util.ArrayList;
import java.util.List;

interface RunnableTask {
    void performAction();
}

enum AppStatus implements RunnableTask {
    START("Starting up") {
        public void performAction() {
            System.out.println("Booting up the application");
        }
    },
    STOP("Shutting down") {
        public void performAction() {
            System.out.println("Shutting down the application");
        }
    };

    private final String message;

    AppStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

class EnumMain {

    public static void main(String[] args) {
        EnumMain hy = new EnumMain();
        List<String> stringList = new ArrayList<>();

        AppStatus currentStatus = AppStatus.STOP;
        System.out.println(currentStatus.getMessage());
        currentStatus.performAction();
        stringList.add("Bayo1");
        stringList.add("Bayo2");
        stringList.add("Bayo3");
        stringList.add("Bayo4");
        stringList.remove(1);
        stringList.remove(String.valueOf("Bayo3"));
        for (String i : stringList) {
            System.out.println(i);
        }
    }
}