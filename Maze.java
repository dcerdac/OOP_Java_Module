import java.util.*;
import java.io.*;
import java.nio.charset.Charset;

/**
 * Creating a instance of a Maze will generate a Map based on the Position(x,y) and CELL Type.
 *
 * Map<Position,CELL> cell
 */

public class Maze {

	public  Map<Position,CELL> cell = new LinkedHashMap<Position,CELL>();
	public Maze()
	{
		String  line;
		int total_line = 0 ;
		
	    try (
	        InputStream fis = new FileInputStream("map1.csv");
	        InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
	        BufferedReader br = new BufferedReader(isr);
	    ) {
	        //while ((line = br.readLine()) != null)
	        	
	        for (int j = 0; j<MazeFrame.SIZE;j++)// <- this will make the existing 10x10 maze shorter
	        									//		 acording to the SIZE given in (MazeFrame.java)
	        {
	        	line = br.readLine();
	        	String[] s = (line.split(",", -1));	        		        	
	        	int i =0 ;
	        	for (int z = 0; z<MazeFrame.SIZE;z++)
	     	    {
	     	    	if (s[z].equals("W"))
	     	    	{	     	    		
	     	    		cell.put(new Position(i, total_line), CELL.CELL_W);
	     	    	}
	     	    	else if (s[z].equals("L"))
	     	    	{
	     	    		cell.put(new Position(i, total_line), CELL.CELL_L);
	     	    	}
	     	    	else if (s[z].equals("T"))
	     	    	{
	     	    		cell.put(new Position(i, total_line), CELL.CELL_T);
	     	    	}
	     	    	else
	     	    	{
	     	    		cell.put(new Position(i, total_line), CELL.CELL_E);
	     	    	}
	     	    	i++;
	     	    }	        	
	        	total_line++;	   
	        }
	    }
	    
		  catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    

	}
	

    

}
