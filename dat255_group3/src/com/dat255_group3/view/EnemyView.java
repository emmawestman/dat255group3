package com.dat255_group3.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class EnemyView {
	
	private static final int FRAME_COLS = 4;
	private OrthographicCamera camera;
	private Texture walkSheet;
	private TextureRegion[] walkFrames;
	private TextureRegion currentFrame;
	private Animation walkAnimation;
	private SpriteBatch spriteBatch;
	
	public EnemyView(OrthographicCamera camera) {
		
		this.walkSheet = new Texture(Gdx.files.internal("ui/tornado_128x4x512.png"));
		this.camera = camera;
		this.spriteBatch = new SpriteBatch();
		
		//Create frames
		TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight());
		walkFrames = new TextureRegion[FRAME_COLS];
		for(int i = 0;i<FRAME_COLS;i++) {
			walkFrames[i] = tmp[0][i];
		}
		
		walkAnimation = new Animation(0.06f, walkFrames);
	}
	
	public void draw(double time, float posX){
		camera.update();
		spriteBatch.setProjectionMatrix(camera.combined);
		currentFrame = walkAnimation.getKeyFrame((float)time, true);
		spriteBatch.begin();
		spriteBatch.draw(currentFrame, posX-70, 0);
		spriteBatch.end();
	}

}
