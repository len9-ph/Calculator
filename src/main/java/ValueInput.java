import java.util.HashMap;
import java.util.Scanner;

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

    /**
     * Method that scan string and ask user for value of letter variables
     * @param input - expression
     */
    public void inputValues(String input) {
        for(Character current : input.toCharArray()) {
            if (Character.isLetter(current)) {
                if(operands.isEmpty() || !operands.containsKey(current)) {
                    Scanner console = new Scanner(System.in);
                    System.out.println("Input value of " + current + "=");
                    Double value = console.nextDouble();
                    operands.put(current, value);
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
