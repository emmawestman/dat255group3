package com.dat255_group3.view;

import com.dat255_group3.model.World;

/** 
 * The view which draws the character, cookies and enemy.
 * 
 * @author The Hans-Gunnar Crew
 */

public class WorldView {

	private World world;

	/**
	 * Constructs a new WorldView with the specified world.
	 * 
	 * @param world the World in which the time is stored
	 */
	public WorldView(World world) {
		this.world = world;
	}

	/**
	 * Draws the character, cookie and enemy views.
	 * 
	 * @param charView the character view
	 * @param cookieView the cookie view
	 * @param enemy the enemy view
	 */
	public void draw(CharacterView charView, CookieView cookieView, EnemyView enemy) {
		charView.draw(world.getTime());
		cookieView.draw();
		enemy.draw(world.getTime(), charView.getCharacter().getDeahLimit());
	}
}
