package com.dat255_group3.view;



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
	public void draw(CharacterView charView, CookieView cookieView) {
		charView.draw();
		cookieView.draw();
	}
}
