package com.dat255_group3.view;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dat255_group3.model.Cookie;

/**
 * The view which draws all the cookies in the game.
 * 
 * @author The Hans-Gunnar Crew
 */
public class CookieView {
	
	private OrthographicCamera camera;
	private ArrayList<Cookie> cookieList;
	private SpriteBatch spriteBatch;
	private Sprite sprite;
	private Texture texture;
	
	/**
	 * Constructs a new CookieView with the specified camera and list
	 * of cookies.
	 * 
	 * @param camera the camera used to display the game
	 * @param cookieList the list of cookies to be drawn.
	 */
	public CookieView(OrthographicCamera camera, ArrayList<Cookie> cookieList) {
		this.camera = camera;
		this.cookieList = cookieList;
		spriteBatch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("ui/cookie.png"));
		sprite = new Sprite(texture);
		sprite.setSize(32f, 32f);
	}
	
	/**
	 * Loops through the cookie list and draws all cookies.
	 */
	public void draw() {
		camera.update();
		spriteBatch.begin();
		spriteBatch.setProjectionMatrix(camera.combined);
		for(int i = 0; i<cookieList.size(); i++) {
			sprite.setPosition(cookieList.get(i).getPosition().x, cookieList.get(i).getPosition().y);
			sprite.draw(spriteBatch);
		}
		spriteBatch.end();
	}
}
