import java.net.Inet4Address;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Class that calculated given expression
 * @author Leonid Gadetsky
 * @see ValueInput
 */
public class Calculator {

    /**
     * value that stores expr
     */
    private final String expression;

    /**
     * Default constructor
     * @param expression - expression that need to be calculated
     */
    public Calculator(String expression) {
        this.expression = expression;
    }

    /**
     * @param token - character of string
     * @return is token function or not
     */
    private static boolean isFunction(String token) {
        return (token.equals("sin") || token.equals("cos") || token.equals("tan") || token.equals("u-"));
    }

    /**
     * @param token - character of string
     * @return is token operator or not
     */
    private static boolean isOperator(String token) {
        return (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") ||
                token.equals("^"));
    }

    /**
     * @param token - character of string
     * @return is token delimiter or not
     */
    private static boolean isDelimiter(String token) {
        return (token.equals("(") || token.equals(")") || isOperator(token));
    }

    /**
     * @param token - character of string
     * @return - priority of operation
     */
    private static int getPriorityOfOperation(String token) {
        return switch (token) {
            case "(" -> 1;
            case "+", "-" -> 2;
            case "*", "/" -> 3;
            default -> 4;
        };
    }

    /**
     * @return - expression in postfix form
     * @throws Exception - if the expression is written incorrectly
     */
    public String fromInfixToPostfix() throws Exception {
        Stack<String> stack = new Stack<>();
        StringTokenizer infix = new StringTokenizer(this.expression, "+-*/^()", true);
        StringBuilder postfixExpr = new StringBuilder();
        String previous = "";
        while (infix.hasMoreTokens()) {
            String current = infix.nextToken();

            if (!infix.hasMoreTokens() && isOperator(current))
                throw new Exception("Expression is incorrect");

            if (current.equals(" ")) continue;
            if (isDelimiter(current) || isFunction(current)) {
                if (isFunction(current))
                    stack.push(current);
                else if (current.equals("("))
                    stack.push(current);
                else if (current.equals(")")) {
                    while (!stack.peek().equals("(")) {
                        postfixExpr.append(stack.pop()).append(" ");
                        if (stack.isEmpty()) throw new Exception("The brackets are not consistent");
                    }
                    stack.pop();
                }
                else{
                    if (current.equals("-") && (previous.isEmpty() || previous.equals("(")))
                        stack.push("u-");
                    else {
                        while (!stack.isEmpty() && (getPriorityOfOperation(current) <= (getPriorityOfOperation(stack.peek()))))
                            postfixExpr.append(stack.pop()).append(" ");
                        stack.push(current);
                    }
                }

            } else {
                postfixExpr.append(current).append(" ");
            }
            previous = current;
        }
        while (!stack.isEmpty()) {
            if(isOperator(stack.peek()))
                postfixExpr.append(stack.pop()).append(" ");
            else
                throw new Exception("The brackets are not consistent");
        }
        return postfixExpr.toString();
    }

    /**
     * @param postfixExpr - expression in postfix form
     * @return - evaluated value of expr
     * @throws ArithmeticException - if we have zero division
     */
    public Double getCalculatedExpression(String postfixExpr) throws ArithmeticException {
        StringTokenizer postfix = new StringTokenizer(postfixExpr);
        Stack<Double> stack = new Stack<>();
        ValueInput valueInput = new ValueInput();
        valueInput.inputValues(this.expression);

        while (postfix.hasMoreTokens()) {
            String current = postfix.nextToken();

            if (isDelimiter(current) || isFunction(current)) {
                if(isFunction(current)) {
                    switch (current) {
                        case "sin" -> stack.push(Math.sin(stack.pop()));
                        case "cos" -> stack.push(Math.cos(stack.pop()));
                        case "tan" -> stack.push(Math.tan(stack.pop()));
                        case "u-" -> stack.push(-stack.pop());
                    }
                }
                else if(isOperator(current)){
                    Double upper = stack.pop();
                    if(upper == 0) throw new ArithmeticException("Division by zero");
                    Double lower = stack.pop();
                    switch (current) {
                        case "+" -> stack.push(lower + upper);
                        case "-" -> stack.push(lower - upper);
                        case "*" -> stack.push(lower * upper);
                        case "/" -> stack.push(lower / upper);
                        case "^" -> stack.push(Math.pow(lower, upper));
                    }
                }
            } else {
                try {
                    stack.push(Double.parseDouble(current));
                }
                catch (NumberFormatException e) {
                    stack.push(valueInput.operands.get(current.charAt(0)));
                }

            }
        }
        return stack.pop();
    }
}
























































