import mypack.MyPackageClass;

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
    int df = 20;
    public static void main(String[] args) {
        EnumMain hy = new EnumMain();
        AppStatus currentStatus = AppStatus.STOP;
        System.out.println(currentStatus.getMessage());
        currentStatus.performAction();
        System.out.println(hy.df);
    }
}