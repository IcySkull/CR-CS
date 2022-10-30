package dataStructures;
public class KeyTuple {
    private char key;
    private double frequency;

    public KeyTuple(char key, double frequency) {
        this.key = key;
        this.frequency = frequency;
    }

    public char getKey() {
        return key;
    }

    public double getFrequency() {
        return frequency;
    }
}
