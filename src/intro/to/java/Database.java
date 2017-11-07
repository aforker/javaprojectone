package intro.to.java;

import java.util.HashMap;

public class Database {

    private UserAccount user1 = new UserAccount(
            "00001", "00001", 200);
    private UserAccount user2 = new UserAccount(
            "00002", "00002", 550);
    private UserAccount user3 = new UserAccount(
            "00003", "00003", 1500);

    private HashMap<String, UserAccount> users = new HashMap<>();

    private Database() {
        users.put(user1.accountNumber, user1);
        users.put(user2.accountNumber, user2);
        users.put(user3.accountNumber, user3);
    }

    public static Database instance = new Database();

    public UserAccount selectFromUsers(String accountNumber) {
        return users.get(accountNumber);
    }
}
