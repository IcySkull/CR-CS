public class HeapsDisplayer {
    public static void leftJustified(Heapable heap) {
        int upperBound = 1;
        int index = 0;
        while (index < heap.getSize()) {
            if (index == upperBound) {
                upperBound = getNextBound(upperBound);
                System.out.print("\n");
            }
            System.out.print(heap.get(index) + " ");
            index++;
        }
        System.out.println();
    }

    public static void centerJustified(Heapable heap) {
        int levels = heap.getLevel();
        int upperBound = 1;
        int index = 0;
        while (index < heap.getSize()) {
            int currLevel = heap.getLevel(index+1);
            if (index == upperBound) {
                upperBound = getNextBound(heap, upperBound);
                System.out.print("\n");
                System.out.print(" ".repeat(getMaxNodes(heap, currLevel-(index+1))));
            }
            System.out.print(heap.get(index) + " ".repeat(levels-currLevel));
            index++;
        }
        System.out.println();
    }

    private static int getNextBound(Heapable heap, int currLevel) {
        return heap.limitNodes(currLevel+1);
    }

    public static void main(String[] args) {
        Heap heap = new Heap();
        
        heap.add(1);
        heap.add(2);
        heap.add(8);
        heap.add(9);
        heap.add(10);
        heap.add(7);
        heap.add(75);
        heap.add(17);
        heap.add(5);

        leftJustified(heap);
        centerJustified(heap);
    }
}