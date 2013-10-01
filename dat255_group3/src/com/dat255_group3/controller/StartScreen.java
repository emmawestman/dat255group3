package com.dat255_group3.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;



public class StartScreen implements Screen{

	private InGameController inGameController;
	private MyGdxGameController myGdxGameController;
	private Stage stage;
	

	public StartScreen(MyGdxGameController myGdxGameController, InGameController inGameController){
		this.myGdxGameController = myGdxGameController;
		this.inGameController = inGameController;
		this.stage = new Stage(0,0, true);
		Gdx.app.log("StartScreen", "end of constructor");
	}
	
	@Override
	public void render(float delta) {
		Gdx.app.log("StartScreen", "render start");
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		Gdx.app.log("Startscreen", "In render");
		
		/*if(Gdx.input.isTouched()){
			myGdxGameController.setScreen(inGameController);
			
		}*/
	
		//Update & draw the stage actors
		stage.act(delta);
		stage.draw();
		Gdx.app.log("StartScreen", "render end");
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log("StartScreen", "resize start");
		stage.setViewport(width, height, true);
		
		Gdx.app.log("StartScreen", "skin start");
		//Fixa med skins senare?
		Skin skin = new Skin();
		Gdx.app.log("StartScreen", "end skin");
		
		Gdx.app.log("StartScreen", "table start");
		//Table
		Table table = new Table();
		table.setWidth(width);
		table.setHeight(height);
		stage.addActor(table);
		
		Gdx.app.log("StartScreen", "table end");
		
		//Fixa med layout senare
		//TableToolkit layout = table.get 
		
		Gdx.app.log("StartScreen", "button start");
		TextButton startGameButton =  new TextButton("Start", skin);
		
		Gdx.app.log("StartScreen", "button add listener");
		startGameButton.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent event, float x, float y){
				Gdx.app.log("In resize", "Listening");
				myGdxGameController.setScreen(inGameController);
			}
			
		});
		
		Gdx.app.log("StartScreen", "resize end");
	}

	@Override
	public void show() {
		Gdx.app.log("StartScreen", "show");
		Gdx.input.setInputProcessor(stage);
		
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
		//stage.dispose();
		
	}

}
