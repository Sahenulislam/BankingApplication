import java.util.ArrayList;
import java.util.Scanner;

abstract class Account {
    protected String name;
    protected int number;
    protected String creationDate;
    protected double balance;
    protected int accountType;

    public Account(String name, int number, String creationDate, double minimumBalance, int accountType) {
        this.name = name;
        this.number = number;
        this.creationDate = creationDate;
        this.balance = minimumBalance;
        this.accountType = accountType;
    }

    public int getNumber() {
        return number;
    }

    public double getBalance() {
        return balance;
    }

    public abstract double getMinimumBalance();

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Account Number: " + number);
        System.out.println("Creation Date: " + creationDate);
        System.out.println("Balance: " + balance + " $");
    }
}

class CurrentAccount extends Account {
    static final double minimumBalance = 100;

    public CurrentAccount(String name, int number, String creationDate, double balance, int accountType) {
        super(name, number, creationDate, balance, accountType);
    }

    public void displayInfo() {
        System.out.println("\nCurrent Account Information:");
        super.displayInfo();
        System.out.println("Account Type: Current Account");
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }
}

class SavingsAccount extends Account {
    static final double minimumBalance = 50;

    public SavingsAccount(String name, int number, String creationDate, double balance, int accountType) {
        super(name, number, creationDate, balance, accountType);
    }

    public void displayInfo() {
        System.out.println("\nSaving Account Information:");
        super.displayInfo();
        System.out.println("Account Type: Saving Account");
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

}

class SalaryAccount extends Account {
    static final double minimumBalance = 10;

    public SalaryAccount(String name, int number, String creationDate, double balance, int accountType) {
        super(name, number, creationDate, balance, accountType);
    }

