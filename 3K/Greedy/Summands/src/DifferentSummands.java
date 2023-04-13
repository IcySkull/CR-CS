import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DifferentSummands {
    public static List<Integer> optimalSummands(int n) {
        return optimalSummands(1, n);
    }

    private static List<Integer> optimalSummands(int l, int k) {
        return k <= 2 * l ? List.of(k) : Stream.concat(
                Stream.of(l),
                optimalSummands(l + 1, k - l).stream()
        ).collect(Collectors.toList());
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}

