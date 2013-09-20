package com.dat255_group3.controller;

import com.dat255_group3.model.InGame;
import com.dat255_group3.view.InGameView;

public class InGameController {
	
	private MyGdxGameController myGdxGameController;
	private InGame inGame;
	private InGameView inGameView;
	
	public InGameController(MyGdxGameController myGdxGameController){
		this.myGdxGameController = myGdxGameController;
		this.inGameView = new InGameView();
		this.inGame = new InGame();
	}

	public InGame getInGame() {
		return inGame;
	}

	public InGameView getInGameView() {
		return inGameView;
	}	
	
}
