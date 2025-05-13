package services;

import models.User;
import java.util.HashMap;
import java.util.Map;

public class UserService {
    private Map<String, User> users = new HashMap<>();

    public UserService() {
        // Add default admin
        users.put("admin", new User("admin", "admin", "admin"));
    }

    public boolean signup(String username, String password, String role) {
        if (users.containsKey(username)) {
            return false;
        }
        users.put(username, new User(username, password, role));
        return true;
    }

    public User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
