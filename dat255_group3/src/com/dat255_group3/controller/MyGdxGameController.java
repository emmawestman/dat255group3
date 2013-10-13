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
	private PlayerController playerController;
	private StartScreen startScreen;
	private LevelScreen levelScreen;
	private GameOverScreen gameOverScreen;
	private PauseScreen pauseScreen;
	private static boolean soundEffectsOn = true;
	
	@Override
	public void create() {
		//create other the scenes and the player and the gameModel
		this.myGdxGame = new MyGdxGame();
		this.playerController = new PlayerController(this);
		this.inGameController = new InGameController(this);
		this.startScreen = new StartScreen(this);
		this.levelScreen = new LevelScreen(this);
		this.gameOverScreen = new GameOverScreen(this);
		this.pauseScreen = new PauseScreen(this);

		SoundController.playBackgroundMusic();
		
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
	
	public MyGdxGame getMyGdxGame() {
		return myGdxGame;
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
	

	public PlayerController getPlayerController() {
		return this.playerController;
	}
	
	public void soundEffectsOn(boolean soundOn) {
		soundEffectsOn = soundOn;
	}

	public static boolean soundEffectsOn() {
		return soundEffectsOn;
	}

	public void save(){
		inGameController.save();
		Gdx.app.log("MyGdx", "Save");
	}

}

