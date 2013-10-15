package com.dat255_group3.screen;

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
		stage.setViewport(width, height, true);
		table.invalidateHierarchy();
		table.setSize(width, height);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		// Setting up the atlas, skin & fonts
		atlas = new TextureAtlas(Gdx.files.internal("ui/button.pack"));
		skin = new Skin(atlas);

		// Setting up the table
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// Setting characteristics for the label
		LabelStyle headerStyle = new LabelStyle();
		headerStyle.font = new BitmapFont(
				Gdx.files.internal("font/whiteL.fnt"), false);
		headerStyle.font.scale(1.2f);
		Label header = new Label("", headerStyle);

		LabelStyle buttonLabelStyle = new LabelStyle();
		buttonLabelStyle.font = new BitmapFont(
				Gdx.files.internal("font/white.fnt"), false);
		buttonLabelStyle.font.scale(1.1f);
		Label buttonLabel = new Label("", buttonLabelStyle);

		LabelStyle scoreNTimeStyle = new LabelStyle();
		scoreNTimeStyle.font = new BitmapFont(
				Gdx.files.internal("font/white.fnt"), false);
		scoreNTimeStyle.font.setScale(1.8f);
		Label timeLabel = new Label("Time: " + this.time, scoreNTimeStyle);
		Label scoreLabel = new Label("Score: " + this.score, scoreNTimeStyle);
		Label highScoreLabel = new Label("High Score: " + IOHandler.getScore(myGdxGameController.getMyGdxGame().getCurrentLevel()), scoreNTimeStyle);
		
		

		// Setting the texts of the labels depending on whether the game was won
		// or lost
		if (!gameOver) {
			header.setText("Congratulations, you have won!");
			buttonLabel.setText("Would you like to proceed to the next level?");
		} else {
			header.setText("Game over");
			buttonLabel.setText("Would you like to retry?");
		}

		// Setting up the characteristics for the buttons
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("button.up");
		textButtonStyle.down = skin.getDrawable("button.down");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = new BitmapFont(
				Gdx.files.internal("font/black.fnt"), false);

		// Instantiating the buttons & setting listeners
		TextButton levelButton = new TextButton("Yes", textButtonStyle);
		levelButton.pad(20);
		levelButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// Go to next level
				if (!gameOver) {
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
				} else {
					// This level
					myGdxGameController.setScreen(myGdxGameController
							.getInGameController());
				}

			}
		});

		TextButton mainMenuButton = new TextButton("No", textButtonStyle);
		mainMenuButton.pad(20);
		mainMenuButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				myGdxGameController.setScreen(myGdxGameController
						.getStartScreen());
			}
		});

		// Adding to the table and actors to the stage
		table.add(header);
		table.getCell(header).spaceBottom(50);
		table.row();
		table.add(timeLabel);
		table.getCell(timeLabel).spaceBottom(50);
		table.row();
		table.add(scoreLabel);
		table.getCell(scoreLabel).spaceBottom(50);
		table.row();
		table.add(highScoreLabel);
		table.getCell(highScoreLabel).spaceBottom(50);
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
		stage.dispose();
		skin.dispose();
		atlas.dispose();

	}

	public void gameOver(int score, double time, boolean gameOver) {
		this.score = score;
		this.time = (double) ((int) (time * 100)) / 100;
		this.gameOver = gameOver;
	}

}