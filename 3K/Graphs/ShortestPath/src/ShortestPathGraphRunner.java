//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -  
//Class -
//Lab  -

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.*;

public class ShortestPathGraphRunner
{
	public static void main( String[] args ) throws IOException
	{
		Scanner file = new Scanner(new File("graph2.dat"));
		int howManyTimes = file.nextInt();
		file.nextLine();
		for(int x=0; x<howManyTimes; x++)
		{
			ShortestPathGraph graph = new ShortestPathGraph(file.nextLine());
			String connections = file.nextLine();
			String node1 = String.valueOf(connections.charAt(0));
			String node2 = String.valueOf(connections.charAt(1));
			int shortest = graph.check(node1, node2);
			if (shortest == 0)
				System.out.println(node1 + " connects to " + node2 + " == no");
			else
				System.out.println(node1 + " connects to " + node2 + " == yes " + shortest);
			System.out.println();
		}
	}
}