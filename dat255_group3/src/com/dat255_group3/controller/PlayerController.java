package com.dat255_group3.controller;

import com.badlogic.gdx.Gdx;
import com.dat255_group3.model.Player;

public class PlayerController {

	private Player player;
	
	public PlayerController(){
		this.player = new Player();
		
		if(Gdx.files.local("io/io.txt").exists()){
			this.player.setBestTime(100);
			this.player.setHighScore(200);
			Gdx.app.log("PlayerController", "Time: " + player.getBestTime() + "Score: " +player.getHighscore());
		} else {
			Gdx.app.log("PlayerController", "Not initiated");
		}
		
	}
	
	public Player getPlayer() {
		return player;
	}
}
