package onlineshopping.controllers;

import onlineshopping.database.UserDb;
import onlineshopping.interfaces.Credential;
import onlineshopping.models.User;
import onlineshopping.views.RegistrationPageView;

import java.util.Scanner;

public class RegistrationController {

    private static final int MAX_REGISTRATION_ATTEMPT = 3;
    private final RegistrationPageView view;
    private final CredentialManager credentialManager = new CredentialManager();
    private static int registrationAttemptCount = 0;

    public RegistrationController(RegistrationPageView view) {
        this.view = view;
    }

    private boolean isCanRegister() {
        if (registrationAttemptCount == 0) {
            return true;
        }
        if (registrationAttemptCount >= MAX_REGISTRATION_ATTEMPT) {
            registrationAttemptCount = 0;
            view.warnMaxRegistrationAttempt();
            Navigator.gotoLastRoute();
            return false;
        } else {
            view.showRegistrationAttemptCount(MAX_REGISTRATION_ATTEMPT - registrationAttemptCount);
            return true;
        }
    }


    public Credential promptRegistration() {
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

        return new User(username, password, User.DEFAULT_MONEY);
    }


    /**
     * @param credential The registration credential to be submitted
     * @return The user if the registration credential is valid, null otherwise
     */
    public User submitRegistrationCredential(Credential credential) {
        if (credentialManager.isUSerAlreadyExist(credential.username())) {
            view.warnDuplicateRegistration();
            return null;
        } else {
            credentialManager.registerUser(credential);
            view.showRegistrationSuccess();
            registrationAttemptCount = 0;
            return (User) credential;
        }
    }
}
