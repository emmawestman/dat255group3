package com.dat255_group3.model;

import com.badlogic.gdx.math.Vector2;
import com.dat255_group3.utils.WorldUtil;

/**
 * A Character is an object that the player controls in the game.
 * 
 * @author The Hans-Gunnar Crew
 */
public class Character {
	private double friction;
	private static float radius; 
	private float deathLimit; //the character is taken by the enemy if it reaches this limit
	private Vector2 position; 

	/**
	 * Constructs a new Character with the specified friction and sets
	 * the default radius to 25, the death limit to 20, and the start position
	 * to the position specified in the level's map.
	 * 
	 * @param friction the friction of the Character in the physics world (a value between 0-1)
	 * 			
	 */
	public Character(double friction){
		this.friction = friction;
		Character.radius = 25;
		this.deathLimit = 20f; 
		this.position = WorldUtil.getStartPos();
	}
	
	/**
	 * Checks if the character has been taken by the enemy.
	 * 
	 * @return true if the character is behind the death limit
	 */
	public boolean isDead(){
		return getPosition().x < deathLimit;
	}

	public static float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		Character.radius = radius;
	}
	
	public double getFriction() {
		return friction;
	}

	public void setFriction(int friction) {
		this.friction = friction;
	}
	
	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public float getDeahLimit() {
		return deathLimit;
	}
	
	/**
	 * Moves the death limit in the same speed as the camera.
	 * 
	 * @param speedP the camera's speed
	 */
	public void moveDeathLimit(float speedP) {
		this.deathLimit = deathLimit + speedP;
	}
}
