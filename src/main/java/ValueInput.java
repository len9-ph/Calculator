import java.util.HashMap;
import java.util.Scanner;

public class ValueInput {
    HashMap<Character, Double> operands;

    public ValueInput() {
        this.operands = new HashMap<>();
    }

    /*public HashMap<Character, Double> getOperands() {
        return operands;
    }*/

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

    @Override
    public String toString() {
        return operands.toString();
    }

}
