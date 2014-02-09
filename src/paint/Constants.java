package paint;

import java.awt.Color;

public abstract class Constants
{
	//GUI
	//Main Window
	/*Set size*/
	public static final int WINDOW_XPOS = 200;
	public static final int WINDOW_YPOS = 100;
	public static final int WINDOW_XSIZE = 600;
	public static final int WINDOW_YSIZE = 500;
	
	//Tool bar
	/*Defaults*/
	public static final Tools ACTIVE_TOOL = Tools.PENCIL;//Active drawing tool
	public static final String ACTIVE_BRUSHMODE = "1";//Active brush mode for tool
	public static final Color ACTIVE_PRIMARY = Color.white;//Active brush colour
	public static final Color ACTIVE_SECONDARY = Color.black;//Active background colour
}
