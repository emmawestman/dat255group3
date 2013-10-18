package com.dat255_group3.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.dat255_group3.controller.MyGdxGameController;
import com.dat255_group3.utils.CoordinateConverter;

/**
 * A class which represents the startmenu of the game. The user is given the
 * options of choosing a character, starting the game or to exit.
 * 
 * @author The Hans-Gunnar Crew
 * 
 */
public class StartScreen implements Screen {

	private MyGdxGameController myGdxGameController;
	private Stage stage;
	private Table table;
	private SpriteBatch spritebatch;
	private Texture texture;
	private Image image;

	// import aurelienribon.tweenengine.TweenManager;
	// private TweenManager tweenmanager;

	public StartScreen(MyGdxGameController myGdxGameController) {
		this.myGdxGameController = myGdxGameController;
		this.stage = new Stage(CoordinateConverter.getCameraWidth(),
				CoordinateConverter.getCameraHeight(), true);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Update & draw the stage actors
		stage.act(delta);
		spritebatch.begin();
		image.setSize(685, 258);
		image.draw(spritebatch, 1f);
		spritebatch.end();
		// Table.drawDebug(stage); //To be removed later on
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// In order to make it look good not depending on the screensize.
		// stage.setViewport(CoordinateConverter.getCameraWidth(),
		// CoordinateConverter.getCameraHeight(), true);
		// stage.setViewport(width, height, true);
		// table.invalidateHierarchy();
		// table.setSize(CoordinateConverter.getCameraWidth(),
		// CoordinateConverter.getCameraHeight());
		// table.setSize(width, height);
	}

	@Override
	public void show() {
		// Setting up the stage
		stage.setViewport(CoordinateConverter.getCameraWidth(),
				CoordinateConverter.getCameraHeight(), true);
		Gdx.input.setInputProcessor(stage);

		// Setting the image for the title of the game
		try {
			texture = new Texture(Gdx.files.internal("menuIcons/gameTitle.png"));

		} catch (GdxRuntimeException e) {
			Gdx.app.log("StartScreen", "Exception", e);
		} catch (Exception e) {
		}
		spritebatch = new SpriteBatch();
		image = new Image(texture);

		// Setting up the table
		table = new Table();
		table.setBounds(CoordinateConverter.getCameraWidth() / 2 - 450, CoordinateConverter.getCameraHeight()/2 - 50,
				CoordinateConverter.getCameraWidth(), 0);

		ImageButtonStyle startButtonStyle = new ImageButtonStyle();
		startButtonStyle.up = myGdxGameController.getScreenUtils()
				.getRectangularSkin().getDrawable("start.up");
		startButtonStyle.down = myGdxGameController.getScreenUtils()
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
				myGdxGameController.getMyGdxGame().setIsGameStarted(false);
				myGdxGameController.setScreen(new LevelScreen(
						myGdxGameController));
			}
		});

		ImageButtonStyle exitButtonStyle = new ImageButtonStyle();
		exitButtonStyle.up = myGdxGameController.getScreenUtils()
				.getRectangularSkin().getDrawable("exit.up");
		exitButtonStyle.down = myGdxGameController.getScreenUtils()
				.getRectangularSkin().getDrawable("exit.down");
		exitButtonStyle.pressedOffsetX = 1;
		exitButtonStyle.pressedOffsetY = -1;

		ImageButton exitButton = new ImageButton(exitButtonStyle);
		exitButton.pad(20);
		exitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				try {
					Gdx.app.exit();
				} catch (GdxRuntimeException e) {
					Gdx.app.log("StartScreen", "Exception", e);
				} catch (Exception e) {
				}
			}
		});

		ImageButtonStyle soundButtonStyle = new ImageButtonStyle();
		soundButtonStyle.up = myGdxGameController.getScreenUtils()
				.getCircularSkin().getDrawable("sound.up");
		soundButtonStyle.down = myGdxGameController.getScreenUtils()
				.getCircularSkin().getDrawable("sound.down");
		soundButtonStyle.checked = myGdxGameController.getScreenUtils()
				.getCircularSkin().getDrawable("sound.checked");

		ImageButton soundButton = new ImageButton(soundButtonStyle);
		// soundEButton.pad(20);
		soundButton.toggle();
		soundButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (MyGdxGameController.soundEffectsOn()) {
					myGdxGameController.soundEffectsOn(false);
				} else {
					myGdxGameController.soundEffectsOn(true);
				}
			}
		});

		ImageButtonStyle musicButtonStyle = new ImageButtonStyle();
		musicButtonStyle.up = myGdxGameController.getScreenUtils()
				.getCircularSkin().getDrawable("music.up");
		musicButtonStyle.down = myGdxGameController.getScreenUtils()
				.getCircularSkin().getDrawable("music.down");
		musicButtonStyle.checked = myGdxGameController.getScreenUtils()
				.getCircularSkin().getDrawable("music.checked");

		ImageButton musicButton = new ImageButton(musicButtonStyle);
		musicButton.pad(20);
		musicButton.toggle();
		musicButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (myGdxGameController.getSoundController()
						.backgroundMusicIsPlaying()) {
					myGdxGameController.getSoundController()
							.pauseBackgroundMusic();
				} else {
					myGdxGameController.getSoundController()
							.playBackgroundMusic();
				}
			}
		});

		// Table table2 = new Table();
		table.center();
		table.add(image);
		table.row();
		table.add(startButton);
		table.getCell(startButton).spaceBottom(30);
		table.row();
		table.add(exitButton);
		table.getCell(exitButton).spaceBottom(30);
		table.row();
		table.add(soundButton).left();
		table.add(musicButton).left();
		// ;
		// table2.center();
		// table2.add(soundEButton).right();
		// table2.add(musicButton);
		// table.add(table2);
		// table.row();
		stage.addActor(table);

		// table.debug(); // To be removed later on
	}

	@Override
	public void hide() {
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
			spritebatch.dispose();
			texture.dispose();
		} catch (GdxRuntimeException e) {
			Gdx.app.log("StartScreen", "Exception", e);
		} catch (Exception e) {
		}
	}

}