import java.util.Scanner;

public class BankApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        BankAccount account = new BankAccount(name, 0);

        System.out.println("Welcome, " + name + "!");

        boolean running = true;
        while (running) {

            // Show the menu every iteration
            System.out.println("\n--- MENU ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check balance");
            System.out.println("4. Transaction history");
            System.out.println("5. Quit");
            System.out.print("Choose: ");

            String choice = scanner.nextLine();

            // React to what the user picked
            switch (choice) {
                case "1":
                    System.out.print("Amount to deposit: ₦");
                    double dep = Double.parseDouble(scanner.nextLine());
                    account.deposit(dep);
                    break;
                case "2":
                    System.out.print("Amount to withdraw: ₦");
                    double wit = Double.parseDouble(scanner.nextLine());
                    account.withdraw(wit);
                    break;
                case "3":
                    account.showBalance();
                    break;
                case "4":
                    account.showHistory();
                    break;
                case "5":
                    System.out.println("Goodbye!");
                    running = false; // exits the while loop
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }
}