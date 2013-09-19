package com.example.dat255_grupp3_2013.model;

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
