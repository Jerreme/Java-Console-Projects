package onlineshopping.controllers;

import onlineshopping.interfaces.Credential;
import onlineshopping.models.LoginCredential;
import onlineshopping.views.LoginPageView;

import java.util.Scanner;

public class LoginController {

    private final LoginPageView view;
    private final CredentialManager credentialManager = new CredentialManager();
    private static int loginAttemptCound = 0;

    private static final int MAX_LOGIN_ATTEMPT = 3;

    public LoginController(LoginPageView view) {
        this.view = view;
    }

    private boolean isCanLogin() {
        if (loginAttemptCound == 0) {
            return true;
        }
        if (loginAttemptCound >= MAX_LOGIN_ATTEMPT) {
            loginAttemptCound = 0;
            view.warnMaxLoginAttempt();
            Navigator.gotoLastRoute();
            return false;
        } else {
            view.showLoginAttemptCount(MAX_LOGIN_ATTEMPT - loginAttemptCound);
            return true;
        }
    }

    public LoginCredential promptLogin() {
        // Check login attempts first
        if (!isCanLogin()) return null;

        Scanner scanner = new Scanner(System.in);
        view.showLogin();
        view.showUsername();
        final String username = scanner.nextLine().trim().toLowerCase();
        view.showPassword();
        final String password = scanner.nextLine().trim().toLowerCase();

        loginAttemptCound += 1;
        return new LoginCredential(username, password);
    }

    /**
     * @param loginCredential The login credential to be submitted
     * @return The user credential if the login credential is valid, null otherwise
     */
    public Credential submitLoginCredential(LoginCredential loginCredential) {
        Credential userCredential = null;
        for (Credential credential : credentialManager.getCredentials()) {
            if (credential.username().equals(loginCredential.username()) &&
                    credential.password().equals(loginCredential.password())) {
                userCredential = credential;
                break;
            }
        }
        if (userCredential == null) {
            view.showLoginFailed();
        }
        return userCredential;
    }
}
