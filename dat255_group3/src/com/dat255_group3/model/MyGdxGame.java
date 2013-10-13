package com.dat255_group3.model;

/**
 * A class which describes the game MyGdxGame.
 * 
 * The game is about a squirrel named Hans-Gunnar who founds cookies falling
 * down from the clouds. He falls promptly in love as he tastes them. In order
 * to maintain this wonderful delicacy in his belongings he runs to catch more.
 * However, these cookies has a propraetor, namely the god of weathers
 * Bartolomeus, who leaked them from his castle. As the discovers the thief he
 * becomes furious and uses all his powers to catch Hans-Gunnar.
 * 
 * 
 * @author The Hans-Gunnar Crew
 * 
 */
public class MyGdxGame {

	private int currentLevel;
	private boolean isGameStarted;

	public MyGdxGame() {
		currentLevel = 0;
		isGameStarted = false;

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

}
