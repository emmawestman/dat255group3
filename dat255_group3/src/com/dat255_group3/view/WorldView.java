package com.dat255_group3.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.dat255_group3.utils.CoordinateConverter;



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
	public void draw(Body gBody, Body charBody, CharacterView charView) {
		
		charView.draw();
		
	}
}
