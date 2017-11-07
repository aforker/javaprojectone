package test.intro.to.java;

import intro.to.java.Database;
import intro.to.java.UserAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    Database db = Database.instance;

    @Test
    void selectFromUsers() {
        assertNull(db.selectFromUsers(""));
        assertNull(db.selectFromUsers("000000"));
        assertNull(db.selectFromUsers(null));
        assertNotNull(db.selectFromUsers("00002"));
    }
}