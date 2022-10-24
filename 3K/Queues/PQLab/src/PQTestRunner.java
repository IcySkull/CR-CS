//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -  

public class PQTestRunner
{
	public static void main ( String[] args )
	{
		String[] lines = new String[] {
			"one two three four five six seven",
			"one two three four five 1 2 3 4 5",
			"a p h j e f m c i d k l g n o b",
		};

		for (String string : lines) {
			PQTester q = new PQTester(string);
			System.out.println("toString() - " + q);
			System.out.println("getMin() - " + q.getMin());
			System.out.println("getNaturalOrder() - " + q.getNaturalOrder());
			System.out.println();
		}
	}
}