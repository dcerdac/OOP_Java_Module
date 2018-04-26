import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * An instance of a MazeElement represents a JPanel that will set its background accordingly to its CELL Type.
 * and will be use later to populate a GridLayout of the Main Frame.
 * 
 */

public class MazeElement extends JPanel {
	
	private CELL c ;
	public CELL getC() {
		return c;
	}

	public void setC(CELL c) {
		this.c = c;
	}

	public String getPath_number() {
		return path_number;
	}

	private String path_number;
	
	public void setPath_number(String path_number) {
		this.path_number = path_number;
	}

	public MazeElement() {
		this.setOpaque(true);
		setBackground(Color.black);
		 this.setLayout(new GridLayout());
	}
	//Main Constructor: path_num is the number inside each block of the maze path.
	public MazeElement(CELL c, String path_num)
	{
		this.c = c;
		this.path_number = path_num;
		 switch(this.c)
		 {
		    case CELL_W :
		    	setBackground(Color.black);
				break;
				
		    case CELL_T :
		    	setBackground(Color.red);
				break;
				
		    case CELL_E :
		    	setBackground(Color.white);
				break;
				
		    case CELL_L :
		    	setBackground(Color.green);
				break;				
		  }
		 
		 this.setLayout(new GridLayout());
		
	}
	
	public MazeElement(CELL c)
	{
		this.c = c;
		this.setOpaque(true);
		
		 switch(this.c)
		 {
		    case CELL_W :
		    	setBackground(Color.black);
				break;
				
		    case CELL_T :
		    	setBackground(Color.red);
				break;
				
		    case CELL_E :
		    	setBackground(Color.white);
				break;
				
		    case CELL_L :
		    	setBackground(Color.green);
				break;				
		  }
		 
		 this.setLayout(new GridLayout());
		
	}
	


	@Override
	protected void paintComponent(Graphics arg0) {		
		super.paintComponent(arg0);
	    Graphics2D g2 = (Graphics2D) arg0;
	    
	    //Checks if the Maze block will need a path number inside.
	    if (this.path_number != null)
	    {
	        g2.setColor(Color.blue);
		    g2.setFont(new Font("Arial", Font.BOLD, 15)); 
		    
		    String msg = this.path_number+"";
		     
		    	//Centering Calculations.
	            int strWidth = 0;
	            int strAccent = 0;
	            int xC = 0;
	            int yC = 0;
	            FontMetrics fm = g2.getFontMetrics();
	            strWidth = fm.stringWidth(msg);
	            strAccent = fm.getAscent();
	            xC = getWidth() / 2 - strWidth / 2;
	            yC = getHeight() / 2 + strAccent / 2;
	            //Finally draw the number centered.
	            g2.drawString(msg, xC, yC);
	    }

	

	}
	
	public void paintNumber(String number)
	{
		this.path_number = number;	    
	    this.repaint();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Dimension getPreferredSize() {
		
		return new Dimension(this.getParent().getWidth() / MazeFrame.SIZE, this.getParent().getHeight() / MazeFrame.SIZE);
	}
	
	

}
