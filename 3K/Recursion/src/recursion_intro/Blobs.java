package recursion_intro;

import math.location.Location;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Blobs
{
	private int[][] mat;   //grid of 1s and 0s
	private int count;
	private ArrayList<Location> visited;

	public Blobs( int rows, int cols )
	{
		count = 0;
		mat = new int[rows][cols];
		for(int r = 0; r < mat.length; r++) {
			for (int c = 0; c < mat[0].length; c++) {
				mat[r][c] = (int)(Math.round(Math.random()));
			}
		}
	}

	public Blobs( int rows, int cols, String s )
	{
		count = 0;
		mat = new int[rows][cols];
		AtomicInteger r = new AtomicInteger();
		s.lines().forEach((line) -> {
			for (int c = 0; c < line.length(); c++) {
				char mark = s.charAt(c);
				mat[r.getAndIncrement()][c] = mark;
			}
		});
	}
	
	public void recur(Location loc)
	{
		if (inMatrix(loc) && getValue(loc) == 1 && !visited.contains(loc)) {
			visited.add(loc);
			count++;
			recur(loc.getRight());
			recur(loc.getDown());
			recur(loc.getLeft());
			recur(loc.getUp());
		}
	}

	private boolean inMatrix(Location loc) {
		int r = loc.getRow();
		int c = loc.getCol();
		return c >= 0 && r >= 0 &&
				r < mat.length && c < mat[0].length;
	}

	private int getValue(Location loc) {
		return mat[loc.getRow()][loc.getCol()];
	}

	public int getBlobCount(int r, int c)
	{
		count = 0;
		visited = new ArrayList<>();
		recur(new Location(r, c));
		return count;
	}

	public int getBlobCount()
	{
		return count;
	}

	public String toString()
	{
		String out = "";
		for(int r = 0; r < mat.length; r++) {
			for (int c = 0; c < mat[0].length; c++) {
				out += mat[r][c] + " ";
			}
			out += "\n";
		}
		return out;
	}
}