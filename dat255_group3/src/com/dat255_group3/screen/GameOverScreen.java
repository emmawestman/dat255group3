package com.dat255_group3.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.dat255_group3.controller.MyGdxGameController;
import com.dat255_group3.io.IOHandler;
import com.dat255_group3.utils.CoordinateConverter;

/**
 * A class which represents the view of a won or a lost game containing the time
 * and the score. The user is given the options of choosing of continuing to the
 * next level if the game was won or to play the same level again if the game
 * was lost.
 * 
 * @author The Hans-Gunnar Crew
 * 
 */
public class GameOverScreen implements Screen {

	private MyGdxGameController myGdxGameController;
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;
	private int score;
	private double time;
	private boolean gameOver;

	// import aurelienribon.tweenengine.TweenManager;
	// private TweenManager tweenmanager;

	public GameOverScreen(MyGdxGameController myGdxGameController) {
		this.myGdxGameController = myGdxGameController;
		this.stage = new Stage(CoordinateConverter.getCameraWidth(),
				CoordinateConverter.getCameraWidth(), true);
		// Setting up the stage
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Update & draw the stage actors
		stage.act(delta);
		// table.drawDebug(stage); //To be removed later on
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// In order to make it look good not depending on the screensize.
		//stage.setViewport(width, height, true);
		//table.invalidateHierarchy();
		//table.setSize(width, height);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		// Setting up the atlas, skin & fonts
		atlas = new TextureAtlas(Gdx.files.internal("menuIcons/rectMenuIcon.pack"));
		//Update atlas with the new images!
		
		
		skin = new Skin(atlas);

		// Setting up the table
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// Setting characteristics for the label
		LabelStyle headerStyle = new LabelStyle();
		headerStyle.font = new BitmapFont(
				Gdx.files.internal("font/whiteL.fnt"), false);
		headerStyle.font.scale(0.6f); //<------------------------------------------------
		Label header = new Label("", headerStyle);

		LabelStyle buttonLabelStyle = new LabelStyle();
		buttonLabelStyle.font = new BitmapFont(
				Gdx.files.internal("font/white.fnt"), false);
		buttonLabelStyle.font.scale(0.5f);
		Label buttonLabel = new Label("", buttonLabelStyle);

		LabelStyle scoreNTimeStyle = new LabelStyle();
		scoreNTimeStyle.font = new BitmapFont(
				Gdx.files.internal("font/white.fnt"), false);
		scoreNTimeStyle.font.setScale(0.9f);
		Label timeLabel = new Label("Time: " + this.time, scoreNTimeStyle);
		Label scoreLabel = new Label("Score: " + this.score, scoreNTimeStyle);
		Label highScoreLabel = new Label("High Score: " + IOHandler.getScore(myGdxGameController.getMyGdxGame().getCurrentLevel()), scoreNTimeStyle);
		
		

		// Setting the texts of the labels depending on whether the game was won
		// or lost
		if (!gameOver) {
			header.setText("Congratulations!");
		} else {
			header.setText("Game over");
		}
		
		ImageButtonStyle retryButtonStyle = new ImageButtonStyle();
		retryButtonStyle.up = skin.getDrawable("restart.up");
		retryButtonStyle.down = skin.getDrawable("restart.down");
		retryButtonStyle.pressedOffsetX = 1;
		retryButtonStyle.pressedOffsetY = -1;
		
		ImageButton retryButton = new ImageButton(retryButtonStyle);
		retryButton.pad(20);
		retryButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
					// This level
					myGdxGameController.setScreen(myGdxGameController
							.getInGameController());

			}
		});
		
		
		
		ImageButtonStyle nextLevelButtonStyle = new ImageButtonStyle();
		nextLevelButtonStyle.up = skin.getDrawable("next.up");
		nextLevelButtonStyle.down = skin.getDrawable("next.down");
		nextLevelButtonStyle.pressedOffsetX = 1;
		nextLevelButtonStyle.pressedOffsetY = -1;
		
		ImageButton nextLevelButton = new ImageButton(nextLevelButtonStyle);
		nextLevelButton.pad(20);
		nextLevelButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// Go to next level
					int nextLevel = myGdxGameController.getMyGdxGame()
							.getCurrentLevel() + 1;
					if (nextLevel < 3) {
						myGdxGameController.getMyGdxGame().setCurrentLevel(
								nextLevel);
						myGdxGameController.getInGameController().loadMap();
						myGdxGameController.setScreen(myGdxGameController
								.getInGameController());
					} else {
						myGdxGameController.setScreen(myGdxGameController
								.getUnlockedScreen());
					} 

			}
		});
		

		ImageButtonStyle homeButtonStyle = new ImageButtonStyle();
		homeButtonStyle.up = skin.getDrawable("homeRect.up");
		homeButtonStyle.down = skin.getDrawable("homeRect.down");
		homeButtonStyle.pressedOffsetX = 1;
		homeButtonStyle.pressedOffsetY = -1;
		
		ImageButton homeButton = new ImageButton(homeButtonStyle);
		homeButton.pad(20);
		homeButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				myGdxGameController.setScreen(myGdxGameController
						.getStartScreen());
			}
		});

		// Adding to the table and actors to the stage
		table.add(header);
		table.getCell(header).spaceBottom(5);
		table.row();
		table.add(timeLabel);
		table.getCell(timeLabel).spaceBottom(5);
		table.row();
		table.add(scoreLabel);
		table.getCell(scoreLabel).spaceBottom(5);
		table.row();
		table.add(highScoreLabel);
		table.getCell(highScoreLabel).spaceBottom(5);
		table.row();
		table.add(buttonLabel);
		table.getCell(buttonLabel).spaceBottom(5);
		table.row();
		table.add(retryButton);
		table.getCell(retryButton).spaceBottom(5);
		table.row();
		if (!gameOver) {
			table.add(nextLevelButton);
		}
		table.add(homeButton);
		table.getCell(homeButton).spaceBottom(5);
		stage.addActor(table);
		
		table.setSize(CoordinateConverter.getCameraWidth(), CoordinateConverter.getCameraHeight());
		stage.setViewport(CoordinateConverter.getCameraWidth(), CoordinateConverter.getCameraHeight(), true);
		

		// table.debug(); //To be removed later on
	}

	@Override
	public void hide() {
		table.clear();
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
		
		try{
			stage.dispose();
			skin.dispose();
			atlas.dispose();
		} catch (GdxRuntimeException e){
			Gdx.app.log("IOHandler", "Exception", e);
		}catch (Exception e) {			
		}
	}

	public void gameOver(int score, double time, boolean gameOver) {
		this.score = score;
		this.time = (double) ((int) (time * 100)) / 100;
		this.gameOver = gameOver;
	}

}