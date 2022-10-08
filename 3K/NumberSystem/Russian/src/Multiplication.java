import javax.xml.crypto.KeySelector.Purpose;

public class Multiplication {
    public static void main(String[] args) {
        System.out.println(mult(9, 9));
    }

    public static int mult(int a, int b) {
        int product = 0;
        while (b > 0) {
            if ((b & 1) == 1)
                product += a;
            a = a << 1;
            b = b >> 1;
        }
        return product;
    }
}
