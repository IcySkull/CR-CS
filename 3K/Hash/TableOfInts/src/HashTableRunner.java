//� A+ Computer Science  -  www.apluscompsci.com
//Name -  
//Date -
//Class - 
//Lab  -

import java.util.*;
import java.nio.file.*;

import static java.lang.System.*;

public class HashTableRunner
{
  public static void main ( String[] args )
  {
		try{
			HashTable table = new HashTable();

			Files.lines(Paths.get("numbers.dat"))
				.forEach(nStr -> {
					int n = Integer.parseInt(nStr);
					table.add(new Number(n));
				});

			System.out.println(table);
		}
		catch(Exception e)
		{
			System.out.println("Houston, we have a problem! \n" + e);
		}
  }
}