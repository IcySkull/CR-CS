package musicPrimitives;

import java.util.*;

import musicPrimitives.instruments.InstrumentString;
import tools.StdAudio;

public class Measure {
    private static List<Chord> chords;

    public Measure(Measure measure) {
        chords = new ArrayList<>(measure.getChords());
    }

    public Measure(List<List<Note>> notes) {
        chords = new ArrayList<>();
        for (List<Note> chord : notes) {
            chords.add(new Chord(chord));
        }
    }

    public Measure(Chord chord) {
        this.chords = new ArrayList<>();
        chords.add(chord);
    }

    public List<Chord> getChords() {
        return chords;
    }

    public void play(Map<Character, InstrumentString> instrument, double msPerMeasure) {
        while (!chords.isEmpty()) {
            ListIterator<Chord> iterator = chords.listIterator();
            while (iterator.hasNext()) {
                Chord chord = iterator.next();
                if (chord.isEmpty()) {
                    iterator.remove();
                }
                else {
                    chord.play(instrument, msPerMeasure);
                }
            }
        }
    }
    @Override
    public String toString() {
        return chords.toString();
    }
}
