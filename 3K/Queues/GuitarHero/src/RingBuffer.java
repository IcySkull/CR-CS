import java.util.*;

public class RingBuffer {
    private double[] ary;
    private int capacity;
    private int size;
    private int first;
    private int last;
    
    public RingBuffer(int capacity) {
        this.capacity = capacity;
        ary = new double[capacity];
        first = 0;
        last = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public void enqueue(double x) {
        if (isFull())
            throw new IllegalStateException();
        size++;
        ary[last] = x;
    }

    public double dequeue() {
        if (size == 0)
            throw new NoSuchElementException();
        double ret = ary[first];
        incrementFirst();
        return ret;
    }

    public double peek() {
        if (size == 0)
            throw new NoSuchElementException();
        return ary[first];
    }

    public boolean incrementLast() {
        last++;
        if (last == capacity) {
            last = 0;
            return true;
        }
        return false;
        
    }

    public int decrementLast(int last) {
        last--;
        if (last == -1) {
            last = capacity-1;
        }
        return last;
    }

    public boolean incrementFirst() {
        first++;
        if (first == capacity) {
            first = 0;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String out = "[";
        while (first != decrementLast(last)) {
            out += ary[first] + ", ";
            incrementFirst();
        }
        out += ary[first] + "]";
        return out;
    }
}
