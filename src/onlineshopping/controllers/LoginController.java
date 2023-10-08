package onlineshopping.controllers;

import onlineshopping.interfaces.Credential;
import onlineshopping.models.User;
import onlineshopping.views.LoginPageView;

import java.util.Scanner;

public class LoginController {

    private final LoginPageView view;
    private final CredentialManager credentialManager = new CredentialManager();
    private static int loginAttemptCount = 0;

    private static final int MAX_LOGIN_ATTEMPT = 3;

    public LoginController(LoginPageView view) {
        this.view = view;
    }

    private boolean isCanLogin() {
        if (loginAttemptCount == 0) return true;
        if (loginAttemptCount >= MAX_LOGIN_ATTEMPT) {
            loginAttemptCount = 0;
            view.warnMaxLoginAttempt();
            Navigator.gotoLastRoute();
            return false;
        } else {
            view.showLoginAttemptCount(MAX_LOGIN_ATTEMPT - loginAttemptCount);
            return true;
        }
    }

    public Credential promptLogin() {
        // Check login attempts first
        if (!isCanLogin()) return null;
        Scanner scanner = new Scanner(System.in);

        view.showLogin();
        view.showUsername();
        final String username = scanner.nextLine().trim().toLowerCase();
        view.showPassword();
        final String password = scanner.nextLine().trim().toLowerCase();

        loginAttemptCount += 1;
        return new User(username, password, User.DEFAULT_MONEY);
    }

    /**
     * @param loginCredential The login credential to be submitted
     * @return The user credential if the login credential is valid, null otherwise
     */
    public User submitLoginCredential(Credential loginCredential) {
        User userCredential = credentialManager.tryLogin(loginCredential);
        if (userCredential == null) {
            view.showLoginFailed();
        } else {
            view.showLoginSuccess();
            loginAttemptCount = 0;
        }
        return userCredential;
    }
}
