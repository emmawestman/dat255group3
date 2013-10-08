package com.dat255_group3.view;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.dat255_group3.model.Cookie;

public class CookieView {
	private OrthographicCamera camera;
	private ShapeRenderer renderer = new ShapeRenderer();
	private ArrayList<Cookie> cookieList;
	
	public CookieView(OrthographicCamera camera, ArrayList<Cookie> cookieList) {
		this.camera = camera;
		this.cookieList = cookieList;
	}
	
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
