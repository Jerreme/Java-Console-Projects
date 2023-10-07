package onlineshopping.views;

import onlineshopping.interfaces.ExitCode;

public class Warn {

    public static void invalidInput() {
        System.out.println("[system] Please enter a valid input.");
    }

    public static void systemMessage(String message) {
        System.out.println("[system] " + message);
    }

    public static void debugMessage(String message) {
        System.out.println("[debug] " + message);
    }

    public static void debugMessageAndExit(String message, ExitCode statusCode) {
        debugMessage(message);
        debugMessage("Exiting with status code " + statusCode);
        System.exit(statusCode.getCode());
    }
}

