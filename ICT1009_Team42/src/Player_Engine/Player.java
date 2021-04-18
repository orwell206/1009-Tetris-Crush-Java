package Player_Engine;

import Game_Engine.TetrominoMovement;

public class Player implements TetrominoMovement{
	private int playerId;
	private int xCoordinates;
	private int yCoordinates; 
	private int deltaX;
	private int deltaY;
	private int boardStart;
	private int boardEnd;
	private int boardX1;
	private int boardX2;
	private String playerName;
	private int lives = 3;
	private int scores = 0;
	private int movementDelay;
	private EasyLevel easyLevel;
	private MedLevel medLevel;
	private HardLevel hardLevel;
	private String choosenLevel;
	
	//For storing into currentPlayer
	private int NORMAL_SPEED;
	private int FASTER_SPEED;
	private int playableHeight;
	private int restrictSpace;

	public Player (int playerId, int xCoordinates, int yCoordinates, int deltaX, int deltaY, int boardStart, int boardEnd, int boardX1, int boardX2, String playerName, String choosenLevel){
		/*
		 * Params : int playerId, int xCoordinates, int yCoordinates, int deltaX, int deltaY, int boardStart, int boardEnd, int boardX1, int boardX2, String playerName, String choosenLevel
		 * 
		 * Desc : Default Constructor. 
		 * 
		 * Returns : None. 
		 */				
		this.playerId = playerId;
		this.xCoordinates = xCoordinates;
		this.yCoordinates = yCoordinates;
		this.deltaX = deltaX;
		this.deltaY = deltaY;
		this.boardStart = boardStart;
		this.boardEnd = boardEnd;
		this.boardX1 = boardX1;
		this.boardX2 = boardX2;
		this.playerName = playerName;
		this.lives = 3;
		this.scores = 0;
		this.choosenLevel = choosenLevel;
		switch (choosenLevel) {
		case ("Easy"):
			easyLevel = new EasyLevel();
			NORMAL_SPEED = easyLevel.getNormalSpeed();
			FASTER_SPEED = easyLevel.getFasterSpeed();
			playableHeight = easyLevel.getPlayableHeight();
			playableHeight = easyLevel.getPlayableHeight();
			break;
		case ("Med"):
			medLevel = new MedLevel();
			NORMAL_SPEED = medLevel.getNormalSpeed();
			FASTER_SPEED = medLevel.getFasterSpeed();
			playableHeight = medLevel.getPlayableHeight();
			playableHeight = medLevel.getPlayableHeight();
			break;
		case ("Hard"):
			hardLevel = new HardLevel();
			NORMAL_SPEED = hardLevel.getNormalSpeed();
			FASTER_SPEED = hardLevel.getFasterSpeed();
			playableHeight = hardLevel.getPlayableHeight();
			playableHeight = hardLevel.getPlayableHeight();
		}
		movementDelay = NORMAL_SPEED;
	}
	
