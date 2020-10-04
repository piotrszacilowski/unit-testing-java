import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    private StringCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new StringCalculator();
    }

    @Test
    void shouldReturnNumberWhenNumberGiven() {
        assertEquals(2, calculator.add("2"));
    }

    @Test
    void shouldSumTwoNumbersWhenTwoNumbersGiven() {
        assertEquals(7, calculator.add("3,4"));
    }

    @Test
    void shouldSumMoreNumbersWhenMoreNumbersIsGiven() {
        assertEquals(20,calculator.add("2,3,5,10"));
    }

    @Test
    void shouldReturnZeroWhenInputIsEmpty() {
        assertEquals(0, calculator.add(""));
    }
}
