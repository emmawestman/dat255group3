package com.dat255_group3.controller;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.dat255_group3.model.Cookie;
import com.dat255_group3.view.CookieView;

/**
 * A controller class for cookies
 * @author The Hans-Gunnar Crew
 *
 */
public class CookieController {

	private CookieView cookieView;
	
	public CookieController(ArrayList<Cookie> cookieList, OrthographicCamera camera) {
		this.cookieView = new CookieView(camera, cookieList);
	}
	
	/**
	 * A method to get the cookieView
	 * @return
	 * 		The cookieView
	 */
	public CookieView getCookieView() {
		return cookieView;
	}
}
