package com.dat255_group3.model;

import com.badlogic.gdx.math.Vector2;

/**
 * A class describing the characteristics of a GameObject. 
 * It administers the positions of a GameObject.
 * @author Grupp 3
 *
 */
public class GameObject {
	private Vector2 position;

	/**
	 * Constructs a GameObject
	 * @param position
	 * 		The position of the GameObject in the World.
	 */
	public GameObject(Vector2 position) {
		this.position = position;	
	}

	/**
	 *  A method which gives the position of the GameObject.
	 * @return
	 * 		The position of the GameObject
	 */
	public Vector2 getPosition() {
		return position;
	}

	/**
	 * Sets the position of the GameObject
	 * @param position
	 * 		The position which the GameObject is to be set to.
	 */
	public void setPosition(Vector2 position) {
		this.position = position;
	}
}
	