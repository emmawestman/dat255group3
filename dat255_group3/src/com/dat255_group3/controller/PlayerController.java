package com.dat255_group3.controller;

import com.dat255_group3.io.IOHandler;
import com.dat255_group3.model.Player;

public class PlayerController {

	private Player player;
	
	public PlayerController(){
		this.player = new Player();
		player.addHighScoresToList(IOHandler.getLevelData());

	}
	
	public Player getPlayer() {
		return player;
	}
}
