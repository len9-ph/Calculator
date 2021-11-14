
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Class that perform string scanning
 * @author Leonid Gadetsky
 *
 */
public class ValueInput {

    /**
     * HaspMap that stores operands and their values
     */
    HashMap<Character, Double> operands;

    public ValueInput() {
        this.operands = new HashMap<>();
    }

    private boolean isFunction(String token) {
        return (token.equals("sin") || token.equals("cos") || token.equals("tan"));
    }
    private boolean isLetter(String token) {
        return token.charAt(0) > 'a' && token.charAt(0) <= 'z' ;
    }

    /**
     * Method that scan string and ask user for value of letter variables
     * @param input - expression
     */
    public void inputValues(String input) {

        StringTokenizer stringTokenizer = new StringTokenizer(input);
        while (stringTokenizer.hasMoreTokens()) {
            String current = stringTokenizer.nextToken();
            if(isLetter(current) && isFunction(current)) {
                if(operands.isEmpty() || !operands.containsKey(current.charAt(0))) {
                    Scanner console = new Scanner(System.in);
                    System.out.println("Input value of " + current + "=");
                    Double value = console.nextDouble();
                    operands.put(current.charAt(0), value);
                }
            }
        }
    }

    /**
     * @return hashmap in string form
     */
    @Override
    public String toString() {
        return operands.toString();
    }

}
