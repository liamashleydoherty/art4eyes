package paint;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Drawing canvas class
 * @author Liam A Doherty
 * @version 9.2.14
 */
public class Canvas extends JPanel
{
	/*Class serial ID*/
	private static final long serialVersionUID = 1L;
	
	//Local fields
	private Color primaryColour;
	private Color secondaryColour;
	private ImageBuffer buffer;
	private ToolBox toolBox;
	private boolean mousePressed;
	private ArrayList<Points> points;
	
	public Canvas(ImageBuffer buffer, ToolBox toolBox, Color primaryColour, Color secondaryColour)
	{
		//Instantiate local fields
		this.buffer = buffer;
		this.toolBox = toolBox;
		this.primaryColour = primaryColour;
		this.secondaryColour = secondaryColour;
		mousePressed = false;
		points = new ArrayList<Points>();
		
		//Setup JPanel
		this.setBackground(secondaryColour);
		this.addMouseListener(new MouseClick());
		this.addMouseMotionListener(new MouseMove());
	}
	
	//Setters
	public void setPrimaryColour(Color primaryColour)
	{
		this.primaryColour = primaryColour;
	}
	
	public void setSecondaryColour(Color secondaryColour)
	{
		this.secondaryColour = secondaryColour;
	}
	
	//Getters
	public Color getPrimaryColour()
	{
		return this.primaryColour;
	}
	
	public Color getSecondaryColour()
	{
		return this.secondaryColour;
	}
	
	//Action methods
	public void initialRender()
	{
		buffer.setNewImage(render());
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g;
		
		if(buffer.getCurrentImage() !=null) 
			g2d.drawImage(buffer.getCurrentImage(), null, 0, 0);
		
		for(int i=0;i<points.size();i++)
		{
			g2d.setColor(points.get(i).getColour());
			g2d.setStroke(points.get(i).getStroke());
			if(i<points.size()-1)
			{
				g2d.drawLine(points.get(i).getX(), points.get(i).getY(), 
						points.get(i+1).getX(), points.get(i+1).getY());
			}else{
				g2d.drawLine(points.get(i).getX(), points.get(i).getY(), 
						points.get(i).getX(), points.get(i).getY());
			}
		}
	}
	
	private BufferedImage render()
	{
        // Create BufferedImage of JPanel
        BufferedImage bi = new BufferedImage(this.getWidth(), 
        this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        this.paint(g2d);
        g2d.dispose();
             
        // Scale dimension size of BufferedImage and return it
        AffineTransform at = new AffineTransform();
        at.scale(1, 1);
        AffineTransformOp scaleOp = new AffineTransformOp(at, 
        AffineTransformOp.TYPE_BILINEAR);
        return scaleOp.filter(bi, null);
	}
	
	private class MouseClick implements MouseListener
	{
		@Override
		public void mouseClicked(MouseEvent e)
		{
			toolBox.drawing(e.getX(), e.getY(), primaryColour, points);
			repaint();
			buffer.setNewImage(render());
			points = new ArrayList<Points>();
			repaint();
		}

		@Override
		public void mouseEntered(MouseEvent e)
		{
			setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}

		@Override
		public void mouseExited(MouseEvent e)
		{
			
		}

		@Override
		public void mousePressed(MouseEvent e)
		{
			mousePressed = true;
			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e)
		{
			mousePressed = false;
			//Render panel contents to the image buffer
			buffer.setNewImage(render());
			points = new ArrayList<Points>();
			repaint();
		}		
	}
	
	private class MouseMove implements MouseMotionListener
	{
		@Override
		public void mouseDragged(MouseEvent arg0)
		{
			if(mousePressed)
			{
				repaint();
				toolBox.drawing(arg0.getX(), arg0.getY(), primaryColour, points);
			}
		}

		@Override
		public void mouseMoved(MouseEvent arg0)
		{
			
		}		
	}
}
