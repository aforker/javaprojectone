package intro.to.java;

import java.math.BigDecimal;
import java.util.Scanner;


class ATM {

    BigDecimal cash = new BigDecimal(500);

    private Scanner scanner = new Scanner(System.in);
    private final String accountNumPattern = "^[0-9]{8}$";
    private final String pinPattern = "^[0-9]{4}$";

    private ATM() {}

    // Create a singleton of ATM so that only have one instance with one cash field for easy tracking
    static ATM instance = new ATM();

    void begin() {
        System.out.println(
                "==================================================\n" +
                "                      Welcome                     \n" +
                "==================================================\n" +
                "\nPlease enter your 8 digit account number:\n"
        );

        String accountNumber = scanner.next();
        UserAccount account = Database.instance.selectFromUsers(accountNumber);

        if (!isAccount(accountNumber)) {
            System.out.println("Incorrect format: account number must be 8 digits\n");
            begin();
        } else if (account == null) {
            System.out.println("Account number was incorrect. Please try again\n");
            begin();
        } else {
            askForPin(account);
        }
    }

    private boolean isAccount(String accountNum) {
        return accountNum.trim().matches(accountNumPattern);
    }

    private void askForPin(UserAccount account) {
        System.out.println("Account found. Please enter your pin (4 digits):\n");

        String pin = scanner.next().trim();

        if (!pin.matches(pinPattern)){
            System.out.println("Incorrect pin format. Must be 4 digits.\n");
            askForPin(account);
        } else if (!pin.equals(account.pin)) {
            System.out.println("Pin incorrect. Please try again.");
            askForPin(account);
        } else {
            displayOptions(account);
        }
    }

    private void displayOptions(UserAccount account) {
        System.out.println("What would you like to do? Please choose a number.\n" +
                "1 - View Balance\n" +
                "2 - Withdrawal\n" +
                "3 - Deposit\n" +
                "4 - Exit");

        String choice = scanner.next();

        switch (choice) {
            case "1":
                System.out.println("Your balance is: " + account.balance);
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
                begin();
             default:
                 System.out.println("Your choice did not match any options. Please try again\n");
                 displayOptions(account);
        }
    }
}
