package com.dat255_group3.view;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.dat255_group3.model.Cookie;

/**
 * A View for all cookies in the game.
 * @author The Hans-Gunnar Crew
 *
 */
public class CookieView {
	private OrthographicCamera camera;
	private ShapeRenderer renderer = new ShapeRenderer();
	private ArrayList<Cookie> cookieList;
	private SpriteBatch spriteBatch;
	private Sprite sprite;
	private Texture texture;
	
	/**
	 * A constructor that takes a camera and a list of cookies.
	 * @param camera
	 * 			The camera that will show the cookies.
	 * @param cookieList
	 * 			The list of cookies to be drawn.
	 */
	public CookieView(OrthographicCamera camera, ArrayList<Cookie> cookieList) {
		this.camera = camera;
		this.cookieList = cookieList;
		spriteBatch = new SpriteBatch();
//		texture = new Texture();
		sprite = new Sprite();
	}
	
	/**
	 * A method which draws all the cookies.
	 */
	public void draw() {
		camera.update();
		renderer.setProjectionMatrix(camera.combined);
		renderer.begin(ShapeType.Filled);
		renderer.setColor(Color.ORANGE);
		for(int i = 0; i<cookieList.size(); i++) {
			renderer.circle(cookieList.get(i).getPosition().x, cookieList.get(i).getPosition().y, 10);
		}
		renderer.end();
	}
	
}
