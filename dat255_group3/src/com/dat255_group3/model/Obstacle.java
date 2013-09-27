package com.dat255_group3.model;

import com.badlogic.gdx.math.Vector2;

/**
 * A class that represents the objects that the character can collide 
 * with in the game.
 * 
 * 
 * @author The Hans-Gunnar Crew
 *
 */

public class Obstacle extends GameObject {

	/** The obstacle's friction value. */
	private int friction;

	/**
	 * Class constructor specifying both position and friction.
	 * 
	 * @param position the obstacle's position in the world
	 * @param friction the obstacle's friction value
	 */
	public Obstacle(Vector2 position, int friction) {
		super(position);
		this.friction = friction;
	}

	/**
	 * Returns an int with the obstacle's friction value.
	 * 
	 * @return the obstacle's friction value
	 */
	public int getFriction() {
		return friction;
	}

	/**
	 * Sets the friction value to the specified value.
	 * 
	 * @param friction the new friction value
	 */
	public void setFriction(int friction) {
		this.friction = friction;
	}
}