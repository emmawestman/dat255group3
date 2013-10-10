package com.dat255_group3.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.dat255_group3.utils.CoordinateConverter;

/** A view class for the InGame model. 
 * @author The Hans-Gunnar Crew
 *
 */
public class InGameView {
	
	private OrthogonalTiledMapRenderer mapRenderer;
	private OrthographicCamera camera;
	private SpriteBatch spriteBatch;
	private BitmapFont font;
	private CharSequence str;
	
	/** A constructor that takes a map. 
	 * @param map
	 */
	
	public InGameView (TiledMap map, OrthographicCamera camera) {
		mapRenderer = new OrthogonalTiledMapRenderer(map);
		this.camera = camera;
		this.spriteBatch = new SpriteBatch();
		this.font = new BitmapFont();
		
	}
	
	
	
	/** Renders the HUD and background of the game. 
	 * 
	 */
	public void draw(WorldView worldView, Body charBody, CharacterView charView, CookieView cookieView, float time, int cookieCounter, boolean gameOver) {
		//Shows selected part of the map
		mapRenderer.setView(camera);
		mapRenderer.render();
		worldView.draw(charView, cookieView);
		//draw game over text
		if(gameOver) drawGameOver();
		//Draw time
		drawTime(time);
		drawCookieCounter(cookieCounter);
		
	}
	
	public void drawGameOver(){
		spriteBatch.begin();
		str = "GAME OVER";
		font.setColor(Color.RED);
		font.draw(spriteBatch, str, (CoordinateConverter.getScreenWidth()/2)-20f, CoordinateConverter.getScreenHeight()/2);
		spriteBatch.end();
	}
	
	public void drawTime(float time) {
		spriteBatch.begin();
		str = "Time: "+ time;
		font.setColor(Color.BLACK);
		font.draw(spriteBatch, str, 20f, CoordinateConverter.getScreenHeight()-30f);
		spriteBatch.end();
	}
	
	public void drawCookieCounter(int cookieCounter) {
		spriteBatch.begin();
		str = "Cookies: "+ cookieCounter;
		font.setColor(Color.BLACK);
		font.draw(spriteBatch, str, CoordinateConverter.getScreenWidth() - 80f, CoordinateConverter.getScreenHeight()-30f);
		spriteBatch.end();
	}
}
