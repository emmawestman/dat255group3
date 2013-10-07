package com.dat255_group3.view;

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


/**
 * A class which represents the view of a won or a lost game containing the time and the score. 
 * The user is given the options of choosing of continuing to the next level if the game was won 
 * or to play the same level again if the game was lost.
 * @author The Hans-Gunnar Crew
 *
 */
public class GameOverScreen implements Screen{

	private MyGdxGameController myGdxGameController;
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;
	private int score;
	private int time;
	private boolean hasWon;
	
//	import aurelienribon.tweenengine.TweenManager;
//	private TweenManager tweenmanager;
	
	
	public GameOverScreen(MyGdxGameController myGdxGameController, int score, int time, boolean hasWon){
		this.myGdxGameController = myGdxGameController;
		this.score = score;
		this.time = time;
		this.hasWon = hasWon;
		this.stage = new Stage(0,0, true);
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
		
		//Setting up the table
		table = new Table(skin);
        table.setBounds( 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        
        //Setting characteristics for the label
        LabelStyle headerStyle = new LabelStyle();
        headerStyle.font =  new BitmapFont(Gdx.files.internal("font/whiteL.fnt"),false);
        headerStyle.font.scale(1.2f);
        Label header = new Label("", headerStyle);
        
        LabelStyle buttonLabelStyle = new LabelStyle();
        buttonLabelStyle.font = new BitmapFont(Gdx.files.internal("font/white.fnt"),false);
        buttonLabelStyle.font.scale(1.1f);
        Label buttonLabel = new Label("", buttonLabelStyle);
        
       
        LabelStyle scoreNTimeStyle = new LabelStyle();
        scoreNTimeStyle.font = new BitmapFont(Gdx.files.internal("font/white.fnt"),false);
        scoreNTimeStyle.font.setScale(1.8f);
        Label score = new Label("Score: " + this.score, scoreNTimeStyle);
        Label time = new Label("Time: " + this.time, scoreNTimeStyle);
        
        
        //Setting the texts of the labels depending on whether the game was won or lost
        if(hasWon){
        	header.setText("Congratulations, you have won!");
        	buttonLabel.setText("Would you like to proceed to the next level?");
        } else {
        	header.setText("Game over");
        	buttonLabel.setText("Would you like to retry?");
        }
        
        //Setting up the characteristics for the buttons
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button.up");
        textButtonStyle.down = skin.getDrawable("button.down");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = new BitmapFont(Gdx.files.internal("font/black.fnt"),false);

        //Instantiating the buttons & setting listeners
        TextButton levelButton = new TextButton("Yes", textButtonStyle);
        levelButton.pad(20);
        levelButton.addListener(new ClickListener(){
        	@Override
			public void clicked (InputEvent event, float x, float y){
        		//To be implemented.
        		
        		if(hasWon){
        			//Next level
        		} else {
        			//This level
        			//myGdxGameController.setScreen(myGdxGameController.getInGameController());
        		}
        		
			}
        });
        
        TextButton mainMenuButton = new TextButton("No", textButtonStyle);
        mainMenuButton.pad(20);
        mainMenuButton.addListener(new ClickListener(){
        	@Override
			public void clicked (InputEvent event, float x, float y){
        		myGdxGameController.setScreen(new StartScreen(myGdxGameController));
			}
        });
        
        //Adding to the table and actors to the stage
        table.add(header);
        table.getCell(header).spaceBottom(50);
        table.row();
        table.add(score);
        table.getCell(score).spaceBottom(50);
        table.row();
        table.add(time);
        table.getCell(time).spaceBottom(50);
        table.row();
        table.add(buttonLabel);
        table.getCell(buttonLabel).spaceBottom(50);
        table.row();
        table.add(levelButton);
        table.getCell(levelButton).spaceBottom(50);
        table.row();
        table.add(mainMenuButton);
        table.getCell(mainMenuButton).spaceBottom(50);
        stage.addActor(table);
        
       // table.debug(); //To be removed later on
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

