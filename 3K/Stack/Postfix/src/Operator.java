public class Operator implements MathSymbol {
    public char op;

    public Operator(char op) {
        this.op = op;
    }

    @Override
    public String toString() {
        return String.valueOf(op);
    }
}
