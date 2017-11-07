package intro.to.java;



public class UserAccount {
    public String accountNumber;
    public String pin;
    public double balance;

    private UserAccount() {}

    public UserAccount(String accountNumber, String pin, double balance) throws IllegalArgumentException {
        if (!pin.matches(ATM.pattern) || !accountNumber.matches(ATM.pattern)) {
            throw new IllegalArgumentException("accountNumber and pin must be 5 digits each");
        }

        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }
}
