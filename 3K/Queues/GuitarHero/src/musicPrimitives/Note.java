package musicPrimitives;

import java.util.Map;

import music.MusicAdapter;
import musicPrimitives.instruments.InstrumentString;

public class Note {
    private char key;
    private PulseNote pulses;

    public Note(char key, PulseNote pulses) {
        this.key = key;
        this.pulses = pulses;
    }

    public void pluck(Map<Character, InstrumentString> instrument) {
        instrument.get(key).pluck();
    }

    public char getKey() {
        return key;
    }

    public void tic(Map<Character, InstrumentString> instrument) {
        instrument.get(key).tic();
    }

    public PulseNote getPulses() {
        return pulses;
    }

    public double getDuration() {
        return pulses.getDuration();
    }

    @Override
    public String toString() {
        return String.valueOf(key);
    }
}
