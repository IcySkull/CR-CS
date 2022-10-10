import java.util.Iterator;

public class PalindromeBinary implements Iterator {
    int pal;

    public PalindromeBinary(int seed) {
        this.pal = seed;
    }

    public PalindromeBinary() {
        this.pal = 0;
    }

    public static int nextPal(int pal) {
        int cb = cBit(pal);
        if (pal == 0)
            return 1;
        else if (pal == getMaskBit(cb)) {
            return pal + 2;
        }

        int middlePos = getMiddlePos(pal);
        int middleBit = getBit(middlePos);
        if (cb % 2 == 1) {
            if ((pal & middleBit) > 0) {
                pal += middleBit + getRightBit(middlePos);
            } else {
                pal += middleBit;
            }
        } else {
            if ((pal & middleBit) > 0) {
                pal += middleBit + getRightBit(middlePos);
            } else {
                pal += getLeftBit(middlePos) + middleBit;
            }
        }
        return pal;
    }

    public static int getMaskBit(int position) {
        return (int) Math.pow(2, position)-1;
    }

    public static int getMiddlePos(int highBit) {
        double highPos = Math.log(highBit) / Math.log(2);
        return (int)highPos/2;
    }

    public static int getBit(int pos) {
        return (int)Math.pow(2, pos);
    }

    public static int getLeftBit(int pos) {
        return getBit(pos) << 1;
    }

    public static int cBit(int bit) {
        return (int)(Math.log(bit) / Math.log(2)) +1;
    }

    public static int getRightBit(int pos) {
        return getBit(pos) >> 1;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Object next() {
        this.pal = nextPal(pal);
        return this.pal;
    }
    
    @Override
    public String toString() {
        return Integer.toBinaryString(pal);
    }
}
