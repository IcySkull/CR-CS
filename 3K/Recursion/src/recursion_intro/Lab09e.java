package recursion_intro;
//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -  

import java.util.*;
import static java.lang.System.*;

public class Lab09e
{
   public static void main( String args[] )
   {
       Permutation test1 = new Permutation("ABC");
       out.println(test1);
       Permutation test2 = new Permutation("abc");
       out.println(test2);
       Permutation test3 = new Permutation("boat");
       out.println(test3);
       Permutation test4 = new Permutation("it");
       out.println(test4);
       Permutation test5 = new Permutation("");
       out.println(test5);
   }
}