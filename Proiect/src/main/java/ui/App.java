package ui;

import functions.Operations;
import functions.Monomial;
import functions.Polynomial;

import java.sql.SQLOutput;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        Polynomial polynomial = new Polynomial(5, -3, 2, 0, -4);
        System.out.println(polynomial);
        Monomial mon = new Monomial(5, 3);
        System.out.println(mon);
        System.out.println(Operations.addition(polynomial, mon));
        System.out.println(Operations.subtraction(polynomial, mon));
        System.out.println(Operations.multiplication(polynomial, mon));
        Polynomial polDiv1 = new Polynomial(-4, 4, -2, 2);
        Polynomial pol = new Polynomial(-5, 6, 6, 8);
        var polDiv2 = new Polynomial(-1, 0, 1);
        Polynomial p = new Polynomial(-2, 1);
        System.out.println(Operations.subtraction(polDiv1, polDiv2));
        System.out.println(Operations.division(polDiv1, p));
        System.out.println(Operations.derivative(polDiv1));
        System.out.println(Operations.integral(pol));
        System.out.println();
        Map<Integer,Double> map =  Operations. getPairs("+x^3-3*x^2+6*x^1-4");
        System.out.println(map);
    }
}
