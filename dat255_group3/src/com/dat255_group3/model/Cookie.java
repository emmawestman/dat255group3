package com.dat255_group3.model;

import com.badlogic.gdx.math.Vector2;


/**
 * A class that represents a cookie
 * @author The Hans-Gunnar Crew
 *
 */
public class Cookie {
	
	private Vector2 position;
	private int radius;

	/**
	 * A constructor that takes a position for the cookie
	 * @param position
	 * 			The position of the cookie
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
