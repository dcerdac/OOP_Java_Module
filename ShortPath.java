import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.plaf.synth.SynthSeparatorUI;

/**
 *  An instance of this class finds the closest path by Breadth-First Search algorithm. 
 *  An array of the path Positions will be retrieve by calling computeShortestPath Method .
 * 
 */
public class ShortPath {
	
	
	public 	Map<Position,Integer> distance = new LinkedHashMap<Position,Integer>();
	public  Map<Position,Position> prevElement = new  LinkedHashMap<Position, Position>();
	
	public boolean success = false;
	Position start = null;
	Position end = null;
		
	public ShortPath(Position start, Position end)
	{
		//Set Source and Destination.
		this.start = start;
		this.end=end;
		
		
		Queue<Position> posQueue= new  LinkedList<Position>();		
		posQueue.add(start);		
		ArrayList<Position> nei = new ArrayList<>();
		int distancia = 0;
		distance.put(start, distancia); //Saves each distance to the origin.
		
		
		while (!posQueue.isEmpty())
		{
		Position pos = posQueue.remove();
			if (((Position) pos).equals(end))
			{				
				System.out.println("Path Found!");
				success = true;
				break;
				
			}
			else 
			{
				nei = adjacents(pos);
				for (Position d : nei)
				{											
					if (!distance.containsKey(d) )
					{							
						posQueue.add(d);						
						distance.put(d, distance.get(pos)+1);	
						prevElement.put(d, pos);
					}			
					
				}			
				
			}
		
			
		}
		 
			
		 
	
			
		    		
	}
	
	//Given a Position it will return all the posible Maze blocks
	public static ArrayList<Position> adjacents(Position p)
	{
		ArrayList<Position> nei = new ArrayList<>();
		for (Enum e: DIR.values())
		{
			Position cp = new Position(p);
			cp.move((DIR) e);
			if (!cp.equals(p))
			{			
				if (MazeFrame.cell_Maze.get(cp).getC() != CELL.CELL_W )
				{
					nei.add(cp);
				}
			}			
		}	
		return nei;		
	}
	
	// This method returns in an array a list of Positions that ensables the Shortest Path.
	public ArrayList computeShortestPath()
	{
		List<Position> items = new ArrayList<Position>();
		Position output = null;
		Position currentV = this.end;
		
	   while (prevElement.containsKey(currentV))
	   {
		   items.add(currentV);
		   currentV = prevElement.get(currentV);
		   
	   }
	   items.add(this.start);
	   return (ArrayList) items;
	
	}



}
