package functions;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Operations {

    public static Map<Integer, Double> getPairs(String string){
        Map<Integer,Double> map = new HashMap<>();
        Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
            if ( !matcher.group(1).contains("x"))
            {
                    map.put( 0, (double)Integer.parseInt( matcher.group(1).substring(0, matcher.group(1).length() )) );
            }
            else
            {
                int index = matcher.group(1).lastIndexOf("x");
                if ( matcher.group(1).charAt(index-1) == '+')
                    map.put(Integer.parseInt(matcher.group(1).substring(index+2)), 1.0);
                else if ( matcher.group(1).charAt(index-1) == '-')
                    map.put(Integer.parseInt(matcher.group(1).substring(index+2) ), -1.0);
                else{
                    map.put(Integer.parseInt(matcher.group(1).substring(index+2)),
                            (double) Integer.parseInt(matcher.group(1).substring(0, index-1)) );
            } }
        }
        return map;
    }
    public static Polynomial addition(Function f1, Function f2) {
        Map<Integer, Double> result = new TreeMap<>();
        for (Map.Entry<Integer, Double> pair : f1.getCoefficients().entrySet())
            result.put(pair.getKey(), pair.getValue());
        for (Map.Entry<Integer, Double> pair : f2.getCoefficients().entrySet()) {
            if (result.containsKey(pair.getKey())) {
                result.put(pair.getKey(), pair.getValue() + result.get(pair.getKey()));
            } else {
                result.put(pair.getKey(), pair.getValue());
            }
        }
        return new Polynomial(result);
    }

    public static Polynomial subtraction(Function f1, Function f2) {
        Map<Integer, Double> result = new TreeMap<>();
        for (Map.Entry<Integer, Double> pair : f1.getCoefficients().entrySet())
            result.put(pair.getKey(), pair.getValue());
        for (Map.Entry<Integer, Double> pair : f2.getCoefficients().entrySet()) {
            if (result.containsKey(pair.getKey())) {
                result.put(pair.getKey(), result.get(pair.getKey()) - pair.getValue());
            } else {
                result.put(pair.getKey(), - pair.getValue());
            }
        }
        return new Polynomial(result);
    }

    public static Polynomial multiplication(Function f1, Function f2) {
        Map<Integer, Double> result = new TreeMap<>();
        for (Map.Entry<Integer, Double> pair1 : f1.getCoefficients().entrySet()) {
            for (Map.Entry<Integer, Double> pair2 : f2.getCoefficients().entrySet()) {
                int newPower = pair1.getKey() + pair2.getKey();
                double newCoefficient = pair1.getValue() * pair2.getValue();
                if (result.containsKey(newPower)) {
                    result.put(newPower, result.get(newPower) + newCoefficient);
                } else {
                    result.put(newPower, newCoefficient);
                }
            }
        }
        return new Polynomial(result);
    }

    public static DivisionResult division(Function f1, Function f2){
        // alg. nu functioneaza cand impart la polinom de gradul 0, iau cazul separat
        Map<Integer, Double> quotinent = new TreeMap<>();

        if (f2.getDegree() == 0) {
            if (f2.getCoefficients().get(0) == 0) {
                throw new ArithmeticException("Cannot divide by 0!");
            }
            for (int i : f1.getCoefficients().keySet())
                quotinent.put(i, f1.getCoefficients().get(i) / f2.getCoefficients().get(0));
            return new DivisionResult(new Polynomial(quotinent), new Polynomial(0));
        }

        while (f1.getDegree() >= f2.getDegree() ){
            int newPower = f1.getDegree() - f2.getDegree();
            double v1 = f1.getCoefficients().get(f1.getDegree());
            double v2 = f2.getCoefficients().get(f2.getDegree());
            double newCoefficient = v1 / v2;
            System.out.println(newCoefficient);
            quotinent.put( newPower, newCoefficient );
            f1 = Operations.subtraction( f1, Operations.multiplication(new Monomial(newCoefficient, newPower), f2));//updatam deimpartitul
        }
        return new DivisionResult(new Polynomial(quotinent), f1);
    }

    public static Polynomial derivative(Function f){
        Map<Integer, Double> result = new TreeMap<>();
        for ( Map.Entry<Integer, Double> pair : f.getCoefficients().entrySet()){
            if ( pair.getKey() == 0 ) continue;
            result.put(pair.getKey()-1, pair.getValue()*pair.getKey());
        }
        return new Polynomial(result);
    }

    public static Polynomial integral(Function f){
        Map<Integer, Double> result = new TreeMap<>();
        for ( Map.Entry<Integer, Double> pair : f.getCoefficients().entrySet()){
            result.put(pair.getKey() + 1, pair.getValue() /(pair.getKey() + 1) );
        }
        return new Polynomial(result);
    }
}
