import java.util.List;

//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -  

public class PalinListRunner
{
	public static void main ( String[] args )
	{
		String[] strTest = new String[] {
			"one two three two one",
			"1 2 3 4 5 one two three four five",
			"a b c d e f g x y z g f h",
			"racecar is racecar",
			"1 2 3 a b c c b a 3 2 1",
			"chicken is a chicken"
		};

		PalinList pList = new PalinList();
		List.of(strTest).forEach(str -> {
			pList.setList(str);
			System.out.println(pList);
		});
	}
}