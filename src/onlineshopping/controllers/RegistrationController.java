package onlineshopping.controllers;

import onlineshopping.interfaces.Credential;
import onlineshopping.models.RegistrationCredential;
import onlineshopping.views.RegistrationPageView;
import onlineshopping.views.Warn;

import java.util.Scanner;

public class RegistrationController {

    private static final int MAX_REGISTRATION_ATTEMPT = 3;
    private static int registrationAttemptCount = 0;
    private final RegistrationPageView view;

    private final CredentialManager credentialManager = new CredentialManager();

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
        for (Credential credential : credentialManager.getCredentials()) {
            if (credential.username().equals(registrationCredential.username())) {
                userCredential = null;
                view.warnDuplicateRegistration();
                break;
            }
        }
        if (userCredential != null) {
            credentialManager.addCredential(userCredential);
        }
        return userCredential;
    }

    private void debugCredentials() {
        Warn.debugMessage("Credentials:");
        for (Credential credential : credentialManager.getCredentials()) {
            System.out.println(credential.username() + " | " + credential.password());
        }
    }
}
