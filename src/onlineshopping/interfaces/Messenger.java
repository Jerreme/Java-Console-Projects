package onlineshopping.interfaces;

public class Messenger {
    public final String SYSTEM_NAME = "system";

    public void print(String message) {
        System.out.print(message);
    }

    public void println(String message) {
        System.out.println(message);
    }

    public void systemMessage(String message) {
        println("[" + SYSTEM_NAME + "] " + message);
    }


}
