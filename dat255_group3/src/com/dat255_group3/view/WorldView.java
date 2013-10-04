package com.dat255_group3.view;

import com.badlogic.gdx.physics.box2d.Body;



/** 
 * @author The Hans-Gunnar Crew
 *
 */
public class WorldView {

	public WorldView () {
		
	}
	
	/**
	 * Draws all the graphical elements of the world
	 */
	public void draw(Body charBody, CharacterView charView) {
		
		charView.draw();
		
	}
}
