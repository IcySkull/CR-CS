//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -  
//Lab  -

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Graph
{
	private Map<String, String> map;
	private boolean yahOrNay;

	public Graph(String line)
	{ 
		System.out.println("line " + line + "\n");
		line = line + " " + (new StringBuilder(line)).reverse().toS;
		map = Arrays.stream(line.split(" "))
			.collect(
				Collectors.toMap(
					s -> ""+s.charAt(0),
					s -> ""+s.charAt(1),
					(String a, String b) -> a + b
				)
		);
	}

	public boolean contains(String letter)
	{
	   return map.containsKey(letter);
	}

	public boolean check(String first, String second)
	{
		System.out.println(first);
		String connections = map.remove(first);
		System.out.println(map);
		return connections.contains(second) || 
			connections.chars()
				.anyMatch(node -> check(""+(char)node, second));
	}

	public String toString()
	{
		return "";
	}
}