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
import com.dat255_group3.controller.OneMoreCookiePleaseController;
import com.dat255_group3.utils.CoordinateConverter;
import com.dat255_group3.utils.InputStage;
import com.dat255_group3.utils.InputStage.OnHardKeyListener;

/**
 * A screen that is shown when the whole game is completed.
 * 
 * @author The Hans-Gunnar Crew
 */
public class GameCompletedScreen implements Screen {

	private OneMoreCookiePleaseController oneMoreCookiePleaseController;
	private InputStage stage;
	private Image messageImage;

	/**
	 * Constructs a new GameCompletedScreen with the specified 
	 * OneMoreCookiePleaseController.
	 * 
	 * @param oneMoreCookiePleaseController the game's OneMoreCookiePleaseController
	 * 		  object
	 */
	public GameCompletedScreen(
			OneMoreCookiePleaseController oneMoreCookiePleaseController) {
		this.oneMoreCookiePleaseController = oneMoreCookiePleaseController;
		this.stage = new InputStage(CoordinateConverter.getCameraWidth(),
				CoordinateConverter.getCameraWidth(), true);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		oneMoreCookiePleaseController.getScreenUtils().drawBackgroundImage();

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
		oneMoreCookiePleaseController.getOneMoreCookiePlease()
				.setIsGameStarted(false);

		// Checks if the back-key has been pressed & if so, the level screen
		// will be shown
		stage.setHardKeyListener(new OnHardKeyListener() {
			@Override
			public void onHardKey(int keyCode, int state) {
				if (keyCode == Keys.BACK && state == 1) {
					oneMoreCookiePleaseController
							.setScreen(oneMoreCookiePleaseController
									.getLevelScreen());
				}
			}
		});

		Table table = new Table(oneMoreCookiePleaseController.getScreenUtils()
				.getCircularSkin());
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// The message image
		Texture messageIcon = new Texture(
				Gdx.files.internal("ui/completedTheGameMsg.png"));
		messageImage = new Image(messageIcon);

		// Home Button
		ImageButtonStyle homeButtonStyle = new ImageButtonStyle();
		homeButtonStyle.up = oneMoreCookiePleaseController.getScreenUtils()
				.getCircularSkin().getDrawable("home.up");
		homeButtonStyle.down = oneMoreCookiePleaseController.getScreenUtils()
				.getCircularSkin().getDrawable("home.up");
		homeButtonStyle.pressedOffsetX = 1;
		homeButtonStyle.pressedOffsetY = -1;

		ImageButton mainMenuButton = new ImageButton(homeButtonStyle);
		mainMenuButton.setPosition(CoordinateConverter.getCameraWidth() - 130,
				30);
		mainMenuButton.pad(20);
		mainMenuButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				oneMoreCookiePleaseController
						.setScreen(oneMoreCookiePleaseController
								.getStartScreen());
			}
		});

		table.row();
		Table column1 = new Table();
		table.add(column1).width(100);
		Table column2 = new Table();
		table.add(column2);
		column2.row();
		column2.add(oneMoreCookiePleaseController.getScreenUtils()
				.getGameTitleImage());
		column2.row();
		column2.add(messageImage);
		Table column3 = new Table();
		table.add(column3).width(100);

		table.setFillParent(true);
		stage.addActor(table);
		stage.addActor(mainMenuButton);
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