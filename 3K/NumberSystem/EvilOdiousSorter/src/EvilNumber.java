public class EvilNumber implements Comparable<EvilNumber> {
    private int bits;
    private int value;

    public EvilNumber(int value) {
        this.value = value;
        this.bits = countBits(value);
    }

    public int countBits(int value) {
        int absValue = value < 0 ? -value : value;
        int bits = 0;
        while (absValue != 0) {
            bits++;
            absValue &= absValue - 1;
        }
        return bits;
    }

    public int getValue() {
        return value;
    }

    public int getBits() {
        return bits;
    }

    @Override
    public int compareTo(EvilNumber o) {
        EvilNumber other = (EvilNumber) o;
        if (this.getBits() % 2 == 1) {
            return other.getBits() % 2 == 1 ? this.getValue() - other.getValue() : 1;
        }
        return other.getBits() % 2 == 0 ? this.getValue() - other.getValue() : -1; 
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
