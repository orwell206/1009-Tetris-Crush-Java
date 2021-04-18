package UI_Engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Settings extends JPanel{
	private JSlider slider;

	public Settings(JFrame mf) {
		/*
		 * Params : JFrame mf 
		 * 
		 * Desc : Default Constructor. 
		 * 
		 * Returns : None. 
		 */		
		// Set base JPanel to take full size of MainFrame
		setPreferredSize(new Dimension(MainFrame.WINDOW_WIDTH, MainFrame.WINDOW_HEIGHT));
		setBackground(Color.GRAY);
		setLayout(null);

		// Label for title
		JLabel label = new JLabel("SETTINGS");
		Font font = new Font("Eras Bold ITC", Font.PLAIN, 25);
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		label.setFont(font.deriveFont(attributes));
		label.setBounds(397, 60, 250, 22);
		add(label);
		
		// Label for background music
		JLabel lblNewLabel_1 = new JLabel("Background Music");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(385, 115, 250, 22);
		add(lblNewLabel_1);
		
		// Button to mute or unmute the music
		JButton musicButton = new JButton("");
		musicButton.setForeground(Color.BLACK);
		musicButton.setOpaque(false);

		// Change the icon of the button according to the bgmusic running state		
		if(MainFrame.bgmusic.getStatus()){
			musicButton.setText("Mute");
			musicButton.setIcon(new ImageIcon(Settings.class.getResource("/Assets/icon_mute.png")));
        } else {
        	musicButton.setText("Unmute");
        	musicButton.setIcon(new ImageIcon(Settings.class.getResource("/Assets/icon_unmute.png")));
        }

        // Button click listener
		musicButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                    	if(MainFrame.bgmusic.getStatus()){
                    		MainFrame.bgmusic.stopMusic();
                    		musicButton.setText("Unmute");
                    		musicButton.setIcon(new ImageIcon(Settings.class.getResource("/Assets/icon_unmute.png")));
                    	} else {
                    		MainFrame.bgmusic.resumeMusic();
                    		musicButton.setText("Mute");
                    		musicButton.setIcon(new ImageIcon(Settings.class.getResource("/Assets/icon_mute.png")));
                       }
                    }
                });
            }
        });
		musicButton.setBounds(390, 145, 139, 45);
        add(musicButton);

        // Label for volume adjustment
		JLabel lblNewLabel_2 = new JLabel("Adjust Music Volume");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(370, 215, 246, 28);
		add(lblNewLabel_2);	
        
		// Slider to adjust the volume of music
        slider = new JSlider(JSlider.HORIZONTAL, 0, 100, (int)(MainFrame.bgmusic.getVolume()*100.0));   
		slider.setMinorTickSpacing(10);  
		slider.setMajorTickSpacing(20);  
		slider.setPaintTicks(true);  
		slider.setPaintLabels(true);
		slider.setBackground(new Color(224, 190, 94));
		Event e = new Event();
		slider.addChangeListener(e);
		slider.setBounds(390, 250, 139, 45);
		add(slider);  
		
		// Adds a 'back' button
		JButton btn_back = new JButton("");
		btn_back.setBackground(Color.WHITE);
		btn_back.setForeground(Color.WHITE);
		btn_back.setOpaque(false);
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						// Save the current settings to Setting.txt
						try {
							// Overwrite the Settings.txt 
							// Note that this will create the Settings.txt if it is not exist
							File f = new File((System.getProperty("user.dir") + File.separator + "src" + File.separator + "Assets" + File.separator + "Settings.txt"));
							FileWriter fw = new FileWriter(f, false); // Put false to overwrite the setting file                                    
							if (MainFrame.bgmusic.getStatus()) {
								fw.write("musicstate=1\n");
							} else {
								fw.write("musicstate=0\n");
							}
							fw.write("volume="+MainFrame.bgmusic.getVolume());
							fw.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
						
						// Redirect to main menu
						JPanel contentPane = new MainMenu(mf);
						mf.getContentPane().removeAll();
						mf.setContentPane(contentPane);
						mf.revalidate();	
						mf.repaint();
					}
				});
			}
		});		
		btn_back.setBounds(10, 0, 149, 121);
		btn_back.setOpaque(false);
		btn_back.setIcon(new ImageIcon(Settings.class.getResource("/Assets/rsz_circled-left.png")));
		add(btn_back);
		
		// Gif in background 
		JLabel Gif = new JLabel("");
        Gif.setForeground(Color.RED);
        Gif.setIcon(new ImageIcon(Settings.class.getResource("/Assets/l8l.gif")));
        Gif.setBounds(306, 0, 335, 568);
        add(Gif);
		
        // Background image
		JLabel BACKGROUND = new JLabel();
		BACKGROUND.setIcon(new ImageIcon(Settings.class.getResource("/Assets/p0102gs2.jpg")));
		BACKGROUND.setBounds(-11, 11, 956, 601);
		add(BACKGROUND);
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Settings.class.getResource("/Assets/qfff.JPG")));
		lblNewLabel.setBounds(0, 0, 945, 38);
		add(lblNewLabel);
	}

	public class Event implements ChangeListener{
		@Override
		public void stateChanged(ChangeEvent e) {
			/*
			 * Params : ChangeEvent e 
			 * 
			 * Desc : Detect changes on the volume slider and set the bgmusic volume accordingly.
			 * 
			 * Returns : None. 
			 */
			MainFrame.bgmusic.setVolume((double)(slider.getValue()/100.0));
		}
	}
}