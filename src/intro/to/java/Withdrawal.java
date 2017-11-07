package intro.to.java;

import java.util.Scanner;

public class Withdrawal implements Transaction {

    private Scanner scanner = new Scanner(System.in);
    private UserAccount account;

    private Withdrawal() {}

    public Withdrawal(UserAccount account) {
        this.account = account;
    }

    @Override
    public void displayMenu() {
        System.out.println("How much would you like to withdraw? Please choose a number.\n" +
                "1 - $20\n" +
                "2 - $40\n" +
                "3 - $60\n" +
                "4 - $100\n" +
                "5 - $200\n" +
                "6 - Cancel");
        String choice = scanner.next();

        double amount;
        switch(choice){
            case "1":
                amount = 20.0;
                break;
            case "2":
                amount = 40.0;
                break;
            case "3":
                amount = 60.0;
                break;
            case "4":
                amount = 100.0;
                break;
            case "5":
                amount = 200.0;
                break;
            case "6":
                System.out.println("Transaction canceled\n");
                ATM.waitASecond();
                ATM.instance.displayMainMenu(account);
                return;
            default:
                System.out.println("Invalid selection. Try again\n");
                ATM.waitASecond();
                displayMenu();
                return;
        }

        if (!enoughInAccount(amount)) {
            System.out.println("You do not have enough funds to complete this withdrawal. Please choose a different amount.\n");
            displayMenu();
        } else if (!enoughInATM(amount)) {
            System.out.println("Error: ATM does not have enough funds to complete this transaction\n");
            displayMenu();
        } else {
            transact(amount);
            completedMessage();
        }
    }

    public boolean enoughInAccount(double amount) {
        return (account.balance - amount) >= 0;
    }

    public boolean enoughInATM(double amount) {
        return (ATM.instance.cashInAtm - amount) >= 0;
    }

    @Override
    public void transact(double amount) {
        account.balance -= amount;
        ATM.instance.cashInAtm -= amount;
    }

    @Override
    public void completedMessage() {
        System.out.println("Remember to take your cash. Thank you!\n");

        ATM.waitASecond();
        ATM.instance.displayMainMenu(account);
    }
}
