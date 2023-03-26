package functions;

public class DivisionResult {
    private Function quotient;
    private Function remainder;
    public DivisionResult(Function quotient, Function remainder) {
        this.quotient = quotient;
        this.remainder = remainder;
    }

    public Function getQuotient() {
        return quotient;
    }
    public Function getRemainder() {
        return remainder;
    }

    @Override
    public String toString() {
        return "Quotient: " + quotient + " | Remainder:" + remainder;
    }
}
