import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FractionalKnapsack {
    public static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;

        PriorityQueue<Integer> profitsIndex = getProfitsIndex(values, weights);

        while (!profitsIndex.isEmpty()) {
            int next = profitsIndex.poll();

            double fits = ((double)capacity) / weights[next];

            if (fits > 1) {
                value += values[next];
                capacity -= weights[next];
            } else {
                value += fits * values[next];
                return value;
            }
        }

        return value;
    }

    private static PriorityQueue<Integer> getProfitsIndex(int[] values, int[] weights) {
        return IntStream.range(0, values.length)
            .boxed()
            .collect(Collectors.toCollection(() ->
                new PriorityQueue<>(
                    Comparator.comparing(i ->
                        ((double)weights[i]) / values[i]
                    )
                )
            ));
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }
} 
