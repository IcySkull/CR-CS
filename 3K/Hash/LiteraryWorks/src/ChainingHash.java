import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ChainingHash implements Iterable<WordCount> {
    private int size;
    private int capacity;
    private List<WordCount>[] table;

    public ChainingHash() {
        size = 0;
        capacity = 97;
        table = new LinkedList[capacity];
    }

    public ChainingHash(int startSize) {
        size = 0;
        capacity = startSize;
        table = new LinkedList[capacity];
    }

    private void rehash() {
        capacity = findNextPrime(capacity * 2);
        List<WordCount>[] oldTable = table;
        table = new LinkedList[capacity];
        size = 0;
        for (List<WordCount> bucket : oldTable) {
            if (bucket == null)
                continue; 
            for (WordCount word : bucket)
                insert(word);
        }
    }

    private int findNextPrime(int n) {
        if (n % 2 == 0)
            n++;
        while (!isPrime(n)) {
            n += 2;
        }
        return n;
    }

    private boolean isPrime(int n) {
        if (n == 2 || n == 3)
            return true;
        if (n == 1 || n % 2 == 0)
            return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    /**
     * Adds the key to the hash table.
     * If there is a collision, it should be dealt with by chaining the keys
     * together.
     * If the key is already in the hash table, it increments that key's counter.
     * 
     * @param keyToAdd : the key which will be added to the hash table
     */
    public void insert(String keyToAdd) {
        insert(new WordCount(keyToAdd));
    }

    public void insert(WordCount key) {
        if (size > capacity / 4 * 3)
            rehash();

        WordCount wordInTable = get(key.getWord());
        if (wordInTable != null) {
            wordInTable.incrementCount(key.getCount());
            return;
        }

        size++;
        int index = map(key.getWord());
        List<WordCount> bucket = table[index];
        if (bucket == null) {
            table[index] = new LinkedList<WordCount>();
            table[index].add(key);
        } else
            bucket.add(key);
    }

    public WordCount get(String key) {
        List<WordCount> bucket = table[map(key)];
        if (bucket == null)
            return null;

        int indexOfKey = bucket.indexOf(new WordCount(key));
        if (indexOfKey == -1)
            return null;

        return bucket.get(indexOfKey);
    }

    /**
     * Returns the number of times a key has been added to the hash table.
     * 
     * @param keyToFind : The key being searched for
     * @return returns the number of times that key has been added.
     */
    public int findCount(String keyToFind) {
        WordCount word = get(keyToFind);
        if (word == null)
            return 0;
        return word.getCount();
    }

    /**
     * Returns the number of keys in the hash table.
     * 
     * @return return keys
     */
    public int getSize() {
        return size;
    }

    private int map(String key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (List<WordCount> bucket : table) {
            if (bucket == null)
                continue;
            sb.append(bucket);
            sb.append("\n");
        }
        return sb.toString();
    }

    public Stream<WordCount> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    @Override
    public Iterator<WordCount> iterator() {
        return new ChainingHashIterator();
    }

    private class ChainingHashIterator implements Iterator<WordCount> {
        private int indexTable;
        private int totalChecked;
        private Iterator<WordCount> bucket;

        public ChainingHashIterator() {
            indexTable = 0;
            totalChecked = 0;
        }

        @Override
        public boolean hasNext() {
            return totalChecked < getSize();
        }

        @Override
        public WordCount next() {
            if (bucket == null || !bucket.hasNext()) {
                while (table[indexTable] == null) {
                    indexTable++;
                }
                bucket = table[indexTable].iterator();
                indexTable++;
            }
            totalChecked++;
            return bucket.next();
        }
    }
}
