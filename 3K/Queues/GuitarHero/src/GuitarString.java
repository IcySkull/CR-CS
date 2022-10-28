public class GuitarString {
    public RingBuffer ringBuffer;
    public static final int SAMPLE_RATE = 44100; // 44100 samples per second
    public static double ENERGY_DECAY_FACTOR = 0.994; // 0.994;
    private int ticCount;
    private int capacity;

    public GuitarString(double frequency) {
        capacity = (int) Math.round(SAMPLE_RATE / frequency);
        pluck();
    }

    public void pluck() {
        ringBuffer = new RingBuffer(capacity);
        while (!ringBuffer.isFull()) {
            ringBuffer.enqueue(Math.random()-0.5);
        }
    }

    public double sample() {
        return ringBuffer.peek();
    }

    public void tic() {
        ticCount++;
        double first = ringBuffer.dequeue();
        double second = ringBuffer.peek();
        double average = (first + second) / 2;
        ringBuffer.enqueue(average * ENERGY_DECAY_FACTOR);
    }

    public int time() {
        return ticCount;
    }
    
}
