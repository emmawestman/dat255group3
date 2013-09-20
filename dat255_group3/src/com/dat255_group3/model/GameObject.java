package com.dat255_group3.model;

import com.badlogic.gdx.math.Vector2;

public class GameObject {
	private int x, y;
	private Vector2 width, height;

	public GameObject() {
		/*
		 * Set values for the positions & for the dimensions. Unsure whether the
		 * constructor should take in information or not
		 */
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Vector2 getWidth() {
		return width;
	}

	public void setWidth(Vector2 width) {
		this.width = width;
	}

	public Vector2 getHeight() {
		return height;
	}

	public void setHeight(Vector2 height) {
		this.height = height;
	}
	
	
	

}