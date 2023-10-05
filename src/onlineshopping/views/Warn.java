package onlineshopping.views;

public class Warn {
    public static void invalidInput() {
        System.out.println("[system] Please enter a valid input.");
    }

    public static void debugMessage(String message) {
        System.out.println("[debug] " + message);
    }

    public static void debugMessageAndExit(String message, int statusCode) {
        debugMessage(message);
        System.exit(statusCode);
    }
}
