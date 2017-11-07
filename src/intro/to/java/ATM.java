package intro.to.java;

import java.util.Scanner;


public class ATM {

    public double cashInAtm = 10000;
    static final String pattern = "^[0-9]{5}$";
    private Scanner scanner = new Scanner(System.in);

    private ATM() {}

    // Create a singleton of ATM so that only have one instance with one cash field for easy tracking
    public static ATM instance = new ATM();

    void begin() {
        System.out.println(
                "==================================================\n" +
                "                      Welcome                     \n" +
                "==================================================\n" +
                "\nPlease enter your 5 digit account number:\n"
        );

        String accountNumber = scanner.next();

        boolean accountValid = verifyAccountNumber(accountNumber);

        if(!accountValid) {
            System.out.println("Account number was incorrect. Must be 5 digits. Please try again\n");
            waitASecond();
            begin();
        } else {
            UserAccount enteredAccount = Database.instance.selectFromUsers(accountNumber);
            askForPin(enteredAccount);
        }
    }

    public boolean verifyAccountNumber(String accountNumber) {
        UserAccount enteredAccount = Database.instance.selectFromUsers(accountNumber);

        return enteredAccount != null && accountNumber.trim().matches(pattern);
    }

    private void askForPin(UserAccount account) {
        System.out.println("\nPlease enter your pin (5 digits):\n");
        String pin = scanner.next().trim();

        boolean valid = validatePin(pin, account);

        if(!valid) {
            System.out.println("Incorrect pin. Must be 5 digits. Please try again\n");
            waitASecond();
            askForPin(account);
        } else {
            displayMainMenu(account);
        }
    }

    public boolean validatePin(String pin, UserAccount account) {
        return pin != null && pin.matches(pattern) && pin.equals(account.pin);
    }

    void displayMainMenu(UserAccount account) {
        System.out.println("What would you like to do? Please choose a number.\n" +
                "1 - View Balance\n" +
                "2 - Withdrawal\n" +
                "3 - Deposit\n" +
                "4 - Exit");

        String choice = scanner.next();

        switch (choice) {
            case "1":
                System.out.println("Your balance is: " + account.balance + "\n");
                waitASecond();
                displayMainMenu(account);
                break;
            case "2":
                Withdrawal withdrawal = new Withdrawal(account);
                withdrawal.displayMenu();
                break;
            case "3":
                Deposit deposit = new Deposit(account);
                deposit.displayMenu();
                break;
            case "4":
                System.out.println("Thank you.\n");
                begin();
                break;
            default:
                System.out.println("Your choice did not match any options. Please try again\n");
                waitASecond();
                displayMainMenu(account);
                break;
        }
    }

    static void waitASecond() {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
