package test.intro.to.java;

import org.junit.jupiter.api.Test;
import intro.to.java.*;


import static org.junit.jupiter.api.Assertions.*;

class UserAccountTest {

    @Test
    void UserAccount() {
        UserAccount goodUser = new UserAccount("00006", "00006", 200);

        assertEquals(goodUser.accountNumber, "00006");
        assertEquals(goodUser.pin, "00006");
        assertEquals(goodUser.balance, 200);

        assertThrows(IllegalArgumentException.class, () -> new UserAccount("k", "1234567", 300));
    }
}