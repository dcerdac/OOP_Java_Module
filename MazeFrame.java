import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * 								Entry Point of the Application.
 * 								------------------------------
 * 		Creates a SIZExSIZE GridLayout, each element is an instance of the MazeElement Class. The Teleporter and
 * 		Lander must be inside an Empty Space. The shortest path is calculated using BFS algorithm. The Maze 
 * 		is responsive to the Frame size.
 * 
 */
public class MazeFrame extends JFrame {
	//			SIZE x SIZE Maze
	//-------------------------------------
		public static final int SIZE=10 ; 
	//-------------------------------------
	
	//Static Maps to made it accessible to the rest of the classes without creating an intance of this class.
	public static Map<Position,MazeElement> cell_Maze = new LinkedHashMap<Position,MazeElement>(); //Map for each maze block
	public static List<Position> items = new ArrayList<Position>(); // Path to draw

	
	public MazeFrame() 
	{
			setTitle("Diego's Maze");			
			Container contentPane = this.getContentPane();			
			JPanel mainMazePanel = new JPanel();
			
			mainMazePanel.setLayout(new GridLayout(SIZE, SIZE));
			mainMazePanel.setPreferredSize(new Dimension(500, 400)); //Frame Size
			
			Maze m = new Maze();
		    Set set = m.cell.entrySet();
		    Iterator iterator = set.iterator();
		    while(iterator.hasNext())
		   // for(int h = 0; h<SIZE;h++)
		    {
		         Map.Entry mentry = (Map.Entry)iterator.next();	
		         MazeElement tobe= new MazeElement((CELL) mentry.getValue());
		         cell_Maze.put((Position) mentry.getKey(), tobe);
		         mainMazePanel.add(tobe);    
		         		         
		         
		         tobe.addMouseListener(new MouseListener(){

						@Override
						public void mouseClicked(MouseEvent arg0) {
														
							MazeElement lol = (MazeElement) arg0.getSource();
							System.out.println((lol.getC()));
							if ((lol.getC() == CELL.CELL_W ) && !arg0.isShiftDown()
									&& !arg0.isControlDown())
							{
								lol.setC(CELL.CELL_E);
								lol.setBackground(Color.white);
							}
							else if (lol.getC() == CELL.CELL_E)
							{
								lol.setC(CELL.CELL_W);
								lol.setBackground(Color.black);
								//You can only change the Teleporter if the space is Empy and
								// there is no Landing 
								if(arg0.isShiftDown() && (lol.getC() != CELL.CELL_L))
								{									
									Position p = getTransport(CELL.CELL_T);
									MazeElement me = cell_Maze.get(p);
									me.setC(CELL.CELL_E);	
									me.setBackground(Color.white);
									lol.setC(CELL.CELL_T);
									lol.setBackground(Color.red);								
								}
								else if(arg0.isControlDown() && (lol.getC() != CELL.CELL_T))
								{									
									Position p = getTransport(CELL.CELL_L);
									MazeElement me = cell_Maze.get(p);
									me.setC(CELL.CELL_E);	
									me.setBackground(Color.white);
									lol.setC(CELL.CELL_L);
									lol.setBackground(Color.green);								
								}
								
							}
							
							//Erase the previous path and draw the new solution.
							erasePath(items);					
							setPremiumPath();							
						
						}

						@Override
						public void mouseEntered(MouseEvent arg0) {
							// TODO Auto-generated method stub
						}

						@Override
						public void mouseExited(MouseEvent arg0) {
							// TODO Auto-generated method stub
						}

						@Override
						public void mousePressed(MouseEvent arg0) {
							// TODO Auto-generated method stub
						}

						@Override
						public void mouseReleased(MouseEvent arg0) {
							// TODO Auto-generated method stub
						}
						
					});
		    
		    }
		    
		    //Finally add the Grid to the Frame.
			this.add(mainMazePanel);
			this.pack();
			
	}
	
	//This methods looks at the Panel Map for the Teleporter/Lander, and returns its position.
	public static Position getTransport (CELL c)
	   {
		   Position s = new Position(1,1);
		   	Set set = cell_Maze.entrySet();
		    Iterator iterator = set.iterator();
		    while(iterator.hasNext()) 
		    {
		         Map.Entry mentry = (Map.Entry)iterator.next();
		         if(((MazeElement) mentry.getValue()).getC() == c)
		         {
		        	 s =(Position) mentry.getKey();
			         	break;
		         }      	 
		    }
			return s;
	   }
	
	//Iterate to each maze block and prints it inside.
	public static void drawPath(List<Position> items)
	{
		  int i = items.size();
		  for (Position p: items)
		  {
			  MazeElement me = cell_Maze.get(p);
			  me.paintNumber(i-- +"");
		  }		  
		
	}
	//Erase the last path found.
	public static void erasePath(List<Position> items)
	{
		  for (Position p: items)
		  {
			  MazeElement me = cell_Maze.get(p);
			  me.paintNumber(null);
		  }		  
		
	}
	
	public static void setPremiumPath()
	{
		//The Maze entrance Position will always be at (0,0) and the End depends on the Maze size.
		Position end = new Position(MazeFrame.SIZE-1,MazeFrame.SIZE-1);
		Position start = new Position (0,0);
		Position T = getTransport(CELL.CELL_T);
		Position L = getTransport(CELL.CELL_L);
		
		
		ShortPath original = new ShortPath(start,end);
		ShortPath transporter = new ShortPath(start,T);	
		ShortPath lander = new ShortPath(L,end);	
		
		List<Position> itemsOriginal = new ArrayList<Position>();
		List<Position> itemsT = new ArrayList<Position>();
		List<Position> itemsL = new ArrayList<Position>();
		
		itemsOriginal = original.computeShortestPath();
		itemsT = transporter.computeShortestPath();
		itemsL = lander.computeShortestPath();	
		
	
		itemsT.removeAll(itemsL);
		itemsL.addAll(itemsT);
		
		//Check all posible combinations of routes, if there is no solution then prompt message.
		if (itemsL.size() < itemsOriginal.size() && transporter.success == true && lander.success==true)
		{
			items = itemsL;
			drawPath(items);		

		}
		else if (original.success==false && transporter.success == true && lander.success==true)
		{
			items = itemsL;
			drawPath(items);	

		}
		else if (itemsL.size() >= itemsOriginal.size() && original.success==true )
		{	
			items = itemsOriginal;
			drawPath(items);		

		}
		else if (original.success==true && (transporter.success == true || lander.success==true))
		{
			items = itemsOriginal;
			drawPath(items);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Can not reach the destination.");
		}
		
	}
	
	public static void main(String[] args) {
		JFrame frm = new MazeFrame();
		
		//Find and draw the shortest path.
		setPremiumPath();
		
		frm.setLocationRelativeTo(null);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setVisible(true);
	}
	
	

}
