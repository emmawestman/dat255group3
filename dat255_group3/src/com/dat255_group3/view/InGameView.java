package com.dat255_group3.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class InGameView {
	
	private OrthogonalTiledMapRenderer mapRenderer;
	private TiledMap map;
	private OrthographicCamera camera; 
	
	public InGameView (TiledMap map, OrthographicCamera camera) {
		this.map = map;
		mapRenderer = new OrthogonalTiledMapRenderer(map);
		this.camera = camera;
		
	}
	
	
	
	public void render() {
		//show a black screen
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		//Shows selected part of the map
		mapRenderer.setView(camera);
		mapRenderer.render();
		
		
		//Skota layouts = lyssnar av olika touch -> 
		
		//If: Vinna -> visa vinna.
	
	}
	
	public void drawJump(){
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	}
}
