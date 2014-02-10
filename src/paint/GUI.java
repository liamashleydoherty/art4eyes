package paint;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;


/**
 * Builds the GUI for a painting program
 * @author Liam A Doherty
 * @version 9.2.14
 */
public class GUI
{
	//Local fields
	/*Canvas holder*/
	private JInternalFrame internalFrame;
	/*Main Menu functionality*/
	private MainMenu mainMenu;
	/*Tool box functionality*/
	private ToolBox toolBox;
	/*Image buffer*/
	private ImageBuffer buffer;
	/*Canvas functionality*/
	private Canvas canvas;
	
	public GUI()
	{
		//Instantiate local fields
		mainMenu = new MainMenu();
		toolBox  = new ToolBox(Constants.ACTIVE_TOOL);
		buffer   = new ImageBuffer();
		canvas   = new Canvas(buffer, toolBox, Constants.ACTIVE_PRIMARY, Constants.ACTIVE_SECONDARY);
		
		//Build top level container
		JFrame frame = new JFrame("Art 4 Eyes");
		frame.setBounds(Constants.WINDOW_XPOS, Constants.WINDOW_YPOS, 
				Constants.WINDOW_XSIZE, Constants.WINDOW_YSIZE);
		frame.setLayout(new BorderLayout());
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		//Add menu bar to top of frame
		frame.add(mainMenu(), BorderLayout.PAGE_START);
		
		//Add panel centre and set layout of pane to border layout
		JPanel panel = new JPanel(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		
		//Add tool bar to page start of panel
		panel.add(toolBar(), BorderLayout.PAGE_START);
		
		//Add internal frame containing canvas
		internalFrame = internalFrame();
		panel.add(internalFrame, BorderLayout.CENTER);
	}
	
	/**
	 * Builds main menu
	 * File - New Ctrl + N
	 * 		- Open Ctrl + O
	 * 		- Save Ctrl + S
	 * 		- Save As...
	 * 		-----------
	 * 		- Exit
	 * Edit	- Undo
	 * 		- Select All Crtl + A
	 * 		- Cut Ctrl + X
	 * 		- Copy Ctrl + C
	 * 		- Paste Ctrl + V
	 * 		-----------
	 * 		- Rotate Right
	 * 		- Rotate Left
	 * 		- Flip Vertical
	 * 		- Flip Horizontal
	 * FX	- Black & White
	 * 		- Invert
	 * Help	- About
	 * @return
	 */
	private JMenuBar mainMenu()
	{
		//Main menu
		JMenuBar main = new JMenuBar();
		
		//File Menu
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		/*New*/
		JMenuItem neW = new JMenuItem("New", 0);
		neW.setMnemonic(KeyEvent.VK_N);
		neW.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		neW.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				mainMenu.fileNew(canvas, buffer);
				internalFrame.setTitle(buffer.getFileName());
			}
		});
		file.add(neW);
		/*Open*/
		JMenuItem open = new JMenuItem("Open", 1);
		open.setMnemonic(KeyEvent.VK_O);
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		open.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				mainMenu.fileOpen(canvas, buffer);
				internalFrame.setTitle(buffer.getFileName());
			}
		});
		file.add(open);
		/*Save*/
		JMenuItem save = new JMenuItem("Save", 2);
		save.setMnemonic(KeyEvent.VK_S);
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		save.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				mainMenu.fileSave(buffer);
			}
		});
		file.add(save);
		/*Save As...*/
		JMenuItem saveas = new JMenuItem("Save As...", 3);
		saveas.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				mainMenu.fileSaveAs();
			}
		});
		file.add(saveas);
		file.addSeparator();
		/*Exit*/
		JMenuItem exit = new JMenuItem("Exit", 4);
		exit.setMnemonic(KeyEvent.VK_E);
		exit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		file.add(exit);
		
		//Edit Menu
		JMenu edit = new JMenu("Edit");
		edit.setMnemonic(KeyEvent.VK_D);
		/*Undo*/
		JMenuItem undo = new JMenuItem("Undo", 0);
		undo.setMnemonic(KeyEvent.VK_U);
		undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
		undo.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				mainMenu.editUndo(buffer, canvas);
			}
		});
		edit.add(undo);
		/*Select All*/
		JMenuItem selectAll = new JMenuItem("Select All", 1);
		selectAll.setMnemonic(KeyEvent.VK_A);
		selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		selectAll.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				mainMenu.editSelectAll();
			}
		});
		edit.add(selectAll);
		/*Cut*/
		JMenuItem cut = new JMenuItem("Cut", 2);
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		cut.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				mainMenu.editCut();
			}
		});
		edit.add(cut);
		/*Copy*/
		JMenuItem copy = new JMenuItem("Copy", 3);
		copy.setMnemonic(KeyEvent.VK_C);
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		copy.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				mainMenu.editCopy();
			}
		});
		edit.add(copy);
		/*Paste*/
		JMenuItem paste = new JMenuItem("Paste", 4);
		paste.setMnemonic(KeyEvent.VK_P);
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		paste.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				mainMenu.editPaste();
			}
		});
		edit.add(paste);
		edit.addSeparator();
		/*Rotate right*/
		JMenuItem rotateRight = new JMenuItem("Rotate Right", 5);
		rotateRight.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				mainMenu.editRotateRight(buffer, canvas);
			}
		});
		edit.add(rotateRight);
		/*Rotate left*/
		JMenuItem rotateLeft = new JMenuItem("Rotate Left", 6);
		rotateLeft.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				mainMenu.editRotateLeft(buffer, canvas);
			}
		});
		edit.add(rotateLeft);
		/*Flip vertical*/
		JMenuItem flipVertical = new JMenuItem("Flip Vertical", 7);
		flipVertical.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				mainMenu.editFlipVertical(buffer, canvas);
			}
		});
		edit.add(flipVertical);
		/*Flip Horizontal*/
		JMenuItem flipHorizontal = new JMenuItem("Flip Horizontal", 8);
		flipHorizontal.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				mainMenu.editFlipHorizontal(buffer, canvas);
			}
		});
		//Effects Menu
		JMenu fx = new JMenu("FX");
		fx.setMnemonic(KeyEvent.VK_X);
		/*Black & White*/
		JMenuItem blackWhite = new JMenuItem("Black & White", 0);
		blackWhite.setMnemonic(KeyEvent.VK_B);
		blackWhite.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
		blackWhite.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				mainMenu.effectsBlackWhite(buffer, canvas);
			}
		});
		fx.add(blackWhite);
		/*Grey Scale*/
		JMenuItem greyScale = new JMenuItem("Grey Scale", 1);
		greyScale.setMnemonic(KeyEvent.VK_G);
		greyScale.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
		greyScale.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				mainMenu.effectsGreyScale(buffer, canvas);
			}
		});
		fx.add(greyScale);
		/*Invert*/
		JMenuItem invert = new JMenuItem("Invert", 2);
		invert.setMnemonic(KeyEvent.VK_I);
		invert.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		invert.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				mainMenu.effectsInvert(buffer, canvas);
			}
		});
		fx.add(invert);
		
		//Help Menu
		JMenu help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);
		/*About*/
		JMenuItem about = new JMenuItem("About", 0);
		about.setMnemonic(KeyEvent.VK_A);
		about.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				mainMenu.helpAbout();
			}
		});
		help.add(about);
		
		main.add(file);
		main.add(edit);
		main.add(fx);
		main.add(help);
		return main;
	}
	
	/**
	 * Originally all the objects were declared in
	 * a for loop using an array of JButtons. However,
	 * I can't figure out a way of implementing
	 * different behaviour in the action listener
	 * for each object without moving the scope
	 * of the buttons array out of the method.
	 * But that doesn't seam elegant as I haven't
	 * done that with any over UI objects. So for
	 * consistency and because there is only a small
	 * number of tools each tool will be declared
	 * separately and not in a loop. This was
	 * the case with the menu items also.
	 * @return
	 */
	private JToolBar toolBar()
	{
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		
		//Tools
		/*Pencil Tool*/
		JButton pencil = new JButton("Pencil");
		pencil.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				toolBox.pencil();
			}
		});
		toolBar.add(pencil);
		/*Brush tool*/
		JButton brush = new JButton("Brush");
		brush.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				toolBox.brush();
			}
		});
		toolBar.add(brush);
		/*Spray tool*/
		JButton spray = new JButton("Spray");
		spray.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				toolBox.spray();
			}
		});
		toolBar.add(spray);
		/*Fill tool*/
		JButton fill = new JButton("Fill");
		fill.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				toolBox.fill();
			}
		});
		toolBar.add(fill);
		/*Line tool*/
		JButton line = new JButton("Line");
		line.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				toolBox.line();
			}
		});
		toolBar.add(line);
		/*curve tool*/
		JButton curve = new JButton("Curve");
		curve.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				toolBox.curve();
			}
		});
		toolBar.add(curve);
		/*Square tool*/
		JButton square = new JButton("Square");
		square.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				toolBox.square();
			}
		});
		toolBar.add(square);
		/*Ellipse tool*/
		JButton ellipse = new JButton("Ellipse");
		ellipse.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				toolBox.ellipse();
			}
		});
		toolBar.add(ellipse);
		
		return toolBar;
	}

	/*
	 * 
	 */
	private JInternalFrame internalFrame()
	{
		JInternalFrame internalFrame = new JInternalFrame();
		internalFrame.setVisible(true);
		internalFrame.setTitle(buffer.getFileName());
		internalFrame.add(canvas);
		return internalFrame;
	}
}
