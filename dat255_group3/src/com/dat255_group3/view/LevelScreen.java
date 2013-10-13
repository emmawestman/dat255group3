package com.dat255_group3.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
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
 * A class which represents the menu for the levels of the game. The user is
 * given the options of choosing which level or to return by pressing the
 * backbutton on the phone.
 * 
 * @author The Hans-Gunnar Crew
 */
public class LevelScreen implements Screen {

	private MyGdxGameController myGdxGameController;
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;
	private BitmapFont black;
	private BitmapFont white;

	public LevelScreen(MyGdxGameController myGdxGameController) {
		this.myGdxGameController = myGdxGameController;
		this.stage = new Stage(0, 0, true);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (Gdx.input.isKeyPressed(Keys.BACK)) {
			Gdx.input.setCatchBackKey(true);
			myGdxGameController.setScreen(myGdxGameController.getStartScreen());
		}

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
		black = new BitmapFont(Gdx.files.internal("font/black.fnt"), false);
		white = new BitmapFont(Gdx.files.internal("font/whiteL.fnt"), false);
		white.scale(1.2f);

		// Setting up the table
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// Setting characteristics for the label
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = white;
		Label label = new Label("Levels", labelStyle);

		// Setting up the characteristics for the buttons
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("button.up");
		textButtonStyle.down = skin.getDrawable("button.down");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = black;

		// Setting buttons & listeners for choosing the levels
		TextButton levelOneButton = new TextButton("Level 1", textButtonStyle);
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

		TextButton levelTwoButton = new TextButton("Level 2", textButtonStyle);
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

		// Adding to the table and actors to the stage
		table.add(label);
		table.getCell(label).spaceBottom(50);
		table.row();
		table.add(levelOneButton);
		table.getCell(levelOneButton).spaceBottom(50);
		table.row();
		table.add(levelTwoButton);
		table.getCell(levelTwoButton).spaceBottom(50);
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
		black.dispose();

	}

}
