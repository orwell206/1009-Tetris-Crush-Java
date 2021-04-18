package Game_Engine;

import java.awt.Color;
import java.awt.Graphics;

import Player_Engine.Player;

public class TetrominoLogic implements TetrominoRotation{
	private long beginTime; 
	private boolean collision = false; 
	private int[][] coordinates;
	private Color color;
	private GameBoard gameboard;

	public TetrominoLogic(TetrominoShape ts, GameBoard gameboard) {
		/*
		 * Params : int coordinates
		 *
		 * Desc : Default constructor
		 * 
		 * Returns : None. 
		 */
		this.coordinates = ts.getShape();
		this.color = ts.getColor();
		this.gameboard = gameboard;
	}

	public int getMovementDelay(Player player){ 
		/*
		 * Params : Player player
		 *
		 * Desc : Gets the movement delay for the player. 
		 * 
		 * Returns : int player.getMovementDelay()
		 */		
		return player.getMovementDelay(); 
	}

	public long getBeginTime(){ 
		/*
		 * Params : None.
		 *
		 * Desc : Gets the begin time for the player. 
		 * 
		 * Returns : long beginTime
		 */				
		return beginTime; 
	}

	public void setBeginTime(long beginTime){ 
		/*
		 * Params : long beginTime.
		 *
		 * Desc : Sets the begin time for the player. 
		 * 
		 * Returns : None.
		 */			
		this.beginTime = beginTime; 
	}

	public boolean getCollision(){ 
		/*
		 * Params : None.
		 *
		 * Desc : Gets the collision status of the tetromino.
		 * 
		 * Returns : bool collision.
		 */				
		return collision; 
	}

	public void setCollision(boolean collision){ 
		/*
		 * Params : bool collison.
		 *
		 * Desc : Sets the collision status of the tetromino.
		 * 
		 * Returns : None.
		 */				
		this.collision = collision; 
	}

	public int[][] getCoordinates(){ 
		/*
		 * Params : None.
		 *
		 * Desc : Gets the coordinates of the tetromino.
		 * 
		 * Returns : None.
		 */			
		return coordinates; 
	}

	public Color getColor(){ 
		/*
		 * Params : None.
		 *
		 * Desc : Gets the color of the tetromino.
		 * 
		 * Returns : Color color.
		 */				
		return color; 
	}

	public void reset(Player player, int xCoordinates, int yCoordinates) {
		/*
		 * Params : Player player, int xCoordinates, int yCoordinates
		 *
		 * Desc : Resets the X & Y coordinates for a new teromino after the current one has been dropped.
		 * 
		 * Returns : None.
		 */
		player.setXCoordinates(xCoordinates);
		player.setYCoordinates(yCoordinates);
		this.collision = false;
	}

	public void renderTetromino(Graphics g, int xCoordinates, int yCoordinates, int block_size){
		/*
		 * Params : Graphics g, int xCoordinates, int yCoordinates, int block_size
		 *
		 * Desc : Renders the current dropping tetromino onto the game board.
		 * 
		 * Returns : None.
		 */		
		for(int row = 0; row < coordinates.length; row++) {
			for(int col = 0; col < coordinates[0].length; col++) {
				if(coordinates[row][col] != 0) {
					g.setColor(color.darker());
					g.fillRect(col * block_size + xCoordinates * block_size, row * block_size + yCoordinates * block_size, block_size, block_size);
					
					g.setColor(color);
					g.fillRect(col * block_size + xCoordinates * block_size, row * block_size + yCoordinates * block_size, block_size-3, block_size-3);
				}
			}
		}
	}

	public void rotateShape(int xCoordinates, int yCoordinates) {
		/*
		 * Params : int xCoordinates, int yCoordinates
		 *
		 * Desc : Public function called from GameBoard.Keybindings() to rotate block orientation.
		 * 
		 * Returns : None.
		 */				
		// Transpose the original shape first
		int[][] rotatedShape = transposeMatrix(coordinates);

		// Reverse/flip the transposed shape to get back the right direction
		reverseMatrix(rotatedShape);

		// Get player's current X Coord
		// Player 2 has coordinates starting from 21 onwards so we need to offset it.
		final int P2_OFFSET = 21;
		int tempPlayerCoord;
		if ((xCoordinates > 21)) {
			tempPlayerCoord = xCoordinates - P2_OFFSET;
		} else {
			tempPlayerCoord = xCoordinates;
		}

		// Check player's X coord and Y coord to see if it goes OOB when rotated
		if((tempPlayerCoord + rotatedShape[0].length > gameboard.getPlayerBoardWidth()) || (yCoordinates + rotatedShape.length > gameboard.getPlayerBoardHeight())) {
			return;
		}
		
		// Check that the rotation of the tetromino does not overlap with the other tetrominos that are already set on the gameboard.
		for (int row = 0; row < rotatedShape.length; row++){
			for (int col = 0; col < rotatedShape[row].length; col++){
				if (rotatedShape[row][col] != 0){
					if (gameboard.getBoard()[yCoordinates + row][xCoordinates + col] != null) {
						return;
					}
				}
			}
		}
		coordinates = rotatedShape;
	}

	public int[][] transposeMatrix(int[][] matrix){
		/*
		 * Params : int[][] matrix
		 *
		 * Desc : Private function called by rotateShape() to transpose matrix
		 * 
		 * Returns : int[][] tempMatrix. 
		 */				
		int[][] tempMatrix = new int[matrix[0].length][matrix.length];
		for(int row = 0; row < matrix.length; row++) {
			for(int col = 0; col < matrix[0].length; col++) {
				tempMatrix[col][row] = matrix[row][col];
			}
		}
		return tempMatrix;
	}

	public void reverseMatrix(int[][] matrix) {
		/*
		 * Params : int[][] matrix
		 *
		 * Desc : Private function called by transposeMatrix() to rotate blocks back to the right direction
		 * 
		 * Returns : None. 
		 */		
		int midPoint = matrix.length / 2;
		for(int row = 0; row < midPoint; row++) {
			int[] tempRow = matrix[row];
			matrix[row] = matrix[matrix.length - row - 1];
			matrix[matrix.length - row - 1] = tempRow;
		}
	}
}