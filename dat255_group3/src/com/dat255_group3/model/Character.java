package com.dat255_group3.model;

public class Character extends GameObject {
	private int weight;
	private double friction;

	
	public Character(){
		this(50,0.5);
	}
	
	public Character(int weight, double friction) {
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
