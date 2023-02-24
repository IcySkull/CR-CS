//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.*;

public class MazeRunner
{
	public static void main( String args[] ) throws IOException
	{
		Scanner file = new Scanner(new File("maze.dat"));
		while (file.hasNextInt()) {
			int size = file.nextInt();
			int[][] maze = new int[size][size];
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					maze[i][j] = file.nextInt(); 
				}
			}
			Maze test = new Maze(maze);
			System.out.println(test);
			boolean exit = test.checkForExitPath(new Location(0, 0));
			System.out.println("exit" + (exit ? " " : " not ") + "found");
			System.out.println();
		}
	}
}