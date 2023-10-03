package projects.arithmeticcalculator;

/*
 * This is a simple arithmetic calculator that can add, subtract, multiply, and divide two numbers.
 * The calculator is programmed in concept of OOP and MVC Pattern in Java. To know more about
 * MVC Pattern, visit: https://www.tutorialspoint.com/design_pattern/mvc_pattern.htm
 */
public class Main {
    public static void main(String[] args) {
        CalculatorModel calculatorModel = new CalculatorModel(0, 0);
        CalculatorView calculatorView = new CalculatorView();
        CalculatorController calculatorController = new CalculatorController(calculatorModel, calculatorView);
        calculatorController.run();
    }
}
