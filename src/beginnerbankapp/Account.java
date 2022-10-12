package beginnerbankapp;

import java.util.Scanner;

/**
 * The Account class holds customer data and basic terminal transactions.
 */
public class Account {
    // Menu constants
    private static final char CHECK_BALANCE = 'A';
    private static final char DEPOSIT_AMOUNT = 'B';
    private static final char WITHDRAW_AMOUNT = 'C';
    private static final char PREVIOUS_TRANSACTION = 'D';
    private static final char INTEREST_PROJECTION = 'E';
    private static final char EXIT_ACCOUNT = 'F';

    // Customer variables
    private int balance;
    private int previousTransaction;
    private final String customerName;
    private final int customerID;

    Account(String customerName, int customerID) {
        this.customerName = customerName;
        this.customerID = customerID;
    }

    /* deposit() and withdraw() functions do not support cents, and will not execute if input numbers are invalid:
     * Negative or zero amount
     * For withdrawals, amount greater than available balance
     */

    /** Attempt to deposit set amount to account balance. */
    private void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
            previousTransaction = amount;
        }
    }

    /** Attempt to withdraw set amount from available account balance. */
    private void withdraw(int amount) {
        if (amount > 0 && amount <= balance) {
            // Execute only if withdraw amount is within balance, non-negative, and not zero.
            balance -= amount;
            previousTransaction = -amount;
        }
    }

    /**
     * Deposit defined by a positive amount held in the previousTransaction variable.
     * Withdraw defined by a negative amount held in the previousTransaction variable.
     * Doesn't return anything, merely prints messages to the terminal.
     */
    private void getPreviousTransaction() {
        if (previousTransaction > 0) {
            System.out.println("Deposited: " + previousTransaction);
        } else if (previousTransaction < 0) {
            System.out.println("Withdrawn: " + Math.abs(previousTransaction));
        } else {
            System.out.println("No transaction occurred.");
        }
    }

    /** Print to user future balance projection based on current interest rate and set time-period in years. */
    private void calculateInterest(int years) {
        double interestRate = 0.0185;
        double newBalance = (balance * interestRate * years) + balance;
        System.out.println("Current interest rate is " + (100 * interestRate) + "%");
        System.out.println("After " + years + " years, your balance will be: " + newBalance);
    }

    /** Single exposed interactive function */
    public void showMenu() {
        char option;
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome, " + customerName + "!");
        System.out.println("Your ID is: " + customerID);
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println();
        System.out.println("""
                A. Check your balance
                B. Make a deposit
                C. Make a withdrawal
                D. View previous transaction
                E. Calculate interest
                F. Exit""");

        do {
            System.out.println();
            System.out.println("Enter an option: ");
            char option1 = sc.next().charAt(0);
            option = Character.toUpperCase(option1);
            System.out.println();

            switch (option) {
                case CHECK_BALANCE -> {
                    System.out.println("BALANCE");
                    System.out.println("--------------------");
                    System.out.println("Balance = $" + balance);
                    System.out.println("--------------------");
                }
                case DEPOSIT_AMOUNT -> {
                    System.out.println("DEPOSIT");
                    System.out.println("Enter amount to deposit: ");
                    int depositAmount = sc.nextInt();
                    deposit(depositAmount);
                    System.out.println();
                }
                case WITHDRAW_AMOUNT -> {
                    System.out.println("WITHDRAW");
                    System.out.println("Enter amount to withdraw: ");
                    int withdrawAmount = sc.nextInt();
                    withdraw(withdrawAmount);
                    System.out.println();
                }
                case PREVIOUS_TRANSACTION -> {
                    System.out.println("--------------------");
                    getPreviousTransaction();
                    System.out.println("--------------------");
                    System.out.println();
                }
                case INTEREST_PROJECTION -> {
                    System.out.println("INTEREST");
                    System.out.println("Enter years of accrued interest: ");
                    int years = sc.nextInt();
                    calculateInterest(years);
                }
                case EXIT_ACCOUNT -> System.out.println("EXIT");
                default -> System.out.println("ERROR: Invalid option. Please enter A-F only.");
            }
        } while (option != EXIT_ACCOUNT);
        System.out.println("Exiting account interface.");
    }

    public int getCustomerID() {
        return customerID;
    }

    public void printBasicInfo() {
        System.out.println("Account name: " + customerName);
        System.out.println("Account ID: " + customerID);
    }
}
