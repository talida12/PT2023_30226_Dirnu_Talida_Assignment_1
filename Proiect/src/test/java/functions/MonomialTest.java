package functions;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class MonomialTest {
    Monomial monomial = new Monomial(5, 3);
    @org.junit.jupiter.api.Test
    void valueIn() {
        assertEquals(monomial.valueIn(2.0) , 40.0);
    }

    @org.junit.jupiter.api.Test
    void getDegree() {
        assertEquals(monomial.getDegree(), 3);
    }

    @org.junit.jupiter.api.Test
    void getCoefficients() {
        Map<Integer, Double> map = new TreeMap<>();
        map.put(3, 5.0);
        assertTrue(monomial.getCoefficients().equals(map));
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        assertEquals(monomial.toString(), "5,0*x^3");
    }
}