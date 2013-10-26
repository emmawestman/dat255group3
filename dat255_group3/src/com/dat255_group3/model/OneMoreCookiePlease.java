package com.dat255_group3.model;

/**
 * OneMoreCookiePlease is the main model class of the game.
 * It contains information about the number of levels, which the current level is,
 * and if the game has started.
 * 
 * @author The Hans-Gunnar Crew
 *
 */
public class OneMoreCookiePlease {

	private int numberOfLevels;
	private int currentLevel;
	private boolean isGameStarted;

	/**
	 * Constructs a new OneMoreCookiePlease, and sets the default values
	 * of the class fields.
	 */
	public OneMoreCookiePlease() {
		currentLevel = 0;
		isGameStarted = false;
		numberOfLevels = 3;
	}

	public boolean getIsGameStarted() {
		return this.isGameStarted;
	}

	public void setIsGameStarted(boolean isGameStarted) {
		this.isGameStarted = isGameStarted;
	}

	public int getCurrentLevel() {
		return this.currentLevel;
	}

	public void setCurrentLevel(int level) {
		this.currentLevel = level;
	}

	public int getNumberOfLevels() {
		return numberOfLevels;
	}

	public void setNumberOfLevels(int numberOfLevels) {
		this.numberOfLevels = numberOfLevels;
	}
}