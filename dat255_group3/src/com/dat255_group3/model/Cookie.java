package com.dat255_group3.model;

import com.badlogic.gdx.math.Vector2;


/**
 * A Cookie is a collectible item in the game that gives you
 * bonus points.
 * 
 * @author The Hans-Gunnar Crew
 *
 */
public class Cookie {
	
	private Vector2 position;
	private int radius;

	/**
	 * Constructs a new Cookie with the specified position, and sets
	 * the default radius to 32 pixels.
	 * 
	 * @param position the position of the cookie
	 * 			
	 */
	public Cookie(Vector2 position) {
		this.position = position;
		radius = 32;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public int getCookieRadius() {
		return radius;
	}

}
