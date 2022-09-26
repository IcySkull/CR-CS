package sptoen.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import sptoen.SpanishToEnglish;

public class SpanishToEnglishTest {
    File dict;

    @Before
    public void init() {
        this.dict = new File("C:/Users/PCD/CR-CS/3K/MapLabs/spantoeng.dat");
    }

    @Test
    public void getLinesToTranslate() throws FileNotFoundException {
        Scanner in = new Scanner(dict);
        SpanishToEnglish dictionary = SpanishToEnglish.createDictionary(in);
        in.close();

        String[][] lines = new String[][]{
            new String[]{"yo", "quiero", "una", "ordenador", "virus"},
            new String[]{"todas", "de", "los", "muchachos", "tienen", "interno", "memoria"},
            new String[]{"mi", "pelo", "es", "cafe"},
            new String[]{"tu", "quieres", "tinta", "con", "su", "papel"},
            new String[]{"rearrancar", "el", "ordenador", "a", "vacio", "el", "pantalla"}
        };

        for (int i = 0; i < lines.length; i++) {
           assertEquals(Arrays.asList(lines[i]), dictionary.getLines().get(i)); 
        }
    }

    @Test
    public void testPutEntry() {

    }

    @Test
    public void testTranslate() {
        SpanishToEnglish dictionary = new SpanishToEnglish();
        String[] expected = new String[] {};
    }
}
