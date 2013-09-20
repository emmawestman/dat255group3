package com.dat255_group3.controller;

import com.dat255_group3.model.World;
import com.dat255_group3.view.WorldView;

public class WorldController {

	private InGameController inGameController;
	private World world;
	private WorldView worldView;
	
	public WorldController(InGameController inGameController){
		this.inGameController = inGameController;
		this.world = new World();
		this.worldView = new WorldView();
	}

	public World getWorld() {
		return world;
	}

	public WorldView getWorldView() {
		return worldView;
	}
	
	
}
