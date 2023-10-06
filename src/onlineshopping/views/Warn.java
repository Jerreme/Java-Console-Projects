package onlineshopping.views;

import onlineshopping.interfaces.Messenger;

public class Warn {
    public static void invalidInput() {
        System.out.println("[system] Please enter a valid input.");
    }

    public static void debugMessage(String message) {
        System.out.println("[debug] " + message);
    }

    public static void debugMessageAndExit(String message, int statusCode) {
        debugMessage(message);
        debugMessage("Exiting with status code " + statusCode);
        System.exit(statusCode);
    }
}
