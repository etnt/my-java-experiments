import java.util.Stack;

// java CalculatorExample.java "1 + 2 * 3 - 4 / 5"

public class CalculatorExample {

    public static void main(String[] args) {
        double answer = calculate(args[0]); 
        System.out.println("The answer is: " + answer);
    }

    public static double calculate(String expression) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operations = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isWhitespace(c)) {
                continue;
            }

            if (c >= '0' && c <= '9') {
                numbers.push((double) (c - '0'));
            } 
            if (c == '(') {
                operations.push(c);
            } else if (c == ')') {
                while (operations.peek() != '(') {
                    processOperation(numbers, operations);
                }
                operations.pop();
            else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!operations.isEmpty() && precedence(c, operations.peek())) {
                    processOperation(numbers, operations);
                }
                operations.push(c);
            }
        }

        while (!operations.isEmpty()) {
            processOperation(numbers, operations);
        }

        return numbers.pop();
    }

    private static boolean precedence(char op1, char op2) {
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        } else {
            return true;
        }
    }

    private static void processOperation(Stack<Double> numbers, Stack<Character> operations) {
        char operation = operations.pop();
        double operand2 = numbers.pop();
        double operand1 = numbers.pop();
        switch (operation) {
            case '+':
                numbers.push(new Add().execute(operand1, operand2));
                break;
            case '-':
                numbers.push(new Subtract().execute(operand1, operand2));
                break;
            case '*':
                numbers.push(new Multiply().execute(operand1, operand2));
                break;
            case '/':
                numbers.push(new Divide().execute(operand1, operand2));
                break;
        }
    }

    public interface Operation {
        double execute(double a, double b);
    }

    public static class Add implements Operation {
        public double execute(double a, double b) {
            return a + b;
        }
    }

    public static class Subtract implements Operation {
        public double execute(double a, double b) {
            return a - b;
        }
    }

    public static class Multiply implements Operation {
        public double execute(double a, double b) {
            return a * b;
        }
    }

    public static class Divide implements Operation {
        public double execute(double a, double b) {
            if (b == 0) {
                throw new IllegalArgumentException("Division by zero is not allowed");
            }
            return a / b;
        }
    }

}
