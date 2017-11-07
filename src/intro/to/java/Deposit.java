package intro.to.java;

import java.util.Scanner;

public class Deposit implements Transaction {

    private UserAccount account;
    private Scanner scanner = new Scanner(System.in);

    private Deposit() {}

    public Deposit(UserAccount account) {
        this.account = account;
    }

    @Override
    public void displayMenu() {
        System.out.println("Please enter the deposit amount including cents (but omit the decimal point) or simply enter 0 to cancel the transaction");
        String choice = scanner.next();

        if (choice.equals("0")){
            ATM.instance.displayMainMenu(account);
        }

        Double amount = amountAsDouble(choice);
        if(amount == null) {
            System.out.println("You must enter a number\n");
            ATM.waitASecond();
            displayMenu();
        } else if (amount < 0) {
            System.out.println("You must enter a POSITIVE number to deposit\n");
            ATM.waitASecond();
            displayMenu();
        } else {
            System.out.println("Insert envelope containing cash\n");
            ATM.waitASecond();

            boolean insertedEnvelope = true;
            if(insertedEnvelope) {
                transact(amount);
                completedMessage();
            }
        }
    }

    public Double amountAsDouble(String choice) {
        try {
            return Double.parseDouble(choice) / 100;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public void transact(double amount) {
        account.balance += amount;
        ATM.instance.cashInAtm += amount;
    }

    @Override
    public void completedMessage() {
        System.out.println("Deposit completed. Thank you\n");

        ATM.waitASecond();
        ATM.instance.displayMainMenu(account);
    }
}
