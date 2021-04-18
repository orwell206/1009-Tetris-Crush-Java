package Game_Engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.*;
import Audio_Engine.SoundEffect;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.KeyboardFocusManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import Player_Engine.KeyBindings;
import Player_Engine.Player;
import Player_Engine.*;
import UI_Engine.MainFrame;
import UI_Engine.MainMenu;

import java.awt.Font;

public class GameBoard extends JPanel {
	// Swing components
	public JLabel lbl_player1Score;
	public JLabel lbl_player2Score;
	public JLabel lbl_player1Live;
	public JLabel lbl_player2Live;	
	private JPanel panel_top;
	private JButton btn_back;
	private JButton btn_pause;
	private JLabel lbl_player1label;
	private JLabel lbl_player2Label;
	private JLabel lbl_upcomingTetro;
	private JLabel lbl_p1UpcomingTetro;
	private JLabel lbl_p2UpcomingTetro;

	private static final int TOTAL_BOARD_WIDTH = 32;
	private static final int TOTAL_BOARD_HEIGHT = 19;
	private static final int PLAYER_BOARD_WIDTH = 10;
	private static final int PLAYER_BOARD_HEIGHT = 16;
	private static final int BOARD_UPPER_SPACE = 60;
	private static final int BLOCK_SIZE = 30;
	private static int fps = 60; 
	private static int delay = fps / 1000; 	
	private Timer looper; 
	private Color[][] gameboard = new Color[TOTAL_BOARD_HEIGHT][TOTAL_BOARD_WIDTH];
	private TetrominoLogic cp1Tetromino;
	private TetrominoLogic cp2Tetromino;
	private TetrominoShape p1UpcomingTetromino = new Tetromino().getRandomTetromino();
	private TetrominoShape p1CurrentTetromino;
	private TetrominoShape p2UpcomingTetromino = new Tetromino().getRandomTetromino();;
	private TetrominoShape p2CurrentTetromino;
	private Player playerOne;
	private Player playerTwo;
	private KeyBindings Keybindings;
	private SoundEffect sound_fallTetris = new SoundEffect(System.getProperty("user.dir") + File.separator + "src" + File.separator + "Assets" + File.separator + "fallTetris.wav");
	private SoundEffect sound_clearSound = new SoundEffect(System.getProperty("user.dir") + File.separator + "src" + File.separator + "Assets" + File.separator + "ClearSound.wav");
	private SoundEffect sound_gameOver = new SoundEffect(System.getProperty("user.dir") + File.separator + "src" + File.separator + "Assets" + File.separator + "gameover.wav");
	public GameBoard(JFrame mf, Player player1, Player player2) {

		setForeground(Color.WHITE);
		/*
		 * Params : JFrame mf, Player player 1, Player player 2
		 *
		 * Default parameterised constructor.
		 */
		setPreferredSize(new Dimension(MainFrame.WINDOW_WIDTH, MainFrame.WINDOW_HEIGHT));
		setBackground(new Color(182,212,255)); // set the blue background 
		setLayout(null);

		// Initialize player stuff
		init_players(player1, player2);

		// Base JPanel
		panel_top = new JPanel();
		panel_top.setBackground(new Color(182,212,255));
		panel_top.setForeground(Color.WHITE);
		panel_top.setBounds(0, 0, MainFrame.WINDOW_WIDTH, 60);
		add(panel_top);
		panel_top.setLayout(null);

		//End game button. Redirects back to MainMenu.java and terminates gameThread.
		btn_back = new JButton("End Game");
		btn_back.setBackground(Color.WHITE);
		btn_back.setForeground(Color.BLACK);
		btn_back.setOpaque(false);
		btn_back.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_back.setIcon(new ImageIcon(GameBoard.class.getResource("/Assets/icon_end.png")));
		btn_back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						JPanel contentPane = new MainMenu(mf);
						mf.getContentPane().removeAll();
						mf.setContentPane(contentPane);
						mf.revalidate();
						mf.repaint();
						stop_gameThread();
					}
				});
			}
		});
		btn_back.setBounds(534, -10, 181, 74);
		panel_top.add(btn_back);

		// Pause game button. Toggles gameThread between running and stopped.
		btn_pause = new JButton("Pause");
		btn_pause.setOpaque(false);
		btn_pause.setBackground(Color.WHITE);
		btn_pause.setForeground(Color.BLACK);
		btn_pause.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_pause.setIcon(new ImageIcon(GameBoard.class.getResource("/Assets/icon_pause.png")));
		btn_pause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pause_gameThread();
			}
		});
		btn_pause.setBounds(243, 0, 181, 64);
		panel_top.add(btn_pause);

		lbl_player1label = new JLabel("Player 1: " + playerOne.getPlayerName());
		lbl_player1label.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_player1label.setBounds(317, 80, 155, 14);
		lbl_player1label.setForeground(new Color(0, 0, 0));
		add(lbl_player1label);

		lbl_player1Score = new JLabel("Score: " + playerOne.getScores());
		lbl_player1Score.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_player1Score.setBounds(317, 105, 155, 14);
		lbl_player1Score.setForeground(new Color(0, 0, 0));
		add(lbl_player1Score);

		lbl_player1Live = new JLabel("Lives: " + playerOne.getLives());
		lbl_player1Live.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_player1Live.setBounds(317, 130, 155, 14);
		lbl_player1Live.setForeground(new Color(0, 0, 0));
		add(lbl_player1Live);

		lbl_player2Label = new JLabel("Player 2: " + playerTwo.getPlayerName());
		lbl_player2Label.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_player2Label.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_player2Label.setBounds(460, 80, 150, 14);
		lbl_player2Label.setForeground(new Color(0, 0, 0));
		add(lbl_player2Label);

		lbl_player2Score = new JLabel("Score: " + playerTwo.getScores());
		lbl_player2Score.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_player2Score.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_player2Score.setBounds(460, 105, 150, 14);
		lbl_player2Score.setForeground(new Color(0, 0, 0));
		add(lbl_player2Score);

		lbl_player2Live = new JLabel("Lives: " + playerTwo.getLives());
		lbl_player2Live.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_player2Live.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_player2Live.setBounds(460, 130, 150, 14);
		lbl_player2Live.setForeground(new Color(0, 0, 0));
		add(lbl_player2Live);

		lbl_upcomingTetro = new JLabel("Next Block");
		Font font = new Font("Eras Bold ITC", Font.PLAIN, 25);
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lbl_upcomingTetro.setFont(font.deriveFont(attributes));
		lbl_upcomingTetro.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_upcomingTetro.setBounds(340, 220, 260, 20);
		add(lbl_upcomingTetro);

		lbl_p1UpcomingTetro = new JLabel("");
		lbl_p1UpcomingTetro.setIcon(new ImageIcon(GameBoard.class.getResource("")));
		lbl_p1UpcomingTetro.setBounds(340, 240, 130, 100);
		add(lbl_p1UpcomingTetro);

		lbl_p2UpcomingTetro = new JLabel("");
		lbl_p2UpcomingTetro.setIcon(new ImageIcon(GameBoard.class.getResource("")));
		lbl_p2UpcomingTetro.setBounds(500, 240, 130, 100);
		add(lbl_p2UpcomingTetro);

		// Enables the listening of the game's key bindings
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		this.Keybindings = new KeyBindings(cp1Tetromino, cp2Tetromino, playerOne, playerTwo);
		manager.addKeyEventDispatcher(this.Keybindings);

		// Start gameThread
		start_gameThread();
		
		//Start effectThreads
		sound_clearSound.run();
		sound_fallTetris.run();
		sound_gameOver.run();
	}

	public int getPlayerBoardWidth() { 
		/*
		 * Params : None. 
		 * 
		 * Desc : gets the player's board width. 
		 * 
		 * Returns : int PLAYER_BOARD_WIDTH.
		 */				
		return PLAYER_BOARD_WIDTH; 
		}

	public int getPlayerBoardHeight() { 
		/*
		 * Params : None. 
		 * 
		 * Desc : gets the player's board height. 
		 * 
		 * Returns : int PLAYER_BOARD_HEIGHT.
		 */				
		return PLAYER_BOARD_HEIGHT; 
		}

	private void init_players(Player player1, Player player2) {
		/*
		 * Params : Player player1, Player player2. 
		 * 
		 * Desc : Initalises player One and Player Two.
		 * 
		 * Returns : None.
		 */		
		this.playerOne = player1;
		this.playerTwo = player2;
		cp1Tetromino = new TetrominoLogic(new Tetromino().getRandomTetromino(), this);
		cp2Tetromino = new TetrominoLogic(new Tetromino().getRandomTetromino(), this);
	}

	private void start_gameThread() {
		/*
		 * Params : None
		 *
		 * Desc : Start gameThread
		 * 
		 * Returns : None. 
		 */
		looper = new Timer(delay, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				updateGameBoard(playerOne, cp1Tetromino, playerOne.getPlayerAbleHeight());
				updateGameBoard(playerTwo, cp2Tetromino, playerTwo.getPlayerAbleHeight());
				repaint();
			}
		});
		looper.start();
	}

	private void stop_gameThread() {
		/*
		 * Params : None.
		 *
		 * Desc : Terminates gameThread.
		 * 
		 * Returns : None. 
		 */
		looper.stop();
	}

	private void pause_gameThread() {
		/*
		 * Params : None
		 *
		 * Desc : Checks status of timer(gameThread) and toggles
		 * 
		 * Returns : None.
		 */
		if (looper.isRunning()) {
			btn_pause.setText("Start");
			btn_pause.setIcon(new ImageIcon(GameBoard.class.getResource("/Assets/icon_resume.png")));
			looper.stop();
		} else {
			btn_pause.setText("Pause");
			btn_pause.setIcon(new ImageIcon(GameBoard.class.getResource("/Assets/icon_pause.png")));
			looper.start();
		}
	}

	private void updateGameBoard(Player player, TetrominoLogic tml, int board_height) {
		/*
		 * Params : None.
		 *
		 * Desc : Based on the users' action, determine the current position of the current dropping tetromino.
		 * 		  Then, ensure that the user is not able to manipulate it horizontally or vertically out of the game board.
		 * 
		 * Returns : None. 
		 */
		// Check if the tetromino collides with another teromino or the bottom of the board
		int xCoordinates = player.getXCoordinates();
		int yCoordinates = player.getYCoordinates();
		int deltaX = player.getDeltaX();
		int boardStart = player.getBoardStart();
		int boardEnd = player.getBoardEnd();
		if (tml.getCollision()) {
			// Check for collision and block OOB to determine "Game Over"
			if (player.getYCoordinates() <= 1) {
				if (player.getLives() != 0) {
					player.setLives();
				}
				//Get the winner score
				if (player.getPlayerId() == 1) playerTwo.setScores(100);
				if (player.getPlayerId()  == 2) playerOne.setScores(100);
				gameOver(player);
				return;
			}
			// only play sound effect if user turn on the background music
			if (MainFrame.bgmusic.getStatus()) {
				sound_fallTetris.setVolume(MainFrame.bgmusic.getVolume()+0.40);
				sound_fallTetris.playMusic();
			}
			
			for (int row = 0; row < tml.getCoordinates().length; row++) {
				for (int col = 0; col < tml.getCoordinates()[0].length; col++) {
					if (tml.getCoordinates()[row][col] != 0) {
						gameboard[yCoordinates + row][xCoordinates + col] = tml.getColor();
					}
				}
			}
			checkLine(player);
			setCurrentTetromino();
			return;
		}

		boolean moveX = true;

		// Check the horizontal movement of the tetromino
		if (!(xCoordinates + deltaX + tml.getCoordinates()[0].length > boardEnd) && !(xCoordinates + deltaX < boardStart)) {
			for (int row = 0; row < tml.getCoordinates().length; row++) {
				for (int col = 0; col < tml.getCoordinates()[row].length; col++) {
					if (tml.getCoordinates()[row][col] != 0) {
						if (gameboard[yCoordinates + row][xCoordinates + deltaX + col] != null) {
							moveX = false;
						}
					}
				}
			}
			if (moveX) {
				xCoordinates += deltaX;
				player.setXCoordinates(xCoordinates);
			}
		}
		player.setDeltaX(0);

		if (System.currentTimeMillis() - tml.getBeginTime() > tml.getMovementDelay(player)) {
			if (!(yCoordinates + 1 + tml.getCoordinates().length > board_height - 1)) {
				for (int row = 0; row < tml.getCoordinates().length; row++) {
					for (int col = 0; col < tml.getCoordinates()[row].length; col++) {
						if (tml.getCoordinates()[row][col] != 0) {
							if (gameboard[yCoordinates + 1 + row][xCoordinates + col] != null) {
								tml.setCollision(true);
							}
						}
					}
				}
				if (!tml.getCollision()) {
					yCoordinates++;
					player.setYCoordinates(yCoordinates);
				}
			}
			else {
				tml.setCollision(true);
			}
			tml.setBeginTime(System.currentTimeMillis());
		}
	}

	private void checkLine(Player player) {
		
		/*
		 * Params : None
		 *
		 * Desc : Checks if the line has been filled. If the line has been filled completely with tetrominos, clear it.
		 * 
		 * Returns : None. 
		 */
		int bottomLine = TOTAL_BOARD_HEIGHT - 1;
		for (int topLine = TOTAL_BOARD_HEIGHT - 1; topLine > 0; topLine--) {
			int count = 0;
			for (int col = player.getBoardStart(); col < player.getBoardEnd(); col++){  // 0 10
				if (gameboard[topLine][col] != null) {
					count++;
				}
				gameboard[bottomLine][col] = gameboard[topLine][col];
			}

			if (count < 10) {
				bottomLine --;
			} else {
				if (player.getPlayerId() == 1) {
					playerTwo.setPlayerRestrictSpace();
					playerTwo.setPlayerAbleHeight();
					player.setScores(20);
					lbl_player1Score.setText("Score:" + player.getScores());
					// only play sound effect if user turn on the background music
					if (MainFrame.bgmusic.getStatus()) {
						sound_clearSound.setVolume(MainFrame.bgmusic.getVolume()+0.40);
						sound_clearSound.playMusic();
					}
				}
				else if (player.getPlayerId() == 2) {
					playerOne.setPlayerRestrictSpace();
					playerOne.setPlayerAbleHeight();
					player.setScores(20);
					lbl_player2Score.setText("Score:" + player.getScores());
					// only play sound effect if user turn on the background music
					if (MainFrame.bgmusic.getStatus()) {
						sound_clearSound.setVolume(MainFrame.bgmusic.getVolume()+0.40);
						sound_clearSound.playMusic();
					}
				}
			}
		}
	}

	public void gameOver(Player player) {
		/*
		 * Params : Player player.
		 *
		 * Desc : Ends the game. 
		 * 
		 * Returns : None. 
		 */
		// Stop gameThread first
		pause_gameThread();

		//Check whether playerOne or playerTwo has 0 lives
		if (playerOne.getLives() == 0 || playerTwo.getLives()==0) {
			
			// only play sound effect if user turn on the background music
			if (MainFrame.bgmusic.getStatus()) {
				sound_gameOver.setVolume(MainFrame.bgmusic.getVolume()+0.40);
				sound_gameOver.playMusic();
			}
			
			String whoWon = null;
			//Check who won the game
			whoWon = ((player.getPlayerId()==1)) ? playerTwo.getPlayerName() : playerOne.getPlayerName();

			//Get the winner score
			int playerScore = ((player.getPlayerId()==1)) ? playerTwo.getScores() : playerOne.getScores() ;
			
			//Execute save leaderboard method
			savePlayerLeaderBoard();
			
			//Show Popout message to declare the winner
			JOptionPane.showMessageDialog(null, "Player " + whoWon  + " won. \nWith a score of " + playerScore + ". \nGame Ended, returning to main menu");
			stop_gameThread();
			
			//Start panel redirection
			JFrame mf = (JFrame) SwingUtilities.getWindowAncestor(this);
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					JPanel contentPane = new MainMenu(mf);
					mf.getContentPane().removeAll();
					mf.setContentPane(contentPane);
					mf.revalidate();
					mf.repaint();
				}
			});
		}
		else {
			//If players have not reach 0 , will run the lines below
			int returnValue = 0;
			//Pop out message to ask whether player wants to continue
			returnValue = JOptionPane.showOptionDialog(null, "Player : " + player.getPlayerName() + " do you wish to continue? It will consome one of your lives", "Select an Option",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			//If player choose Yes, will refresh the game board
			if (returnValue == 0) {
				stop_gameThread();
				JFrame mf = (JFrame) SwingUtilities.getWindowAncestor(this);
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						playerOne.resetPlayerLevel();
						playerTwo.resetPlayerLevel();
						JPanel contentPane = new GameBoard(mf,playerOne,playerTwo);
						mf.setContentPane(contentPane);
						mf.revalidate();
						mf.repaint();
					}
				});
			}
			else {
				//Else will stop game and redirect to main menu
				stop_gameThread();
				//Start panel redirection
				JFrame mf = (JFrame) SwingUtilities.getWindowAncestor(this);
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						JPanel contentPane = new MainMenu(mf);
						mf.getContentPane().removeAll();
						mf.setContentPane(contentPane);
						mf.revalidate();
						mf.repaint();
					}
				});
			}
		}
	}

	public void savePlayerLeaderBoard() {
		//Create New arrayList to store
		ArrayList<String[]> lines = new ArrayList<String[]>();
		String line;
		try {
			//Check if File exist 
			File file = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "Assets" + File.separator + "leaderboard.txt");
			boolean exists = file.exists();
			
			//if file doesn't exists, will create new file
			if (exists == false) {
				file.createNewFile();
			}
			//Open up the file into buffer reader 
			BufferedReader scoreBoardReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + File.separator + "src" + File.separator + "Assets" + File.separator + "leaderboard.txt"));
			while((line = scoreBoardReader.readLine()) != null) {
				//Split the input string into arrays and add into ArrayList
			   lines.add(line.split(","));
			}
			//Close Reader
			scoreBoardReader.close();
			//check if there is any existing records in the file/lines arry
			if (lines.size() == 0) {
				//Check if playerOne Win live is not 0
				if (playerOne.getLives() != 0) {
						//Assign player Name and Player score
						String[] playerWhoScoredHigher = {playerOne.getPlayerName(), String.valueOf(playerOne.getScores())};
						//Add into ArrayList according to the index
						lines.add(playerWhoScoredHigher);
					//If playerTwo Win and live is not 0
				}else if (playerTwo.getLives() !=0) {
					
						//Assign player Name and Player score
					String[] playerWhoScoredHigher = {playerTwo.getPlayerName(), String.valueOf(playerTwo.getScores())};
					//Add player to arraylist
					lines.add(playerWhoScoredHigher);
					
				}//End of else if 
			}
			//If there is existing records in the file will run the lines below
			else {
				if (playerOne.getLives() != 0) {
					
					for (int k=0; k < lines.size()-1; k++) {
						//Convert all imported lines to no white space and lowercase
						String linesWithoutWhiteSpace = lines.get(k)[0].replaceAll("\\s", "").toLowerCase();
						//Convert all player name to no white space and lowercase
						String playerNameWithoutWhiteSpace = playerOne.getPlayerName().replaceAll("\\s", "").toLowerCase();
						//Check If player name match and whether score is heigher than the imported score
						if (linesWithoutWhiteSpace.contains(playerNameWithoutWhiteSpace)) {
							lines.remove(k);
						}
					}
				// Loop through the arraylist
				for (int i=0; i < lines.size()-1; i++) {
					//Check if playerOne Win live is not 0
					
						//Check playerOne Score against the existing scoreboard
						if (playerOne.getScores() >= Integer.parseInt(lines.get(i)[1]))
						{
							//Assign player Name and Player score
							String[] playerWhoScoredHigher = {playerOne.getPlayerName(), String.valueOf(playerOne.getScores())};
							//Add into ArrayList according to the index
							lines.add(i, playerWhoScoredHigher);
							//If the arraylist exceed 10, remove the last element
							if (lines.size() > 10)
								lines.remove(lines.size()-1);
							//Break out of loop
							break;
						}
						
					}//End of For loop
						
					}//End of if statement
				//If playerTwo Win and live is not 0
				else if (playerTwo.getLives() !=0) {
					for (int k=0; k < lines.size()-1; k++) {
						//Convert all imported lines to no white space and lowercase
						String linesWithoutWhiteSpace = lines.get(k)[0].replaceAll("\\s", "").toLowerCase();
						//Convert all player name to no white space and lowercase
						String playerNameWithoutWhiteSpace = playerOne.getPlayerName().replaceAll("\\s", "").toLowerCase();
						//Check If player name match and whether score is heigher than the imported score
						if (linesWithoutWhiteSpace.contains(playerNameWithoutWhiteSpace)) {
							lines.remove(k);
						}
					}
						for (int i=0; i < lines.size()-1; i++) {
							//Check playerTwo Score against the existing scoreBoard
							if (playerTwo.getScores() >= Integer.parseInt(lines.get(i)[1]))
							{
								//Assign player Name and Player score
							String[] playerWhoScoredHigher = {playerTwo.getPlayerName(), String.valueOf(playerTwo.getScores())};
							//Add player to arraylist
							lines.add(i, playerWhoScoredHigher);
							//If the arraylist exceed 10, remove the last element
							if (lines.size() > 10)
								lines.remove(lines.size()-1);
							break;
							}
						}//End of For loop
					}//End of else if 
				
			}//End of else statement
			
			//Open writer to write to local file
			FileWriter writer = new FileWriter(System.getProperty("user.dir") + File.separator + "src" + File.separator + "Assets" + File.separator + "leaderboard.txt"); 
			//Read each arraylist
			for(String[] str: lines) {
				//Write to file
					writer.write(str[0] + "," + str[1] + System.lineSeparator());
			}
			//Close Writer
			writer.close();
		} catch (IOException e) {
			System.out.println("There is some issue saving your records in the file. Please Check!");
		}
	}
	
	private void setP1UpcomingTetromino() {
		/*
		 * Params : None
		 *
		 * Desc : Sets a new teromino block onto the gameboard.
		 * 
		 * Returns : None. 
		 */
		p1UpcomingTetromino = new Tetromino().getRandomTetromino();
		lbl_p1UpcomingTetro.setIcon(new ImageIcon(getClass().getResource("/Assets/" + p1UpcomingTetromino.getName() + ".png")));
	}

	private void setP2UpcomingTetromino() {
		/*
		 * Params : None
		 *
		 * Desc : Sets a new teromino block onto the gameboard.
		 * 
		 * Returns : None. 
		 */
		p2UpcomingTetromino = new Tetromino().getRandomTetromino();
		lbl_p2UpcomingTetro.setIcon(new ImageIcon(getClass().getResource("/Assets/" + p2UpcomingTetromino.getName() + ".png")));
	}

	public void setCurrentTetromino() {
		/*
		 * Params : None
		 *
		 * Desc : Sets a new teromino block onto the gameboard.
		 * 
		 * Returns : None. 
		 */
		if (cp1Tetromino.getCollision()) {
			p1CurrentTetromino = p1UpcomingTetromino;
			cp1Tetromino = new TetrominoLogic(p1CurrentTetromino, this);
			cp1Tetromino.reset(this.playerOne, 4, 0);
			this.Keybindings.cp1Tetromino = cp1Tetromino;
			setP1UpcomingTetromino();
		}

		else if (cp2Tetromino.getCollision()) {
			p2CurrentTetromino = p2UpcomingTetromino;
			cp2Tetromino = new TetrominoLogic(p2CurrentTetromino, this);
			cp2Tetromino.reset(this.playerTwo, 25, 0);
			this.Keybindings.cp2Tetromino = cp2Tetromino;
			setP2UpcomingTetromino();
		}
	}

	public void render(Graphics g) {
		/*
		 * Params : Graphics g
		 *
		 * Desc : Renders the Tetromino on the gameboard.
		 * 
		 * Returns : None. 
		 */
		for (int row = 0; row < gameboard.length; row++) {
			for (int col = 0; col < gameboard[row].length; col++) {
				if (gameboard[row][col] != null) {
					g.setColor(gameboard[row][col].darker());
					g.fillRect(col * BLOCK_SIZE, row * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
					
					g.setColor(gameboard[row][col]);
					g.fillRect(col * BLOCK_SIZE, row * BLOCK_SIZE, BLOCK_SIZE-3, BLOCK_SIZE-3);
				}
			}
		}
	}

	public void render(Graphics g, Player player) {
		/*
		 * Params : Graphics g, Player player
		 *
		 * Desc : Renders the player's white grid boxes.
		 * 
		 * Returns : None. 
		 */
		g.setColor(Color.GRAY);
		for(int row = 2; row < TOTAL_BOARD_HEIGHT; row++) {
			g.drawLine(player.getBoardX1(), BLOCK_SIZE * row, player.getBoardX2(), BLOCK_SIZE * row);
		}
		for(int col = player.getBoardStart(); col < player.getBoardEnd() + 1; col++) {
			g.drawLine(col * BLOCK_SIZE, BOARD_UPPER_SPACE, col * BLOCK_SIZE, BLOCK_SIZE * (TOTAL_BOARD_HEIGHT - 1));
		}
	}


	public void render(Graphics g, Player player, int restrictSpace) {
		/*
		 * Params : Graphics g, Player player, int restrictSpace
		 *
		 * Desc : Renders the player's playable space on the gameboard.
		 * 
		 * Returns : None. 
		 */
		int boardHeight;
		if (player.getPlayerId() == 1) {
			boardHeight = playerOne.getPlayerAbleHeight();;
		}
		else {
			boardHeight = playerTwo.getPlayerAbleHeight();;
		}
		
		for(int col = player.getBoardStart(); col < player.getBoardEnd(); col++) {
			for(int row = boardHeight - 1; row < boardHeight; row++) {
				g.setColor(Color.black);
				g.fillRect(col * BLOCK_SIZE, row * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE * row);
				
				g.setColor(Color.getHSBColor(226, 220, 205));
				g.fillRect(col * BLOCK_SIZE, row * BLOCK_SIZE, BLOCK_SIZE-3, BLOCK_SIZE-3 * row);
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		/*
		 * Params: Graphics g
		 *
		 * Desc : Paints the game board's grid lines for player 1 and player 2.
		 * 
		 * Returns : None. 
		 */
		// Paints the game board background
		super.paintComponent(g);

		// Renders the tetromino shape 
		cp1Tetromino.renderTetromino(g, this.playerOne.getXCoordinates(), this.playerOne.getYCoordinates(), BLOCK_SIZE);
		cp2Tetromino.renderTetromino(g, this.playerTwo.getXCoordinates(), this.playerTwo.getYCoordinates(), BLOCK_SIZE);

		// Draw the tetromino onto the game board 
		this.render(g);

		// Renders the player's playable space 
		this.render(g, playerOne, playerOne.getPlayerRestrictSpace());
		this.render(g, playerTwo, playerTwo.getPlayerRestrictSpace());

		// Draw game board grid for player 1 
		this.render(g, playerOne);
		this.render(g, playerTwo);
	}
	
	public Color[][] getBoard() {
		/*
		 * Params: None.
		 *
		 * Desc : Returns the gameboard.
		 * 
		 * Returns : Color gameboard. 
		 */		
		return gameboard; 
	}
}	