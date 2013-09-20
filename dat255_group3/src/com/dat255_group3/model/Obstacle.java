package com.dat255_group3.model;

public class Obstacle extends GameObject {

	private int friction;

	public Obstacle(int friction) {
		this.friction = friction;
	}

	public int getFriction() {
		return friction;
	}

	public void setFriction(int friction) {
		this.friction = friction;
	}
	
	

}