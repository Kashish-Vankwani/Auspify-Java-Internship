import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Step 1: Account Class
class Account {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private List<String> transactionHistory;

    // Constructor
    public Account(String accountNumber, String accountHolderName, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with initial deposit: $" + initialDeposit);
    }

    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    // Step 2: Deposit Logic
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Invalid amount! Deposit amount must be greater than zero.");
            return;
        }
        balance += amount;
        transactionHistory.add("Deposited: $" + amount + " | Current Balance: $" + balance);
        System.out.println("✅ Successfully deposited $" + amount);
    }

    // Step 2 & 4: Withdrawal Logic with Validation
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Invalid amount! Withdrawal amount must be greater than zero.");
            return;
        }
        if (amount > balance) {
            System.out.println("❌ Insufficient Balance! Your balance is: $" + balance);
            return;
        }
        balance -= amount;
        transactionHistory.add("Withdrew: $" + amount + " | Current Balance: $" + balance);
        System.out.println("✅ Successfully withdrew $" + amount);
    }

    // Step 3: Account Details & Balance
    public void displayAccountInfo() {
        System.out.println("\n---------------------------------");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + accountHolderName);
        System.out.println("Current Balance: $" + balance);
        System.out.println("---------------------------------");
    }

    // Step 5: Transaction History
    public void showTransactionHistory() {
        System.out.println("\n--- Transaction History for Account: " + accountNumber + " ---");
        for (String record : transactionHistory) {
            System.out.println("- " + record);
        }
    }
}

// Main Banking Application Class
public class BankManagementSystem {
    private static List<Account> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=================================");
            System.out.println("     BANK MANAGEMENT SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Create New Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance / Details");
            System.out.println("5. View Transaction History");
            System.out.println("6. Exit");
            System.out.print("Select an option (1-6): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    performDeposit();
                    break;
                case 3:
                    performWithdrawal();
                    break;
                case 4:
                    checkAccountInfo();
                    break;
                case 5:
                    viewHistory();
                    break;
                case 6:
                    running = false;
                    System.out.println("\nThank you for using Bank Management System!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select between 1 and 6.");
            }
        }
        scanner.close();
    }

    private static void createAccount() {
        System.out.println("\n--- Create Account ---");
        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine();

        // Duplicate check
        if (findAccount(accNum) != null) {
            System.out.println("❌ Account number already exists!");
            return;
        }

        System.out.print("Enter Account Holder Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Initial Deposit: $");
        double initialDeposit = scanner.nextDouble();

        if (initialDeposit < 0) {
            System.out.println("❌ Initial deposit cannot be negative.");
            return;
        }

        accounts.add(new Account(accNum, name, initialDeposit));
        System.out.println("✅ Account created successfully!");
    }

    private static void performDeposit() {
        Account acc = getAccountInput();
        if (acc != null) {
            System.out.print("Enter Amount to Deposit: $");
            double amount = scanner.nextDouble();
            acc.deposit(amount);
        }
    }

    private static void performWithdrawal() {
        Account acc = getAccountInput();
        if (acc != null) {
            System.out.print("Enter Amount to Withdraw: $");
            double amount = scanner.nextDouble();
            acc.withdraw(amount);
        }
    }

    private static void checkAccountInfo() {
        Account acc = getAccountInput();
        if (acc != null) {
            acc.displayAccountInfo();
        }
    }

    private static void viewHistory() {
        Account acc = getAccountInput();
        if (acc != null) {
            acc.showTransactionHistory();
        }
    }

    // Utility helper method
    private static Account getAccountInput() {
        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine();
        Account acc = findAccount(accNum);
        if (acc == null) {
            System.out.println("❌ Account not found!");
        }
        return acc;
    }

    private static Account findAccount(String accNum) {
        for (Account a : accounts) {
            if (a.getAccountNumber().equalsIgnoreCase(accNum)) {
                return a;
            }
        }
        return null;
    }
}