package com.dat255_group3.controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.dat255_group3.model.Character;
import com.dat255_group3.view.CharacterView;
/**
 * The controller class for the character.
 * 
 * @author The Hans-Gunnar Crew
 */
public class CharacterController {

	private WorldController worldController;
	private Character character;
	private CharacterView characterView;
	
	/**
	 * Constructs a new CharacterController, with the specified WorldController 
	 *  and camera, and instantiates a Character (with a default friction of 0.5)
	 * and a CharacterView.
	 * 
	 * @param worldController the game's WorldController object
	 * @param camera the camera used to display the game
	 */
	public CharacterController(WorldController worldController, OrthographicCamera camera){
		this.worldController = worldController;
		this.character = new Character(0.5);
		this.characterView = new CharacterView(character, camera);
	}

	public Character getCharacter() {
		return character;
	}

	public CharacterView getCharacterView() {
		return characterView;
	}
	
	/**
	 * Checks if the character can perform a jump, i.e if the character is on the ground,
	 * and if so, invokes the jump method.
	 */
	public void tryToJump() {
		if(this.worldController.getCharBody().getLinearVelocity().y == 0) {
			jump();
		}
	}
	
	/**
	 * Makes the character jump.
	 */
	public void jump(){
		float impulse = this.worldController.getCharBody().getMass()*6;
		worldController.getCharBody().applyLinearImpulse(0.0f, impulse, 
				worldController.getCharBody().getWorldCenter().x, worldController.getCharBody().getWorldCenter().y, true);
	}
}


