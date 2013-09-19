package com.example.dat255_grupp3_2013.model;

import com.badlogic.gdx.math.Vector2;

public class Character extends GameObject {
	private int weight;
	private int friction;
	
	/*
	 * Default constructor
	 */
	public Character(){
		super();
	}
	
	public Character(int weight, int friction){
		this.friction = friction;
		this.weight = weight;
		
	}

}
