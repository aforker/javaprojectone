package test.intro.to.java;

import intro.to.java.ATM;
import intro.to.java.Deposit;
import intro.to.java.UserAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepositTest {

    @Test
    void transact() {
        // Reset the atm's cash so that the other tests can't interfere with our expectations
        ATM.instance.cashInAtm = 10000;

        UserAccount account = new UserAccount("00001", "00001", 200);
        Deposit deposit = new Deposit(account);

        deposit.transact(30);

        assertEquals(account.balance, 230);
        assertEquals(ATM.instance.cashInAtm, 10030);
    }

    @Test
    void amountAsDouble() {
        UserAccount account = new UserAccount("00001", "00001", 200);
        Deposit deposit = new Deposit(account);

        // Should be $50 because the last two digits are the decimals
        double amount = deposit.amountAsDouble("5000");
        assertEquals(amount, 50.0);
    }
}