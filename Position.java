import javax.swing.plaf.synth.SynthSeparatorUI;
import java.io.*;
import java.util.*;
 
public class Position  implements Comparable {
	
	
	 private int x; 
	 private int y;
	 
	 public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Position(int x, int y)
	 {
		 this.x =x;
		 this.y =y;
	 }
	 
	 public Position(Position other)
	 {
	     x = other.x ;
	     y = other.y;
	 }
	 // Prints (x,y)
	 public String toString()
	 {
		 return ("("+ x +"," + y +")");
	 }

	
	 
	 @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	//Customized equals method.
	@Override
	public boolean equals(Object obj) {
		if (obj.hashCode() == this.hashCode())
		{
			return true;
		}
		else
		{
			return false;
		}
	}


	public void move(DIR dir)
	 {		
	       switch (dir) {
           case DIR_UP: 
        	   if (this.y-1 >=0)
        	   {        	
        		  this.y--;
        	   }        	   
               
               break;
           case DIR_DOWN: 
        	   //Check if is outsite the frame
        	   if (this.y+1 <= MazeFrame.SIZE-1)
        	   {  
        		  this.y++;
        	   }   
               break;
           case DIR_LEFT:
        	   
        	   if (this.x-1 >=0)
        	   {  
        		  this.x--;
        	   }     
               break;
           case DIR_RIGHT:         	        	   
        	   //Check if is outsite the frame.
        	   if (this.x+1 <= MazeFrame.SIZE-1)
        	   {  
        		  this.x++;
        	   }   
               break;
       }
	 }
	 

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
