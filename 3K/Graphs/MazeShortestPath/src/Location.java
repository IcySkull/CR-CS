
public class Location {
    int row;
    int col;
    
    public Location(int r, int c) {
        row = r;
        col = c;
    }

    @Override
    public int hashCode() {
        return row * 1000 + col;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Location) {
            Location loc = (Location) obj;
            return row == loc.row && col == loc.col;
        }
        return false;
    }

    public String toString() {
        return "(" + row + ", " + col + ")";
    }
}
