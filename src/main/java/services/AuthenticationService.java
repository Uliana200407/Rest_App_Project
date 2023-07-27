package services;

import dao.UserRegistry;
import model.User;
import util.PasswordUtils;

public class AuthenticationService {
    private UserRegistry userRegistry;

    public AuthenticationService(UserRegistry userRegistry) {
        this.userRegistry = userRegistry;
    }

    public User authenticate(String username, String password) {
        User user = userRegistry.getUserByUsername(username);
        if (user != null && PasswordUtils.verifyPassword(password, user.getPasswordHash())) {
            return user; // Аутентифікація пройшла успішно
        }
        return null; // Помилка аутентифікації
    }
}
