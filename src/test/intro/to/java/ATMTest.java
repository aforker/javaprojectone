package test.intro.to.java;

import org.junit.jupiter.api.Test;
import intro.to.java.*;

import static org.junit.jupiter.api.Assertions.*;

class ATMTest {

    private ATM atm = ATM.instance;

    @Test
    void handleUserInput() {
        assertFalse(atm.verifyAccountNumber("123456"));
        assertFalse(atm.verifyAccountNumber(null));
        assertTrue(atm.verifyAccountNumber("00001"));
    }


    @Test
    void validatePin() {
        UserAccount account = Database.instance.selectFromUsers("00001");
        assertFalse(atm.validatePin("jfks", account));
        assertFalse(atm.validatePin("", account));
        assertFalse(atm.validatePin(null, account));
        assertTrue(atm.validatePin("00001", account));
    }
}