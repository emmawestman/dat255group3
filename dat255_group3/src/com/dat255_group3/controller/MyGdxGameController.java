package com.dat255_group3.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.dat255_group3.model.MyGdxGame;
import com.dat255_group3.utils.CoordinateConverter;


public class MyGdxGameController extends Game {
	private MyGdxGame myGdxGame;
	private InGameController inGameController;
	private PlayerController playerController;
	
	@Override
	public void create() {
		//create other the scenes and the player and the gameModel
		this.myGdxGame = new MyGdxGame();
		this.playerController = new PlayerController(this);
		this.inGameController = new InGameController(this);
		
		//go to the first screen
		setScreen(new StartScreen(this,inGameController));
		
		
		
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
		Gdx.app.log("Viking", "Height: "+height);
		CoordinateConverter.setScreenHeight(height);		
	}

	public void setWidth(int width) {
		Gdx.app.log("Viking", "Width: "+width);
		CoordinateConverter.setScreenWidth(width);
	}
}
