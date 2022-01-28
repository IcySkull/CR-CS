package hard8;

import java.util.List;

public class Hard8 {

    public static <T extends Comparable<? super T>> void selectionSort(List<T> list, T target) {
        for (int i = 0; i<list.size(); i++) {
            int targetIndex = i;
            for (int j = 1 + i; j<list.size(); j++) {
                if (list.get(j).equals(target)) {
                    targetIndex = j;
                }
            }


            swap(list, targetIndex, i);
        }
    }

    private static <T> void swap(List<T> list, int a, int b) {
        T tmp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, tmp);
    }
}
