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
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.dat255_group3.controller.MyGdxGameController;
import com.dat255_group3.utils.CoordinateConverter;
import com.dat255_group3.utils.InputStage;
import com.dat255_group3.utils.InputStage.OnHardKeyListener;

/**
 * A class which represents the screen being shown when the game is paused.
 * 
 * @author The Hans-Gunnar Crew
 */
public class PauseScreen implements Screen {

	private MyGdxGameController myGdxGameController;
	private InputStage stage;
	private Texture messageIcon;
	private Image messageImage;
	

	public PauseScreen(MyGdxGameController myGdxGameController) {
		this.myGdxGameController = myGdxGameController;
		// Setting up the stage
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

		// Checks if the back-key has been pressed  & if so, the level screen will be shown
	    stage.setHardKeyListener(new OnHardKeyListener() {          
	        @Override
	        public void onHardKey(int keyCode, int state) {
	            if(keyCode==Keys.BACK && state==1){
	            	myGdxGameController.setScreen(myGdxGameController.getLevelScreen());      
	            }       
	        }
	    });


		// Setting up the table
		Table table = new Table();
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		//The message image
		messageIcon = new Texture(Gdx.files.internal("ui/pausedTitle.png"));
		messageImage = new Image(messageIcon);

		/*
		 * Setting buttons, their characteristics & listeners with options of
		 * continuing to play, restart or to return to the main-menu
		 */
		ImageButtonStyle resumeButtonStyle = new ImageButtonStyle();
		resumeButtonStyle.up = myGdxGameController.getScreenUtils().getRectangularSkin().getDrawable("resume.up");
		resumeButtonStyle.down = myGdxGameController.getScreenUtils().getRectangularSkin().getDrawable("resume.down");
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
		restartButtonStyle.up = myGdxGameController.getScreenUtils().getRectangularSkin().getDrawable("restart.up");
		restartButtonStyle.down = myGdxGameController.getScreenUtils().getRectangularSkin().getDrawable("restart.down");
		restartButtonStyle.pressedOffsetX = 1;
		restartButtonStyle.pressedOffsetY = -1;

		ImageButton restartButton = new ImageButton(restartButtonStyle);
		restartButton.pad(20);
		restartButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// This level
				myGdxGameController.getMyGdxGame().setIsGameStarted(false);
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
		homeButton.setPosition(CoordinateConverter.getCameraWidth()-130, 30);
		homeButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				myGdxGameController.setScreen(myGdxGameController
						.getStartScreen());
			}
		});



		ImageButtonStyle soundButtonStyle = new ImageButtonStyle();
		if(MyGdxGameController.soundEffectsOn()) {
			soundButtonStyle.up = myGdxGameController.getScreenUtils()
					.getCircularSkin().getDrawable("sound.checked");
			soundButtonStyle.down = myGdxGameController.getScreenUtils()
					.getCircularSkin().getDrawable("sound.down");
			soundButtonStyle.checked = myGdxGameController.getScreenUtils()
					.getCircularSkin().getDrawable("sound.up");
		}else{
			soundButtonStyle.up = myGdxGameController.getScreenUtils()
					.getCircularSkin().getDrawable("sound.up");
			soundButtonStyle.down = myGdxGameController.getScreenUtils()
					.getCircularSkin().getDrawable("sound.down");
			soundButtonStyle.checked = myGdxGameController.getScreenUtils()
					.getCircularSkin().getDrawable("sound.checked");

		}

		ImageButton soundButton = new ImageButton(soundButtonStyle);
		soundButton.setPosition(120, 30);
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
		if(myGdxGameController.getSoundController().backgroundMusicIsPlaying()) {
			musicButtonStyle.up = myGdxGameController.getScreenUtils()
					.getCircularSkin().getDrawable("music.checked");
			musicButtonStyle.down = myGdxGameController.getScreenUtils()
					.getCircularSkin().getDrawable("music.down");
			musicButtonStyle.checked = myGdxGameController.getScreenUtils()
					.getCircularSkin().getDrawable("music.up");
		}else{
			musicButtonStyle.up = myGdxGameController.getScreenUtils()
					.getCircularSkin().getDrawable("music.up");
			musicButtonStyle.down = myGdxGameController.getScreenUtils()
					.getCircularSkin().getDrawable("music.down");
			musicButtonStyle.checked = myGdxGameController.getScreenUtils()
					.getCircularSkin().getDrawable("music.checked");
		}
		ImageButton musicButton = new ImageButton(musicButtonStyle);
		musicButton.setPosition(20,30);
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
		
		// Pause button
		ImageButtonStyle pauseButtonStyle = new ImageButtonStyle();
		pauseButtonStyle.up = myGdxGameController.getScreenUtils()
				.getCircularSkin().getDrawable("pause.up");
		pauseButtonStyle.down = myGdxGameController.getScreenUtils()
				.getCircularSkin().getDrawable("play.up");
		pauseButtonStyle.checked = myGdxGameController.getScreenUtils()
				.getCircularSkin().getDrawable("play.up");
		ImageButton pauseButton = new ImageButton(pauseButtonStyle);
		pauseButton.setPosition(CoordinateConverter.getCameraWidth() - 130,
				CoordinateConverter.getCameraHeight() - 70);
		stage.addActor(pauseButton);
		pauseButton.toggle();
		pauseButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				myGdxGameController.setScreen(myGdxGameController
						.getInGameController());
			}
		});

		// Adding to the table and actors to the stage
		table.add(messageImage).center();
		table.getCell(messageImage).spaceBottom(50);
		table.row();
		table.add(resumeButton).center();
		table.getCell(resumeButton).spaceBottom(20);
		table.row();
		table.add(restartButton).center();
		table.getCell(restartButton).spaceBottom(20);
		table.row();
		table.setFillParent(true);

		stage.addActor(table);
		stage.addActor(soundButton);
		stage.addActor(musicButton);
		stage.addActor(homeButton);

		table.setSize(CoordinateConverter.getCameraWidth(), CoordinateConverter.getCameraHeight());
		stage.setViewport(CoordinateConverter.getCameraWidth(), CoordinateConverter.getCameraHeight(), true);
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
		try{
			stage.dispose();
		} catch (GdxRuntimeException e){
			Gdx.app.log("PauScreen", "Exception", e);
		}catch (Exception e) {			
		}
	}

}