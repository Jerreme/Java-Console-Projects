package onlineshopping.views;

import onlineshopping.interfaces.Messenger;

public class LoginPageView extends Messenger {
    public void showLogin() {
        println("\n-------- Login --------");
    }

    public void showUsername() {
        print("Enter username: ");
    }

    public void showPassword() {
        print("Enter password: ");
    }

    public void showLoginFailed() {
        systemMessage("Check your username and password.");
    }

    public void showLoginAttemptCount(int remainingAttempt) {
        systemMessage("You have " + remainingAttempt + " login attempt(s) left.");
    }

    public void warnMaxLoginAttempt() {
        systemMessage("You have reached the maximum login attempt.");
    }

    public void showLoginSuccess() {
        systemMessage("Login successfully.");
    }

}
