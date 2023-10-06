package onlineshopping.controllers;

import onlineshopping.interfaces.Credential;
import onlineshopping.models.User;
import onlineshopping.views.Warn;

import java.util.ArrayList;

public class CredentialManager {

    private static final ArrayList<Credential> credentials = new ArrayList<>();
    private static Credential loggedUser = null;

    public void registerUser(Credential credential) {
        if (credential instanceof User user) {
            if (user.username() == null || user.password() == null) {
                Warn.debugMessage("Username or password is null!");
                return;
            }
            if (user.username().equals("") || user.password().equals("")) {
                Warn.debugMessage("Username or password is empty!");
                return;
            }
        } else {
            Warn.debugMessage("Credential is not an instance of User!");
            return;
        }
        credentials.add(credential);
    }

    public Credential tryLogin(Credential credential) {
        for (Credential user : credentials) {
            if (user.username().equals(credential.username()) &&
                    user.password().equals(credential.password())) {
                loggedUser = user;
                return user;
            }
        }
        return null;
    }

    public boolean isUSerAlreadyExist(String username) {
        for (Credential user : credentials) {
            if (user.username().equals(username)) return true;
        }
        return false;
    }

    public ArrayList<Credential> getUsers() {
        return new ArrayList<>(credentials);
    }

    public User getUser() {
        return (User) loggedUser;
    }

    public static void logout() {
        loggedUser = null;
    }
}
