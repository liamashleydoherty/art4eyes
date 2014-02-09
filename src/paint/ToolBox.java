package paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Tool box class.
 * This class contains the functionality for 
 * tools available to the user in the tool box.
 * E.g. pencil, brush, fill etc...
 * @author Liam A Doherty
 * @version 9.2.14
 */
public class ToolBox
{
	//Declare local fields
	private Tools activeTool;
	
	/**
	 * Tool box constructor
	 * @param activeTool
	 */
	public ToolBox(Tools activeTool)
	{
		this.activeTool = activeTool;
	}
	
	//Getters
	public Tools getActiveTool()
	{
		return this.activeTool;
	}

	//Functionality methods
	public void pencil()
	{
		activeTool = Tools.PENCIL;
	}
	
	public void brush()
	{
		activeTool = Tools.BRUSH;
	}
	
	public void spray()
	{
		activeTool = Tools.SPRAY;
	}
	
	public void fill()
	{
		activeTool = Tools.FILL;
	}
	
	public void line()
	{
		activeTool = Tools.LINE;
	}
	
	public void curve()
	{
		activeTool = Tools.CURVE;
	}
	
	public void square()
	{
		activeTool = Tools.SQUARE;
	}
	
	public void ellipse()
	{
		activeTool = Tools.ELLIPSE;
	}
	
	/*
	 * Where the actual algorithms for each tool should be
	 * implemented. So far just a pencil tool....
	 */
	public void drawing(int x, int y, Color colour, ArrayList<Points> points)
	{
		switch(activeTool)
		{
		case PENCIL:
			points.add(new Points(x, y, colour, 
					new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)));
			break;
		case BRUSH:
			break;
		case SPRAY:
			break;
		case FILL:
			break;
		case LINE:
			break;
		case CURVE:
			break;
		case SQUARE:
			break;
		case ELLIPSE:
			break;
			default:break;
		}
	}
}
