package com.dat255_group3.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;



public class StartScreen implements Screen{

	private InGameController inGameController;
	private MyGdxGameController myGdxGameController;
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;
	private BitmapFont black;
	private BitmapFont white;
	

	public StartScreen(MyGdxGameController myGdxGameController, InGameController inGameController){
		this.myGdxGameController = myGdxGameController;
		this.inGameController = inGameController;
		this.stage = new Stage(0,0, true);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
		//Update & draw the stage actors
		stage.act(delta);
		table.drawDebug(stage); //To be removed later on
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		Gdx.app.log("StartScreen", "showMethod");
		
		//Setting up the stage
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		
		//Setting up the atlas, skin & fonts
		atlas = new TextureAtlas(Gdx.files.internal("ui/button.pack"));
		skin = new Skin(atlas);
		black = new BitmapFont(Gdx.files.internal("font/black.fnt"),false);
		white = new BitmapFont(Gdx.files.internal("font/whiteL.fnt"),false);
		white.scale(1.2f);
		
		//Setting up the table
		table = new Table(skin);
        table.setBounds( 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        
        //Setting characteristics for the label
        Gdx.app.log("Startscreen", "label");
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = white;
        Label label = new Label("CookieGame",labelStyle);
        
        //Setting up the characteristics for the button
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button.up");
        textButtonStyle.down = skin.getDrawable("button.down");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = black;

        //Instantiating the button
        TextButton startButton = new TextButton("Start", textButtonStyle);
        startButton.pad(20);
        startButton.addListener(new ClickListener(){
        	@Override
			public void clicked (InputEvent event, float x, float y){
				myGdxGameController.setScreen(inGameController);
			}
        });
        
        //Instantiating the button
        TextButton exitButton = new TextButton("Exit", textButtonStyle);
        exitButton.pad(20);
        exitButton.addListener(new ClickListener(){
        	@Override
			public void clicked (InputEvent event, float x, float y){
				Gdx.app.log("screen", "exit");
				Gdx.app.exit();
			}
        });
        
        //Adding to the table and actors to the stage
        table.add(label);
        table.getCell(label).spaceBottom(50);
        table.row();
        table.add(startButton);
        table.getCell(startButton).spaceBottom(50);
        table.row();
        table.add(exitButton);
        table.row();
        stage.addActor(table);
        
        table.debug(); //To be removed later on
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
		stage.dispose();
		skin.dispose();
		atlas.dispose();
		black.dispose();
		
	}

}
