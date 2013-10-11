package com.dat255_group3.model;

/**
 * A class which describes the state of the player being playing a game. 
 * Administers the time and score of the game which is given by each level.
 * @author The Hans-Gunnar Crew
 *
 */
public class InGame {
	private int time;
	private int score;
	private float speedM;
	private float speedP;

	/**
	 * Constructs a InGame
	 */
	public InGame() {
		speedM = 1.5f;
	}

	/**
	 * A method which gives the time of the current game.
	 * @return
	 * 		The time of the current game.
	 */
	public int getTime() {
		return time;
	}

	/**
	 * Sets the time of the current game.
	 * @param time
	 * 			The the time which the current game is to be set to.
	 */
	public void setTime(int time) {
		this.time = time;
	}

	/**
	 *  A method which gives the score of the current game.
	 * @return
	 * 		The score of the current game.
	 */
	public int getScore() {
		return score;
	}

	/**
	 *  Sets the score of the current game.
	 * @param score
	 * 		The the score which the current game is to be set to.
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 *  Sets the speed in meters
	 * @param speedM
	 * 			The speed in meters.
	 */
	public void setSpeedM(float speedM) {
		this.speedM = speedM;
	}
	
	/**
	 *  A method which gives the speed in meters of the current game.
	 * @return
	 * 		The speed in meters of the current game.
	 */
	public float getSpeedM() {
		return speedM;
	}
	
	/**
	 *  A method which gives the speed in pixels of the current game.
	 * @return
	 * 		The speed in pixels of the current game.
	 */
	public float getSpeedP() {
		return speedP;
	}
	
	/**
	 *  Sets the speed in pixels.
	 * @param speedP
	 * 			The speed in pixels.
	 */
	public void setSpeedP(float speedP) {
		this.speedP = speedP;
	}
	
	
}