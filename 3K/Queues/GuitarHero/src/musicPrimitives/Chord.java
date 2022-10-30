package musicPrimitives;

import java.time.Duration;
import java.util.*;

public class Chord {
    private List<Note> notes;
    private char start;

    public Chord(List<Note> notes, char start) {
        this.notes = notes;
        this.start = start;
    }
}
