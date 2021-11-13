public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String postfix = calculator.infixToPostfix("10*5+15-(4+1)^2-25");
        System.out.println(postfix);
    }
}
