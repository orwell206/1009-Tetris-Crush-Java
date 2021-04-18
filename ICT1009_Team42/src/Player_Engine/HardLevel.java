package Player_Engine;

import Player_Engine.Player;

public class HardLevel extends Level{

	public HardLevel() {
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
		 * Desc : Gets the game's speed for "Hard" difficulty.
		 * 
		 * Returns : int NORMAL_SPEED.
		 */				
		return NORMAL_SPEED-1300;
	}
	
	public int getFasterSpeed() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Gets the game's speed when the player presses the "S" key.
		 * 
		 * Returns : int NORMAL_SPEED.
		 */							
		return FASTER_SPEED-880;
	}
	
	public int getPlayableHeight() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Gets the player's playable height for "Hard" difficulty.
		 * 
		 * Returns : int PlayableHeight.
		 */			
		return PlayableHeight-4;
	}
	
	public int getRestrictSpace() {
		/*
		 * Params : None. 
		 * 
		 * Desc : Gets the player's initial restricted space for "Hard" difficulty.
		 * 
		 * Returns : int RestrictSpace.
		 */				
		return RestrictSpace+4;
	}
}