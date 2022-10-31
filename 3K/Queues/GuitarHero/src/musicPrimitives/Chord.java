package musicPrimitives;

import java.util.*;

import music.MusicAdapter;
import musicPrimitives.instruments.InstrumentString;
import tools.StdAudio;

public class Chord {
    private Queue<List<Note>> notes;
    private List<Note> playingNotes;
    private long startGroupNote;

    public Chord() {
        this.playingNotes = new ArrayList<>();
    }

    public Chord(Queue<List<Note>> notes) {
        this();
        this.notes = notes;
    }

    public Chord(List<Note> notes) {
        this();
        this.notes = new LinkedList<>();
        this.notes.add(notes);
    }

    public Chord(Note note) {
        this();
        this.notes = new LinkedList<>();
        this.notes.add(List.of(note));
    }

    public boolean isEmpty() {
        return notes.isEmpty() && playingNotes.isEmpty();
    }

    public Queue<List<Note>> getNotes() {
        return notes;
    }

    public void play(Map<Character, InstrumentString> instrument, double msPerMeasure) {
        long currentTime = System.currentTimeMillis();
        long elapsedFromGroupStart = currentTime - startGroupNote;
        if (playingNotes.isEmpty()) {
            playingNotes = notes.poll();
            startGroupNote = System.currentTimeMillis();
            for (Note note : playingNotes) {
                note.pluck(instrument);
            }
        } else {
            ListIterator<Note> iterator = playingNotes.listIterator();
            while (iterator.hasNext()) {
                Note note = iterator.next();
                double noteHold = note.getDuration()*msPerMeasure;
                if (noteHold <= elapsedFromGroupStart) 
                    iterator.remove();
                else
                    note.tic(instrument);
            }
        }
        MusicAdapter.outAudio(instrument);
    }

    @Override
    public String toString() {
        return notes.toString();
    }
}
