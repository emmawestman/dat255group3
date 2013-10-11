package com.dat255_group3.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.dat255_group3.model.MyGdxGame;
import com.dat255_group3.utils.CoordinateConverter;
import com.dat255_group3.view.GameOverScreen;
import com.dat255_group3.view.LevelScreen;
import com.dat255_group3.view.PauseScreen;
import com.dat255_group3.view.StartScreen;


public class MyGdxGameController extends Game {
	private MyGdxGame myGdxGame;
	private InGameController inGameController;
	private LevelScreen levelScreen;
	private GameOverScreen gameOverScreen;
	private PauseScreen pauseScreen;
	private StartScreen startScreen;
	private PlayerController playerController;
	private int currentLevel;
	
	@Override
	public void create() {
		//create other the scenes and the player and the gameModel
		this.startScreen = new StartScreen(this);
		this.levelScreen = new LevelScreen(this);
		this.myGdxGame = new MyGdxGame();
		this.playerController = new PlayerController(this);
		this.inGameController = new InGameController(this);
		this.gameOverScreen = new GameOverScreen(this);
		this.pauseScreen = new PauseScreen(this);
		
		//go to the first screen
		setScreen(startScreen);
	}

	@Override
	public void dispose() {
		super.dispose();
		inGameController.dispose();
	}

	
	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
	public void setHeight(int height){
		Gdx.app.log("Size", "Height: "+height);
		CoordinateConverter.setScreenHeight(height);		
	}

	public void setWidth(int width) {
		Gdx.app.log("Size", "Width: "+width);
		CoordinateConverter.setScreenWidth(width);
	}
	
	public InGameController getInGameController(){
		return inGameController;
	}
	
	public StartScreen getStartScreen(){
		return startScreen;
	}
	
	public LevelScreen getLevelScreen() {
		return this.levelScreen;
	}
	
	public PauseScreen getPauseScreen() {
		return this.pauseScreen;
	}
	
	public GameOverScreen getGameOverScreen() {
		return this.gameOverScreen;
	}
	
	public int getCurrentLevel(){
		return this.currentLevel;
		
	}
	
	public void setCurrentLevel(int level){
		this.currentLevel = level;
	}
}