    public void displayInfo() {
        System.out.println("\nSalary Account Information:");
        super.displayInfo();
        System.out.println("Account Type: Salary Account");
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }
}

public class BankingApplication {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    static int accountNumber = 1010;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nBanking Application Menu:");
            System.out.println("1. Create a new account");
            System.out.println("2. Display all accounts");
            System.out.println("3. Update an account");
            System.out.println("4. Delete an account");
            System.out.println("5. Deposit an amount into your account");
            System.out.println("6. Withdraw an amount from your account");
            System.out.println("7. Search for account");
            System.out.println("8. Exit");
            System.out.print("Enter your choice (1-8): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    displayAllAccounts();
                    break;
                case 3:
                    updateAccount();
                    break;
                case 4:
                    deleteAccount();
                    break;
                case 5:
                    depositAmount();
                    break;
                case 6:
                    withdrawAmount();
                    break;
                case 7:
                    searchAccount();
                    break;
                case 8:
                    System.out.println("\nExiting the application. Goodbye!\n");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInvalid choice. Please enter a number between 1 and 8.");
            }
        }
    }

    private static void createAccount() {
        System.out.println("\nSelect Account Type:");
        System.out.println("1. Current Account");
        System.out.println("2. Savings Account");
        System.out.println("3. Salary Account");
        System.out.print("Enter your choice (1-3): ");
        int accountType = scanner.nextInt();
        scanner.nextLine();
        System.out.print("\nEnter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter creation date: ");
        String creationDate = scanner.next();
        System.out.print("Enter initial balance");

        if (accountType == 1) {
            System.out.print("(Minimum " + CurrentAccount.minimumBalance + "$ opeing Current Account): ");
        } else if (accountType == 2) {
            System.out.print("(Minimum " + SavingsAccount.minimumBalance + "$ opeing Current Account): ");
        } else {
            System.out.print("(Minimum " + SalaryAccount.minimumBalance + "$ opeing Current Account): ");
        }
        double initialBalance = scanner.nextDouble();

        if (accountType == 1) {
            if (initialBalance >= CurrentAccount.minimumBalance) {
                accountNumber++;
                CurrentAccount account = new CurrentAccount(name, accountNumber, creationDate, initialBalance,
                        accountType);
                accounts.add(account);
                System.out.println("\nCurrent Account created successfully.");
                System.out.println("\nYour Account number is: " + accountNumber);
            } else {
                System.out.println("\nAccount not Created Minimum balance " + CurrentAccount.minimumBalance
                        + "$ required for Current Account");
            }
        } else if (accountType == 2) {
            if (initialBalance >= SavingsAccount.minimumBalance) {
                accountNumber++;
                SavingsAccount account = new SavingsAccount(name, accountNumber, creationDate, initialBalance,
                        accountType);
                accounts.add(account);
                System.out.println("\nSavings Account created successfully.");
                System.out.println("\nYour Account number is: " + accountNumber);
            } else {
                System.out.println("\nAccount not Created Minimum balance " + SavingsAccount.minimumBalance
                        + "$ required for Savings Account");
            }
        } else if (accountType == 3) {
            if (initialBalance >= SalaryAccount.minimumBalance) {
                accountNumber++;
                SalaryAccount account = new SalaryAccount(name, accountNumber, creationDate, initialBalance,
                        accountType);
                accounts.add(account);
                System.out.println("\nSavings Account created successfully.");
                System.out.println("\nYour Account number is: " + accountNumber);
            } else {
                System.out.println("\nAccount not Created Minimum balance " + SalaryAccount.minimumBalance
                        + "$ required for Savings Account");
            }
        } else {
            System.out.println("Invalid choice.");
        }

    }

    private static void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("\nNo accounts found.");
        } else {
            for (Account account : accounts) {
                account.displayInfo();
            }
        }
    }

    private static void updateAccount() {
        System.out.print("\nEnter account number to update: ");
        int accountNumber = scanner.nextInt();

        for (Account account : accounts) {
            if (account.getNumber() == accountNumber) {
                scanner.nextLine();
                System.out.print("Enter new Name: ");
                String newName = scanner.nextLine();
                account.setName(newName);
                System.out.println("Account updated successfully!");
                return;
            }
        }

        System.out.println("\nAccount not found.");
    }

    private static void deleteAccount() {
        System.out.print("\nEnter account number to delete: ");
        int accountNumber = scanner.nextInt();

        for (Account account : accounts) {
            if (account.getNumber() == accountNumber) {
                accounts.remove(account);
                System.out.println("Account deleted successfully!");
                return;
            }
        }

        System.out.println("\nAccount not found.");
    }

    private static void depositAmount() {
        System.out.print("\nEnter account number to deposit into: ");
        int accountNumber = scanner.nextInt();

        for (Account account : accounts) {
            if (account.getNumber() == accountNumber) {
                System.out.print("Enter amount to deposit: ");
                double amount = scanner.nextDouble();
                account.setBalance(account.getBalance() + amount);
                System.out.println("\nDeposit successful!");
                return;
            }
        }

        System.out.println("\nAccount not found.");
    }

    private static void withdrawAmount() {
        System.out.print("\nEnter account number to withdraw from: ");
        int accountNumber = scanner.nextInt();

        for (Account account : accounts) {
            if (account.getNumber() == accountNumber) {
                System.out.print("Enter amount to withdraw: ");
                double amount = scanner.nextDouble();
                if (amount <= account.getBalance() - account.getMinimumBalance()) {
                    account.setBalance(account.getBalance() - amount);
                    System.out.println("\nWithdrawal successful!");
                } else {
                    System.out.println("\nInsufficient funds.");
                }
                return;
            }
        }

        System.out.println("\nAccount not found.");
    }

    private static void searchAccount() {
        System.out.print("\nEnter account number to search: ");
        int accountNumber = scanner.nextInt();

        for (Account account : accounts) {
            if (account.getNumber() == accountNumber) {
                account.displayInfo();
                return;
            }
        }

        System.out.println("\nAccount not found.");
    }
}
