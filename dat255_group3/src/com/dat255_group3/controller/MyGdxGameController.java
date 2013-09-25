package com.dat255_group3.controller;

import com.badlogic.gdx.Game;
import com.dat255_group3.model.MyGdxGame;


public class MyGdxGameController extends Game {
	private MyGdxGame myGdxGame;
	private InGameController inGameController;
	private PlayerController playerController;
	
	@Override
	public void create() {
		//create other the scenes and the player and the gameModel
		this.myGdxGame = new MyGdxGame();
		this.playerController = new PlayerController(this);
		
		
		//go to the first screen
		setScreen(new InGameController(this));
		
		
		
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
}
