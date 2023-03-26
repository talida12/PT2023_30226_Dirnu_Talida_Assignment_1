package functions;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class OperationsTest {
    Polynomial pol1 = new Polynomial(1, 2, 3);
    Polynomial pol2 = new Polynomial(1, 2, 3, 4);
    @Test
    void getPairs() {
        Map<Integer, Double> map = new TreeMap<>();
        map.put(0, 1.0);
        map.put(1, 2.0);
        map.put(2, 3.0);
        map.put(3, 4.0);
        assertEquals(map, Operations.getPairs("+4*x^3+3*x^2+2*x^1+1"));
    }

    @Test
    void addition() {
        assertEquals(Operations.addition(pol1, pol2), new Polynomial(2, 4, 6, 4));
    }
    @Test
    void subtraction() {
        assertEquals(Operations.subtraction(pol1, pol2), new Polynomial ( 0, 0, 0, -4));
    }
    @Test
    void multiplication() {
        assertEquals(Operations.multiplication(pol1, pol2), new Polynomial(1, 4, 10, 16, 17, 12));
    }
    @Test
    void division() {
            assertEquals((Polynomial) Operations.division(pol1, pol2).getQuotient(),  new Polynomial());
            assertEquals((Polynomial) Operations.division(pol1, pol2).getRemainder(), new Polynomial(1, 2, 3));
    }
    @Test
    void derivative() {
        Polynomial p = new Polynomial(1, 2, 3, 8, 5);
        assertEquals(Operations.derivative(p), new Polynomial(2, 6, 24, 20) );
    }
    @Test
    void integral() {
        Polynomial p = new Polynomial(3, 4, 3, 8);
        assertEquals( Operations.integral(p), new Polynomial(0, 3, 2, 1, 2));
    }
}