package intro.to.java;

import java.math.BigDecimal;

class UserAccount {
    String accountNumber;
    String pin;
    BigDecimal balance;

    private UserAccount() {}

    UserAccount(String accountNumber, String pin, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }
}
