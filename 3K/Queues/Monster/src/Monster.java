//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import java.util.Arrays;
import java.util.Scanner;
import static java.lang.System.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.util.Collections.*;

public class Monster implements Comparable<Monster>
{
 //add stuff like methods and instance variables
     private int teethSize, height, weight;
     public Monster()
     {
      this(0,0,0);
     }
     public Monster(int teethSize, int height, int weight)
     {
      setAttributes(teethSize,height,weight);
     }
     public void setAttributes(int teethSize, int height, int weight)
     {
      this.teethSize=teethSize;
      this.height=height;
      this.weight=weight;
     }
     public int compareTo(Monster other)
     {
      // by teeth, weight and then height

      return (teethSize*3) - (other.getTeethSize()*3) +
                (weight*2) - (other.getWeight()*2) + 
                height - other.getHeight();
     }
     public String toString()
     {
      return "" + teethSize+ " "+ height + " " + weight;
     }
    public int getTeethSize() {
        return teethSize;
    }
    public int getHeight() {
        return height;
    }
    public int getWeight() {
        return weight;
    }
}