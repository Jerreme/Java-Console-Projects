package projects.arithmeticcalculator;

public class CalculatorView {

    public void displayWelcomeMessage() {
        System.out.println("--- Welcome to the Arithmetic Calculator! ---");
    }

    public void displayAnswer(double num1, double num2, String operator, double answer) {
        System.out.println("\n" + num1 + " " + operator + " by " + num2 + " is -> " + answer);
    }

    public void displayErrorMessage(String message) {
        System.out.printf(message);
    }
}
