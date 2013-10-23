package com.dat255_group3.model;
/**
 * A class that describes the world in the game.
 * This is where the time and the number of cookies collected is stored.
 * 
 * @author The Hans-Gunnar Crew
 */

public class World {
	
	private int cookieCounter;
	private double time;

	public int getCookieCounter() {
		return cookieCounter;
	}

	public void setCookieCounter(int cookieCounter) {
		this.cookieCounter = cookieCounter;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}
	
	/**
	 * Increases the cookie counter one step.
	 */
	public void increaseCookieCounter() {
		this.cookieCounter++;
	}
}