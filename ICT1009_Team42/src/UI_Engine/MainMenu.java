package UI_Engine;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import Game_Engine.GameBoard;
import Player_Engine.Player;

import java.awt.Font;

/*
 *
 * MainMenu.java
 * Launching point for other components
 *
 */

public class MainMenu extends JPanel {
	private JTextField player1;
	private JTextField player2;
	private Player playerOne;
	private Player playerTwo;

    public MainMenu(JFrame mf) {
		/*
		 * Params : JFrame mf 
		 * 
		 * Desc : Default Constructor. 
		 * 
		 * Returns : None. 
		 */		    	
        // Set base JPanel to take full size of MainFrame
        setPreferredSize(new Dimension(952, 579));
        setBackground(Color.GRAY);
        setLayout(null);	// layout(null) = absolute positioning using coordinates

        // Label for player 1 name
        JLabel playerlabel1 = new JLabel("Player 1:");
        playerlabel1.setFont(new Font("Tahoma", Font.BOLD, 16));
        playerlabel1.setForeground(Color.black);
        playerlabel1.setBounds(380, 68, 121, 14);
        add(playerlabel1);
        
        // Text field for player 1 name
        player1 = new JTextField();
        player1.setBounds(380, 90, 162, 32);
        add(player1);
        player1.setColumns(10);
        
        // Label for player 2 name
        JLabel playerlabel2 = new JLabel("Player 2:");
        playerlabel2.setFont(new Font("Tahoma", Font.BOLD, 16));
        playerlabel2.setForeground(Color.black);
        playerlabel2.setBounds(380, 147, 109, 14);
        add(playerlabel2);
        
        // Text field for player 2 name
        player2  = new JTextField();
        player2.setBounds(380, 165, 163, 32);
        add(player2);
        player2.setColumns(10);
        
        // Label for difficulty level
        JLabel levellabel = new JLabel("Difficulty Level:");
        levellabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        levellabel.setForeground(Color.black);
        levellabel.setBounds(380, 230, 300, 14);
        add(levellabel);
        
        // Radio button for difficulty level
        JRadioButton easyLevel = new JRadioButton("Easy");
        JRadioButton medLevel = new JRadioButton("Medium");
        JRadioButton hardLevel = new JRadioButton("Hard");
        medLevel.setSelected(true);
        ButtonGroup group = new ButtonGroup();
        group.add(easyLevel);
        group.add(medLevel);
        group.add(hardLevel);
        easyLevel.setBounds(375, 250, 65, 20);
        medLevel.setBounds(425, 250, 75, 20);
        hardLevel.setBounds(495, 250, 75, 20);
        easyLevel.setOpaque(false);
        medLevel.setOpaque(false);
        hardLevel.setOpaque(false);
        add(easyLevel);
        add(medLevel);
        add(hardLevel);

        // Label for error message
        JLabel errorLabel = new JLabel("");
        errorLabel.setBounds(360, 285, 396, 14);
        errorLabel.setForeground(new Color(199, 38, 38));
        add(errorLabel);
        
        // Start button
        JButton btn_playNow = new JButton("Start Game");
        btn_playNow.setOpaque(false);
        btn_playNow.setBackground(Color.BLACK);
        btn_playNow.setIcon(new ImageIcon(MainMenu.class.getResource("/Assets/icon_start.png")));
        // Button click listener
        btn_playNow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // Get the player name and validate whether the names are empty
                        String player1_name = player1.getText();
						String player2_name = player2.getText();

                        // If any of the name field is empty, display error message
                        if (player1_name.trim().isEmpty() || player2_name.trim().isEmpty()) {
                            errorLabel.setText("Please input names for both players");
                        } else {
                        	String choosenLevel ="";
                        	if (easyLevel.isSelected()) {
                        		choosenLevel = "Easy";
                        	} else if (medLevel.isSelected()) {
                        		choosenLevel = "Med";
                        	}
                        	else if (hardLevel.isSelected()) {
                        		choosenLevel = "Hard";
                        	}
                    		playerOne = new Player(1, 4, 0, 0, 4, 0, 10, 0, 300,player1_name,choosenLevel);
                    		playerTwo = new Player(2, 25, 0, 0, 4, 21, 31, 929, 630,player2_name,choosenLevel);
                            // Generates JPanel based on where you want to go and passes in "Frame"
                            // Load GameBoard.java and pass in JFrame, in this case "mf" from line 17
                            JPanel contentPane = new GameBoard(mf,playerOne,playerTwo);
                            // Set active pane to the one generated above (GameBoard.java)
                            mf.setContentPane(contentPane);
                            mf.revalidate();
                        }

                    }
                });
            }
        });
        btn_playNow.setBounds(390, 320, 139, 45);
        add(btn_playNow);
        
        // Scoreboard icon
        JButton score_Board = new JButton("");
        score_Board.setBackground(Color.WHITE);
        score_Board.setForeground(Color.WHITE);
        score_Board.setOpaque(false);
        score_Board.setIcon(new ImageIcon(MainMenu.class.getResource("/Assets/scoreboard.png")));
        score_Board.setBounds(864, 124, 75, 75);
        // Button click listener
        score_Board.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // Generates JPanel based on where you want to go and passes in "Frame"
                        // Load GameBoard.java and pass in JFrame, in this case "mf" from line 17
                        JPanel scoreBoard = new ScoreBoard(mf);
                        // Set active pane to the one generated above (GameBoard.java)
                        mf.setContentPane(scoreBoard);
                        mf.revalidate();
                    }
                });
            }
        });
        add(score_Board);
        
        // Setting icon
        JButton btn_settings = new JButton("");
        btn_settings.setEnabled(false);
        btn_settings.setBackground(Color.WHITE);
        btn_settings.setOpaque(false);
        btn_settings.setForeground(new Color(102, 204, 255));
        btn_settings.setIcon(null);
        btn_settings.setIcon(new ImageIcon(MainMenu.class.getResource("/Assets/baseline_settings_black.png")));
        btn_settings.setBounds(864, 49, 75, 75);
        btn_settings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // Generates JPanel based on where you want to go and passes in "Frame"
                        // Load GameBoard.java and pass in JFrame, in this case "mf" from line 17
                        JPanel settingBoard = new Settings(mf);
                        // Set active pane to the one generated above (GameBoard.java)
                        mf.setContentPane(settingBoard);
                        mf.revalidate();
                    }
                });
            }
        });
        add(btn_settings);
        
        // Gif in background 
        JLabel Gif = new JLabel("");
        Gif.setForeground(Color.RED);
        Gif.setIcon(new ImageIcon(MainMenu.class.getResource("/Assets/l8l.gif")));
        Gif.setBounds(305, 0, 335, 568);
        add(Gif);
        
        // Background image
        JLabel backGround = new JLabel("");
        backGround.setBackground(Color.WHITE);
        backGround.setIcon(new ImageIcon(MainMenu.class.getResource("/Assets/p0102gs2.jpg")));
        backGround.setBounds(-18, -57, 1077, 731);
        backGround.setForeground(Color.WHITE);
        add(backGround);
        JLabel topLabel = new JLabel("New label");
        topLabel.setIcon(new ImageIcon(MainMenu.class.getResource("/Assets/qfff.JPG")));
        topLabel.setBounds(-18, 0, 970, 38);
        add(topLabel);
    }
}