package com.dat255_group3.model;

import com.badlogic.gdx.math.Vector2;

public class Character extends GameObject {
	private int weight;
	private double friction;

	public Character(Vector2 position, double friction, int weight){
		super(position);
		this.friction = friction;
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public double getFriction() {
		return friction;
	}

	public void setFriction(int friction) {
		this.friction = friction;
	}
	
	

}
