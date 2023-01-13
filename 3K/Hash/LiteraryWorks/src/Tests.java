import static org.junit.Assert.assertEquals;

import java.util.*;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;


public class Tests {
    ChainingHash shakesChaining;
    ChainingHash baconChaining;
    QPHash shakesQP;
    QPHash baconQP;
    Map<String, Integer> shakesTable;
    Map<String, Integer> baconTable;

    String[] shakes;
    String[] bacon;

    @Before
    public void setUp() {
        FileInput.init();
        
        shakes = FileInput.readShakespeare();
        bacon = FileInput.readBacon();

        shakesChaining = new ChainingHash(shakes);
        baconChaining = new ChainingHash(bacon);

        shakesQP = new QPHash(shakes);
        baconQP = new QPHash(bacon);

        shakesTable = Arrays.stream(shakes)
            .collect(
                Collectors.toMap(
                    word -> word,
                    word -> 1,
                    (a, b) -> a + b
                )
            );
        baconTable = Arrays.stream(bacon)
            .collect(
                Collectors.toMap(
                    word -> word,
                    word -> 1,
                    (a, b) -> a + b
                )
            );
    }

    @Test
    public void testFindCount1() {
        for (String word : shakesTable.keySet()) {
            int count = shakesTable.getOrDefault(word, 0);
            assert shakesChaining.findCount(word) == count;
            assert shakesQP.findCount(word) == count;
        }

        for (String word : baconTable.keySet()) {
            int count = baconTable.getOrDefault(word, 0);
            assert baconChaining.findCount(word) == count;
            assert baconQP.findCount(word) == count;
        }    
    }

    @Test
    public void testFindCount2() {
        String itr = (String) shakesChaining.getNextKey();
        while (itr != null) {
            int count = shakesTable.getOrDefault(itr, 0);
            assert shakesChaining.findCount(itr) == count;
            assert shakesQP.findCount(itr) == count;
            itr = (String) shakesChaining.getNextKey();
        }

        itr = (String) baconQP.getNextKey();
        while (itr != null) {
            int count = baconTable.getOrDefault(itr, 0);
            assert baconChaining.findCount(itr) == count;
            assert baconQP.findCount(itr) == count;
            itr = (String) baconQP.getNextKey();
        }

    }

    @Test
    public void testGetNextKeyShakes() {
        int countOfKeys = 0;
        String itr = (String) shakesChaining.getNextKey();
        while (itr != null) {
            countOfKeys++;
            itr = (String) shakesChaining.getNextKey();
        }

        assert countOfKeys == shakesChaining.getSize();
        assert countOfKeys == shakesTable.size();

        countOfKeys = 0;
        itr = (String) shakesQP.getNextKey();
        while (itr != null) {
            countOfKeys++;
            itr = (String) shakesQP.getNextKey();
        }

        assert countOfKeys == shakesQP.getSize();
        assert countOfKeys == shakesTable.size();
    }

    @Test
    public void testGetNextKeyBacon() {
        int countOfKeys = 0;
        String itr = (String) baconChaining.getNextKey();
        while (itr != null) {
            countOfKeys++;
            itr = (String) baconChaining.getNextKey();
        }

        assert countOfKeys == baconChaining.getSize();
        assert countOfKeys == baconTable.size();

        countOfKeys = 0;

        itr = (String) baconQP.getNextKey();
        while (itr != null) {
            countOfKeys++;
            itr = (String) baconQP.getNextKey();
        }

        assert countOfKeys == baconQP.getSize();
        assert countOfKeys == baconTable.size();
    }

    @Test
    public void testSameSize() {
        assertEquals(shakesChaining.getSize(), shakesTable.size());
        assert shakesQP.getSize() == shakesTable.size();
        assert baconChaining.getSize() == baconTable.size();
        assert baconQP.getSize() == baconTable.size();
    }
}
