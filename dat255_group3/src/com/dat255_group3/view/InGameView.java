package com.dat255_group3.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/** A view class for the InGame model. 
 * @author The Hans-Gunnar Crew
 *
 */
public class InGameView {
	
	private OrthogonalTiledMapRenderer mapRenderer;
	private TiledMap map;
	private OrthographicCamera camera; 
	
	/** A constructor that takes a map. 
	 * @param map
	 */
	public InGameView (TiledMap map) {
		this.map = map;
		mapRenderer = new OrthogonalTiledMapRenderer(map);
		camera = new OrthographicCamera();
		
	}
	
	
	
	/** Renders the HUD and background of the game. 
	 * 
	 */
	public void render() {
		//show a black screen
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		//Shows selected part of the map
		//mapRenderer.setView(camera);
		//mapRenderer.render();
		
		
		//Skota layouts = lyssnar av olika touch -> 
		
		//If: Vinna -> visa vinna.
	
	}
	/*
	/** Does nothing right now.
	 * 
	 
	public void drawJump(){
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	}
	*/

}
