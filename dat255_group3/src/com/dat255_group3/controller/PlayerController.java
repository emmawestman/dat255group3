package com.dat255_group3.controller;

import com.dat255_group3.model.Player;

public class PlayerController {

	private Player player;
	
	public PlayerController(){
		this.player = new Player();
	}
	
	public Player getPlayer() {
		return player;
	}
}
