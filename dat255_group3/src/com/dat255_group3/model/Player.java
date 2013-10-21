package com.dat255_group3.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.dat255_group3.io.IOHandler;




/**
 * A class that represents the user playing the game.
 * 
 * 
 * @author The Hans-Gunnar Crew
 *
 */

public class Player {
	private int score;
	private List <Integer> highScores;
	
	/**
	 * Class constructor.
	 */
	public Player(){
		highScores = new ArrayList <Integer>();
		IOHandler.readScore();
		addHighScoresToList(IOHandler.getLevelData());
		
	}
	
	
	/**
	 * Returns and int representing the player's high score.
	 * 
	 * @return the player's high score
	 */
	public int getHighScore(int level){
		return highScores.get(level-1);
	}
	
	public void setNewHighScore (int levelNbr, int score){
		if (contains(levelNbr)) {
			setHighScore(levelNbr, score);
			
		}else {
			highScores.add(score);
		}
		
		Gdx.app.log("plyer", "set new Hs: " + highScores.get(levelNbr));
		Gdx.app.log("player", "score: " + score);
		Gdx.app.log("Player", "Contains: 1: " + highScores.get(0) + " 2: " + highScores.get(1) + " 3: " + highScores.get(2));
		
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void setHighScore(int levelNbr, int score) {
		this.highScores.set(levelNbr-1, score);
	}
	
	public List<Integer> getHighScoreList() {
		return this.highScores;
	}
	
	public void calculateScore(double time, int cookies, boolean gameLost) {
		if(gameLost){
			this.score = cookies*100;
		}else {
			this.score = (int) (Math.pow(20/time, 3.0)*1000 + cookies*100);
		}
	}
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
	 
	public boolean contains(int levelNbr) {
		return highScores.size() > levelNbr;
		
	}
 
}
