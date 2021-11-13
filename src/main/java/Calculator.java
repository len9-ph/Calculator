import java.util.Stack;
import java.util.StringTokenizer;

/**
 *
 */
public class Calculator {

    /**
     * Value that saved calculated expression
     */
    private double calculatedValue;

    /**
     * Default constructor
     */
    public Calculator() {
        this.calculatedValue = 0;
    }

    private static boolean isFunction(String token) {
        return (token.equals("sin") || token.equals("cos") || token.equals("tg") || token.equals("ctg"));
    }

    /**
     * @param token
     * @return
     */
    private static boolean isOperator(String token) {
        return (token.equals("-") || token.equals("*") || token.equals("/") ||
                token.equals("^") || token.equals("+"));
    }

    /**
     * @param token
     * @return
     */
    private static boolean isDelimiter(String token) {
        return (token.equals("(") || token.equals(")") || isOperator(token));
    }

    /**
     * @param token
     * @return
     */
    private static int priority(String token) {
        return switch (token) {
            case "(" -> 1;
            case "+", "-" -> 2;
            case "*", "/" -> 3;
            default -> 4;
        };
    }

    /**
     * @param infixExpr
     * @return
     * @throws Exception
     */
    public String fromInfixToPostfix(String infixExpr) throws Exception {
        Stack<String> stack = new Stack<>();
        StringTokenizer infix = new StringTokenizer(infixExpr, "+-*/^()", true);
        StringBuilder postfixExpr = new StringBuilder();

        while (infix.hasMoreTokens()) {
            String temp = infix.nextToken();

            if (!infix.hasMoreTokens() && isOperator(temp))
                throw new Exception("Expression is incorrect");

            if (!isDelimiter(temp) && !isFunction(temp))
                postfixExpr.append(temp).append(" ");
            else {
                if (isFunction(temp))
                    stack.push(temp);
                else if (temp.equals("("))
                    stack.push(temp);
                else if (temp.equals(")")) {
                    while (!stack.peek().equals("(")) {
                        postfixExpr.append(stack.pop()).append(" ");
                        if (stack.isEmpty()) throw new Exception("The brackets are not consistent");
                    }
                    stack.pop();
                } else {
                    while (!stack.isEmpty() && (priority(temp) <= (priority(stack.peek()))))
                        postfixExpr.append(stack.pop()).append(" ");
                    stack.push(temp);
                }
            }
        }
        while (!stack.isEmpty()) {
            if(isOperator(stack.peek()))
                postfixExpr.append(stack.pop()).append(" ");
            else
                throw new Exception("Syntax error");
        }
        return postfixExpr.toString();
    }




}



















