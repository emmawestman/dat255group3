package com.dat255_group3.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	
	private Character character;
	private OrthographicCamera camera;
	private SpriteBatch spriteBatch;
	private Sprite sprite;
	private Texture texture;
	
	/** A constructor that takes a character class.
	 * 
	 * @param character
	 */
	public CharacterView (Character character, OrthographicCamera camera) {
		this.character = character;
		this.spriteBatch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("ui/characterStandingStill.png"));
		sprite = new Sprite(texture);
		sprite.setSize(Character.getRadius()*2, Character.getRadius()*2);
		this.camera = camera;
	}
	
	
	/**
	 * A method which draws the character (which is currently just a turquoise rectangle)
	 * The Characters appearance will change with time. This is only to test.
	 */
	public void draw(){
		camera.update();
		spriteBatch.begin();
		spriteBatch.setProjectionMatrix(camera.combined);
		sprite.setPosition(character.getPosition().x-Character.getRadius(), character.getPosition().y-Character.getRadius());
		sprite.draw(spriteBatch);
		spriteBatch.end();
	}
}
