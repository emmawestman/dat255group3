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
public class HighScoreScreen implements Screen {

	private MyGdxGameController myGdxGameController;
	private InputStage stage;
	private Table table;
	private Texture highScoreLabelTexture;

	public HighScoreScreen(MyGdxGameController myGdxGameController) {
		this.myGdxGameController = myGdxGameController;
		this.stage = new InputStage(CoordinateConverter.getCameraWidth(),
				CoordinateConverter.getCameraHeight(), true);
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
		myGdxGameController.getMyGdxGame().setIsGameStarted(false);

		// Checks if the back-key has been pressed & if so, confirming screen to
		// exit the application will be shown
		stage.setHardKeyListener(new OnHardKeyListener() {
			@Override
			public void onHardKey(int keyCode, int state) {
				if (keyCode == Keys.BACK && state == 1) {
					myGdxGameController.setScreen(myGdxGameController
							.getStartScreen());
				}
			}
		});

		// Setting up the table
		table = new Table();
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		try {
			highScoreLabelTexture = new Texture(
			Gdx.files.internal("ui//highScoreTitle.png"));
		} catch (GdxRuntimeException e) {
			Gdx.app.log("LevelScreen", "Exception", e);
		} catch (Exception e) {
		}
		Image highScoreLabelImage = new Image(highScoreLabelTexture);

		LabelStyle scoreLabelStyle = new LabelStyle();
		scoreLabelStyle.font = new BitmapFont(
				Gdx.files.internal("font/white.fnt"), false);
		scoreLabelStyle.font.setScale(1.8f);

		LabelStyle levelStyle = new LabelStyle();
		levelStyle.font = new BitmapFont(Gdx.files.internal("font/white.fnt"),
				false);
		levelStyle.font.scale(1.1f);

		Label levelOneLabel = new Label("Level 1", levelStyle);
		Label levelOneScore = new Label("Score: " + myGdxGameController.getPlayerController().getPlayer().getHighScore(1), scoreLabelStyle);

		Label levelTwoLabel = new Label("Level 2", levelStyle);
		Label levelTwoScore = new Label("Score: " + myGdxGameController.getPlayerController().getPlayer().getHighScore(2),
				scoreLabelStyle);
		
		Label levelThreeLabel = new Label("Level 3", levelStyle);
		Label levelThreeScore = new Label("Score: " + myGdxGameController.getPlayerController().getPlayer().getHighScore(3),
				scoreLabelStyle);

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
		table.add(levelOneLabel);
		table.getCell(levelOneLabel).spaceBottom(20);
		table.row();
		table.add(levelOneScore);
		table.getCell(levelOneScore).spaceBottom(20);
		table.row();
		table.add(levelTwoLabel);
		table.getCell(levelTwoLabel).spaceBottom(20);
		table.row();
		table.add(levelTwoScore);
		table.getCell(levelTwoScore).spaceBottom(20);
		table.row();
		table.add(levelThreeLabel);
		table.getCell(levelThreeLabel).spaceBottom(20);
		table.row();
		table.add(levelThreeScore);
		table.getCell(levelThreeScore).spaceBottom(20);
		highScoreLabelImage.setPosition(270, 400);
		stage.addActor(highScoreLabelImage);
		stage.addActor(table);

		// table.debug(); //To be removed later on
		table.setSize(CoordinateConverter.getCameraWidth(),
				CoordinateConverter.getCameraHeight());
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
		stage.dispose();
	}

}
