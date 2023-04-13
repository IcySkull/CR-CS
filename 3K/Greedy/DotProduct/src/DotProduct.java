import java.util.*;

public class DotProduct {
    public static long maxDotProduct(int[] a, int[] b) {
        PriorityQueue<Integer> pqA = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> pqB = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < a.length; i++) {
            pqA.add(a[i]);
            pqB.add(b[i]);
        }

        long result = 0;

        while (!pqA.isEmpty()) {
            result += (long) pqA.poll() * pqB.poll();
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        System.out.println(maxDotProduct(a, b));
    }
}