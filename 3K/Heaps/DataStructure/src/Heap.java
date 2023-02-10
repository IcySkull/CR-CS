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

    private int getParentIndex(int bot) {
        if (bot == 0) {
            return -1;
        }
        return (bot-1)/2;
    }

    private void swapUp(int botIndex) {
        int parentIndex = getParentIndex(botIndex);
        if (parentIndex != -1 && data[botIndex] >= data[parentIndex]) {
            swap(botIndex, parentIndex);
            swapUp(parentIndex);
        }
    }

    public void remove() {
    }

    private void swapDown(int start, int stop) {

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
        out.println("\n\nPRINTING THE HEAP!\n\n");
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
        // heap.remove();
        // heap.print();
        // heap.remove();
        // heap.print();
        // heap.remove();
        // heap.print();
        // heap.remove();
        // heap.print();
        // heap.remove();
        // heap.print();
        // heap.remove();
        // heap.print();
        // heap.remove();

        // heap.print();
        // heap.add(25);
        // heap.print();
        // heap.add(35);
        // heap.print();
        // heap.remove();
        // heap.print();
    }
}