import java.io.File;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

public class Scheduling {

    public static long weightedSum(String file) {
        try (Scanner scanner = new Scanner(new File(file))) {
            int n = scanner.nextInt();
            int[] weights = new int[n];
            int[] lengths = new int[n];
            for (int i = 0; i < n; i++) {
                weights[i] = scanner.nextInt();
                lengths[i] = scanner.nextInt();
            }
            return weightedSum(weights, lengths);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static long weightedSum(int[] weights, int[] lengths) {
        AtomicInteger completionTime = new AtomicInteger(0);
        return IntStream.range(0, weights.length)
                .sequential()
                .boxed()
                .sorted(
                    Comparator.comparingDouble((Integer i) -> ((double) weights[i]) / lengths[i])
                        .reversed()
                ).reduce(
                    0L,
                    (acc, i) -> acc + weights[i] * completionTime.addAndGet(lengths[i]),
                    Long::sum
                );
    }

    public static void main(String[] args) throws Exception {
        System.out.println(weightedSum("jobs.txt"));
    }
}