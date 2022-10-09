import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) throws Exception {
        IntStream.range(-10, 10).forEach(n -> {
            int radix = 8;
            System.out.println(radix + " + " + n + ": " + add(radix, n) + " diff: " + ((radix+n)-add(radix, n)));
        });

        System.out.println(add(-5, -8));
    }

    public static int add(int a, int b) {
        return b != 0 ? add(a^b, (a&b) << 1) : a;
    }
}
