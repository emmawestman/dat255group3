package com.dat255_group3.controller;

import com.dat255_group3.view.CharacterView;

public class CharacterController {

	private WorldController worldController;
	private Character character;
	private CharacterView characterView;
	
	public CharacterController(WorldController worldController){
		this.worldController = worldController;
		this.characterView = new CharacterView();
		//skapa Character
	}

	public Character getCharacter() {
		return character;
	}

	public CharacterView getCharacterView() {
		return characterView;
	}
	
}


