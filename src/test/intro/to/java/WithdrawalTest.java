package test.intro.to.java;

import intro.to.java.ATM;
import intro.to.java.UserAccount;
import intro.to.java.Withdrawal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WithdrawalTest {

    @Test
    void transact() {
        // Reset the atm's cash so that the other tests can't interfere with our expectations
        ATM.instance.cashInAtm = 10000;

        UserAccount account = new UserAccount("00001", "00001", 200);
        Withdrawal withdrawal = new Withdrawal(account);

        withdrawal.transact(200);

        assertEquals(account.balance, 0);
        assertEquals(ATM.instance.cashInAtm, 9800);
    }

    @Test
    void enoughInAccount() {
        UserAccount account = new UserAccount("00001", "00001", 200);
        Withdrawal withdrawal = new Withdrawal(account);

        assertFalse(withdrawal.enoughInAccount(250));
        assertTrue(withdrawal.enoughInAccount(50));
    }

    @Test
    void enoughInATM() {
        Withdrawal withdrawal = new Withdrawal(new UserAccount("00001", "00001", 20));
        ATM.instance.cashInAtm = 10;

        assertFalse(withdrawal.enoughInATM(250));
        assertTrue(withdrawal.enoughInATM(5));
    }
}