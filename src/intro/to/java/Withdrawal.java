package intro.to.java;

public class Withdrawal implements Transaction {

    @Override
    public void displayMenu() {

    }

    @Override
    public void transact() {

    }

    @Override
    public void updateAtmBalance() {

    }

    @Override
    public void completedMessage() {
        // Todo - show completed message and then:

        ATM.waitASecond();
        ATM.instance.displayMainMenu();
    }
}
