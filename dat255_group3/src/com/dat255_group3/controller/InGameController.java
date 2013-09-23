package com.dat255_group3.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dat255_group3.model.InGame;
import com.dat255_group3.view.InGameView;

public class InGameController implements Screen{
	
	private MyGdxGameController myGdxGameController;
	private InGame inGame;
	private InGameView inGameView;
	private WorldController worldController;
	private float timeStep = 1.0f / 60.0f;
	private final int velocityIterations = 6;
	private final int positionIterations = 2;
	
	
	public InGameController(MyGdxGameController myGdxGameController){
		this.myGdxGameController = myGdxGameController;
		this.inGameView = new InGameView();
		this.inGame = new InGame();
		this.worldController = new WorldController(this);
		
		
		
	}
	
	@Override
	public void render(float delta) {
		//show a green screen
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		//uppdate the physics in the world... belongs in a update method?
		if(delta > 0) {
			this.timeStep = (float) delta / 1000f * 4; //4 is for getting a good speed, may change
		}
		this.worldController.getPhysicsWorld().step(this.timeStep, this.velocityIterations, this.positionIterations);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		//show a yellow screen
		Gdx.gl.glClearColor(1, 1, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}	

	public InGame getInGame() {
		return inGame;
	}

	public InGameView getInGameView() {
		return inGameView;
	}


	
}
