import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

class MergeSort {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(5, 4, 3, 2, 1));
        divideAndConquerIteratively(MergeSort::merge, list);
        System.out.println(list);
    }

    static <T extends Comparable<T>> List<T> merge(List<T> left, List<T> right) {
        List<T> result = new ArrayList<>(left.size() + right.size());
        int leftIndex = 0;
        int rightIndex = 0;
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex).compareTo(right.get(rightIndex)) < 0) {
                result.add(left.get(leftIndex));
                leftIndex++;
            } else {
                result.add(right.get(rightIndex));
                rightIndex++;
            }
        }
        while (leftIndex < left.size()) {
            result.add(left.get(leftIndex));
            leftIndex++;
        }
        while (rightIndex < right.size()) {
            result.add(right.get(rightIndex));
            rightIndex++;
        }
        return result;
    }

    static <T> void divideAndConquerIteratively(BinaryOperator<List<T>> op, List<T> list) {
        List<List<T>> wrapper = list.stream().map(Collections::singletonList).collect(Collectors.toList());
        while (wrapper.size() != 1) {
            wrapper = groupPairs(op, wrapper);
        }
    }

static <T> List<List<T>> groupPairs(BinaryOperator<List<T>> mergeOperator, List<List<T>> list) {
        List<List<T>> result = new ArrayList<>(list.size() / 2);
        for (int i = 0; i < list.size(); i += 2) {
            if (i + 1 < list.size()) {
                result.add(mergeOperator.apply(list.get(i), list.get(i + 1)));
            } else {
                result.add(list.get(i));
            }
        }
        return result;
    }

        // for (int currSize = 1; currSize < n; currSize *= 2) {
        //     for (int leftStart = 0; leftStart < n; leftStart += 2 * currSize) {
        //         int mid = Math.min(leftStart + currSize - 1, n-1);
        //         int end = Math.min(leftStart + 2 * currSize - 1, n-1);
        //         List<T> left = list.subList(leftStart, mid + 1);
        //         List<T> right = list.subList(mid + 1, end + 1);
        //         op.apply(left, right);
        //     }
        // }

}