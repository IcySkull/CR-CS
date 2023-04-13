import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CoveringSegments {

    public static int[] optimalPoints(Segment[] segments) {
        Map<Integer, Set<Segment>> connectedSegments = getConnectedSegments(segments);
        
        System.out.println();
        System.out.println("NEW TEST CASE");
        System.out.println("size: " + segments.length);
        if (segments.length < 100)
            System.out.println("segments: " + Arrays.toString(segments));

        PriorityQueue<Integer> schedule = connectedSegments.keySet().stream()
                .collect(Collectors.toCollection(
                    () -> new PriorityQueue<>(Comparator.comparingInt(
                        point -> connectedSegments.get(point).size()
                    ).reversed())
        ));

        Set<Segment> coveredSegments = new HashSet<>();
        List<Integer> points = new ArrayList<>();

        while (!schedule.isEmpty()) {
            int point = schedule.poll();
            Set<Segment> segmentsAtPoint = connectedSegments.get(point);

            if (coveredSegments.containsAll(segmentsAtPoint))
                continue;

            points.add(point);
            coveredSegments.addAll(segmentsAtPoint);
        }

        return points.stream().mapToInt(i -> i).toArray();
    }

    private static Map<Integer, Set<Segment>> getConnectedSegments(Segment[] segments) {
        Map<Integer, Set<Segment>> connectedSegments = new HashMap<>();

        for (Segment segment : segments) {
            for (int point = segment.start; point <= segment.end; point++) {
                connectedSegments.putIfAbsent(point, new HashSet<>());
                connectedSegments.get(point).add(segment);
            }
        }

        return connectedSegments;
    }

    public static class Segment {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public boolean in(int point) {
            return start <= point && point <= end;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", start, end);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
 
