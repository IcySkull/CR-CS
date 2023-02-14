public class HeapsDisplayer {
    public static void leftJustified(Heapable heap) {
        int upperBound = 1;
        int index = 0;
        while (index < heap.getSize()) {
            if (index == upperBound) {
                upperBound = getNextBound(heap, index);
                System.out.print("\n");
            }
            System.out.print(heap.get(index) + " ");
            index++;
        }
        System.out.println();
        System.out.println();
    }

    public static void centerJustified(Heapable heap) {
        int levels = heap.getLevel(heap.getSize());
        int upperBound = 1;
        int index = 0;
        System.out.print(" ".repeat(getSpacing(levels, 1)));
        while (index < heap.getSize()) {
            int currLevel = heap.getLevel(index+1);

            // if new level is reached add the spaces to start printing the new level
            if (index == upperBound) {
                upperBound = getNextBound(heap, upperBound);
                System.out.print("\n");
                System.out.print(" ".repeat(getSpacing(levels, currLevel)));
            }

            System.out.print(heap.get(index) + " ".repeat(getSpacing(levels, currLevel-1)));
            index++;
        }
        System.out.println();
        System.out.println();
    }

    private static int getSpacing(int levels, int currLevel) {
        return (int)Math.pow(2, levels-currLevel)-1;
    }


    private static int getNextBound(Heapable heap, int size) {
        return heap.maxNodes(heap.getLevel(size)+1);
    }

    public static void main(String[] args) {
        Heap heap = new Heap();
        
        heap.add(1);
        heap.add(2);
        heap.add(8);
        heap.add(9);
        heap.add(6);
        heap.add(7);
        heap.add(3);
        heap.add(4);
        heap.add(5);
        heap.add(10);

        leftJustified(heap);
        centerJustified(heap);
    }
}