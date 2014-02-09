package paint;

import java.awt.BasicStroke;
import java.awt.Color;

/**
 * Points class. 
 * This class represents a single point on the screen.
 * While the user is drawing, these points are stored
 * in an array before being rendered as part of the
 * image.
 * @author Liam A Doherty
 * @version 9.2.14
 */
public class Points
{
	//Declare local fields
	private int x;
	private int y;
	private Color colour;
	private BasicStroke stroke;
	
	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param colour
	 * @param stroke
	 */
	public Points(int x, int y, Color colour, BasicStroke stroke)
	{
		this.x = x;
		this.y = y;
		this.colour = colour;
		this.stroke = stroke;
	}
	
	//Getters
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public Color getColour()
	{
		return this.colour;
	}
	
	public BasicStroke getStroke()
	{
		return this.stroke;
	}
}
