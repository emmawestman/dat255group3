package com.dat255_group3.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.GdxRuntimeException;
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
		atlas = new TextureAtlas(Gdx.files.internal("gameOver/gameOver.pack"));
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

		/*
		 * Setting buttons, their characteristics & listeners with options of
		 * continuing to play, restart or to return to the main-menu
		 */
		ImageButtonStyle resumeButtonStyle = new ImageButtonStyle();
		resumeButtonStyle.up = skin.getDrawable("retry.up");
		resumeButtonStyle.down = skin.getDrawable("retry.down");
		resumeButtonStyle.pressedOffsetX = 1;
		resumeButtonStyle.pressedOffsetY = -1;

		ImageButton resumeButton = new ImageButton(resumeButtonStyle);
		resumeButton.pad(20);
		resumeButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				myGdxGameController.setScreen(myGdxGameController
						.getInGameController());
			}
		});

		ImageButtonStyle restartButtonStyle = new ImageButtonStyle();
		restartButtonStyle.up = skin.getDrawable("retry.up");
		restartButtonStyle.down = skin.getDrawable("retry.down");
		restartButtonStyle.pressedOffsetX = 1;
		restartButtonStyle.pressedOffsetY = -1;

		ImageButton restartButton = new ImageButton(restartButtonStyle);
		restartButton.pad(20);
		restartButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// This level
				myGdxGameController.setScreen(myGdxGameController
						.getInGameController());

			}
		});

		ImageButtonStyle homeButtonStyle = new ImageButtonStyle();
		homeButtonStyle.up = skin.getDrawable("home.up");
		homeButtonStyle.down = skin.getDrawable("home.down");
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

		// Adding to the table and actors to the stage
		table.add(header);
		table.getCell(header).spaceBottom(50);
		table.row();
		table.add(resumeButton);
		table.getCell(resumeButton).spaceBottom(50);
		table.row();
		table.add(restartButton);
		table.getCell(restartButton).spaceBottom(50);
		table.row();
		table.add(homeButton);
		table.getCell(homeButton).spaceBottom(50);
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
		try{
			stage.dispose();
			skin.dispose();
			atlas.dispose();
		} catch (GdxRuntimeException e){
			Gdx.app.log("IOHandler", "Exception", e);
		}catch (Exception e) {			
		}
	}

}