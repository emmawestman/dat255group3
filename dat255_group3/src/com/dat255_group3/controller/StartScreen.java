package com.dat255_group3.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	private SpriteBatch batch;
	private Texture pic;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;
	private BitmapFont white;
	

	public StartScreen(MyGdxGameController myGdxGameController, InGameController inGameController){
		this.myGdxGameController = myGdxGameController;
		this.inGameController = inGameController;
		this.stage = new Stage(0,0, true);
		this.batch = new SpriteBatch();
		//this.pic = new Texture(Gdx.files.internal("start.png"));
		Gdx.app.log("StartScreen", "end of constructor");
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		/*if(Gdx.input.isTouched()){
			myGdxGameController.setScreen(inGameController);
			
		}*/
	
		//Update & draw the stage actors
		stage.act(delta);
		//table.drawDebug(stage);
		//batch.begin();
		//batch.draw(pic, pic.getWidth(), pic.getHeight());
		stage.draw();
		//batch.end();
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
		
		
		//Setting up the atlas, skin & font
		atlas = new TextureAtlas(Gdx.files.internal("ui/button.pack"));
		skin = new Skin(atlas);
		white = new BitmapFont(Gdx.files.internal("font/white.fnt"),false);
		
		//Setting up the table
		table = new Table(skin);
        table.setBounds( 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        //Setting up the characteristics for the button
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button.up");
        textButtonStyle.down = skin.getDrawable("button.down");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = white;

        //Instantiating the button
        TextButton startButton = new TextButton("Start", textButtonStyle);
        startButton.pad(20);
        startButton.addListener(new ClickListener(){
        	@Override
			public void clicked (InputEvent event, float x, float y){
				Gdx.app.log("In resize", "Listening");
				myGdxGameController.setScreen(inGameController);
			}
        });
        
        //Setting characteristics for the label
        Gdx.app.log("Startscreen", "label");
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = white;
        Label label = new Label("CookieGame",labelStyle);
        
        //Adding to the table and actors to the stage
        table.add(label);
        table.row();
       // table.debug();
        table.add(startButton);
        stage.addActor(table);
  
        
        // creating buttons
     /*   TextButton buttonPlay = new TextButton("PLAY", skin, "big");
        buttonPlay.addListener(new ClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                        stage.addAction(sequence(moveTo(0, -stage.getHeight(), .5f), run(new Runnable() {

                                @Override
                                public void run() {
                                	myGdxGameController.setScreen(inGameController);
                                }
                        })));
                }
        });
        buttonPlay.pad(15);*/	
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
