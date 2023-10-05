package onlineshopping.views;

import onlineshopping.interfaces.Messenger;

public class Authentication extends Messenger {
    public void showAuthScreen() {
        println("\nWelcome to the Online Shopping Console App!");
        println("[1] Login");
        println("[2] Register");
        println("[0] Exit");
    }

    public void showOutro() {
        println("\n-------- Exit --------");
    }

    public void showLogin() {
        println("\n-------- Login --------");
    }

    public void showRegistration() {
        println("\n-------- Registration --------");
    }

    public void showUsername() {
        print("Enter username: ");
    }

    public void showPassword() {
        print("Enter password: ");
    }

    public void showConfirmPassword() {
        print("Confirm password: ");
    }

    public void showLoginFailed() {
        systemMessage("Check your username and password.");
    }

    public void showRegistrationFailed() {
        systemMessage("Registration failed. Passwords does not match.");
    }

    public void showLoginAttemptCount(int remainingAttempt) {
        systemMessage("You have " + remainingAttempt + " login attempt(s) left.");
    }

    public void showRegistrationAttemptCount(int remainingAttempt) {
        systemMessage("You have " + remainingAttempt + " registration attempt(s) left.");
    }

    public void warnMaxLoginAttempt() {
        systemMessage("You have reached the maximum login attempt.");
    }

    public void warnMaxRegistrationAttempt() {
        systemMessage("You have reached the maximum registration attempt.");
    }

    public void warnDuplicateRegistration() {
        systemMessage("Username already exists.");
    }
}
