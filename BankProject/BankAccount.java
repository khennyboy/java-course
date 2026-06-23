import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private String ownerName;
    private double balance;
    private List<Transaction> transactions;

    // Constructor — runs when you do: new BankAccount("Sheriff", 0)
    public BankAccount(String ownerName, double initialBalance) {
        this.ownerName = ownerName;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return; // stop early — don't allow bad input
        }
        balance += amount;
        transactions.add(new Transaction("DEPOSIT", amount));
        System.out.printf("Deposited ₦%.2f. New balance: ₦%.2f%n", amount, balance);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds.");
            return;
        }
        balance -= amount;
        transactions.add(new Transaction("WITHDRAWAL", amount));
        System.out.printf("Withdrew ₦%.2f. New balance: ₦%.2f%n", amount, balance);
    }

    public void showBalance() {
        System.out.printf("Account: %s | Balance: ₦%.2f%n", ownerName, balance);
    }

    public void showHistory() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        System.out.println("--- Transaction History ---");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
}