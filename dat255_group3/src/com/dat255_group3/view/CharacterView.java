package com.dat255_group3.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.dat255_group3.model.Character;
import com.dat255_group3.utils.WorldUtil;

/**  A view class for the character Model. 
 * @author The Hans-Gunnar Crew
 * 
 */
public class CharacterView {
	
	private Circle characterCircle;
	private Character character;
	private ShapeRenderer shape = new ShapeRenderer();
	private OrthographicCamera camera;
	
	/** A constructor that takes a character class.
	 * 
	 * @param character
	 */
	public CharacterView (Character character, OrthographicCamera camera) {
		this.character = character;
		characterCircle = new Circle();
		characterCircle.radius =  Character.getRadius();
		this.camera = camera;
	}
	
	
	/**
	 * A method which draws the character (which is currently just a turquoise rectangle)
	 * The Characters appearance will change with time. This is only to test.
	 */
	public void draw(){
		camera.update();
		shape.setProjectionMatrix(camera.combined);
		shape.begin(ShapeType.Filled);
		shape.setColor(Color.CYAN);
		shape.circle(character.getPosition().x, character.getPosition().y, characterCircle.radius);
		shape.end();
		

	}
}
