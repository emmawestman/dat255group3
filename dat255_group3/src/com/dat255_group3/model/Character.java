package com.dat255_group3.model;

public class Character extends GameObject {
	private int weight;
	private int friction;

	public Character(int weight, int friction) {
		this.friction = friction;
		this.weight = weight;

	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getFriction() {
		return friction;
	}

	public void setFriction(int friction) {
		this.friction = friction;
	}
	
	

}
