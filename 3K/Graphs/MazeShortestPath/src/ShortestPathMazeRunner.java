//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -  
//Class -
//Lab  -

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.*;

public class ShortestPathMazeRunner
{
	public static void main( String args[] ) throws IOException
	{
		Scanner file = new Scanner(new File("maze2.dat"));
		while (file.hasNextInt()) {
			int size = file.nextInt();
			int[][] maze = new int[size][size];
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					maze[i][j] = file.nextInt(); 
				}
			}
			ShortestPathMaze test = new ShortestPathMaze(maze);
			int shortest = test.shortest(new Location(0, 0));
			System.out.println(test);
			System.out.println("shortest exit" + (shortest == 0 ? " not found" : " found in " + shortest + " steps"));
			System.out.println();
		}
	}
}