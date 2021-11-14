public class Main {
    public static void main(String[] args) throws Exception {
        Calculator calculator = new Calculator("7-10*43+sin(0.52)");
        System.out.println(calculator.fromInfixToPostfix());
    }
}
