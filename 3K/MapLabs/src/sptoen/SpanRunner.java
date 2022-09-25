package sptoen;//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -  
//Class -
//Lab  -

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;

import org.hamcrest.DiagnosingMatcher;

import static java.lang.System.*;

public class SpanRunner
{
	public static void main( String args[] ) throws IOException
	{
		SpanishToEnglish dictionary = createDictionary();
		System.out.println(dictionary);
	}

	public static SpanishToEnglish createDictionary() {
		Scanner in = new Scanner(new File("spantoeng.dat");
		SpanishToEnglish dictionary = new SpanishToEnglish();
		int numWords = Integer.parseInt(in.nextLine());
		while (numWords != 0){
			String[] line = in.nextLine().split(" ");
			dictionary.putEntry(line[0], line[1]);
			numWords--;
		}

		List<String> lines = new ArrayList<>();
		while (in.hasNextLine()) {
			lines.add(in.nextLine());
		}
		dictionary.setLines(lines);

		return dictionary;
	}
}