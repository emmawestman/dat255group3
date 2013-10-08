package com.dat255_group3.controller;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.dat255_group3.model.Cookie;
import com.dat255_group3.view.CookieView;

public class CookieController {

	private ArrayList<Cookie> cookieList;
	private CookieView cookieView;
	
	public CookieController(ArrayList<Cookie> cookieList, OrthographicCamera camera) {
		this.cookieList = cookieList;
		this.cookieView = new CookieView(camera, cookieList);
	}
	
	public CookieView getCookieView() {
		return cookieView;
	}
}
