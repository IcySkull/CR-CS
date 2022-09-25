package uniondiff;//© A+ Computer Science  -  www.apluscompsci.com
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

public class MathSetRunner
{
	public static void main(String args[]) throws IOException
	{
		Scanner in = new Scanner(new File("mathsetdata.dat"));
		List<List<Integer>> nums = new ArrayList<>();
		while (in.hasNextLine()) {
			String[] lineNums = in.nextLine().split(" ");
			List<Integer> ns = new ArrayList<>();
			nums.add(Arrays.stream(lineNums).map(Integer::valueOf).collect(Collectors.toList()));
		}

		for (int i = 0; i < nums.size(); i+=2) {
			out.println(i);
			MathSet sets = new MathSet(nums.get(i), nums.get(i+1));
			out.println("set one " + sets.getOne());
			out.println("set two " + sets.getTwo());
			out.println("union " + sets.union());
			out.println("intersection " + sets.intersection());
			out.println("A - B " + sets.differenceAMinusB());
			out.println("B - A " + sets.differenceBMinusA());
			out.println("symetric diff " + sets.symmetricDifference());
			out.println();
		}
	}
}
