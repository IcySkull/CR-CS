import java.util.Arrays;

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
     * @param arr of integers
     */
    public static void insertSort(int[] arr) {
        //todo
    }

    /**
     * For each index, find the smallest element and perform one swap. Runs in
     * 0(N^2) for all cases but performs at most N-1 swaps.
     *
     * @param arr
     */
    public static void heapSort(int[] arr) {
        Integer size = arr.length;
        int[] sorted = new int[size];
        ensureHeap(arr, 0);
        int index = 0;
        System.out.println(Arrays.toString(arr));
        System.out.println(isHeap(arr));
        while (size > 0) {
            int next = remove(arr, size--);
            sorted[index++] = next;
        }
    }

    public static boolean isHeap(int[] arr) {
        for (int parent = 0; parent < arr.length; parent++) {
            int leftChild = getLeftChild(arr.length, parent);
            int rightChild = getRightChild(arr.length, parent);
            if (leftChild != -1 && arr[parent] > arr[leftChild])
                return false;
            else if (rightChild != -1 && arr[parent] > arr[rightChild])
                return false;
        }
        return true;
    }

    public static int remove(int[] data, int size) {
        if (size <= 0)
            throw new IllegalStateException();
        size--;
        int removed = data[0];
        data[0] = data[size];
        swapDown(data, 0, size);
        return removed;
    }

    private static void swapDown(int[] data, int start, int stop) {
        int leftIndex = getLeftChild(stop, start);
        int rightIndex = getRightChild(stop, start);
        int maxIndex = start;
        if (leftIndex != -1 && data[leftIndex] >= data[maxIndex]) {
            maxIndex = leftIndex;
        }
        if (rightIndex != -1 && data[rightIndex] >= data[maxIndex]) {
            maxIndex = rightIndex;
        }
        if (maxIndex != start) {
            swap(data, start, maxIndex);
            swapDown(data, maxIndex, stop);
        }
    }

    private static void ensureHeap(int[] data, int parent) {
        if (parent == -1)
            return;
        int leftChild = getLeftChild(data.length, parent);
        int rightChild = getRightChild(data.length, parent);
        int smallestChild = parent;

        ensureHeap(data, leftChild);
        ensureHeap(data, rightChild);

        if (leftChild != -1 && data[leftChild] < data[smallestChild])
            smallestChild = leftChild;
        if (rightChild != -1 && data[rightChild] < data[smallestChild])
            smallestChild = rightChild;
        
        if (smallestChild != parent) {
            swap(data, parent, smallestChild);
            ensureHeap(data, smallestChild);
        }
    }

    private static int getLeftChild(int size, int parent) {
        int leftChild = 2 * parent + 1;
        if (leftChild >= size) {
            return -1;
        }
        return leftChild;
    }

    private static int getRightChild(int size, int parent) {
        int rightChild = 2 * parent + 2;
        if (rightChild >= size) {
            return -1;
        }
        return rightChild;
    }

    private static void swap(int[] data, int aIndex, int bIndex) {
        int tmp = data[aIndex];
        data[aIndex] = data[bIndex];
        data[bIndex] = tmp;
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
     * @param arr
     */
    public static void mergeSort(int[] arr) {
        //todo
    }

    private static void mergeSortRecursive(int[] arr, int begin, int end) {
        //todo
    }

    // merge two portions that are sorted respectively by creating an auxilliary
    // array as a place holder then copy it back
    private static void merge(int[] arr, int start, int middle, int end) {
        //todo
    }
}
