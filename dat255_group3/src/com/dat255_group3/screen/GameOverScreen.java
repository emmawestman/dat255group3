package com.dat255_group3.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.dat255_group3.controller.MyGdxGameController;
import com.dat255_group3.io.IOHandler;
import com.dat255_group3.utils.CoordinateConverter;
import com.dat255_group3.utils.InputStage;
import com.dat255_group3.utils.InputStage.OnHardKeyListener;

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
	private InputStage stage;
	private Texture titleTexture;
	private Image titleImage;
	private int score;
	private double time;
	private boolean gameOver;

	public GameOverScreen(MyGdxGameController myGdxGameController) {
		this.myGdxGameController = myGdxGameController;
		this.stage = new InputStage(CoordinateConverter.getCameraWidth(),
				CoordinateConverter.getCameraWidth(), true);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		myGdxGameController.getScreenUtils().drawBackgroundImage();

		// Update & draw the stage actors
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);

		// Checks if the back-key has been pressed & if so, the level screen
		// will be shown
		stage.setHardKeyListener(new OnHardKeyListener() {
			@Override
			public void onHardKey(int keyCode, int state) {
				if (keyCode == Keys.BACK && state == 1) {
					myGdxGameController.setScreen(myGdxGameController
							.getLevelScreen());
				}
			}
		});

		// Setting up the table
		Table table = new Table();
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		LabelStyle scoreNTimeStyle = new LabelStyle();
		scoreNTimeStyle.font = new BitmapFont(
				Gdx.files.internal("font/white.fnt"), false);
		scoreNTimeStyle.font.setScale(1.5f);
		Label timeLabel = new Label("Time: " + this.time, scoreNTimeStyle);
		Label scoreLabel = new Label("Score: " + this.score, scoreNTimeStyle);
		Label highScoreLabel = new Label("High Score: "
				+ IOHandler.getScore(myGdxGameController.getMyGdxGame()
						.getCurrentLevel()), scoreNTimeStyle);

		// Setting the texts of the labels depending on whether the game was won
		// or lost
		if (!gameOver) {
			try {
				titleTexture = new Texture(
						Gdx.files.internal("ui/congratulationsTitle.png"));
			} catch (Exception e) {
			}
		} else {
			try {
				titleTexture = new Texture(
						Gdx.files.internal("ui/gameOverTitle.png"));
			} catch (Exception e) {
			}
		}

		ImageButtonStyle retryButtonStyle = new ImageButtonStyle();
		retryButtonStyle.up = myGdxGameController.getScreenUtils()
				.getRectangularSkin().getDrawable("restart.up");
		retryButtonStyle.down = myGdxGameController.getScreenUtils()
				.getRectangularSkin().getDrawable("restart.down");
		retryButtonStyle.pressedOffsetX = 1;
		retryButtonStyle.pressedOffsetY = -1;

		ImageButton retryButton = new ImageButton(retryButtonStyle);
		retryButton.pad(20);
		if (!gameOver) {
			retryButton.setPosition(300, 80);
		} else {
			retryButton.setPosition(430, 80);
		}

		retryButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				myGdxGameController.setScreen(myGdxGameController
						.getInGameController());

			}
		});

		ImageButtonStyle nextLevelButtonStyle = new ImageButtonStyle();
		nextLevelButtonStyle.up = myGdxGameController.getScreenUtils()
				.getRectangularSkin().getDrawable("next.up");
		nextLevelButtonStyle.down = myGdxGameController.getScreenUtils()
				.getRectangularSkin().getDrawable("next.down");
		nextLevelButtonStyle.pressedOffsetX = 1;
		nextLevelButtonStyle.pressedOffsetY = -1;

		ImageButton nextLevelButton = new ImageButton(nextLevelButtonStyle);
		nextLevelButton.pad(20);
		nextLevelButton.setPosition(570, 80);
		nextLevelButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				int nextLevel = myGdxGameController.getMyGdxGame()
						.getCurrentLevel() + 1;
				if (nextLevel <= myGdxGameController.getMyGdxGame()
						.getNumberOfLevels()) {
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
		homeButtonStyle.up = myGdxGameController.getScreenUtils()
				.getCircularSkin().getDrawable("home.up");
		homeButtonStyle.down = myGdxGameController.getScreenUtils()
				.getCircularSkin().getDrawable("home.down");
		homeButtonStyle.pressedOffsetX = 1;
		homeButtonStyle.pressedOffsetY = -1;

		ImageButton homeButton = new ImageButton(homeButtonStyle);
		homeButton.pad(20);
		homeButton.setPosition(CoordinateConverter.getCameraWidth() - 120, 30);
		homeButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				myGdxGameController.setScreen(myGdxGameController
						.getStartScreen());
			}
		});

		// Adding to the table and actors to the stage
		table.add(timeLabel);
		table.getCell(timeLabel).spaceBottom(5);
		table.row();
		table.add(scoreLabel);
		table.getCell(scoreLabel).spaceBottom(5);
		table.row();
		table.add(highScoreLabel);
		table.getCell(highScoreLabel).spaceBottom(5);

		table.setFillParent(true);
		stage.addActor(table);
		titleImage = new Image(titleTexture);
		titleImage.setPosition(265, 400);
		stage.addActor(titleImage);
		stage.addActor(homeButton);
		if (!gameOver) {
			stage.addActor(nextLevelButton);
		}
		stage.addActor(retryButton);

		table.setSize(CoordinateConverter.getCameraWidth(),
				CoordinateConverter.getCameraHeight());
		stage.setViewport(CoordinateConverter.getCameraWidth(),
				CoordinateConverter.getCameraHeight(), true);

		// table.debug(); //To be removed later on
	}

	@Override
	public void hide() {
		stage.clear();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {

		try {
			stage.dispose();
		} catch (GdxRuntimeException e) {
			Gdx.app.log("GameOverScreen", "Exception", e);
		} catch (Exception e) {
		}
	}

	public void gameOver(int score, double time, boolean gameOver) {
		this.score = score;
		this.time = (double) ((int) (time * 100)) / 100;
		this.gameOver = gameOver;
	}

}