public class Main {
    public static void main(String[] args) throws Exception {
        Calculator calculator = new Calculator();
        String postfix = calculator.fromInfixToPostfix("sin(30)+5^2*(3+2)");
        System.out.println(postfix);
    }
}
