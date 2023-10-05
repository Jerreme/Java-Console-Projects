package onlineshopping.controllers;

import onlineshopping.interfaces.Credential;
import onlineshopping.models.LoginCredential;
import onlineshopping.models.RegistrationCredential;
import onlineshopping.views.Authentication;
import onlineshopping.views.Warn;

import java.util.ArrayList;
import java.util.Scanner;

public class AuthenticationController {
    private final Authentication view;
    private static ArrayList<Credential> credentials = new ArrayList<>();

    private static int loginAttemptCound = 0;
    private static final int MAX_LOGIN_ATTEMPT = 3;

    private static int registrationAttemptCount = 0;
    private static final int MAX_REGISTRATION_ATTEMPT = 3;

    public AuthenticationController(Authentication view) {
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

    private boolean isCanRegister() {
        if (registrationAttemptCount == 0) {
            return true;
        }
        if (registrationAttemptCount >= MAX_LOGIN_ATTEMPT) {
            registrationAttemptCount = 0;
            view.warnMaxRegistrationAttempt();
            Navigator.gotoLastRoute();
            return false;
        } else {
            view.showRegistrationAttemptCount(MAX_REGISTRATION_ATTEMPT - registrationAttemptCount);
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
        for (Credential credential : credentials) {
            if (credential.getUsername().equals(loginCredential.getUsername()) &&
                    credential.getPassword().equals(loginCredential.getPassword())) {
                userCredential = credential;
                break;
            }
        }
        if (userCredential == null) {
            view.showLoginFailed();
        }
        return userCredential;
    }

    public RegistrationCredential promptRegistration() {
        // Check registration attempts first
        if (!isCanRegister()) return null;

        Scanner scanner = new Scanner(System.in);
        view.showRegistration();
        view.showUsername();
        final String username = scanner.nextLine().trim().toLowerCase();
        view.showPassword();
        final String password = scanner.nextLine().trim().toLowerCase();
        view.showConfirmPassword();
        final String confirmPassword = scanner.nextLine().trim().toLowerCase();

        if (!password.equals(confirmPassword)) {
            registrationAttemptCount += 1;
            view.showRegistrationFailed();
            return promptRegistration();
        }

        return new RegistrationCredential(username, password);
    }


    /**
     * @param registrationCredential The registration credential to be submitted
     * @return The user credential if the registration credential is valid, null otherwise
     */
    public Credential submitRegistrationCredential(RegistrationCredential registrationCredential) {
        Credential userCredential = registrationCredential;
        for (Credential credential : credentials) {
            if (credential.getUsername().equals(registrationCredential.getUsername())) {
                userCredential = null;
                view.warnDuplicateRegistration();
                break;
            }
        }
        if (userCredential != null) {
            credentials.add(userCredential);
        }
        return userCredential;
    }

    private void debugCredentials() {
        Warn.debugMessage("Credentials:");
        for (Credential credential : credentials) {
            System.out.println(credential.getUsername() + " | " + credential.getPassword());
        }
    }
}
