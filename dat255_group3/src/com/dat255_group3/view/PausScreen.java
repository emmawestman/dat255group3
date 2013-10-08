package com.dat255_group3.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
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
import com.dat255_group3.controller.MyGdxGameController;

/**
 * A class which represents the screen being shown when the game is paused.
 * @author The Hans-Gunnar Crew
 */
public class PausScreen implements Screen {

			private MyGdxGameController myGdxGameController;
			private Stage stage;
			private TextureAtlas atlas;
			private Skin skin;
			private Table table;
			private BitmapFont black;
			private BitmapFont white;
			

			public PausScreen(MyGdxGameController myGdxGameController){
				this.myGdxGameController = myGdxGameController;
				this.stage = new Stage(0,0, true);
			}
			
			@Override
			public void render(float delta) {
				Gdx.gl.glClearColor(0, 0, 0, 0);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
				if (Gdx.input.isKeyPressed(Keys.BACK)){
					Gdx.input.setCatchBackKey(true);
					  myGdxGameController.setScreen(myGdxGameController.getStartScreen());
				}
				
				//Update & draw the stage actors
				stage.act(delta);
				//table.drawDebug(stage); //To be removed later on
				stage.draw();
			}

			@Override
			public void resize(int width, int height) {
				//In order to make it look good not depending on the screen.
				stage.setViewport(width, height, true);
				table.invalidateHierarchy();
				table.setSize(width,height);
			}

			@Override
			public void show() {
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
		        LabelStyle labelStyle = new LabelStyle();
		        labelStyle.font = white;
		        Label header = new Label("Paused",labelStyle);
		        
		        //Setting up the characteristics for the button
		        TextButtonStyle textButtonStyle = new TextButtonStyle();
		        textButtonStyle.up = skin.getDrawable("button.up");
		        textButtonStyle.down = skin.getDrawable("button.down");
		        textButtonStyle.pressedOffsetX = 1;
		        textButtonStyle.pressedOffsetY = -1;
		        textButtonStyle.font = black;

		        //Instantiating the button
		        TextButton resumeButton = new TextButton("Resume", textButtonStyle);
		        resumeButton.pad(20);
		        resumeButton.addListener(new ClickListener(){
		        	@Override
					public void clicked (InputEvent event, float x, float y){
						myGdxGameController.setScreen(myGdxGameController.getInGameController());
					}
		        });
		        
		        
		        //Adding to the table and actors to the stage
		        table.add(header);
		        table.getCell(header).spaceBottom(50);
		        table.row();
		        table.add(resumeButton);
		        table.getCell(resumeButton).spaceBottom(50);
		        table.row();
		        stage.addActor(table);
		        
		       //table.debug(); //To be removed later on
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
