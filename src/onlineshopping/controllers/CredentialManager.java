package onlineshopping.controllers;

import onlineshopping.database.UserDb;
import onlineshopping.interfaces.Credential;
import onlineshopping.models.User;
import onlineshopping.views.Warn;

public class CredentialManager {
    private static User loggedUser = null;

    public void registerUser(Credential credential) {
        if (!(credential instanceof User user)) {
            Warn.debugMessage("Credential is not an instance of User!");
            return;
        }
        if (user.username() == null || user.password() == null) {
            Warn.debugMessage("Username or password is null!");
            return;
        }
        if (user.username().isEmpty() || user.password().isEmpty()) {
            Warn.debugMessage("Username or password is empty!");
            return;
        }
        final UserDb userDb = new UserDb();
        userDb.addUser(new User(user.username(), user.password(), User.DEFAULT_MONEY));
    }

    public User tryLogin(Credential credential) {
        final UserDb userDb = new UserDb();
        final User user = userDb.getUser(credential);
        CredentialManager.loggedUser = user;
        return user;
    }

    public boolean isUSerAlreadyExist(String username) {
        final UserDb userDb = new UserDb();
        return userDb.isUserAlreadyExist(username);
    }

    public static User getLoggedInUser() {
        return CredentialManager.loggedUser;
    }

    public static void updateUser(User user) {
        CredentialManager.loggedUser = user;
        // Update user in database
        final UserDb userDb = new UserDb();
        userDb.updateUserBalance(user);
    }

    public static void logout() {
        CredentialManager.loggedUser = null;
    }
}
