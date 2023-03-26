package functions;

import java.util.Map;

public interface Function {
    public double valueIn(double x);
    public int getDegree();
    public Map<Integer, Double> getCoefficients();

}
