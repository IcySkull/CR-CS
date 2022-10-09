import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) throws Exception {
        int pal = 0;
        int i = 12;
        while (i != 0) {
            System.out.println(Integer.toBinaryString(pal));
            pal = nextPal(pal);
            i--;
        }
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
}
