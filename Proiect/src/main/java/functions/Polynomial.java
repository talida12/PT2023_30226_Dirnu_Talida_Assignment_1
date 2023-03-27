package functions;

import java.util.*;

public class Polynomial implements Function {
    private final Map<Integer, Double> coefficients;

    private int degree;
    public Polynomial(double... values){
        coefficients = new TreeMap<>(Collections.reverseOrder()); // ensures that the keys are sorted automatically
        int pos = 0;
        degree = 0;
        for ( double i : values ) {
            if (i != 0) {
                coefficients.put(pos, i);
                degree = pos;
            }
            pos++;
        }
        if (coefficients.entrySet().isEmpty()) {
            coefficients.put(0, 0.0);
        }
    }

    public Polynomial(Map<Integer, Double> values){
        coefficients = new TreeMap<>(Collections.reverseOrder()); // ensures that the keys are sorted automatically
        for (Map.Entry<Integer, Double> pair : values.entrySet()) {
            if (pair.getValue() != 0) {
                coefficients.put(pair.getKey(), pair.getValue());
            }
        }
        if (coefficients.entrySet().isEmpty()) {
            coefficients.put(0, 0.0);
        }
        degree = Collections.max(coefficients.keySet());
    }

    public int getDegree() {
        return degree;
    }

    public Map<Integer, Double> getCoefficients() {
        return coefficients;
    }
    public double valueIn(double x) { //optional
        double value = 0;
        for (Map.Entry<Integer, Double> pair : coefficients.entrySet()) {
            value += Math.pow(x, pair.getKey()) * pair.getValue();
        }
        return value;
    }
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Map.Entry<Integer, Double> pair : coefficients.entrySet()) {
            if (pair.getValue() < 0) {
                if (pair.getKey() == 0) {
                    str.append(pair.getValue());
                } else if (pair.getValue() == -1) {
                    str.append("-x^").append(pair.getKey());
                } else {
                    str.append(String.format("%.2f", pair.getValue())).append("*x^").append(pair.getKey());
                }
            }
            else {
                if (pair.getKey() == 0) {
                    str.append('+').append(String.format("%.2f", pair.getValue()));
                } else if (pair.getValue() == 1) {
                    str.append("+x^").append(pair.getKey());
                } else {
                    str.append("+").append(String.format("%.2f", pair.getValue())).append("*x^").append(pair.getKey());
                }
            }
        }
        return str.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return coefficients.equals ( ((Function)obj).getCoefficients() );
    }
}
