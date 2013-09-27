package com.dat255_group3.controller;

import com.badlogic.gdx.math.Vector2;
import com.dat255_group3.model.Character;
import com.dat255_group3.view.CharacterView;

public class CharacterController {

	private WorldController worldController;
	private Character character;
	private CharacterView characterView;
	
	public CharacterController(WorldController worldController){
		this.worldController = worldController;
		
		/*
		 * The character and its view will later on be placed in a map/lists as we will have different characters
		 */
		this.character = new Character(new Vector2(-25f, -25f), 0.0, 50);
		this.characterView = new CharacterView(character);
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
	/*
	 * A very basic jump-method 
	 * This is only a test
	 */
	public void jump(){
		worldController.getCharBody().applyLinearImpulse(0.0f, 9000000, 
				worldController.getCharBody().getWorldCenter().x, worldController.getCharBody().getWorldCenter().y, true);
	
		//worldController.getCharBody().getMass()*worldController.getGravity().y*2f
	
	}
	
}


