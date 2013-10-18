package com.dat255_group3.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.dat255_group3.controller.MyGdxGameController;
import com.dat255_group3.utils.CoordinateConverter;

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
	private Table table;
	private BitmapFont black;
	private BitmapFont white;
	private Skin skin;
	private Texture levelTexture;
	private SpriteBatch spriteBatch;

	public LevelScreen(MyGdxGameController myGdxGameController) {
		this.myGdxGameController = myGdxGameController;
		this.stage = new Stage(0, 0, true);
		spriteBatch = new SpriteBatch();
		myGdxGameController.getScreenUtils().setCamera(spriteBatch);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		spriteBatch.begin();
		myGdxGameController.getScreenUtils().getBackgroundImage().draw(spriteBatch, 1);
		spriteBatch.end();

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
		// stage.setViewport(width, height, true);
		// table.invalidateHierarchy();
		// table.setSize(width, height);
	}

	@Override
	public void show() {
		// Setting up the stage
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		// Setting the image for the title of the game
		try {
			levelTexture = new Texture(
					Gdx.files.internal("ui/selectLevelTitle.png"));
		} catch (GdxRuntimeException e) {
			Gdx.app.log("LevelScreen", "Exception", e);
		} catch (Exception e) {
		}
		Image levelImage = new Image(levelTexture);

		// Setting up the atlas, skin & fonts
		atlas = new TextureAtlas(Gdx.files.internal("ui/button.pack"));
		skin = new Skin(atlas);
		black = new BitmapFont(Gdx.files.internal("font/black.fnt"), false);
		white = new BitmapFont(Gdx.files.internal("font/whiteL.fnt"), false);
		white.scale(1.2f);

		// Setting up the table
		table = new Table();
		table.setBounds(0,0,CoordinateConverter.getCameraWidth(), CoordinateConverter.getCameraHeight());

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
		
		ImageButtonStyle homeButtonStyle = new ImageButtonStyle();
		homeButtonStyle.up = myGdxGameController.getScreenUtils().getCircularSkin().getDrawable("home.up");
		homeButtonStyle.down = myGdxGameController.getScreenUtils().getCircularSkin().getDrawable("home.down");
		homeButtonStyle.pressedOffsetX = 1;
		homeButtonStyle.pressedOffsetY = -1;

		ImageButton homeButton = new ImageButton(homeButtonStyle);
		homeButton.pad(20);
		//homeButton.setPosition(x, y) //TODO
		homeButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				myGdxGameController.setScreen(myGdxGameController
						.getStartScreen());
			}
		});

		// Adding to the table and actors to the stage
		table.setFillParent(true);
		table.add(levelImage);
		table.getCell(levelImage).spaceBottom(30);
		table.row();
		table.add(levelOneButton);
		table.getCell(levelOneButton).spaceBottom(50);
		table.row();
		table.add(levelTwoButton);
		table.getCell(levelTwoButton).spaceBottom(50);
		table.row();
		stage.addActor(table);
		stage.addActor(homeButton);

		// table.invalidateHierarchy();
		stage.setViewport(CoordinateConverter.getCameraWidth(),
				CoordinateConverter.getCameraHeight(), true);

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
		try {
			stage.dispose();
			skin.dispose();
			atlas.dispose();
			black.dispose();
		} catch (GdxRuntimeException e) {
			Gdx.app.log("LevelScreen", "Exception", e);
		} catch (Exception e) {
		}
	}

}