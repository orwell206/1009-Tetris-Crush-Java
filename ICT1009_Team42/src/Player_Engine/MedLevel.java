package Player_Engine;

public class MedLevel extends Level{

	public MedLevel() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Default constructor. 
		 * 
		 * Returns : None.
		 */				
		super();
	}
	
	public int getNormalSpeed() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Gets the game's speed for "Medium" difficulty.
		 * 
		 * Returns : int NORMAL_SPEED.
		 */				
		return NORMAL_SPEED - 1000;
	}
	
	public int getFasterSpeed() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Gets the game's speed when the player presses the "S" key.
		 * 
		 * Returns : int NORMAL_SPEED.
		 */							
		return FASTER_SPEED - 800;
	}
	
	public int getPlayableHeight() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Gets the player's playable height for "Hard" difficulty.
		 * 
		 * Returns : int PlayableHeight.
		 */				
		return PlayableHeight - 2;
	}
	
	public int getRestrictSpace() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Gets the player's initial restricted space for "Hard" difficulty.
		 * 
		 * Returns : int RestrictSpace.
		 */				
		return RestrictSpace + 2;
	}
}