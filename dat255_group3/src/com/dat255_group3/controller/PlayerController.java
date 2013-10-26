package com.dat255_group3.controller;

import com.dat255_group3.io.IOHandler;
import com.dat255_group3.model.Player;

/**
 * The controller class for the player.
 * 
 * @author The Hans-Gunnar Crew
 *
 */
public class PlayerController {

	private Player player;
	
	/**
	 * Constructs a new PlayerController, instantiates a Player and adds the player's
	 * score to the high score.
	 */
	public PlayerController(){
		this.player = new Player();
		player.addHighScoresToList(IOHandler.getLevelData());
	}
	
	public Player getPlayer() {
		return player;
	}
}
