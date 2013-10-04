package com.dat255_group3.view;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.dat255_group3.utils.CoordinateConverter;



/** 
 * @author The Hans-Gunnar Crew
 *
 */
public class WorldView {
	private ShapeRenderer shape = new ShapeRenderer();
	private Rectangle deathRect; //if the character touches, he dies
	

	public WorldView () {
		deathRect = new Rectangle();
		deathRect.width = 64f;
		deathRect.height = CoordinateConverter.getScreenHeight();
	}
	
	/**
	 * Draws all the graphical elements of the world
	 */
	public void draw(Body charBody, CharacterView charView) {
		shape.begin(ShapeType.Filled);
		shape.setColor(Color.RED);
		shape.rect(0, CoordinateConverter.getScreenHeight(), deathRect.width, deathRect.height);
		shape.end();
		
		charView.draw();
		
	}

	public Rectangle getDeathRect() {
		return deathRect;
	}

	public void setDeathRect(Rectangle deathRect) {
		this.deathRect = deathRect;
	}
}
