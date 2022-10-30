package musicPrimitives.threePulse;

import musicPrimitives.PulseNote;

public enum Note3 implements PulseNote {
    HALF(2.0/3),
    QUARTER(1.0/3),
    EIGHTH(1.0/6),
    DOT(1.0/6);

    private double duration;

    Note3(double duration) {
        this.duration = duration;
    }

    public double getDuration() {
        return duration;
    }
}
