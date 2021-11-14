import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



class CalculatorTest {

    @org.junit.jupiter.api.Test
    void getCalculatedExpression() throws Exception {

        Calculator calculator1 = new Calculator("92/10");
        assertEquals(calculator1.getCalculatedExpression(), 9.2);

        Calculator calculator2 = new Calculator("2^10-5+4*6");
        assertEquals(calculator2.getCalculatedExpression(), 1043);

        Calculator calculator3 = new Calculator("(-10 - 5)");
        assertEquals(calculator3.getCalculatedExpression(), -15);

       Calculator calculator4 = new Calculator("7+(-10)*43");
        assertEquals(calculator4.getCalculatedExpression(), -423);
    }
}