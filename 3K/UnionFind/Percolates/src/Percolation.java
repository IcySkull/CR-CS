import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] mat; // boolean matrix to represent open and closed sites
    private WeightedQuickUnionUF disjointSet;
    private WeightedQuickUnionUF fullSet;
    private int topComponent;
    private int botComponent;
    private int opened;

    public Percolation(int n) // create n-by-n grid, with all sites blocked
    {
        if (n <= 0)
            throw new IllegalArgumentException("n must be greater than 0");
        mat = new boolean[n][n];
        disjointSet = new WeightedQuickUnionUF(n * n);
        fullSet = new WeightedQuickUnionUF(n * n);
        topComponent = -1;
        botComponent = -1;
    }

    public int numberOfOpenSites() // number of open sites
    {
        return opened;
    }

    public void open(int row, int col) // open site (row, col) if it is not open already
    {
        checkRange(row, col);

        int fromID = getID(row, col);

        if (isTopRow(row)) {
            if (topComponent == -1)
                topComponent = fromID;
            else { // connect to top component even if path doesnt exists between open sites
                disjointSet.union(fromID, topComponent);
                fullSet.union(fromID, topComponent);
            }
        } 
        
        if (isBottomRow(row)) {
            if (botComponent == -1)
                botComponent = fromID;
            else
                disjointSet.union(fromID, botComponent);
        }

        // basically disjointSet and fullSet are the same except that fullSet doesn't
        // connect all bottom sites, in constrast to disjointSet that it does add
        // all bottom sites to the same component no matter if they are not actually connected
        // by a path of open sites (see else statemets above)


        if (exists(row - 1, col) && isOpen(row - 1, col)) { // check top
            int toID = getID(row - 1, col);
            disjointSet.union(fromID, toID);
            fullSet.union(fromID, toID);
        }

        if (exists(row + 1, col) && isOpen(row + 1, col)) { // check bottom
            int toID = getID(row + 1, col);
            disjointSet.union(fromID, toID);
            fullSet.union(fromID, toID);
        }

        if (exists(row, col - 1) && isOpen(row, col - 1)) { // check left
            int toID = getID(row, col - 1);
            disjointSet.union(fromID, toID);
            fullSet.union(fromID, toID);
        }

        if (exists(row, col + 1) && isOpen(row, col + 1)) { // check right
            int toID = getID(row, col + 1);
            disjointSet.union(fromID, toID);
            fullSet.union(fromID, toID);
        }

        if (!mat[row - 1][col - 1])
            opened++;
        mat[row - 1][col - 1] = true;
    }

    public boolean isFull(int row, int col) // is site (row, col) full?
    {
        checkRange(row, col);

        if (topComponent == -1)
            return false;

        return fullSet.find(getID(row, col)) == fullSet.find(topComponent);
    }

    public boolean percolates() // does the system percolate?
    {
        if (topComponent == -1 || botComponent == -1)
            return false;
        return disjointSet.find(topComponent) == disjointSet.find(botComponent);
    }

    private boolean exists(int r, int c) { // helper method to check if site exists
        return r >= 1 && r <= mat.length && c >= 1 && c <= mat[0].length;
    }

    private int getID(int r, int c) { // helper method to calculate ID
        checkRange(r, c);
        return (r - 1) * (mat.length) + (c-1);
    }

    public boolean isOpen(int row, int col) // is site (row, col) open?
    {
        checkRange(row, col);
        return mat[row - 1][col - 1];
    }

    private void checkRange(int row, int col) {
        if (!(row >= 1 && row <= mat.length && col >= 1 && col <= mat[0].length))
            throw new IllegalArgumentException("Index out of bounds");
    }

    // check if row is within top row
    private boolean isTopRow(int row) {
        return row == 1;
    }

    // check if row is within bottom row
    private boolean isBottomRow(int row) {
        return row == mat.length;
    }


    private void print() { // prints boolean[][] called mat
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                System.out.print((mat[i][j] ? 1 : 0) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) // test client (optional){
    {
        Percolation p = new Percolation(1);
        p.open(1, 1);
        System.out.println(p.percolates());
    }
}