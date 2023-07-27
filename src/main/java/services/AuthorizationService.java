package services;

import model.Role;
import model.User;
import util.CustomHttpMethod;

public class AuthorizationService {
    public boolean hasAccess(User user, CustomHttpMethod httpMethod, String requestUri) {
        Role userRole = user.getRole();
        switch (httpMethod) {
            case GET:
                return true; // Доступ для всіх користувачів
            case POST:
            case PUT:
            case DELETE:
                return userRole == Role.ADMIN; // Доступ тільки для користувачів з роллю Admin
            default:
                return false; // Всі інші запити не допускаються
        }
    }


}
