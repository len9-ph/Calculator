import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



class CalculatorTest {

    Calculator calculator;


    @org.junit.jupiter.api.Test
    void fromInfixToPostfix() throws Exception {
        Calculator calculator1 = new Calculator("92/10");
        String expected1 = "92 10 / ";
        String actual1 = calculator1.fromInfixToPostfix();
        assertEquals(expected1, actual1);

        Calculator calculator2 = new Calculator("2^10-5+4*6");
        String expected2 = "2 10 ^ 5 - 4 6 * + ";
        String actual2 = calculator2.fromInfixToPostfix();
        assertEquals(expected2, actual2);
    }

    @org.junit.jupiter.api.Test
    void getCalculatedExpression() throws Exception {
        Calculator calculator1 = new Calculator("92/10");

        assertEquals(calculator1.getCalculatedExpression(calculator1.
                fromInfixToPostfix()), 9.2);

        Calculator calculator2 = new Calculator("2^10-5+4*6");
        String expected2 = "2 10 ^ 5 - 4 6 * + ";

        assertEquals(calculator1.getCalculatedExpression(calculator2.
                fromInfixToPostfix()), 1043);


    }
}