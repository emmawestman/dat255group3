package com.dat255_group3.model;

public class Obstacle extends GameObject {

	private int friction;

	/*
	 * Default constructor
	 */
	public Obstacle(){
		super();
	}

	public Obstacle(int friction) {
		this.friction = friction;
	}

}