package onlineshopping.views;

public class Authentication {
    public void showAuthScreen() {
        System.out.println("\nWelcome to the Online Shopping Console App!");
        System.out.println("[1] Login");
        System.out.println("[2] Register");
        System.out.println("[0] Exit");
    }

    public void showLogin() {
        System.out.println("\n-------- Login --------");
    }

    public void showRegistration() {
        System.out.println("\n-------- Registration --------");
    }

    public void showUsername() {
        System.out.print("Enter username: ");
    }

    public void showPassword() {
        System.out.print("Enter password: ");
    }

    public void showConfirmPassword() {
        System.out.print("Confirm password: ");
    }

    public void showLoginFailed() {
        System.out.println("[system] Check your username and password.");
    }

    public void showRegistrationFailed() {
        System.out.println("[system] Registration failed. Passwords does not match.");
    }

    public void showLoginAttemptCount(int remainingAttempt) {
        System.out.println("[system] You have " + remainingAttempt + " login attempt(s) left.");
    }

    public void showRegistrationAttemptCount(int remainingAttempt) {
        System.out.println("[system] You have " + remainingAttempt + " registration attempt(s) left.");
    }

    public void warnMaxLoginAttempt() {
        System.out.println("[system] You have reached the maximum login attempt.");
    }

    public void warnMaxRegistrationAttempt() {
        System.out.println("[system] You have reached the maximum registration attempt.");
    }

    public void warnDuplicateRegistration() {
        System.out.println("[system] Username already exists.");
    }
}
