package com.dat255_group3.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;



public class StartScreen implements Screen{

	private InGameController inGameController;
	private MyGdxGameController myGdxGameController;
	private Stage stage;
	private SpriteBatch batch;
	

	public StartScreen(MyGdxGameController myGdxGameController, InGameController inGameController){
		this.myGdxGameController = myGdxGameController;
		this.inGameController = inGameController;
		this.stage = new Stage(0,0, true);
		Gdx.app.log("StartScreen", "end of constructor");
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		/*if(Gdx.input.isTouched()){
			myGdxGameController.setScreen(inGameController);
			
		}*/
	
		//Update & draw the stage actors
		stage.act(delta);
		batch.begin();
		stage.draw();
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log("StartScreen", "resize start");
		stage.setViewport(width, height, true);
		Gdx.app.log("StartScreen", "setViewport done");
		Gdx.app.log("StartScreen", "skin start");
		//Fixa med skins senare?
		// Initialize skin
		/*Gdx.app.log("StartScreen", "atlas start");
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("start.png"));
		Gdx.app.log("StartScreen", "atlas end");
		Skin skin = new Skin(Gdx.files.internal("skins/buttons.json"));
		Skin skin = new Skin();
		Gdx.app.log("StartScreen", "skin construct");
		Gdx.app.log("StartScreen", "add atlas to skin");
		skin.addRegions(atlas);
	
		Gdx.app.log("StartScreen", "end skin");*/
		
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
		try {
			
			//NinePatch patch = new NinePatch(new Texture(Gdx.files.internal("start.png")), 12, 12, 12, 12);
			TextButtonStyle style = new TextButtonStyle();
			style.font = new BitmapFont();
			Gdx.app.log("StartScreen", "style font");
			style.checkedFontColor = Color.CYAN;
			Gdx.app.log("StartScreen", "style color");
			Gdx.app.log("StartScreen", "style end");	
			TextButton startGameButton =  new TextButton("Start", style);
			Gdx.app.log("StartScreen", "button x & y");
			startGameButton.setX(500f);
		    startGameButton.setY(280f);
		    startGameButton.setWidth(300f);
		    startGameButton.setHeight(60f);
			Gdx.app.log("StartScreen", "button instanciated");	
			//startGameButton.add("Start", "Comic sans ms", Color.BLACK);
			Gdx.app.log("StartScreen", "button added features");	
			Gdx.app.log("StartScreen", "button add listener");
			startGameButton.addListener(new ClickListener() {
				@Override
				public void clicked (InputEvent event, float x, float y){
					Gdx.app.log("In resize", "Listening");
					myGdxGameController.setScreen(inGameController);
				}
				
			});
		} catch (Exception e){
			Gdx.app.log("StartScreen", "button ex", e);
		}
		
		
		// layout.register( "startGameButton", startGameButton );
		Gdx.app.log("StartScreen", "resize end");
		
		
		//parse the layout text
		// layout.parse( Gdx.files.internal( "data/menuscreen.txt" ).readString() );
	}

	@Override
	public void show() {
		Gdx.app.log("StartScreen", "show");
		Gdx.input.setInputProcessor(stage);
		batch = new SpriteBatch();
		
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
