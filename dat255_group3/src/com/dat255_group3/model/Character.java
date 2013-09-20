package com.dat255_group3.model;

public class Character extends GameObject {
	private int weight;
	private int friction;

	/*
	 * Default constructor
	 */
	public Character() {
		super();
	}

	public Character(int weight, int friction) {
		this.friction = friction;
		this.weight = weight;

	}

}
