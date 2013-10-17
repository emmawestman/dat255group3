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
import com.dat255_group3.utils.CoordinateConverter;

/**
 * A class which represents the screen being shown when the game is paused.
 * 
 * @author The Hans-Gunnar Crew
 */
public class PauseScreen implements Screen {

	private MyGdxGameController myGdxGameController;
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;

	public PauseScreen(MyGdxGameController myGdxGameController) {
		this.myGdxGameController = myGdxGameController;
		this.stage = new Stage(0, 0, true);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Update & draw the stage-actors
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// In order to make it look good not depending on the screen.
		//stage.setViewport(width, height, true);
		//table.invalidateHierarchy();
		//table.setSize(width, height);
	}

	@Override
	public void show() {
		// Setting up the stage
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		// Setting up the atlas, skin & fonts
		atlas = new TextureAtlas(Gdx.files.internal("ui/button.pack"));
		skin = new Skin(atlas);

		// Setting up the table
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// Setting characteristics for the label
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = new BitmapFont(Gdx.files.internal("font/whiteL.fnt"),
				false);
		labelStyle.font.scale(1.2f);
		Label header = new Label("Paused", labelStyle);

		// Setting up the characteristics for the button
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("button.up");
		textButtonStyle.down = skin.getDrawable("button.down");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = new BitmapFont(
				Gdx.files.internal("font/black.fnt"), false);

		// Setting buttons & listeners with options of continuing to play or to
		// return to the main-menu
		TextButton resumeButton = new TextButton("Resume", textButtonStyle);
		resumeButton.pad(20);
		resumeButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				myGdxGameController.setScreen(myGdxGameController
						.getInGameController());
			}
		});

		TextButton mainMenuButton = new TextButton("Main Menu", textButtonStyle);
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
		table.add(resumeButton);
		table.getCell(resumeButton).spaceBottom(50);
		table.row();
		table.add(mainMenuButton);
		table.getCell(mainMenuButton).spaceBottom(50);
		table.row();
		stage.addActor(table);
		
		//table.invalidateHierarchy();
		table.setSize(CoordinateConverter.getCameraWidth(), CoordinateConverter.getCameraHeight());
		stage.setViewport(CoordinateConverter.getCameraWidth(), CoordinateConverter.getCameraHeight(), true);
	}

	@Override
	public void hide() {
		table.clear();

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
		skin.dispose();
		atlas.dispose();
	}

}