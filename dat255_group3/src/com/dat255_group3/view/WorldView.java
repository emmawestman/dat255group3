package com.dat255_group3.view;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;



/** 
 * @author The Hans-Gunnar Crew
 *
 */
public class WorldView {
	private ShapeRenderer shape = new ShapeRenderer();

	public WorldView () {
		
	}
	
	/**
	 * Draws all the graphical elements of the world
	 */
	public void draw(ArrayList<Body> gBody, Body charBody, CharacterView charView) {
		
		charView.draw();
		
	}
}
