package com.dat255_group3.model;
/**
 * A class that represents the user playing the game.
 * 
 * 
 * @author The Hans-Gunnar Crew
 *
 */

public class Player {

	/** The player's current score.*/
	private int score;
	
	/** The player's high score.*/
	private int highscore;
	
	/**
	 * Class constructor.
	 */
	public Player(){
		
	}
	
	/**
	 * Returns an int representing the player's current score.
	 * 
	 * @return the player's current score
	 */
	public int getScore(){
		return score;
	}
	
	/**
	 * Returns and int representing the player's high score.
	 * 
	 * @return the player's high score
	 */
	public int getHighscore(){
		return highscore;
	}
	
	public void calculateScore(float time, int cookies) {
		this.score = (int) ((60/time)*1000 + cookies*100);
		
	}
}
