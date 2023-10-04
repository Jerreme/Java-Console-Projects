package arithmeticcalculator;

public enum Operator {
    ADD, SUBTRACT, MULTIPLY, DIVIDE;

    public static Operator operatorFromString(String operator) {
        return switch (operator) {
            case "1" -> ADD;
            case "2" -> SUBTRACT;
            case "3" -> MULTIPLY;
            case "4" -> DIVIDE;
            default -> null;
        };
    }

    public String operatorPastTense() {
        return switch (this) {
            case ADD -> "Added";
            case SUBTRACT -> "Subtracted";
            case MULTIPLY -> "Multiplied";
            case DIVIDE -> "Divided";
        };
    }

}
