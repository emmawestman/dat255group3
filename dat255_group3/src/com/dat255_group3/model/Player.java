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
	
	/** The player's current score.*/
	private int bestTime;
	
	/** The player's high score.*/
	private int highScore;
	
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
		return highScore;
	}
	
	public void setHighScore (int score){
		this.highScore = score;
	}
	
	/**
	 * Returns and int representing the player's high score.
	 * 
	 * @return the player's high score
	 */
	public int getBestTime(){
		return bestTime;
	}
	
	public void setBestTime (int time){
		this.bestTime = time;
	}
	
	public void calculateScore(double time, int cookies, boolean gameLost) {
		if(gameLost){
			this.score = cookies*100;
		}else {
			this.score = (int) ((60/time)*1000 + cookies*100);
		}
	}

}
