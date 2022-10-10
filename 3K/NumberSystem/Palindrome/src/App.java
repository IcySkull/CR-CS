import java.util.List;
import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) throws Exception {
        int it = 12;
        PalindromeBinary palindrome = new PalindromeBinary();
        while (it-- != 0) {
            System.out.println(palindrome);
            palindrome.next();
        }
    }
}
