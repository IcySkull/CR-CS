import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BuildHeapTests {
    private BuildHeap heap;
    
    @Before
    public void setUp() throws Exception {
        heap = new BuildHeap();
    }

    @Test
    public void testHeapify1() throws IOException {
        heap.solve("tests/own1.txt");
        checkHeap(heap);
    }

    @Test
    public void testHeapify2() throws IOException {
        heap.solve("tests/own2.txt");
        checkHeap(heap);
    }

    @Test
    public void testHeapify3() throws IOException {
        heap.solve("tests/03.txt");
        checkHeap(heap);
    }

    @Test
    public void testHeapify4() throws IOException {
        heap.solve("tests/04.txt");
        checkHeap(heap);
    }

    public void checkHeap(BuildHeap heap) {
        int[] data = heap.getData();
        assert heap.getSwaps().size() < 4 * data.length;
        for (int i = 0; i < data.length; i++) {
            int leftChild = getLeftChild(i, data);
            int rightChild = getRightChild(i, data);
            if (leftChild != -1) {
                assert data[i] <= data[leftChild];
            }
            if (rightChild != -1) {
                assert data[i] <= data[rightChild];
            }
        }
    }

    private int getLeftChild(int parent, int[] data) {
        int leftChild = 2 * parent + 1;
        if (leftChild >= data.length) {
            return -1;
        }
        return leftChild;
    }

    private int getRightChild(int parent, int[] data) {
        int rightChild = 2 * parent + 2;
        if (rightChild >= data.length) {
            return -1;
        }
        return rightChild;
    }
}
