package intro.to.java;

import java.math.BigDecimal;
import java.util.HashMap;

public class Database {

    private UserAccount user1 = new UserAccount(
            "00000001", "0001", new BigDecimal("200"));
    private UserAccount user2 = new UserAccount(
            "00000002", "0002", new BigDecimal("550"));
    private UserAccount user3 = new UserAccount(
            "00000003", "0003", new BigDecimal("1500"));

    private HashMap<String, UserAccount> users = new HashMap<>();

    private Database() {
        users.put(user1.accountNumber, user1);
        users.put(user2.accountNumber, user2);
        users.put(user3.accountNumber, user3);
    }

    static Database instance = new Database();

    UserAccount selectFromUsers(String accountNumber) {
        return users.get(accountNumber);
    }
}
