import java.util.List;

//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -  
//Lab  -

public class MonsterPQRunner
{
	public static void main ( String[] args )
	{
		List<Monster> monsters = List.of(
			new Monster(1, 1, 1),
			new Monster(2, 2, 2),
			new Monster(1, 2, 3),
			new Monster(3, 2, 1),
			new Monster(2, 1, 3),
			new Monster(2, 3, 1),
			new Monster(3, 1, 2),
			new Monster(3, 3, 3)
		);

		MonsterPQ mPQ = new MonsterPQ(monsters);
		System.out.println(mPQ);

		System.out.println("Min: " + mPQ.getMin());
		System.out.println("removeMin: " + mPQ.removeMin());
		System.out.println();

		System.out.println(mPQ);
		System.out.println("Min: " + mPQ.getMin());
		System.out.println("removeMin: " + mPQ.removeMin());
		System.out.println(mPQ);
	}
}