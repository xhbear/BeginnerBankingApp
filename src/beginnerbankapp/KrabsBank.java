package beginnerbankapp;

import java.util.LinkedList;
import java.util.Scanner;

/** The KrabsBank singleton class is the bank object in the program that provides account creation service. */
public class KrabsBank {

    // Bank service constants
    private static final int CREATE_ACCOUNT = 1;
    private static final int ACCESS_ACCOUNT = 2;
    private static final int PRINT_ALL = 3;
    private static final int EXIT = 4;
    Scanner sc = new Scanner(System.in);
    private static KrabsBank INSTANCE = null;
    private final LinkedList<Account> accounts = new LinkedList<>();
    private RecordHolder recordHolder = RecordHolder.getInstance();
    private KrabsBank(){}

    public static KrabsBank getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new KrabsBank();
        }
        return INSTANCE;
    }

    public void createAccount() {
        System.out.println("Welcome to Krabs Bank!");
        System.out.println("Please input name: ");
        String customerName = sc.next();
        Account newAccount;
        if (customerName == null) {
            newAccount = new Account("Eugene Harold Krabs", recordHolder.registerCustomerId());
        } else {
            newAccount = new Account(customerName, recordHolder.registerCustomerId());
        }
        accounts.add(newAccount);
        System.out.println("Account created!");
        System.out.println("Your account ID is: " + newAccount.getCustomerID());
        System.out.println();
    }

    /** Access customer menu based on corresponding ID. */
    public void accessAccountCommands() {
        int acctId = sc.nextInt();
        for (Account account : accounts) {
            if (account.getCustomerID() == acctId) {
                account.showMenu();
                break;
            }
        }
        System.out.println("No such account exists!");
    }

    public void printAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts recorded.");
        } else {
            for (Account account : accounts) {
                account.printBasicInfo();
            }
        }
    }

    /** Access Krabs Bank services */
    public void bankService() {
        int command = 0;
        while (command != 4) {
            Scanner sc = new Scanner(System.in);
            System.out.println("""
                    1 - Create account
                    2 - Access account by ID
                    3 - Print all accounts in bank(authorized users only)
                    4 - Exit bank
                    """);
            command = sc.nextInt();

            switch (command) {
                case CREATE_ACCOUNT -> createAccount();
                case ACCESS_ACCOUNT -> accessAccountCommands();
                case PRINT_ALL -> printAllAccounts();
                case EXIT -> System.out.println("EXIT");
                default -> System.out.println("ERROR: Invalid input. Please enter 1-3 only.");
            }
        }
        System.out.println("Thank you for banking with Krabs Bank!");
    }
}
