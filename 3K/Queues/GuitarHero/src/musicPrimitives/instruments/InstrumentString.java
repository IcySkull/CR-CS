package musicPrimitives.instruments;

import java.util.*;

import javax.sound.midi.Instrument;

import dataStructures.RingBuffer;
import musicPrimitives.Harmonic;

public class InstrumentString {
    public RingBuffer ringBuffer;
    public static final int SAMPLE_RATE = 44100; // 44100 samples per second
    public static double ENERGY_DECAY_FACTOR = 0.994; // 0.994;
    public static final int NATURAL_TICKS = 40;
    private int ticCount;
    private int capacity;
    private char key;
    private Instruments instrument;

    public InstrumentString(double frequency, char key, Instruments instrument) {
        capacity = (int) Math.round(SAMPLE_RATE / frequency);
        this.key = key;
        this.instrument = instrument;
        pluckZero();
    }

    public char getKey() {
        return key;
    }

    public RingBuffer getRingBuffer() {
        return ringBuffer;
    }

    private List<Harmonic> getHarmoics() {
        List<Harmonic> harmonics = new ArrayList<Harmonic>();
        double[] ampHarmonics = instrument.getHarmonicsAmpList();
        for (int i = 0; i < ampHarmonics.length; i++) {
            harmonics.add(new Harmonic(capacity, i, ampHarmonics[i]/12.0));
        }
        return harmonics;
    }

    public void pluckZero() {
        ringBuffer = new RingBuffer(capacity);
        for (int i = 0; i < capacity; i++) {
            ringBuffer.enqueue(0);
        }
    }

    public void pluck() {
        ringBuffer = new RingBuffer(capacity);
        addHarmonics(getHarmoics());
    }

    public void addHarmonics(List<Harmonic> harmonics) {
        for (int i = 0; i < capacity; i++) {
            double sample = 0;
            for (Harmonic harmonic : harmonics) {
                sample += harmonic.sample(i);
            }
            ringBuffer.enqueue(fixHigh(sample));
        }
    }

    public static double fixHigh(double sample) {
        if (sample > 1.0)
            return Math.sqrt(sample);
        return sample;
    }

    public static double evenSamples(double first, double second) {
        double out = (first + second) / 2;
        return fixHigh(out);
    }

    public static double addSamples(double first, double second) {
        double out = first + second;
        return fixHigh(out);
    }

    public double sample() {
        return ringBuffer.peek();
    }

    public void tic() {
        ticCount++;
        double first = ringBuffer.dequeue();
        double second = ringBuffer.peek();
        double average = (first + second) / 2;
        double out = average * ENERGY_DECAY_FACTOR;
        ringBuffer.enqueue(out);
    }

    public int time() {
        return ticCount;
    }
    
    @Override
    public String toString() {
        return String.valueOf(key);
    }
}
