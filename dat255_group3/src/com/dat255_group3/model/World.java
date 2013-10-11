package com.dat255_group3.model;
/**
 * World is a class that describes the world in the game.
 * 
 * 
 * @author The Hans-Gunnar Crew
 *
 */

public class World {
	
	private int cookieCounter;
	private float time;

	/**
	 * Class constructor.
	 */
	public World() {
		
	}

	public int getCookieCounter() {
		return cookieCounter;
	}

	public void setCookieCounter(int cookieCounter) {
		this.cookieCounter = cookieCounter;
	}

	public float getTime() {
		return time;
	}

	public void setTime(float time) {
		this.time = time;
	}
	
	public void increaseCoockieCounter() {
		this.cookieCounter++;
	}
}