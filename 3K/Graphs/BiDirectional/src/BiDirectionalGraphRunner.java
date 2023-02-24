//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -  
//Class -
//Lab  -

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;
import static java.lang.System.*;

public class BiDirectionalGraphRunner
{
	public static void main( String[] args ) throws IOException
	{
		Scanner file = new Scanner(new File("bidgraph.dat"));
		int size = file.nextInt();
		file.nextLine();
		for(int i=0; i<size; i++)
		{
			BiDirectionalGraph graph = new BiDirectionalGraph(file.nextLine());
			String[] edge = file.nextLine().split(" ");
			boolean existsEdge = graph.check(edge[0], edge[1], new TreeSet<String>());
			System.out.println(edge[0] + " CONNECTS TO " + edge[1] + " = " + (existsEdge ? "YAH" : "NAH"));
		}
	}
}