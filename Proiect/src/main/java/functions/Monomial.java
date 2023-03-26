package functions;

import java.util.Map;

public class Monomial implements Function {
    private double coefficient;
    private int power;
    public Monomial(double coefficient, int power){
        this.coefficient = coefficient;
        this.power = power;
    }
    @Override
    public double valueIn(double x) {
        return coefficient * Math.pow(x, power);
    }

    @Override
    public int getDegree() {
        return power;
    }

    @Override
    public Map<Integer, Double> getCoefficients() {
        return Map.of(power, coefficient);
    }

    public String toString() {
        return String.format("%.2g", coefficient) + "*x^" + power;    }

}
