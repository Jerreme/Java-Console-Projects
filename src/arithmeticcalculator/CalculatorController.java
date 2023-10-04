package arithmeticcalculator;

public class CalculatorController {
    private final CalculatorModel calculatorModel;
    private final CalculatorView calculatorView;
    private boolean retry = true;
    private double answer = 0;

    public CalculatorController(CalculatorModel calculatorModel, CalculatorView calculatorView) {
        this.calculatorModel = calculatorModel;
        this.calculatorView = calculatorView;
    }

    public void run() {
        Prompter prompter = new Prompter();

        while (retry) {
            calculatorView.displayWelcomeMessage();
            askForNumbers();
            askForOperator();
            displayAnswer();
            retry = prompter.promptForRetry();
        }
    }

    private void askForNumbers() {
        Prompter prompter = new Prompter();
        double firstNum = prompter.promptForNumber("Enter the first number: ");
        double secondNum = prompter.promptForNumber("Enter the second number: ");
        updateModel(firstNum, secondNum);
    }

    private void askForOperator() {
        Prompter prompter = new Prompter();
        String operator = prompter.promptForOperator();
        Operator operatorEnum = Operator.operatorFromString(operator);

        // Guard Clause (https://refactoring.com/catalog/replaceNestedConditionalWithGuardClauses.html)
        if (operatorEnum == null) {
            warnUser("[Invalid operator]");
            askForOperator();
            return;
        }

        calculatorModel.setOperator(operatorEnum);
        switch (operatorEnum) {
            case ADD -> answer = calculatorModel.add();
            case SUBTRACT -> answer = calculatorModel.subtract();
            case MULTIPLY -> answer = calculatorModel.multiply();
            case DIVIDE -> answer = calculatorModel.divide();
        }
    }

    private void updateModel(double firstNum, double secondNum) {
        calculatorModel.setFirstNumber(firstNum);
        calculatorModel.setSecondNumber(secondNum);
    }

    private void displayAnswer() {
        calculatorView.displayAnswer(calculatorModel.getFirstNumber(), calculatorModel.getSecondNumber(),
                calculatorModel.getOperator().operatorPastTense(), answer);
    }

    private void warnUser(String message) {
        calculatorView.displayErrorMessage(message);
    }
}

class Prompter {
    double promptForNumber(String prompt) {
        System.out.print(prompt);

        double number;
        try {
            number = new java.util.Scanner(System.in).nextDouble();
        } catch (Exception e) {
            System.out.println("[Invalid number]");
            number = promptForNumber(prompt);
        }

        return number;
    }

    String promptForOperator() {
        System.out.println();
        System.out.println("[1] Add         [3] Multiply");
        System.out.println("[2] Subtract    [4] Divide");
        System.out.print("Enter the operator: ");
        return new java.util.Scanner(System.in).nextLine();
    }

    boolean promptForRetry() {
        System.out.println();
        System.out.println("[y] Yes         [n] No");
        System.out.print("Would you like to calculate again?: ");
        String response = new java.util.Scanner(System.in).nextLine().toLowerCase().trim();

        boolean retry = response.equals("y");
        if (!(response.equals("n") || response.equals("y"))) System.out.println("[Invalid] Exitting...");
        else if (!retry) System.out.println("Thank you for using the Arithmetic Calculator!");
        else System.out.println();
        return retry;
    }
}