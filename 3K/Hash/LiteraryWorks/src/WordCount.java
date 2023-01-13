public class WordCount {
    private String word;
    private Integer count;

    public WordCount(String word) {
        this.word = word;
        this.count = 1;
    }

    public WordCount(String word, Integer count) {
        this.word = word;
        this.count = count;
    }

    public WordCount() {
    }

    public String getWord() {
        return word;
    }

    public Integer getCount() {
        return count;
    }

    public void incrementCount() {
        count++;
    }

    public void decrementCount() {
        count--;
    }

    public void decrementCount(int count) {
        this.count -= count;
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
            return word.equals((String) obj);
        return false;
    }

    @Override
    public String toString() {
        return "(" + word + ", " + count + ")";
    }
}