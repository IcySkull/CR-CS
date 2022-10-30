package musicPrimitives;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.DoubleStream;

public class Harmonic implements Iterable<Double> {
    private double[] string;

    public Harmonic(int length, int nodes, double amplitude) {
        string = new double[length];
        pluckHarmonic(nodes, amplitude);
    }

    public void pluckHarmonic(int nodes, double amplitude) {
        for (int i = 0; i < string.length; i++) {
            string[i] = amplitude * Math.sin((nodes+1) * (Math.PI * (i+1)/string.length) );
        }
    }

    public double sample() {
        return string[string.length-1];
    }

    public double sample(int index) {
        if (index < 0 || index >= string.length)
            throw new IllegalArgumentException("Index out of bounds, probably the Harmonic is not the same size as the RingBuffer");
        return string[index];
    }

    public DoubleStream getHarmonic() {
        return Arrays.stream(string);
    }

    @Override
    public Iterator<Double> iterator() {
        return getHarmonic().iterator();
    }
}