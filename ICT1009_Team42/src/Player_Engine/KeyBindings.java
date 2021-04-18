package Player_Engine;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

import Game_Engine.TetrominoLogic;


public class KeyBindings implements KeyEventDispatcher {
	public TetrominoLogic cp1Tetromino;
	public TetrominoLogic cp2Tetromino;
	public Player playerOne;
	public Player playerTwo;
	
	public KeyBindings(TetrominoLogic cp1Tetromino, TetrominoLogic cp2Tetromino, Player playerOne, Player playerTwo){
		/*
		 * Params : TetrominoLogic cp1Tetromino, TetrominoLogic cp2Tetromino, Player playerOne, Player playerTwo. 
		 * 
		 * Desc : Default constructor. 
		 * 
		 * Returns : None.
		 */					
		this.cp1Tetromino = cp1Tetromino;
		this.cp2Tetromino = cp2Tetromino;
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
	}
	
	public boolean dispatchKeyEvent(KeyEvent e) {
		/*
		 * Params : KeyEvent e. 
		 * 
		 * Desc : Listens for the keys pressed by the players. 
		 * 
		 * Returns : false.
		 */		
		// Detects for a key press
		if (e.getID() == KeyEvent.KEY_PRESSED) {
			// Player 1 key bindings
			if (e.getKeyCode() == KeyEvent.VK_W) {
				cp1Tetromino.rotateShape(playerOne.getXCoordinates(), playerOne.getYCoordinates());
			}
			else if (e.getKeyCode() == KeyEvent.VK_S) {
				playerOne.moveDownwards();
			}
			else if (e.getKeyCode() == KeyEvent.VK_D) {
				playerOne.moveRight();
			}
			else if (e.getKeyCode() == KeyEvent.VK_A) {
				playerOne.moveLeft();
			}

			// Player 2 key bindings
			else if (e.getKeyCode() == KeyEvent.VK_UP) {
				cp2Tetromino.rotateShape(playerTwo.getXCoordinates(), playerTwo.getYCoordinates());
			}
			else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				playerTwo.moveDownwards();
			}
			else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				playerTwo.moveRight();
			}
			else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				playerTwo.moveLeft();
			}
		}
		// Detects for a key release
		else if (e.getID() == KeyEvent.KEY_RELEASED) {
			// Player 1 key bindings
			if (e.getKeyCode() == KeyEvent.VK_S) {
				playerOne.moveDefault();
			}
			// Player 2 key bindings
			else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				playerTwo.moveDefault();
			}
		}
		return false;
	}
}