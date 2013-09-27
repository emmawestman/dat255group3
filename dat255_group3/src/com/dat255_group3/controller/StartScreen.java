package com.dat255_group3.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class StartScreen implements Screen{

	private InGameController inGameController;
	private MyGdxGameController myGdxGameController;
	

	public StartScreen(MyGdxGameController myGdxGameController, InGameController inGameController){
		this.myGdxGameController = myGdxGameController;
		this.inGameController = inGameController;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		Gdx.app.log("Startscreen", "In render");
		
		if(Gdx.input.isTouched()){
			myGdxGameController.setScreen(inGameController);
			
		}
		
		
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
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

}
