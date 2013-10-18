package com.dat255_group3.model;

import java.util.ArrayList;
import java.util.List;


/**
 * A class that represents the user playing the game.
 * 
 * 
 * @author The Hans-Gunnar Crew
 *
 */

public class Player {

	/** The player's current score.*/
	private List <Integer> highScores;
	
	/** The player's current score.*/
	private int score;
	
	/**
	 * Class constructor.
	 */
	public Player(){
		highScores = new ArrayList <Integer>();
		
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
	public int getHighscore(int levelNr){
		return highScores.get(levelNr-1);
	}
	
	public void setHighScore (int levelNr, int score){
		this.highScores.set(levelNr-1, score);
	}
	
	public void calculateScore(double time, int cookies, boolean gameLost) {
		if(gameLost){
			this.score = cookies*100;
		}else {
			this.score = (int) (Math.pow(20/time, 3.0)*1000 + cookies*100);
		}
	}

}
