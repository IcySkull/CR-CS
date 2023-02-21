//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -  
//Class -
//Lab  -

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Scanner;
import static java.lang.System.*;

public class ShortestPathGraph
{
	private Map<String, String> map;
	private boolean yayOrNay;
	private int shortest;

	public ShortestPathGraph(String line) {
		line = line.trim();
		line = line + " " + (new StringBuilder(line)).reverse().toString();
		map = Arrays.stream(line)
			.collect(Collectors.toMap()
		);
	}

	public boolean contains(String letter)
	{
		return true;
	}

	public void check(String first, String second, int steps) {
	}


	public String toString()
	{
		return "";
	}
}