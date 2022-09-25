package oddsevens;//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.System.*;

public class OddRunner
{
	public static void main( String args[] ) throws IOException
	{
		Scanner in = new Scanner(new File("oddevens.dat"));
		List<List<Integer>> nums = new ArrayList<>();
		while (in.hasNextLine()) {
			String[] lineNums = in.nextLine().split(" ");
			List<Integer> ns = new ArrayList<>();
			nums.add(Arrays.stream(lineNums).map(Integer::valueOf).collect(Collectors.toList()));
		}
		for (List<Integer> sequence : nums) {
			OddEvenSets oddEvenSet = new OddEvenSets(sequence);
			out.println(oddEvenSet);
		}
	}
}