	public int getPlayerId() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Returns the player's id. 
		 * 
		 * Returns : int playerId. 
		 */				
		return this.playerId;
	}
	
	public int getXCoordinates() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Returns the player's X Coordinates. 
		 * 
		 * Returns : int xCoordinates. 
		 */				
		return this.xCoordinates;
	}
	
	public void setXCoordinates(int xCoordinates) {
		/*
		 * Params : int xCoordinates. 
		 * 
		 * Desc : Sets the player's X Coordinates. 
		 * 
		 * Returns : None. 
		 */			
		this.xCoordinates = xCoordinates;
	}
	
	public int getYCoordinates() {
		/*
		 * Params : None.
		 * 
		 * Desc : Gets the player's X Coordinates. 
		 * 
		 * Returns : int yCoordinates. 
		 */				
		return this.yCoordinates;
	}
	
	
	public void setYCoordinates(int yCoordinates) {
		/*
		 * Params : int yCoordinates. 
		 * 
		 * Desc : Sets the player's Y Coordinates. 
		 * 
		 * Returns : None. 
		 */				
		this.yCoordinates = yCoordinates;
	}
	
	public int getDeltaX() {
		/*
		 * Params : None.
		 * 
		 * Desc : Gets the player's delta x coordinates.  
		 * 
		 * Returns : int deltaX. 
		 */				
		return this.deltaX;
	}
	
	public void setDeltaX(int deltaX) {
		/*
		 * Params : int deltaX.
		 * 
		 * Desc : Sets the player's delta X coordinates.  
		 * 
		 * Returns : None. 
		 */						
		this.deltaX = deltaX;
	}
	
	public int getDeltaY() {
		/*
		 * Params : None.
		 * 
		 * Desc : Gets the player's delta Y coordinates.  
		 * 
		 * Returns : int deltaY. 
		 */						
		return this.deltaY;
	}
	
	public void setDeltaY(int deltaY) {
		/*
		 * Params : int deltaY. 
		 * 
		 * Desc : Sets the player's delta Y coordinates. 
		 * 
		 * Returns : None. 
		 */				
		this.deltaY = deltaY;
	}

	public int getBoardStart() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Returns the player's board starting position. 
		 * 
		 * Returns : int boardStart. 
		 */					
		return this.boardStart;
	}
	
	
	public void setBoardStart(int boardStart) {
		/*
		 * Params : int boardStart. 
		 * 
		 * Desc : Sets the player's board starting position. 
		 * 
		 * Returns : None.
		 */				
		this.boardStart = boardStart;
	}
	
	public int getBoardEnd() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Returns the player's board ending position. 
		 * 
		 * Returns : int boardStart. 
		 */				
		return this.boardEnd;
	}
	
	
	public void setBoardEnd(int boardEnd) {
		/*
		 * Params : int boardStart. 
		 * 
		 * Desc : Sets the player's board ending position. 
		 * 
		 * Returns : None.
		 */					
		this.boardEnd = boardEnd;
	}	
	
	public int getBoardX1() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Gets the player's board X1 coordinates. 
		 * 
		 * Returns : int boardX1.
		 */			
		return this.boardX1;
	}
	
	
	public void setBoardX1(int boardX1) {
		/*
		 * Params : int boardX1. 
		 * 
		 * Desc : Sets the player's board X1 coordinates. 
		 * 
		 * Returns : None.
		 */					
		this.boardX1 = boardX1;
	}
	
	public int getBoardX2() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Gets the player's board X2 coordinates. 
		 * 
		 * Returns : int boardX2.
		 */					
		return this.boardX2;
	}
	
	
	public void setBoardX2(int boardX2) {
		/*
		 * Params : int boardX2. 
		 * 
		 * Desc : Sets the player's board X2 coordinates. 
		 * 
		 * Returns : None.
		 */				
		this.boardX2 = boardX2;
	}	
	
	public void setScores(int score) {
		/*
		 * Params : int score. 
		 * 
		 * Desc : Sets the player's score. 
		 * 
		 * Returns : None.
		 */						
		this.scores += score;
	}
	
	public void setLives() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Sets the player's lives. 
		 * 
		 * Returns : None.
		 */				
		this.lives -= 1;
	}
	
	public int getScores() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Gets the player's scores. 
		 * 
		 * Returns : int score.
		 */					
		return scores;
	}
	
	public int getLives() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Gets the player's lives. 
		 * 
		 * Returns : int lives.
		 */				
		return lives;
	}
	
	public String getPlayerName() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Gets the player's name. 
		 * 
		 * Returns : string lives.
		 */				
		return playerName;
	}
	
	public void resetPlayerLevel() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Sets the game's difficulty level.  
		 * 
		 * Returns : None.
		 */				
		switch (choosenLevel) {
			case ("Easy"):
				playableHeight = easyLevel.getPlayableHeight();
				playableHeight = easyLevel.getPlayableHeight();
				break;
			case ("Med"):
				playableHeight = medLevel.getPlayableHeight();
				playableHeight = medLevel.getPlayableHeight();
				break;
			case ("Hard"):
				playableHeight = hardLevel.getPlayableHeight();
				playableHeight = hardLevel.getPlayableHeight();
		}
	}
	
	public int getMovementDelay() {
		/*
		 * Params : None.
		 *
		 * Desc : Gets the player's movement delay.
		 * 
		 * Returns : int movementDelay. 
		 */		
		return this.movementDelay;
	}
	
	public void moveDownwards() {
		/*
		 * Params : None.
		 *
		 * Desc : Moves the current dropping tetromino block downwards.
		 * 
		 * Returns : None. 
		 */
		movementDelay = FASTER_SPEED;
	}

	public void moveLeft() {
		/*
		 * Params : None.
		 *
		 * Desc : Moves the current dropping tetromino block left.
		 * 
		 * Returns None.
		 */
		this.setDeltaX(-1);
	}

	public void moveRight() {
		/*
		 * Params : None.
		 *
		 * Desc: Moves the current dropping tetromino block right.
		 * 
		 * Returns : None. 
		 */
		this.setDeltaX(1);
	}

	public void moveDefault() {
		/*
		 * Params : None.
		 *
		 * Desc : Does nothing to the tetromino block.
		 * 
		 * Returns : None.
		 */
		movementDelay = NORMAL_SPEED;
	
	}	
	public int getPlayerAbleHeight() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Gets the player's board playable height. 
		 * 
		 * Returns : None.
		 */				
		return playableHeight;
	}
	
	public void setPlayerAbleHeight() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Sets the player's board playable height. 
		 * 
		 * Returns : None.
		 */			
		playableHeight -= 1;
	}
	
	public int getPlayerRestrictSpace() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Gets the player's board restricted space. 
		 * 
		 * Returns : int restrictSpace.
		 */			
		return restrictSpace;
	}
	
	public void setPlayerRestrictSpace() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Sets the player's board restricted space. 
		 * 
		 * Returns : None.
		 */			
		restrictSpace +=1;
	}
}