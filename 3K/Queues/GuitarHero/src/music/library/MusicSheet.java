package music.library;

import java.util.List;

import musicPrimitives.Measure;

public interface MusicSheet {
    List<Measure> getMeasures();
    int getBPM();
    int getBeatsPerMeasure();
    
}
