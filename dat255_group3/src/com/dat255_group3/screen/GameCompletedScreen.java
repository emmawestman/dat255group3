package com.dat255_group3.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.dat255_group3.controller.MyGdxGameController;
import com.dat255_group3.utils.CoordinateConverter;
import com.dat255_group3.utils.ScreenUtils;

/**
 * A class which represents the screen being shown when a yet unlocked level is
 * chosen.
 * 
 * @author The Hans-Gunnar Crew
 */
public class GameCompletedScreen implements Screen {

	private MyGdxGameController myGdxGameController;
	private Stage stage;
	private Texture messageIcon;
	private Image messageImage;
	private Table table;
	private Table column1;
	private Table column2;
	private Table column3;
	


	public GameCompletedScreen(MyGdxGameController myGdxGameController) {
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
		

	}

	@Override
	public void show() {
		// Setting up the stage
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		table = new Table(myGdxGameController.getScreenUtils().getCircularSkin());
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		//The message image
		messageIcon = new Texture(Gdx.files.internal("menuIcons/completedTheGameMsg.png"));
		messageImage = new Image(messageIcon);

		//Home Button
		ImageButtonStyle homeButtonStyle = new ImageButtonStyle();
		homeButtonStyle.up = myGdxGameController.getScreenUtils().getCircularSkin().getDrawable("home.up");
		homeButtonStyle.down = myGdxGameController.getScreenUtils().getCircularSkin().getDrawable("home.up");
		homeButtonStyle.pressedOffsetX = 1;
		homeButtonStyle.pressedOffsetY = -1;

		ImageButton mainMenuButton = new ImageButton(homeButtonStyle);
		mainMenuButton.setPosition(CoordinateConverter.getCameraWidth() -130, 30);
		mainMenuButton.pad(20);
		mainMenuButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				myGdxGameController.setScreen(myGdxGameController
						.getStartScreen());
			}
		});
		
		
		table.row();
		column1 = new Table();
		table.add(column1).width(100);
		column2 = new Table();
		table.add(column2);
		column2.row();
		column2.add(myGdxGameController.getScreenUtils().getGameTitleImage());
		column2.row();
		column2.add(messageImage);
		column3 = new Table();
		table.add(column3).width(100);
		
		table.setFillParent(true);
		stage.addActor(myGdxGameController.getScreenUtils().getBackgroundImage());
		stage.addActor(table);
		stage.addActor(mainMenuButton);
		stage.setViewport(CoordinateConverter.getCameraWidth(), CoordinateConverter.getCameraHeight(), true);

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
		stage.dispose();

	}

}