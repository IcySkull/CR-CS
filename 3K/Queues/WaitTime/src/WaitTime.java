import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WaitTime {
    public static void main(String[] args) {
        System.out.println(waitTime(new int[]{5,3,4}, 1)); // 12
        System.out.println(waitTime(new int[]{10,2,3,3}, 2)); // 10
        System.out.println(waitTime(new int[]{2,3,10}, 2)); // 12
        
        System.out.println(waitTime(new int[]{17, 2, 3, 6, 7, 8, 9, 4, 11, 22, 45, 1, 2, 3, 
            4, 5, 6, 7, 53, 3, 2, 34, 42, 3, 3, 4, 4}, 6)); // 70

        System.out.println(waitTime (new int[]{1, 2, 54, 12, 6, 7, 8, 4, 12, 17, 2, 3, 6, 7, 
            8, 9, 12, 13, 11, 17, 22, 6, 5, 4, 3, 7, 8, 9, 12, 3, 4, 5, 6, 7, 15, 14, 1, 9, 8, 
            7, 8, 9, 8, 44, 4, 11, 22, 45, 1, 2, 3, 9, 5, 4, 3, 2, 5, 6, 7, 8, 9, 5, 5, 4, 5, 6, 
            7, 53, 3, 2, 34, 42, 3, 3, 4, 4}, 13)); // 92
    }

    public static int waitTime(int[] customers, int n) {
        List<Integer> listCusto = getList(customers);
        List<Integer> workingTime = listCusto.subList(0, n);
        Queue<Integer> waitTime = new LinkedList<>(listCusto.subList(n, listCusto.size()));
        return waitTime(workingTime, waitTime);
    }

    public static int waitTime(List<Integer> tellers, Queue<Integer> waiting) {
        if (waiting.isEmpty()) 
            return Collections.max(tellers);

        int min = Collections.min(tellers);
        return min + waitTime(
            tellers.stream()
                .map(m -> {
                    if (m == min) {
                        if (waiting.isEmpty())
                            return 0;
                        return waiting.poll();
                    }
                    return m - min;
                })
            .collect(Collectors.toList()), 
            waiting);
    }

    public static List<Integer> getList(int[] arr) {
        List<Integer> list = new ArrayList<>(arr.length);
        for (int i : arr) {
            list.add(i);
        }
        return list;
    }
}