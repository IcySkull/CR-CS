package music.library;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import musicPrimitives.Chord;
import musicPrimitives.Measure;
import musicPrimitives.Note;
import musicPrimitives.Song;
import musicPrimitives.threePulse.Note3;

public class SongOfStorms implements MusicSheet {
    private List<Measure> measures;

    public SongOfStorms() {
        measures = new ArrayList<>();
        measures.add(firstMeasure());
        measures.add(secondMeasure());
        System.out.println(measures);
    }

    public Measure firstMeasure() {
        Note re = new Note('r', Note3.QUARTER);
        Note fa = new Note('y', Note3.QUARTER);
        Note la = new Note('i', Note3.QUARTER);
        List<Note> group1 = new ArrayList<>(List.of(re));
        List<Note> group2 = new ArrayList<>(List.of(fa, la));
        return new Measure(List.of(group1, group2));
    }

    public Measure secondMeasure() {
        System.out.println(measures + "before");
        Note re1 = new Note('t', Note3.QUARTER);
        Note fa1 = new Note('u', Note3.QUARTER);
        Note la1 = new Note('o', Note3.QUARTER);
        List<Note> group1 = new ArrayList<>(List.of(re1));
        List<Note> group2 = new ArrayList<>(List.of(fa1, la1));
        Queue<List<Note>> chord = new LinkedList<List<Note>>(List.of(group1, group2));
        Chord firstChord = new Chord(chord);
        Measure measure = new Measure(firstChord);
        System.out.println(measures + "after");
        return measure;
    }

    @Override
    public List<Measure> getMeasures() {
        return measures;
    }

    @Override
    public int getBPM() {
        return 100;
    }

    @Override
    public int getBeatsPerMeasure() {
        return 3;
    }
}
