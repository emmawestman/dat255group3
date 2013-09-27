package com.dat255_group3.controller;

import com.dat255_group3.model.Player;

public class PlayerController {

	private MyGdxGameController myGdxGameController;
	private Player player;
	
	public PlayerController(MyGdxGameController myGdxGameController){
		this.myGdxGameController = myGdxGameController;
		this.player = new Player();
	}
	
	public Player getPlayer() {
		return player;
	}
}
