package com.dat255_group3.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dat255_group3.model.Character;

/**  
 * The view for the character. This class handles the character's
 * animation.
 *  
 * @author The Hans-Gunnar Crew
 */
public class CharacterView {

	private static final int FRAMES_COLS = 4;
	private Character character;
	private OrthographicCamera camera;
	private SpriteBatch spriteBatch;
	private Texture walkSheet;
	private TextureRegion[] walkFrames;
	private TextureRegion currentFrame;
	private Animation walkAnimation;

	/**
	 * Constructs a new CharacterView with the specified Character and camera.
	 * 
	 * @param character the Character that is to be animated
	 * @param camera the camera used to display the game
	 */
	public CharacterView (Character character, OrthographicCamera camera) {
		this.character = character;
		this.spriteBatch = new SpriteBatch();
		this.camera = camera;

		//Create animation frames for walking
		walkSheet = new Texture(Gdx.files.internal("ui/CharacterSpritesheet.png"));
		TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAMES_COLS, walkSheet.getHeight());
		walkFrames = new TextureRegion[FRAMES_COLS];
		for(int i = 0;i<FRAMES_COLS;i++) {
			walkFrames[i] = tmp[0][i];
		}

		walkAnimation = new Animation(0.06f, walkFrames);
	}

	/**
	 * Animates the character.
	 * 
	 * @param time the time that's being updated in the game
	 */
	public void draw(double time){
		camera.update();
		spriteBatch.begin();
		spriteBatch.setProjectionMatrix(camera.combined);
		currentFrame = walkAnimation.getKeyFrame((float)time, true);
		spriteBatch.draw(currentFrame, character.getPosition().x-Character.getRadius(), character.getPosition().y-Character.getRadius());
		spriteBatch.end();
	}

	public Character getCharacter() {
		return character;
	}
}
