package musicPrimitives;

import java.util.List;

import musicPrimitives.threePulse.Interval;

public class Song {
    private List<Interval> intervals;
    private int bpm;

    public Song(List<Interval> intervals, int bpm) {
        this.intervals = intervals;
        this.bpm = bpm;
    }
}
