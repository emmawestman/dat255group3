package com.dat255_group3.controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.dat255_group3.model.Character;
import com.dat255_group3.view.CharacterView;

public class CharacterController {

	private WorldController worldController;
	private Character character;
	private CharacterView characterView;
	
	public CharacterController(WorldController worldController, OrthographicCamera camera){
		this.worldController = worldController;
		
		/*
		 * The character and its view will later on be placed in a map/lists as we will have different characters
		 */
		this.character = new Character(0.0, 50);
		this.characterView = new CharacterView(character, camera);

	}

	public Character getCharacter() {
		return character;
	}

	public CharacterView getCharacterView() {
		return characterView;
	}
	
	public void tryToJump() {
		if(this.worldController.getCharBody().getLinearVelocity().y == 0) {
			jump();
		}
	}
	
	public void jump(){
		float impulse = this.worldController.getCharBody().getMass()*5;
		worldController.getCharBody().applyLinearImpulse(0.0f, impulse, 
				worldController.getCharBody().getWorldCenter().x, worldController.getCharBody().getWorldCenter().y, true);
	
	}
}


