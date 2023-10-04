package arithmeticcalculator;

public class CalculatorModel {
    private double firstNumber;
    private double secondNumber;
    private Operator operator;

    CalculatorModel(double firstNumber, double secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    public double getFirstNumber() {
        return this.firstNumber;
    }

    protected void setFirstNumber(double firstNumber) {
        this.firstNumber = firstNumber;
    }

    public double getSecondNumber() {
        return this.secondNumber;
    }

    protected void setSecondNumber(double secondNumber) {
        this.secondNumber = secondNumber;
    }

    public Operator getOperator() {
        return this.operator;
    }

    protected void setOperator(Operator operator) {
        this.operator = operator;
    }

    protected double add() {
        return this.firstNumber + this.secondNumber;
    }

    protected double subtract() {
        return this.firstNumber - this.secondNumber;
    }

    protected double multiply() {
        return this.firstNumber * this.secondNumber;
    }

    protected double divide() {
        return this.firstNumber / this.secondNumber;
    }

    public double modulo() {
        return this.firstNumber % this.secondNumber;
    }
}
