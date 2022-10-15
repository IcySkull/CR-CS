public class Number implements MathSymbol {
    public double n;

    public Number(char number) {
        n = Character.getNumericValue(number);
    }

    public Number(int number) {
        n = number;
    }

    @Override
    public String toString() {
        return String.valueOf(n);
    }
}
