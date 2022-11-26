import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class MergeInversions {
    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> bigList = Arrays.asList(90,40,20,30,10,67,3,15,61,11,7,9,2,5,4,6,8,1,0,50,23,12,54,76,90,34);
        long count = countInversions(bigList);
        System.out.println(bigList);
        System.out.println(count);
    }

    static <T extends Comparable<T>> long countInversions(List<T> list) {
        int count = 0;
        if (list.size() == 0 || list.size() == 1)
            return 0;

        count += countInversions(list.subList(0, list.size()/2));
        count += countInversions(list.subList(list.size()/2, list.size()));
        return count + mergeSorted(list);
    }

    static long countInversions(int[] list) {
        return countInversions(Arrays.stream(list).boxed().collect(Collectors.toList()));
    }

    static <T extends Comparable<T>> long mergeSorted(List<T> list) {
        int count = 0;
        int partition = list.size()/2;
        int i = 0;
        int j = partition;
        while (i != j && i < list.size() && j < list.size()) {
            T a = list.get(i);
            T b = list.get(j);
            if (a.compareTo(b) > 0) {
                list.set(i, b);
                list.set(j, a);
                count += j - i;
                if (j+1 == list.size())
                    count += mergeSorted(list);
                else if (list.get(j+1).compareTo(a) < 0)
                    j++;
            }
            i++;
        }
        return count;
    }

    static long mergeSorted(int[] ary){
        return mergeSorted(Arrays.stream(ary).boxed().collect(Collectors.toList()));
    }
}
