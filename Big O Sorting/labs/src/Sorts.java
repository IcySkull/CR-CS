
/**
 *
 * @author Your name
 */
public class Sorts {

    /**
     * Classical insertion sort algorithm that runs in 0(N^2) unless the array
     * is sorted in which the best case is O(N). Conceptually the algorithm is
     * similar to sorting a hand of cards as the dealer deals each card. Each
     * time you pick up a card you shift all cards over that are less than that
     * card then insert the new card in the appropriate location. Hence the name
     * insertion sort.
     *
     * @param arr array of integers to be sorted
     */
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && arr[j] < arr[j-1]) {
                swap(arr, j-1, j);
                j--;
            }
        }
    }

    /**
     * For each index, find the smallest element and perform one swap. Runs in
     * 0(N^2) for all cases but performs at most N-1 swaps.
     *
     * @param arr array of integers to be sorted
     */
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length -1; i++) {
            int minIndex = i;
            int j;
            for (j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, minIndex, i);
        }
    }

    private static void swap(int[] arr, int firstIndex, int secondIndex) {
        int tmp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = tmp;
    }

    /**
     * Divide and Conquer algorithm that picks a pivot and moves all elements
     * that are less than the pivot to the left side and similarly all elements
     * larger than the pivot to the right. Repeat the process on both sides. The
     * algorithm is linearithmic O(nlogn) but can degenerate if a poorly chosen
     * pivot is selected. Choose a randomized pivot to help prevent this. The 
     * algorithm is quite fast as the name suggests, takes advantage of cache hits 
     * but it is not stable(no impact on ints).
     *
     * @param arr
     */
    public static void quickSort(int[] arr) {
        //todo
    }

    private static void quickSortRecursive(int[] arr, int left, int right) {
        //todo
    }

    private static int pivot(int[] arr, int left, int right) {
        //todo
      
        return -1;
    }

    /**
     * Divide and conquer algorithm that repeatedly divides the array into
     * halves then merges the halves together. The algorithm is stable and runs
     * in O(nlogn) in all cases. It's an out of place algorithm since a temporary
     * array is used in the merge method.
     *
     * @param arr array of int to be sorted
     */
    public static void mergeSort(int[] arr) {
        mergeSortRecursive(arr, 0, arr.length-1);
    }

    private static void mergeSortRecursive(int[] arr, int begin, int end) {
        if (begin < end) {
            int middle = begin + (end - begin)/2;
            mergeSortRecursive(arr, begin, middle);
            mergeSortRecursive(arr, middle+1, end);
            merge(arr, begin, middle, end);
        }
    }

    // merge two portions that are sorted respectively by creating an auxilliary
    // array as a place holder then copy it back
    private static void merge(int[] arr, int start, int middle, int end) {
        int leftLen = middle - start + 1;
        int rightLen = end - middle;

        int[] leftArr = new int[leftLen];
        int[] rightArr = new int[rightLen];

        for (int i = 0; i < leftLen; i++)
            leftArr[i] = arr[start + i];
        for (int j = 0; j < rightLen; j++)
            rightArr[j] = arr[middle + j + 1];

        int i = 0, j = 0, k = start;
        while (i < leftLen && j < rightLen) {
            if (leftArr[i] < rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < leftLen) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < rightLen) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }
}
