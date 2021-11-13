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


    /**
     * @param ch
     * @return
     */
    public static int priority(String ch) {
        //first priority * and +
        if (ch == "*" || ch == "/") return 2;
        //second priority + and -
        else if (ch == "+" || ch == "-") return 1;
        // ch isn't operator
        return 0;
    }

    /**
     * @param ch - character from infix string
     * @return true or false
     */
    public static boolean isOperator(String ch) {
        return (ch.equals("+") || ch.equals("-") || ch.equals("*") || ch.equals("/") ||
                ch.equals("^") || ch.equals("sin") || ch.equals("cos") || ch.equals("(") ||
                ch.equals(")"));
    }

    /**
     * @param infixExpression - expression that is fed to the input
     * @return
     */
    public String fromInfixToPostfix(String infixExpression) {

        StringTokenizer infixEx = new StringTokenizer(infixExpression,"+-*^()/",
                true);
        Stack<String> operators = new Stack<>();
        StringBuilder postfixExpression = new StringBuilder();

        while (infixEx.hasMoreTokens()) {
            String temp = infixEx.nextToken();

            if (!isOperator(temp)) postfixExpression.append(temp + " ");
            else if (temp.equals("(")) operators.push(temp);
            else if (temp.equals(")"))  {
                while(!operators.isEmpty() && !operators.peek().equals("("))
                    postfixExpression.append(operators.pop() + " ");

                if (!operators.isEmpty() && !operators.peek().equals("(")) return null;
                else if(!operators.isEmpty()) operators.pop();
            }
            else {
                while (!operators.isEmpty() && (priority(temp) <= priority(operators.peek())))
                    postfixExpression.append(operators.pop() + "");
                operators.push(temp);
            }
        }

        while (!operators.isEmpty())
            postfixExpression.append(operators.pop() + " ");
        return postfixExpression.toString();
    }
    /**
     * @param postfixExpression
     */
    public void postfixEvaluation(String postfixExpression) {



    }

}