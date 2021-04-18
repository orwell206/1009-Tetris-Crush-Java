package UI_Engine;

import java.awt.EventQueue;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Audio_Engine.BgMusic;

/* 
* 
* MainFrame.java
* Java Swing "Frame" component which serves as a base for all following JPanels to draw on-top of
* 
* Basic flow
* [JFrame] MainFrame.java -> new MainFrame()
* 								| 
* 								| -> [JPanel] MainMenu.java
* 										| 
* 										| -> [JButton] Start Game
* 						  						| 
* 												| -> [JPanel] GameBoard.java
* 										| 
* 										| -> [JButton] Settings
* 												| 
* 												| -> [JPanel] Settings.java
*/

public class MainFrame {
	// Variables used to retrieve data from Settings.txt
	private static String temp; 
	private static String line; 
	
	// Define the default settings for music
	private static double volume = 0.30;
	private static int musicstate = 1;
	private static boolean running = true; 	
	
	private JFrame frame;
	private JPanel contentPane;
	public static final int WINDOW_WIDTH = 945; // 945
	public static final int WINDOW_HEIGHT = 579;  
	public static final String APP_NAME = "Tetris Crush - Team 42";
	public static final BgMusic bgmusic = new BgMusic(System.getProperty("user.dir") + File.separator + "src" + File.separator +"Assets"+File.separator +"Tetris.wav");
	
	public MainFrame() {
		/*
		 * Params : None.
		 * 
		 * Desc : Default Constructor. 
		 * 
		 * Returns : None. 
		 */				
		// Core application window variables
		frame = new JFrame(APP_NAME);
		frame.setTitle(APP_NAME);
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(screenSize.width/2-frame.getSize().width/2, screenSize.height/2-frame.getSize().height/2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);

		// Generates new JPanel based on where you want to go and passes in "Frame"
		// Load initial page as MainMenu.java
		contentPane = new MainMenu(frame);
		// Set active pane to the one generated above (MainMenu.java)
		frame.setContentPane(contentPane);
	}
	
	public static void main(String[] args) {
		/*
		 * Params : String[] args
		 * 
		 * Desc : Main method.  
		 * 
		 * Returns : None. 
		 */				
		// Read data from Settings.txt
		try {
			BufferedReader f = new BufferedReader(new FileReader(System.getProperty("user.dir") + File.separator + "src" + File.separator + "Assets" + File.separator + "Settings.txt")); 
			while((line = f.readLine()) !=null){
				temp="";
				// Get music state to decide whether to mute bgmusic
				if (line.startsWith("musicstate=", 0)){ 
					temp = line.substring(line.indexOf("=") + 1); 
					musicstate= Integer.parseInt(temp);
					// If musicstate == 0, set running state to false (mute the bgmusic)
					// Else the running state will remain as default, which is true
					if (musicstate == 0) {
						running = false;
					}
				}
		
				// Get music volume
				if (line.startsWith("volume=", 0)){ 
					temp = line.substring(line.indexOf("=") + 1); 
					volume = Double.parseDouble(temp);
					// If volume is not within range 0-1, take default setting
					if (volume<0 || volume>1){
					    volume = 0.30;
					} 
				}
			} 
			f.close();
		
		// Print error message when:
		// - Settings file not found
		// - Data type error for musicstate and volume
		// - Null error due to no value found for musicstate and volume
		} catch (IOException | NumberFormatException |NullPointerException e) {
		   System.out.println("Unable to retreive setting from settings.txt. Default setting will be used");
		
		} finally {
			// Start bgmusic thread
			bgmusic.run();				
			bgmusic.setVolume(volume);	
			bgmusic.setStatus(running);
			// Play bgmusic only when running == true
			if (running){				
				bgmusic.playMusic();
		   }
		} 

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Renders "Frame" on start of application 
				new MainFrame();
			}
		});
	}
}