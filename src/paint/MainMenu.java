package paint;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

/**
 * Main menu class.
 * This class holds all the functionality for
 * menu options that can be triggered by the GUI
 * @author Liam A Doherty
 * @version 9.2.14
 */
public class MainMenu
{
	/**
	 * Creates a new blank canvas
	 * @param canvas
	 * @param buffer
	 */
	public void fileNew(Canvas canvas, ImageBuffer buffer)
	{
		buffer.setNewImage(null);
		buffer.setFileName("Untitled");
		buffer.resetPrevImages();
		canvas.repaint();
	}
	
	/**
	 * Loads a new image into the application
	 * @param canvas
	 * @param buffer
	 */
	public void fileOpen(Canvas canvas, ImageBuffer buffer)
	{
		JFileChooser open = new JFileChooser();
		int openVal = open.showOpenDialog(null);
		if(openVal == JFileChooser.APPROVE_OPTION)
		{
			try{
				buffer.setNewImage(ImageIO.read(new File(open.getSelectedFile().getPath())));
				buffer.setFileName(open.getSelectedFile().getName());
				buffer.resetPrevImages();
				canvas.repaint();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Saves the image
	 * @param buffer
	 */
	public void fileSave(ImageBuffer buffer)
	{
		JFileChooser save = new JFileChooser();
		int saveVal = save.showSaveDialog(null);
		if(saveVal == JFileChooser.APPROVE_OPTION)
		{
			
		}
	}
	
	/**
	 * Saves the current image to a specified file
	 */
	public void fileSaveAs()
	{
		//JFileChooser saveAs = new JFileChooser();
		//int saveAsVal = saveAs.showSaveDialog(new JDialog());
	}
	
	/**
	 * Undo functionality
	 * @param buffer
	 * @param canvas
	 */
	public void editUndo(ImageBuffer buffer, Canvas canvas)
	{
		buffer.undo(canvas);
	}
	
	public void editSelectAll()
	{
		
	}
	
	public void editCut()
	{
		
	}
	
	public void editCopy()
	{
		
	}
	
	public void editPaste()
	{
		
	}
	
	public void helpAbout()
	{
		
	}
}

