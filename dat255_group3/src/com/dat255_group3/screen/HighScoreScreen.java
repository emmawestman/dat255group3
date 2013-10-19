		package com.dat255_group3.screen;

	import java.util.List;

import com.badlogic.gdx.Gdx;
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
import com.dat255_group3.utils.CoordinateConverter;


	/**
	 * A class which represents the view of a won or a lost game containing the time and the score. 
	 * The user is given the options of choosing of continuing to the next level if the game was won 
	 * or to play the same level again if the game was lost.
	 * @author The Hans-Gunnar Crew
	 *
	 */
	public class HighScoreScreen implements Screen{

		private MyGdxGameController myGdxGameController;
		private Stage stage;
		private TextureAtlas atlas;
		private Skin skin;
		private Table table;
		private List <Integer> highScores;
		
//		import aurelienribon.tweenengine.TweenManager;
//		private TweenManager tweenmanager;
		
		
		public HighScoreScreen(MyGdxGameController myGdxGameController, List <Integer> scores, List <Integer> times ){
			this.myGdxGameController = myGdxGameController;
			this.highScores = scores;
			
			this.stage = new Stage(0,0, true);
			Gdx.input.setInputProcessor(stage);
		}
		
		@Override
		public void render(float delta) {
			Gdx.gl.glClearColor(0, 0, 0, 0);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			//Update & draw the stage actors
			stage.act(delta);
			//table.drawDebug(stage); //To be removed later on
			stage.draw();
		}

		@Override
		public void resize(int width, int height) {
			//In order to make it look good not depending on the screensize.
			//stage.setViewport(width, height, true);
			//table.invalidateHierarchy();
			//table.setSize(width,height);
		}

		@Override
		public void show() {		
			//Setting up the atlas, skin & fonts
			atlas = new TextureAtlas(Gdx.files.internal("ui/button.pack"));
			skin = new Skin(atlas);
			
			//Setting up the table
			table = new Table(skin);
	        table.setBounds( 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	        
	        
	        //Setting characteristics for the label
	        LabelStyle headerStyle = new LabelStyle();
	        headerStyle.font =  new BitmapFont(Gdx.files.internal("font/whiteL.fnt"),false);
	        headerStyle.font.scale(1.2f);
	        Label header = new Label("Highscores", headerStyle);
	        
	        LabelStyle scoreNTimeStyle = new LabelStyle();
	        scoreNTimeStyle.font = new BitmapFont(Gdx.files.internal("font/white.fnt"),false);
	        scoreNTimeStyle.font.setScale(1.8f);
	       
	        LabelStyle levelStyle= new LabelStyle();
	        levelStyle.font = new BitmapFont(Gdx.files.internal("font/white.fnt"),false);
	        levelStyle.font.scale(1.1f);
	        
	        Label levelOneLabel = new Label("Level 1", levelStyle);
	        Label levelOneScore = new Label("Score: " + highScores.get(0), scoreNTimeStyle);
	        
	        Label levelTwoLabel = new Label("Level 1", levelStyle);
	        Label levelTwoScore = new Label("Score: " + highScores.get(0), scoreNTimeStyle);
	        
	        
	        
	        //Setting up the characteristics for the buttons
	        TextButtonStyle textButtonStyle = new TextButtonStyle();
	        textButtonStyle.up = skin.getDrawable("button.up");
	        textButtonStyle.down = skin.getDrawable("button.down");
	        textButtonStyle.pressedOffsetX = 1;
	        textButtonStyle.pressedOffsetY = -1;
	        textButtonStyle.font = new BitmapFont(Gdx.files.internal("font/black.fnt"),false);

	        //Instantiating the buttons & setting listeners
	        TextButton returnButton = new TextButton("Return", textButtonStyle);
	        returnButton.pad(20);
	        returnButton.addListener(new ClickListener(){
	        	@Override
				public void clicked (InputEvent event, float x, float y){
	        		myGdxGameController.setScreen(myGdxGameController.getStartScreen());
				}
	        });

	        //Adding to the table and actors to the stage
	        table.add(header);
	        table.getCell(header).spaceBottom(50);
	        table.row();
	        table.add(levelOneLabel);
	        table.getCell(levelOneLabel).spaceBottom(20);
	        table.row();
	        table.add(levelOneScore);
	        table.getCell(levelOneScore).spaceBottom(20);
	        table.row();
	        table.add(levelTwoLabel);
	        table.getCell(levelOneLabel).spaceBottom(20);
	        table.row();
	        table.add(levelTwoScore);
	        table.getCell(levelOneScore).spaceBottom(20);
	        table.row();
	        table.add(returnButton);
	        table.getCell(returnButton).spaceBottom(20);
	        table.row();
	        stage.addActor(table);
	        
	       // table.debug(); //To be removed later on
	        
			//table.invalidateHierarchy();
			table.setSize(CoordinateConverter.getCameraWidth(), CoordinateConverter.getCameraHeight());
			stage.setViewport(CoordinateConverter.getCameraWidth(), CoordinateConverter.getCameraHeight(), true);

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
			Gdx.app.log("Start", "dispose");
			stage.dispose();
			skin.dispose();
			atlas.dispose();
			
		}

	}
