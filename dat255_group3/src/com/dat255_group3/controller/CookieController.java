package com.dat255_group3.controller;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.dat255_group3.model.Character;
import com.dat255_group3.model.Cookie;
import com.dat255_group3.view.CookieView;

/**
 * The controller class for the cookies.
 * 
 * @author The Hans-Gunnar Crew
 */
public class CookieController {

	private CookieView cookieView;
	private ArrayList<Cookie> cookieList;
	private int cookieIndex; //indicates which cookie should be checked for collision
	private WorldController worldController;
	
	/**
	 * Constructs a new CookieController with the specified WorldController, 
	 * cookie list and camera, instantiates a CookieView and sets the cookiIndex to 0.
	 * 
	 * @param worldController the game's WorldController object
	 * @param cookieList the list of cookies that are displayed in the game
	 * @param camera the camera used to display the game
	 */

	public CookieController(WorldController worldController,
			ArrayList<Cookie> cookieList, OrthographicCamera camera) {
		this.cookieView = new CookieView(camera, cookieList);
		this.cookieList = cookieList;
		cookieIndex = 0;
		this.worldController = worldController;
	}
	
	public CookieView getCookieView() {
		return cookieView;
	}

	public ArrayList<Cookie> getCookieList() {
		return cookieList;
	}
	
	/**
	 * Checks which cookie is nearest to the character, so that only the nearest
	 * cookie is checked for collision detection.
	 * 
	 * @param characterController the game's CharacterController object
	 */

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

	/**
	 * Checks if the character is colliding with the nearest cookie.
	 * 
	 * @param character the game's Character object
	 */
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
 
	/**
	 * Removes the collected cookie from the list, so that it's no longer
	 * drawn in the view.
	 */

	public void collision() {
		cookieList.remove(cookieIndex);
		worldController.getWorld().increaseCookieCounter();
		if (OneMoreCookiePleaseController.soundEffectsOn()) {
			worldController.getSoundController().playCookieSound();
		}
	}
}
