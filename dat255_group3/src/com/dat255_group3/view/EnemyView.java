package com.dat255_group3.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * The view which animates the enemy at the left side of the display.
 * 
 * @author The Hans-Gunnar Crew
 */

public class EnemyView {

	private static final int FRAME_COLS = 4;
	private OrthographicCamera camera;
	private Texture walkSheet;
	private TextureRegion[] walkFrames;
	private TextureRegion currentFrame;
	private Animation walkAnimation;
	private SpriteBatch spriteBatch;

	/**
	 * Constructs a new EnemyView with the specified camera.
	 * 
	 * @param camera the camera that is used to display the game
	 */
	public EnemyView(OrthographicCamera camera) {

		this.walkSheet = new Texture(Gdx.files.internal("ui/tornado_128x4x512.png"));
		this.camera = camera;
		this.spriteBatch = new SpriteBatch();

		//Create frames for animation
		TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight());
		walkFrames = new TextureRegion[FRAME_COLS];
		for(int i = 0;i<FRAME_COLS;i++) {
			walkFrames[i] = tmp[0][i];
		}

		walkAnimation = new Animation(0.06f, walkFrames);
	}

	/**
	 * Animates the enemy.
	 * 
	 * @param time the time that's being updated in the game
	 * @param deathLimit the x-coordinate which when reached, the game is lost,
	 * 			and which is being moved at the same speed as the camera
	 */
	public void draw(double time, float deathLimit){
		camera.update();
		spriteBatch.setProjectionMatrix(camera.combined);
		currentFrame = walkAnimation.getKeyFrame((float)time, true);
		spriteBatch.begin();
		spriteBatch.draw(currentFrame, deathLimit-70, 0); //-70 so that the enemy is drawn behind the deathLimit
		spriteBatch.end();
	}
}
