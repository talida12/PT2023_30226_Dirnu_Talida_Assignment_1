package functions;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest {
    Polynomial p = new Polynomial(1, 2, 3, 4, 5, 6);
    @Test
    void getDegree() {
        assertEquals(p.getDegree(), 5);
    }
    @Test
    void getCoefficients() {
        Map<Integer, Double> coef = new TreeMap<>();
        coef.put(0, 1.0);
        coef.put(1, 2.0);
        coef.put(2, 3.0);
        coef.put(3, 4.0);
        coef.put(4, 5.0);
        coef.put(5, 6.0);
        assertTrue(p.getCoefficients().equals(coef));
    }
    @Test
    void valueIn() {
        assertEquals(p.valueIn(1.0), 21);
    }
    @Test
    void testToString() {
        assertEquals(p.toString(), "+6,0*x^5+5,0*x^4+4,0*x^3+3,0*x^2+2,0*x^1+1,0");
    }
    @Test
    void testEquals() {
        
    }
}