public class WordCount {
    private String word;
    private int count;

    public WordCount(String word) {
        this.word = word;
        this.count = 1;
    }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        count++;
    }

    public void incrementCount(int count) {
        this.count += count;
    }

    @Override
    public int hashCode() {
        return word.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WordCount)
            return word.equals(((WordCount) obj).getWord());
        else if (obj instanceof String)
            return word.equals(obj);
        return false;
    }

    @Override
    public String toString() {
        return "(" + word + ", " + count + ")";
    }
}