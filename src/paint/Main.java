package paint;

/**
 * Main application class. 
 * Builds the GUI and sets up application functionality
 * @author Liam A Doherty
 * @version 9.2.14
 */
public class Main
{
	/**
	 * Main class constructor.
	 * Launches application components
	 */
	public Main()
	{
		/*Launch GUI*/
		java.awt.EventQueue.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new GUI();
			}
		});
	}
	
	/**
	 * Application entry point.
	 * Creates an instance on the main class
	 */
	public static void main(String[] args)
	{
		new Main();
	}
}
