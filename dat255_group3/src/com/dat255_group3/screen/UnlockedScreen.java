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

/**
 * A class which represents the screen being shown when a yet unlocked level is
 * chosen.
 * 
 * @author The Hans-Gunnar Crew
 */
public class UnlockedScreen implements Screen {

	private MyGdxGameController myGdxGameController;
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;

	public UnlockedScreen(MyGdxGameController myGdxGameController) {
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
		stage.setViewport(width, height, true);
		table.invalidateHierarchy();
		table.setSize(width, height);
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
		LabelStyle headerStyle = new LabelStyle();
		headerStyle.font = new BitmapFont(
				Gdx.files.internal("font/whiteL.fnt"), false);
		headerStyle.font.scale(1.2f);
		Label header = new Label("Unlocked!", headerStyle);

		LabelStyle buttonLabelStyle = new LabelStyle();
		buttonLabelStyle.font = new BitmapFont(
				Gdx.files.internal("font/white.fnt"), false);
		buttonLabelStyle.font.scale(1.1f);
		Label textLabel = new Label("The next level is not locked up yet",
				buttonLabelStyle);

		Label optionsLabel = new Label("Would you like to play level 2 again?",
				buttonLabelStyle);

		// Setting up the characteristics for the button
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("button.up");
		textButtonStyle.down = skin.getDrawable("button.down");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = new BitmapFont(
				Gdx.files.internal("font/black.fnt"), false);

		// Setting buttons & listeners with options of replaying the previous level or to
		// return to the main-menu
		TextButton yesButton = new TextButton("Yes", textButtonStyle);
		yesButton.pad(20);
		yesButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				myGdxGameController.setScreen(myGdxGameController
						.getInGameController());
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
		table.add(textLabel);
		table.getCell(textLabel).spaceBottom(50);
		table.row();
		table.add(optionsLabel);
		table.getCell(optionsLabel).spaceBottom(50);
		table.row();
		table.add(yesButton);
		table.getCell(yesButton).spaceBottom(50);
		table.row();
		table.add(mainMenuButton);
		table.getCell(mainMenuButton).spaceBottom(50);
		table.row();
		stage.addActor(table);
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