package com.dat255_group3.model;

import com.badlogic.gdx.math.Vector2;

public class Obstacle extends GameObject {

	private int friction;

	public Obstacle(Vector2 position, int friction) {
		super(position);
		this.friction = friction;
	}

	public int getFriction() {
		return friction;
	}

	public void setFriction(int friction) {
		this.friction = friction;
	}
	
	

}