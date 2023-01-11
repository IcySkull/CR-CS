import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class QPHash implements Iterable<String> {
    private int size; 
    private int capacity; 
    private WordCount[] table; 

    public QPHash() {
        capacity = 97;
        table = new WordCount[capacity];
        size = 0;
    }

    public QPHash(int startSize) {
        capacity = startSize;
        table = new WordCount[capacity];
        size = 0;
    }

    public QPHash(String[] words) {
        this();
        for (String word : words) {
            insert(word);
        }
    }

    private void rehash() {
        capacity = findNextPrime(capacity * 2);
        WordCount[] oldTable = table;
        table = new WordCount[capacity];
        size = 0;
        for (WordCount word : oldTable) {
            if (word != null) {
                insert(word);
            }
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
     * If there is a collision, a new location should be found using quadratic
     * probing.
     * If the key is already in the hash table, it increments that key's counter.
     * 
     * @param keyToAdd : the key which will be added to the hash table
     */
    public void insert(Object keyToAdd) {
        if (keyToAdd instanceof String)
            insert((String) keyToAdd);
        else if (keyToAdd instanceof WordCount)
            insert((WordCount) keyToAdd);
        else
            throw new IllegalArgumentException("Key must be a String or WordCount");
    }

    public void insert(String keyToAdd) {
        insert(new WordCount(keyToAdd));
    }

    public void insert(WordCount word) {
        WordCount wordInTable = get(word.getWord());
        if (wordInTable != null) {
            wordInTable.incrementCount(word.getCount());
            return;
        }
        size++;
        int index = map(word.getWord());
        table[index] = word;
        if (size > capacity / 2)
            rehash();
    }

    public WordCount get(String keyToFind) {
        return table[map(keyToFind)];
    }

    public int map(String key) {
        int i = 0;
        int hash = Math.abs(key.hashCode());
        int index = (hash + i * i) % capacity;
        while (table[index] != null) {
            if (table[index].equals(new WordCount(key)))
                return index;
            i++;
            index = (hash + i * i) % capacity;
        }
        return index;
    }

    /**
     * Returns the number of times a key has been added to the hash table.
     * 
     * @param keyToFind : The key being searched for
     * @return returns the number of times that key has been added.
     */
    public int findCount(Object keyToFind) {
        if (!(keyToFind instanceof String))
            throw new IllegalArgumentException("Key must be a String");
        String key = (String) keyToFind;
        WordCount word = get(key);
        if (word != null)
            return word.getCount();
        return 0;
    }

    /**
     * Returns the number of keys in the hash table.
     * 
     * @return return keys
     */
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (WordCount word : table) {
            if (word != null)
                sb.append(word + " ");
        }
        return sb.toString();
    }

    public Stream<String> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    @Override
    public Iterator<String> iterator() {
        return new QPHashIterator();
    }

    private class QPHashIterator implements Iterator<String> {
        private int indexTable;
        private int totalChecked;

        public QPHashIterator() {
            indexTable = 0;
        }

        @Override
        public boolean hasNext() {
            return totalChecked < size;
        }

        @Override
        public String next() {
            if (hasNext()) {
                while (table[indexTable] == null) {
                    indexTable++;
                }
                totalChecked++;
                return table[indexTable++].getWord();
            }
            return null;
        }
    }
}
