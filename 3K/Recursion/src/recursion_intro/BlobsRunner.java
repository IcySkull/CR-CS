package recursion_intro;

import java.util.*;

public class BlobsRunner
{	
	public static void main(String[] args)
	{
		Blobs test1 = new Blobs(10, 11);
		System.out.println(test1);

		System.out.println(test1.getBlobCount(1, 1));
	}
}

/*


Example Cases :
0010010010
0100100101
1001001010
0011110101
0111101010
1001010100
0010101101
0101010010
1010100100
0101001000

1 1
2 3
5 7


000
111
010

0 0
1 1
2 0


Example Output : 
 1
10
 3

0
4
0



*/


