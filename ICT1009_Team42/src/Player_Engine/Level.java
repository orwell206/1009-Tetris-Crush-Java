package Player_Engine;

public class Level {
	protected int NORMAL_SPEED;
	protected int FASTER_SPEED;
	protected int PlayableHeight;
	protected int RestrictSpace;
	
	public Level() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Default constructor. 
		 * 
		 * Returns : None.
		 */					
		this.NORMAL_SPEED = 1500;
		this.FASTER_SPEED = 900;
		this.PlayableHeight = 19;
		this.RestrictSpace = 1;
	}
	
	public int getNormalSpeed() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Gets the game's speed.
		 * 
		 * Returns : int NORMAL_SPEED.
		 */			
		return NORMAL_SPEED;
	}
	
	public int getFasterSpeed() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Gets the game's speed when the player presses the "S" key.
		 * 
		 * Returns : int NORMAL_SPEED.
		 */				
		return FASTER_SPEED;
	}
	
	public int getPlayableHeight() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Gets the player's playable height.
		 * 
		 * Returns : int PlayableHeight.
		 */						
		return PlayableHeight;
	}
	
	public int getRestrictSpace() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Gets the player's initial restricted space.
		 * 
		 * Returns : int RestrictSpace.
		 */				
		return RestrictSpace;
	}
}