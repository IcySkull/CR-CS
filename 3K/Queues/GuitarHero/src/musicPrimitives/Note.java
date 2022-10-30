package musicPrimitives;

public class Note {
    private int distance;
    private PulseNote pulses;

    public Note(int distance, PulseNote pulses) {
        this.distance = distance;
        this.pulses = pulses;
    }

    public int getDistanceToOther(Note other) {
        return distance - other.distance;
    }

    public int getDistance() {
        return distance;
    }

    public PulseNote getPulses() {
        return pulses;
    }

    public double getDuration() {
        return pulses.getDuration();
    }
}
