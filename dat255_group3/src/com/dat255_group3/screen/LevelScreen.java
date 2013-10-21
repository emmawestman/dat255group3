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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.dat255_group3.controller.MyGdxGameController;
import com.dat255_group3.utils.CoordinateConverter;
import com.dat255_group3.utils.InputStage;
import com.dat255_group3.utils.InputStage.OnHardKeyListener;

/**
 * A class which represents the menu for the levels of the game. The user is
 * given the options of choosing which level or to return by pressing the
 * backbutton on the phone.
 * 
 * @author The Hans-Gunnar Crew
 */
public class LevelScreen implements Screen {

	private MyGdxGameController myGdxGameController;
	private InputStage stage;
	private Texture levelTexture;

	public LevelScreen(MyGdxGameController myGdxGameController) {
		this.myGdxGameController = myGdxGameController;
		this.stage = new InputStage(CoordinateConverter.getCameraWidth(),
				CoordinateConverter.getCameraHeight(), true);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		myGdxGameController.getScreenUtils().drawBackgroundImage();

		// Update & draw the stage-actors
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);

		// Checks if the back-key has been pressed & if so, the start screen will be shown
		stage.setHardKeyListener(new OnHardKeyListener() {
			@Override
			public void onHardKey(int keyCode, int state) {
				if (keyCode == Keys.BACK && state == 1) {
					myGdxGameController.setScreen(myGdxGameController
							.getStartScreen());
				}
			}
		});

		// Setting the image for the title
		try {
			levelTexture = new Texture(
					Gdx.files.internal("ui/selectLevelTitle.png"));
		} catch (GdxRuntimeException e) {
			Gdx.app.log("LevelScreen", "Exception", e);
		} catch (Exception e) {
		}
		Image levelImage = new Image(levelTexture);

		// Setting buttons & listeners for choosing the levels
		ImageButtonStyle levelOneStyle = new ImageButtonStyle();
		levelOneStyle.up = myGdxGameController.getScreenUtils()
				.getRectangularSkin().getDrawable("levelOne.up");
		levelOneStyle.down = myGdxGameController.getScreenUtils()
				.getRectangularSkin().getDrawable("levelOne.down");
		levelOneStyle.pressedOffsetX = 1;
		levelOneStyle.pressedOffsetY = -1;

		ImageButton levelOneButton = new ImageButton(levelOneStyle);
		levelOneButton.pad(20);
		levelOneButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				myGdxGameController.getMyGdxGame().setCurrentLevel(1);
				myGdxGameController.getInGameController().loadMap();
				myGdxGameController.setScreen(myGdxGameController
						.getInGameController());
			}
		});

		ImageButtonStyle levelTwoStyle = new ImageButtonStyle();
		levelTwoStyle.up = myGdxGameController.getScreenUtils()
				.getRectangularSkin().getDrawable("levelTwo.up");
		levelTwoStyle.down = myGdxGameController.getScreenUtils()
				.getRectangularSkin().getDrawable("levelTwo.down");
		levelTwoStyle.pressedOffsetX = 1;
		levelTwoStyle.pressedOffsetY = -1;

		ImageButton levelTwoButton = new ImageButton(levelTwoStyle);
		levelTwoButton.pad(20);
		levelTwoButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				myGdxGameController.getMyGdxGame().setCurrentLevel(2);
				myGdxGameController.getInGameController().loadMap();
				myGdxGameController.setScreen(myGdxGameController
						.getInGameController());
			}
		});

		ImageButtonStyle levelThreeStyle = new ImageButtonStyle();
		levelThreeStyle.up = myGdxGameController.getScreenUtils()
				.getRectangularSkin().getDrawable("levelThree.up");
		levelThreeStyle.down = myGdxGameController.getScreenUtils()
				.getRectangularSkin().getDrawable("levelThree.down");
		levelThreeStyle.pressedOffsetX = 1;
		levelThreeStyle.pressedOffsetY = -1;

		ImageButton levelThreeButton = new ImageButton(levelThreeStyle);
		levelThreeButton.pad(20);
		levelThreeButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				myGdxGameController.getMyGdxGame().setCurrentLevel(3);
				myGdxGameController.getInGameController().loadMap();
				myGdxGameController.setScreen(myGdxGameController
						.getInGameController());
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
		homeButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				myGdxGameController.setScreen(myGdxGameController
						.getStartScreen());
			}
		});
		
		//Setting high scores of the levels
		LabelStyle scoreLabelStyle = new LabelStyle();
		scoreLabelStyle.font = new BitmapFont(
				Gdx.files.internal("font/white.fnt"), false);
		scoreLabelStyle.font.setScale(1.8f);

		Label levelOneScore = new Label("Score: " + myGdxGameController.getPlayerController().getPlayer().getHighScore(1), scoreLabelStyle);
		Label levelTwoScore = new Label("Score: " + myGdxGameController.getPlayerController().getPlayer().getHighScore(2),
				scoreLabelStyle);
		Label levelThreeScore = new Label("Score: " + myGdxGameController.getPlayerController().getPlayer().getHighScore(3),
				scoreLabelStyle);


		// Setting the positions of the actors and add them to the stage
		levelImage.setPosition(270, 400);
		levelOneButton.setPosition(180, 160);
		levelTwoButton.setPosition(405, 160);
		levelThreeButton.setPosition(630, 160);
		homeButton.setPosition(CoordinateConverter.getCameraWidth() - 120, 30);
		
		levelOneScore.setPosition(180, 60);
		levelTwoScore.setPosition(405, 60);
		levelThreeScore.setPosition(630, 60);
		
		stage.addActor(levelImage);
		stage.addActor(levelOneButton);
		stage.addActor(levelTwoButton);
		stage.addActor(levelThreeButton);
		stage.addActor(homeButton);

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
		} catch (GdxRuntimeException e) {
			Gdx.app.log("LevelScreen", "Exception", e);
		} catch (Exception e) {
		}
	}

}