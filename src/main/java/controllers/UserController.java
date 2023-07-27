package controllers;

import dao.UserRegistry;
import model.Role;
import model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.AuthenticationService;
import services.AuthorizationService;
import util.CustomHttpMethod;
import util.PasswordUtils;

import javax.servlet.http.HttpServletRequest;

import static token.tokenUtils.validateTokenAndGetUser;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRegistry userRegistry;
    private final AuthenticationService authenticationService;
    private final AuthorizationService authorizationService;

    public UserController(UserRegistry userRegistry,
                          AuthenticationService authenticationService,
                          AuthorizationService authorizationService) {
        this.userRegistry = userRegistry;
        this.authenticationService = authenticationService;
        this.authorizationService = authorizationService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (userRegistry.getUserByUsername(user.getUsername()) != null) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }

        user.setPasswordHash(PasswordUtils.hashPassword(user.getPasswordHash()));

        userRegistry.registerUser(user);

        return ResponseEntity.ok("User registered successfully");
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        User user = authenticationService.authenticate(username, password);
        if (user != null) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable int userId, HttpServletRequest request) {
        User authenticatedUser = getAuthenticatedUser(request);
        if (authenticatedUser != null && authorizationService.hasAccess(authenticatedUser, CustomHttpMethod.GET, request.getRequestURI())) {
            User user = userRegistry.getUserById(userId);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
    private User getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            return null;
        }

        User authenticatedUser = validateTokenAndGetUser(token);

        return authenticatedUser;
    }

    public boolean hasAccess(User user, CustomHttpMethod httpMethod, String requestUri) {
        Role userRole = user.getRole();
        switch (httpMethod) {
            case GET:
                return true; // All users will have access to get requests
            case POST:
            case PUT:
            case DELETE:
                return userRole == Role.ADMIN; //Only admin will have the ability to delete requests
            default:
                return false;
        }
    }
}
