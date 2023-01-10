//� A+ Computer Science  -  www.apluscompsci.com
//Name -  
//Date -
//Class - 
//Lab  -

import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.lang.System.*;

public class HashTableRunner
{
  public static void main ( String[] args )
  {
		try{
			HashTable table = new HashTable();
			
			Files.lines(Paths.get("words.dat")).skip(1)
				.forEach( word -> {
					table.add(new Word(word));
				});
			System.out.println(table);
		}
		catch(Exception e)
		{
			System.out.println("Houston, we have a problem!");
		}
  }
}