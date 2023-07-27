package dao;

import model.User;
import java.util.HashMap;
import java.util.Map;

public class UserRegistry {
    private Map <String, User > users = new HashMap <> ();

    public boolean registerUser(User user) {
        if (!users.containsKey(user.getUsername())) {
            users.put(user.getUsername(), user);
            return true;
        }
        return false;
    }

    public User getUserByUsername(String username) {
        return users.get(username);
    }

    public User getUserById(int userId) {
        return null;

    }
}
