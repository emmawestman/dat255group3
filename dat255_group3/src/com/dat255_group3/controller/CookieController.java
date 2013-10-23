package com.dat255_group3.controller;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.dat255_group3.model.Character;
import com.dat255_group3.model.Cookie;
import com.dat255_group3.view.CookieView;

/**
 * A controller class for cookies
 * 
 * @author The Hans-Gunnar Crew
 * 
 */
public class CookieController {

	private CookieView cookieView;
	private ArrayList<Cookie> cookieList;
	private int cookieIndex;
	private WorldController worldController;

	public CookieController(WorldController worldController,
			ArrayList<Cookie> cookieList, OrthographicCamera camera) {
		this.cookieView = new CookieView(camera, cookieList);
		this.cookieList = cookieList;
		cookieIndex = 0;
		this.worldController = worldController;
	}

	/**
	 * A method to get the cookieView
	 * 
	 * @return The cookieView
	 */
	public CookieView getCookieView() {
		return cookieView;
	}

	public ArrayList<Cookie> getCookieList() {
		return cookieList;
	}

	public void checkNextCookie(CharacterController characterController) {
		if (cookieList.size() > 0 && cookieList.size() > cookieIndex) {
			characterController.getCharacter();
			if (cookieList.get(cookieIndex).getPosition().x
					+ cookieList.get(0).getCookieRadius() > characterController
					.getCharacter().getPosition().x - Character.getRadius()) {
				checkCookieCollision(characterController.getCharacter());
			} else if (cookieIndex < cookieList.size() - 1) {
				cookieIndex++;
			}
		}
	}

	public void checkCookieCollision(Character character) {
		if (Math.abs(cookieList.get(cookieIndex).getPosition().x + 32
				- character.getPosition().x) < Character.getRadius()) {
			if (Math.abs(cookieList.get(cookieIndex).getPosition().y
					- character.getPosition().y
					+ cookieList.get(0).getCookieRadius() / 2) <= Character
					.getRadius()) {
				collision();
			}
		}
	}

	public void collision() {
		cookieList.remove(cookieIndex);
		worldController.getWorld().increaseCookieCounter();
		if (OneMoreCookiePleaseController.soundEffectsOn()) {
			worldController.getSoundController().playCookieSound();
		}
	}
}
