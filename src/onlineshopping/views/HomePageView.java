package onlineshopping.views;

import onlineshopping.interfaces.Messenger;

public class HomePageView extends Messenger {
    public void showAuthScreen() {
        println("\n-------- Home --------");
        println("[1] Buy Products");
        println("[2] View Cart");
        println("[3] Profile");
        println("[0] Logout");
    }

    public void welcomeUser(String username) {
        systemMessage("Welcome " + username + "!");
    }

    public void showLogoutMessage() {
        systemMessage("You have been logged out.");
    }


}
