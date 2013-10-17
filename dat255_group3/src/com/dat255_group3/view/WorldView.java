package com.dat255_group3.view;

import com.dat255_group3.model.World;



/** 
 * @author The Hans-Gunnar Crew
 *
 */
public class WorldView {
	
	private World world;

	public WorldView(World world) {
		this.world = world;
		
	}
	
	/**
	 * Draws all the graphical elements of the world
	 */
	public void draw(CharacterView charView, CookieView cookieView, EnemyView enemy) {
		charView.draw(world.getTime());
		cookieView.draw();
		enemy.draw(world.getTime(), charView.getCharacter().getDeahLimit());
	}
}
