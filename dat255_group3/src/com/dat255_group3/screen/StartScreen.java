package com.dat255_group3.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.dat255_group3.controller.OneMoreCookiePleaseController;
import com.dat255_group3.io.IOHandler;
import com.dat255_group3.utils.CoordinateConverter;
import com.dat255_group3.utils.InputStage;
import com.dat255_group3.utils.InputStage.OnHardKeyListener;

/**
 * A start menu to the game. The user is given the option to start/exit
 * the game, and turn the sound and music on/off.
 * 
 * @author The Hans-Gunnar Crew
 */
public class StartScreen implements Screen {

	private OneMoreCookiePleaseController oneMoreCookiePleaseController;
	private InputStage stage;
	private Texture imageTexture;

	/**
	 * Constructs a new StartScreen with the specified OneMoreCookiePleaseController.
	 * 
	 * @param oneMoreCookiePleaseController the game's OneMoreCookiePleaseController object
	 */
	public StartScreen(OneMoreCookiePleaseController oneMoreCookiePleaseController) {
		this.oneMoreCookiePleaseController = oneMoreCookiePleaseController;
		this.stage = new InputStage(CoordinateConverter.getCameraWidth(),
				CoordinateConverter.getCameraHeight(), true);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		oneMoreCookiePleaseController.getScreenUtils().drawBackgroundImage();

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
		oneMoreCookiePleaseController.getOneMoreCookiePlease().setIsGameStarted(false);

		// Checks if the back-key has been pressed & if so, confirming screen to
		// exit the application will be shown
		stage.setHardKeyListener(new OnHardKeyListener() {
			@Override
			public void onHardKey(int keyCode, int state) {
				if (keyCode == Keys.BACK && state == 1) {
					oneMoreCookiePleaseController.setScreen(oneMoreCookiePleaseController
							.getExitPopUpScreen());
				}
			}
		});

		// Setting the image for the title of the game
		try {
		} catch (GdxRuntimeException e) {
			Gdx.app.log("StartScreen", "Exception", e);
		} catch (Exception e) {
		}
		Image image = oneMoreCookiePleaseController.getScreenUtils().getGameTitleImage();

		// Setting up the layout table
		Table table = new Table();
		table.setBounds(0, 0, CoordinateConverter.getCameraWidth(),
				CoordinateConverter.getCameraHeight());

		ImageButtonStyle startButtonStyle = new ImageButtonStyle();
		startButtonStyle.up = oneMoreCookiePleaseController.getScreenUtils()
				.getRectangularSkin().getDrawable("start.up");
		startButtonStyle.down = oneMoreCookiePleaseController.getScreenUtils()
				.getRectangularSkin().getDrawable("start.down");
		startButtonStyle.pressedOffsetX = 1;
		startButtonStyle.pressedOffsetY = -1;

		// Setting buttons & listeners with the options of starting the game or
		// to quit
		ImageButton startButton = new ImageButton(startButtonStyle);
		startButton.pad(20);
		startButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				oneMoreCookiePleaseController.getOneMoreCookiePlease().setIsGameStarted(false);
				oneMoreCookiePleaseController.setScreen(new LevelScreen(
						oneMoreCookiePleaseController));
			}
		});

		ImageButtonStyle exitButtonStyle = new ImageButtonStyle();
		exitButtonStyle.up = oneMoreCookiePleaseController.getScreenUtils()
				.getRectangularSkin().getDrawable("exit.up");
		exitButtonStyle.down = oneMoreCookiePleaseController.getScreenUtils()
				.getRectangularSkin().getDrawable("exit.down");
		exitButtonStyle.pressedOffsetX = 1;
		exitButtonStyle.pressedOffsetY = -1;

		ImageButton exitButton = new ImageButton(exitButtonStyle);
		exitButton.pad(20);
		exitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {

				try {
					IOHandler.saveHighScores(oneMoreCookiePleaseController
							.getPlayerController().getPlayer()
							.getHighScoreList());
				} catch (GdxRuntimeException e) {
					Gdx.app.log("StartScreen", "Exception", e);
				} catch (Exception e) {
				}
				oneMoreCookiePleaseController.setScreen(oneMoreCookiePleaseController
						.getExitPopUpScreen());
			}
		});

		ImageButtonStyle soundButtonStyle = new ImageButtonStyle();
		if (OneMoreCookiePleaseController.soundEffectsOn()) {
			soundButtonStyle.up = oneMoreCookiePleaseController.getScreenUtils()
					.getCircularSkin().getDrawable("sound.checked");
			soundButtonStyle.down = oneMoreCookiePleaseController.getScreenUtils()
					.getCircularSkin().getDrawable("sound.down");
			soundButtonStyle.checked = oneMoreCookiePleaseController.getScreenUtils()
					.getCircularSkin().getDrawable("sound.up");
		} else {
			soundButtonStyle.up = oneMoreCookiePleaseController.getScreenUtils()
					.getCircularSkin().getDrawable("sound.up");
			soundButtonStyle.down = oneMoreCookiePleaseController.getScreenUtils()
					.getCircularSkin().getDrawable("sound.down");
			soundButtonStyle.checked = oneMoreCookiePleaseController.getScreenUtils()
					.getCircularSkin().getDrawable("sound.checked");
		}

		ImageButton soundButton = new ImageButton(soundButtonStyle);
		soundButton.setPosition(120, 30);
		soundButton.toggle();
		soundButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (OneMoreCookiePleaseController.soundEffectsOn()) {
					oneMoreCookiePleaseController.soundEffectsOn(false);
				} else {
					oneMoreCookiePleaseController.soundEffectsOn(true);
				}
			}
		});

		ImageButtonStyle musicButtonStyle = new ImageButtonStyle();
		if (oneMoreCookiePleaseController.getSoundController().backgroundMusicIsPlaying()) {
			musicButtonStyle.up = oneMoreCookiePleaseController.getScreenUtils()
					.getCircularSkin().getDrawable("music.checked");
			musicButtonStyle.down = oneMoreCookiePleaseController.getScreenUtils()
					.getCircularSkin().getDrawable("music.down");
			musicButtonStyle.checked = oneMoreCookiePleaseController.getScreenUtils()
					.getCircularSkin().getDrawable("music.up");
		} else {
			musicButtonStyle.up = oneMoreCookiePleaseController.getScreenUtils()
					.getCircularSkin().getDrawable("music.up");
			musicButtonStyle.down = oneMoreCookiePleaseController.getScreenUtils()
					.getCircularSkin().getDrawable("music.down");
			musicButtonStyle.checked = oneMoreCookiePleaseController.getScreenUtils()
					.getCircularSkin().getDrawable("music.checked");
		}

		ImageButton musicButton = new ImageButton(musicButtonStyle);
		musicButton.pad(20);
		musicButton.setPosition(20, 30);
		musicButton.toggle();
		musicButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (oneMoreCookiePleaseController.getSoundController()
						.backgroundMusicIsPlaying()) {
					oneMoreCookiePleaseController.getSoundController()
					.pauseBackgroundMusic();
				} else {
					oneMoreCookiePleaseController.getSoundController()
					.playBackgroundMusic();
				}
			}
		});

		table.add(image).center();
		table.getCell(image).spaceBottom(30);
		table.row();
		table.add(startButton).center();
		table.getCell(startButton).spaceBottom(30);
		table.row();
		table.row();
		table.add(exitButton).center();
		table.getCell(exitButton).spaceBottom(30);
		table.row();
		table.setFillParent(true);

		stage.addActor(musicButton);
		stage.addActor(soundButton);
		stage.addActor(table);
		stage.setViewport(CoordinateConverter.getCameraWidth(),
				CoordinateConverter.getCameraHeight(), true);
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
			imageTexture.dispose();
		} catch (GdxRuntimeException e) {
			Gdx.app.log("StartScreen", "Exception", e);
		} catch (Exception e) {
		}
	}

	public void exit() {
	}
}