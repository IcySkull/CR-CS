import static java.lang.System.out;


public class Heap {

    private int[] data;
    private int size;

    public Heap() {
        this(10);
    }

    public Heap(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    private void resize() {
        int capacity = data.length*2;
        int[] newData = new int[capacity];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    public void add(int value) {
        size++;
        if (size > data.length) {
            resize();
        }
        data[size-1] = value;
        swapUp(size-1);
    }

    private void swapUp(int botIndex) {
        int parentIndex = getParentIndex(botIndex);
        if (parentIndex != -1 && data[botIndex] >= data[parentIndex]) {
            swap(botIndex, parentIndex);
            swapUp(parentIndex);
        }
    }

    private int getParentIndex(int bot) {
        if (bot == 0) {
            return -1;
        }
        return (bot-1)/2;
    }

    public void remove() {
        if (size <= 0)
            throw new IllegalStateException();
        size--;
        data[0] = data[size];
        swapDown(0, size);
    }

    public double logb(int base, int n) {
        return Math.log(n) / Math.log(base);
    }

    private int indexMin() {
        int min = getFirstBot();
        for (int i = min+1; i < size; i++) {
            if (data[i] < data[min])
                min = i;
        }
        return min;
    }

    private int getFirstBot() {
        int prevLevel = (int) (Math.log(size) / Math.log(2));
        return (int) Math.round(Math.pow(2, prevLevel) - 1);
    }

    private void swapDown(int start, int stop) {
        int leftIndex = getLeftChildIndex(start);
        int rightIndex = getRightChildIndex(start);
        int maxIndex = start;
        if (leftIndex != -1 && data[leftIndex] >= data[maxIndex]) {
            maxIndex = leftIndex;
        }
        if (rightIndex != -1 && data[rightIndex] >= data[maxIndex]) {
            maxIndex = rightIndex;
        }
        if (maxIndex != start) {
            swap(start, maxIndex);
            swapDown(maxIndex, stop);
        }
    }

    private int getLeftChildIndex(int parent) {
        int pos = parent*2+1;
        if (pos >= size) {
            return -1;
        }
        return pos;
    }

    private int getRightChildIndex(int parent) {
        int pos = parent*2+2;
        if (pos >= size) {
            return -1;
        }
        return pos;
    }

    // simple helper method that swaps values at indices loc1 and loc2
    private void swap(int loc1, int loc2) {
        int tmp = data[loc1];
        data[loc1] = data[loc2];
        data[loc2] = tmp;
    }

    private void doubleData() {

    }

    // part 2
    public void print() {
        out.println("\nPRINTING THE HEAP!");
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        String out = "";
        for (int i = 0; i<size; i++) {
            out += data[i] + " ";
        }
        return out;
    }

    public static void main(String... a) {
        Heap heap = new Heap();

        // test add and remove here
        
        heap.add(1);
        heap.add(2);
        heap.add(8);
        heap.add(9);
        heap.add(10);
        heap.add(7);
        heap.add(75);
        heap.add(17);
        heap.add(5);

        heap.print();
        heap.remove();
        heap.print();
        heap.remove();
        heap.print();
        heap.remove();
        heap.print();
        heap.remove();
        heap.print();
        heap.remove();
        heap.print();
        heap.remove();
        heap.print();
        heap.remove();

        heap.print();
        heap.add(25);
        heap.print();
        heap.add(35);
        heap.print();
        heap.remove();
        heap.print();
    }
}
