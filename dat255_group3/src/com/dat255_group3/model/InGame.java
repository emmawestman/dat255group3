package com.dat255_group3.model;

import com.dat255_group3.utils.CoordinateConverter;


/**
 * A class which represents the game as its being played. This is where the
 * time, score and speed is stored. 
 * 
 * @author The Hans-Gunnar Crew
 */
public class InGame {
	private int time;
	private int score;
	private float speedM; //the speed in meters
	private float speedP; //the speed in pixels
	private float delayTime;

	/**
	 * Constructs a new InGame and sets the default speed in
	 * meters to 0.5f.
	 */
	public InGame() {
		speedM = 0.5f;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void setSpeedM(float speedM) {
		this.speedM = speedM;
	}
	
	public float getSpeedM() {
		return speedM;
	}
	
	public float getSpeedP() {
		return speedP;
	}
	
	public void setSpeedP(float speedP) {
		this.speedP = speedP;
	}
	
	public float getDelayTime() {
		return delayTime;
	}
	
	public void setDelayTime(float delayTime) {
		this.delayTime = delayTime;
	}

	/**
	 * Updates the speed in pixels.
	 * 
	 * @param delta the time in seconds since the last render
	 */
	public void updateSpeedP(float delta) {
		this.speedP = CoordinateConverter.meterToPixel(this.speedM * delta);		
	}
}