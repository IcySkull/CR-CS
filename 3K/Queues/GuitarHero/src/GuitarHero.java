import java.util.*;

import music.MusicAdapter;
import music.library.SongOfStorms;
import musicPrimitives.Song;
import tools.StdDraw;


public class GuitarHero {

    public static void main(String[] args) {
        play();
    }


    private static void play() {
        StdDraw.clear();
        Song song = new Song(new SongOfStorms());
    }
}
