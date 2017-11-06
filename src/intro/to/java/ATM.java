package intro.to.java;

import java.math.BigDecimal;
import java.util.Scanner;


class ATM {

    static UserAccount currentAccount;
    BigDecimal cash = new BigDecimal(500);
    private Scanner scanner = new Scanner(System.in);
    private final String pattern = "^[0-9]{5}$";
    private final String lineSeparator = "\n_____________________________________________\n\n";

    private ATM() {}

    // Create a singleton of ATM so that only have one instance with one cash field for easy tracking
    static ATM instance = new ATM();

    void begin() {
        System.out.println(
                "==================================================\n" +
                "                      Welcome                     \n" +
                "==================================================\n" +
                "\nPlease enter your 5 digit account number:\n"
        );

        String accountNumber = scanner.next();
        UserAccount enteredAccount = Database.instance.selectFromUsers(accountNumber);

        if (!isAccount(accountNumber)) {
            System.out.println("Incorrect format: account number must be 5 digits\n");
            begin();
        } else if (enteredAccount == null) {
            System.out.println("Account number was incorrect. Please try again\n");
            begin();
        } else {
            askForPin(enteredAccount);
        }
    }

    private boolean isAccount(String accountNum) {
        return accountNum.trim().matches(pattern);
    }

    private void askForPin(UserAccount account) {
        System.out.println("\nPlease enter your pin (5 digits):\n");

        String pin = scanner.next().trim();

        if (!pin.matches(pattern)){
            System.out.println("Incorrect pin format. Must be 5 digits.\n");
            askForPin(account);
        } else if (!pin.equals(account.pin)) {
            System.out.println("Pin incorrect. Please try again.");
            askForPin(account);
        } else {
            currentAccount = account;
            displayMainMenu();
        }
    }

    void displayMainMenu() {
        System.out.println("What would you like to do? Please choose a number.\n" +
                "1 - View Balance\n" +
                "2 - Withdrawal\n" +
                "3 - Deposit\n" +
                "4 - Exit");

        String choice = scanner.next();

        switch (choice) {
            case "1":
                System.out.println("Your balance is: " + currentAccount.balance + "\n");
                waitASecond();
                displayMainMenu();
                break;
            case "2":
                Withdrawal withdrawal = new Withdrawal();
                withdrawal.displayMenu();
                break;
            case "3":
                Deposit deposit = new Deposit();
                deposit.displayMenu();
                break;
            case "4":
                System.out.println("Thank you.\n");
                currentAccount = null;
                begin();
                break;
            default:
                System.out.println("Your choice did not match any options. Please try again\n");
                waitASecond();
                displayMainMenu();
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
