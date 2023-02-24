
public class Location implements Comparable<Location> {
    int row;
    int col;
    
    public Location(int r, int c) {
        row = r;
        col = c;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Location))
            return false;
        Location loc = (Location) obj;
        return this.row == loc.row && this.col == loc.col;
    }

    @Override
    public int compareTo(Location o) {
        if (this.row > o.row)
            return 1;
        else if (this.row == o.row) {
            if (this.col > o.col)
                return 1;
            else if (this.col < o.col)
                return -1;
            return 0;
        }
        else if (this.col > o.col)
            return 1;
        else if (this.col < o.col)
            return -1;
        return 0;
    }
}
