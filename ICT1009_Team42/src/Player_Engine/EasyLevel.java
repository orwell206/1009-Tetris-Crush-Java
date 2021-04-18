package Player_Engine;

public class EasyLevel extends Level {

	public EasyLevel() {
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
		 * Desc : Gets the game's speed for "Easy" difficulty.
		 * 
		 * Returns : int NORMAL_SPEED.
		 */				
		return NORMAL_SPEED - 700;
	}
	
	public int getFasterSpeed() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Gets the game's speed when the player presses the "S" key.
		 * 
		 * Returns : int NORMAL_SPEED.
		 */					
		return FASTER_SPEED - 600;
	}
	
	public int getPlayableHeight() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Gets the player's playable height for "Easy" difficulty.
		 * 
		 * Returns : int PlayableHeight.
		 */					
		return PlayableHeight - 1;
	}
	
	public int getRestrictSpace() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Gets the player's initial restricted space for "Easy" difficulty.
		 * 
		 * Returns : int RestrictSpace.
		 */					
		return RestrictSpace + 1;
	}
}