import java.util.Iterator;

public class InventoryGroup implements Iterable<LetterInventory>{
    private String word;
    private LetterInventory item;
    private int count;

    InventoryGroup(String word) {
        this.word = word;
        item = new LetterInventory(word);
        count = 1;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void increment() {
        count += 1;
    }

    public LetterInventory get() {
        count--;
        return item;
    }

    public void decrement() {
        count -= 1;
    }

    @Override
    public Iterator<LetterInventory> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<LetterInventory> {
        @Override
        public boolean hasNext() {
            return count > 0;
        }

        @Override
        public LetterInventory next() {
            return get();
        }
    }

    @Override
    public String toString() {
        return word;
    }
}
