package com.dat255_group3.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.dat255_group3.model.MyGdxGame;
import com.dat255_group3.utils.CoordinateConverter;
import com.dat255_group3.view.StartScreen;


public class MyGdxGameController extends Game {
	private MyGdxGame myGdxGame;
	private StartScreen startScreen;
	private InGameController inGameController;
	private PlayerController playerController;
	
	@Override
	public void create() {
		//create other the scenes and the player and the gameModel
		this.myGdxGame = new MyGdxGame();
		this.playerController = new PlayerController(this);
		this.inGameController = new InGameController(this);
		this.startScreen = new StartScreen(this);
		
		//go to the first screen
		setScreen(startScreen);
	}

	@Override
	public void dispose() {
		
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
}
