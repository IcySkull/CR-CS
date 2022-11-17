import java.util.ArrayList;

public class AssassinManager {
    private AssassinNode killRingFront;
    private AssassinNode graveyardFront;

    public AssassinManager(ArrayList<String> nameList) {
        if (nameList == null || nameList.size() == 0) {
            throw new IllegalArgumentException();
        }
        killRingFront = new AssassinNode(nameList.get(0));
        AssassinNode itr = killRingFront;
        for (int i = 1; i < nameList.size(); i++) {
            if (nameList.get(i) == null) {
                throw new IllegalArgumentException();
            }
            itr.next = new AssassinNode(nameList.get(i));
            itr = itr.next;
        }
        itr.next = killRingFront;
    }

    //////// DO NOT MODIFY AssassinNode.  You will lose points if you do. ////////
    /**
     * Each AssassinNode object represents a single node in a linked list
     * for a game of Assassin.
     */
    private static class AssassinNode {
        public final String name;  // this person's name
        public String killer;      // name of who killed this person (null if alive)
        public AssassinNode next;  // next node in the list (null if none)
        
        /**
         * Constructs a new node to store the given name and no next node.
         */
        public AssassinNode(String name) {
            this(name, null);
        }

        /**
         * Constructs a new node to store the given name and a reference
         * to the given next node.
         */
        public AssassinNode(String name, AssassinNode next) {
            this.name = name;
            this.killer = null;
            this.next = next;
        }
    }

    public void kill(String string) {
        if (isGameOver()) {
            throw new IllegalStateException();
        }
        string = string.toLowerCase();
        AssassinNode itr = killRingFront;
        do {
            if (itr.next.name.toLowerCase().equals(string)) {
                itr.next.killer = itr.name;
                AssassinNode temp = itr.next.next;
                itr.next.next = graveyardFront;
                graveyardFront = itr.next;
                if (itr.next == killRingFront) {
                    itr.next = temp;
                    killRingFront = temp;
                } else {
                    itr.next = temp;
                }
                return;
            }
            itr = itr.next;
        } while (itr != killRingFront);
        throw new IllegalArgumentException();
    }

    public String killRing() {
        if (isGameOver()) {
            return killRingFront.name + " won the game!";
        }
        String result = "";
        AssassinNode itr = killRingFront;
        do {
            result += "  " + itr.name + " is stalking " + itr.next.name + "\n";
            itr = itr.next;
        } while (itr != killRingFront);
        return result;
    }

    public String graveyard() {
        String result = "";
        AssassinNode itr = graveyardFront;
        while (itr != null) {
            result += "  " + itr.name + " was killed by " + itr.killer + "\n";
            itr = itr.next;
        }
        return result;
    }

    public boolean killRingContains(String name) {
        AssassinNode itr = killRingFront;
        name = name.toLowerCase();
        do {
            if (itr.name.toLowerCase().equals(name)) {
                return true;
            }
            itr = itr.next;
        } while (itr != killRingFront);
        return false;
    }

    public boolean graveyardContains(String name) {
        name = name.toLowerCase();
        AssassinNode itr = graveyardFront;
        while (itr != null) {
            if (itr.name.toLowerCase().equals(name)) {
                return true;
            }
            itr = itr.next;
        }
        return false;
    }

    public boolean isGameOver() {
        return killRingFront.next == killRingFront;
    }

    public String winner() {
        if (isGameOver())
            return killRingFront.name;
        return null;
    }
}
