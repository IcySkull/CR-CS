package sptoen;//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -  
//Class -
//Lab  -

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class SpanRunner
{
	public static void main( String args[] ) throws IOException
	{
		Scanner in = new Scanner(new File("spantoeng.dat"));
		SpanishToEnglish dictionary = SpanishToEnglish.createDictionary(in);
		for (List<String> line : dictionary.translateLines()) {
			String out = "";
			for (String word: line) {
				out += word + " ";
			}
			System.out.println(out);
		}
	}

}