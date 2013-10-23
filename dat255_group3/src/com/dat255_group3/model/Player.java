package com.dat255_group3.model;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.Gdx;
import com.dat255_group3.io.IOHandler;

/**
 * A Player is the user playing the game.
 * 
 * @author The Hans-Gunnar Crew
 *
 */

public class Player {

	private List <Integer> highScores;
	private int currentScore;

	/**
	 * Constructs a new Player, instantiates an ArrayList for the high score,
	 * and reads the high score.
	 */
	public Player(){
		highScores = new ArrayList <Integer>();
		IOHandler.readScore();
	}
	
	public int getHighScore(int level){
		return highScores.get(level-1);
	}
	
	public void setNewHighScore (int levelNbr, int score){
		if (contains(levelNbr)) {
			setHighScore(levelNbr, score);
		}else {
			highScores.add(score);
		}
	}
	
	public int getScore() {
		return currentScore;
	}
	
	public void setScore(int score) {
		this.currentScore = score;
	}
	
	public void setHighScore(int levelNbr, int score) {
		this.highScores.set(levelNbr-1, score);
	}
	
	public List<Integer> getHighScoreList() {
		return this.highScores;
	}

	/**
	 * Calculates the player's score using the following formula:
	 * (40/time)^3.0*1000 + cookies*100.
	 * 
	 * @param time the amount of time the player spent on the level
	 * @param cookies the number of cookies the player collected on the level
	 * @param gameLost true if the player died before completing the level
	 */
	public void calculateScore(double time, int cookies, boolean gameLost) {
		if(gameLost){
			this.currentScore = cookies*100;
		}else {
			this.currentScore = (int) (Math.pow(40/time, 3.0)*1000 + cookies*100);
		}
	}
	
	/**
	 * Adds the player's new high score to the high score list.
	 * 
	 * @param levelData 
	 */
	public void addHighScoresToList(String [] levelData) {
		try {
			for (int i=0; i<levelData.length; i++) {
				highScores.add(Integer.parseInt(levelData[i]));
			}
		}catch (Exception e) {
			Gdx.app.log("Player", "Exception" + e);
			for (int i=0; i<3; i++) {
				highScores.add(0);
			}
		}
	}
	
	/**
	 * 
	 * 
	 * @param levelNbr the current level
	 * @return 
	 */
	public boolean contains(int levelNbr) {
		return highScores.size() > levelNbr;
	}
}
