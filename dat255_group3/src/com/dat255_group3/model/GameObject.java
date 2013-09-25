package com.dat255_group3.model;

import com.badlogic.gdx.math.Vector2;

public class GameObject {
	private Vector2 position;

	public GameObject(Vector2 position) {
		this.position = position;	
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}
}
	