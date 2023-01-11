import java.util.Arrays;
import java.util.Hashtable;

import org.junit.Before;
import org.junit.Test;


public class Tests {
    ChainingHash shakesChaining;
    ChainingHash baconChaining;
    QPHash shakesQP;
    QPHash baconQP;
    Hashtable<String, Integer> shakesTable;
    Hashtable<String, Integer> baconTable;

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
                Hashtable::new, 
                (table, word) -> table.put(word, table.getOrDefault(word, 0) + 1), 
                Hashtable::putAll
            );
        baconTable = Arrays.stream(bacon)
            .collect(
                Hashtable::new, 
                (table, word) -> table.put(word, table.getOrDefault(word, 0) + 1), 
                Hashtable::putAll
            );
    }

    @Test
    public void testFindCountQP() {
        for (String word : shakesTable.keySet()) {
            assert shakesChaining.findCount(word) == shakesTable.get(word);
            assert shakesQP.findCount(word) == shakesTable.get(word);
        }

        for (String word : baconTable.keySet()) {
            assert baconChaining.findCount(word) == baconTable.get(word);
            assert baconQP.findCount(word) == baconTable.get(word);
        }    
    }

    @Test
    public void testSameSize() {
        assert shakesChaining.getSize() == shakesTable.size();
        assert shakesQP.getSize() == shakesTable.size();
        assert baconChaining.getSize() == baconTable.size();
        assert baconQP.getSize() == baconTable.size();
    }
}
