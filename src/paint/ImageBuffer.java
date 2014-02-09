package paint;

import java.awt.image.BufferedImage;
import java.util.Stack;

/**
 * Image Buffer class holds the current image
 * and a previous images. The previous images are held
 * in a stack, so as new elements are added to the canvas
 * the new image can be buffered and pushed onto the stack.
 * If the user needs to go back using undo, the previous
 * images can be popped off the stack
 * @author Liam A Doherty
 * @version 9.2.14
 */
public class ImageBuffer
{
	//Declare local fields
	private BufferedImage image;
	private String fileName;
	private Stack<BufferedImage> prevImages;
	
	/**
	 * Constructore
	 */
	public ImageBuffer()
	{
		//Instantiate local fields
		image = null;
		fileName = "Untitled";
		prevImages = new Stack<BufferedImage>();
	}
	
	//Setters
	/**
	 * Sets the file name of the image buffer
	 * @param fileName
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}
	
	/**
	 * Stores a new image in the image buffer and pushes
	 * the previous image onto the stack
	 * @param newImage
	 */
	public void setNewImage(BufferedImage newImage)
	{
		if(image!=null) prevImages.push(image);
		this.image = newImage;
	}
	
	//Getters
	/**
	 * Returns the file name of the image buffer
	 * @return fileName
	 */
	public String getFileName()
	{
		return this.fileName;
	}
	
	/**
	 * Returns the current image in the image buffer
	 * @return image
	 */
	public BufferedImage getCurrentImage()
	{
		return this.image;
	}
	
	//Action methods
	/**
	 * Clears the previous image stack. Used when a new file is created/loaded.
	 */
	public void resetPrevImages()
	{
		prevImages = new Stack<BufferedImage>();
	}
	
	/**
	 * Pops the former image of the previous
	 * image stack and renders it to the 
	 * canvas
	 * @param canvas
	 */
	public void undo(Canvas canvas)
	{
		if(prevImages.size() > 0)
		{
			image = prevImages.pop();
			canvas.repaint();
		}else{
			image = null;
			canvas.repaint();
		}
	}
}
