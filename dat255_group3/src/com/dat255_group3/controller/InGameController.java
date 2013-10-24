package com.dat255_group3.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.dat255_group3.model.InGame;
import com.dat255_group3.utils.CoordinateConverter;
import com.dat255_group3.utils.GyroUtils;
import com.dat255_group3.utils.InputStage;
import com.dat255_group3.utils.InputStage.OnHardKeyListener;
import com.dat255_group3.view.InGameView;

/**
 * The controller class which controls the entire game.
 * 
 * @author The Hans-Gunnar Crew
 */
public class InGameController implements Screen {

	private InGame inGame;
	private InGameView inGameView;
	private WorldController worldController;
	private float timeStep = 1.0f / 10.0f; 
	private OneMoreCookiePleaseController oneMoreCookiePleaseController;
	private final int velocityIterations = 6;
	private final int positionIterations = 2;
	private TiledMap map;
	private OrthographicCameraController cameraController;
	private boolean gameOver;
	private boolean isCountingDown = true;
	private InputStage stage;

	/**
	 * Constructs a new InGameController with the specified OneMoreCookiePleaseController object.
	 * 
	 * @param myGdxGameController
	 */

	public InGameController(OneMoreCookiePleaseController oneMoreCookiePleaseController) {
		this.oneMoreCookiePleaseController = oneMoreCookiePleaseController;
		this.cameraController = new OrthographicCameraController();
		this.cameraController.create();
		this.stage = new InputStage(CoordinateConverter.getCameraWidth(),
				CoordinateConverter.getCameraHeight(), true);
	}

	@Override
	public void render(float delta) {
		// Shows a white screen in case background does not load
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Update the stage actors
		stage.act(delta);
		inGame.setDelayTime(inGame.getDelayTime() + delta);



		// Checks if the game is over or won
		if (hasWon()) {
			this.gameOver = false;
			gameOver();
		}

		if (this.worldController.getCharacterController().getCharacter()
				.isDead()) {
			this.gameOver = true;
			gameOver();
		}

		// check collision with the closest cookie
		worldController.checkCookies();

		// draws the world and its components
		this.inGameView.draw(this.worldController.getWorldView(),
				this.worldController.getCharBody(), this.worldController
				.getCharacterController().getCharacterView(),
				this.worldController.getCookieController().getCookieView(),
				this.worldController.getEnemy(), worldController.getWorld()
				.getTime(), worldController.getWorld()
				.getCookieCounter(), gameOver);


		stage.draw();


		// Checks whether the screen has been touched. If so, a method which
		// will make the character jump is invoked.
		if (Gdx.input.isTouched()) {
			worldController.getCharacterController().tryToJump();
		}

		// Count Down before the game starts to move
		if (isCountingDown) {
			if (inGame.getDelayTime() <= 1.0) {
				inGameView.drawCountDownNbr(inGame.getDelayTime());

			} else if (inGame.getDelayTime() <= 2.0) {
				inGameView.drawCountDownNbr(inGame.getDelayTime());

			} else if (inGame.getDelayTime() <= 3.0) {
				inGameView.drawCountDownNbr(inGame.getDelayTime());
			} else {
				isCountingDown = false;
			}
		} else {
			update(delta);
		}
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		Gdx.input.setCatchBackKey(true);

		// Checks whether the back button has been pressed. If so, a
		// pause pop-up-screen will be shown.
		stage.setHardKeyListener(new OnHardKeyListener() {
			@Override
			public void onHardKey(int keyCode, int state) {
				if (keyCode == Keys.BACK && state == 1) {
					oneMoreCookiePleaseController.setScreen(oneMoreCookiePleaseController
							.getPauseScreen());
				}
			}
		});

		// Pause button
		ImageButtonStyle pauseButtonStyle = new ImageButtonStyle();
		pauseButtonStyle.up = oneMoreCookiePleaseController.getScreenUtils()
				.getCircularSkin().getDrawable("play.up");
		pauseButtonStyle.down = oneMoreCookiePleaseController.getScreenUtils()
				.getCircularSkin().getDrawable("play.down");
		pauseButtonStyle.checked = oneMoreCookiePleaseController.getScreenUtils()
				.getCircularSkin().getDrawable("pause.up");
		ImageButton pauseButton = new ImageButton(pauseButtonStyle);
		pauseButton.setPosition(CoordinateConverter.getCameraWidth() - 130,
				CoordinateConverter.getCameraHeight() - 70);
		stage.addActor(pauseButton);
		pauseButton.toggle();
		pauseButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				InGameController.this.pause();
			}
		});

		if (oneMoreCookiePleaseController.getOneMoreCookiePlease().getIsGameStarted() == false) {
			this.cameraController = new OrthographicCameraController();
			this.cameraController.create();
			loadMap();
			this.inGameView = new InGameView(map, cameraController.getCamera(), this.oneMoreCookiePleaseController.getOneMoreCookiePlease().getCurrentLevel());
			this.inGame = new InGame();
			this.worldController = new WorldController(this, inGame.getSpeedM());
			this.gameOver = false;
			oneMoreCookiePleaseController.getOneMoreCookiePlease().setIsGameStarted(true);
		}
		this.cameraController.resume();
		inGame.setDelayTime(0);
		isCountingDown = true;

	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
		oneMoreCookiePleaseController.setScreen(oneMoreCookiePleaseController.getPauseScreen());
		cameraController.pause();
	}

	@Override
	public void resume() {
		cameraController.resume();
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		try {
			map.dispose();
			cameraController.dispose();
		} catch (GdxRuntimeException e) {
			Gdx.app.log("InGameController", "Exception", e);
		} catch (Exception e) {
		}

	}

	public InGame getInGame() {
		return inGame;
	}

	public InGameView getInGameView() {
		return inGameView;
	}

	public TiledMap getMap() {
		return map;
	}

	public OrthographicCamera getCamera() {
		return cameraController.getCamera();
	}

	/**
	 * Checks if the game has been won.
	 * 
	 * @return true if the character has reached the finish line
	 */
	public boolean hasWon() {
		return worldController.getCharacterController().getCharacter()
				.getPosition().x >= worldController.getFinishLineX();
	}

	/**
	 * Saves the player's score, if it's a new high score.
	 */
	public void save() {
		//  if score > high score for the current level
		if (this.oneMoreCookiePleaseController.getPlayerController().getPlayer().getScore() > 
		this.oneMoreCookiePleaseController.getPlayerController().getPlayer()
		.getHighScore(oneMoreCookiePleaseController.getOneMoreCookiePlease().getCurrentLevel())) {

			oneMoreCookiePleaseController.getPlayerController().getPlayer().setNewHighScore
			(this.oneMoreCookiePleaseController.getOneMoreCookiePlease().getCurrentLevel(),
					this.oneMoreCookiePleaseController.getPlayerController().getPlayer().getScore());
		}
	}

	public void reset() {
		// reset
	}

	/**
	 * Updates the game.
	 * 
	 * @param delta the time in seconds since the last render
	 */
	public void update(float delta) {

		this.timeStep = delta;

		// update the physics
		this.worldController.getPhysicsWorld().step(this.timeStep,
				this.velocityIterations, this.positionIterations);

		// Update the position of the camera
		cameraController.render();

		// update the time
		worldController.getWorld().setTime(
				worldController.getWorld().getTime() + delta);

		// Check the pitch of the device and changes the speed
		inGame.setSpeedM(1.5f * GyroUtils.gyroSteering());

		// Updates the speed of the camera and enemy
		this.inGame.updateSpeedP(delta);
		cameraController.setSpeedP(inGame.getSpeedP());

		// give character speed
		this.worldController.moveCharacter(this.inGame.getSpeedM());

		// update the model position for the character to the position
		// of the physical body
		this.worldController.uppdatePositions(this.worldController
				.getCharBody(), this.worldController.getCharacterController()
				.getCharacter());

		// Update the position of the death limit
		worldController.getCharacterController().getCharacter()
		.moveDeathLimit(inGame.getSpeedP());
	}

	/**
	 * Called when the game is lost.
	 */
	public void gameOver() {
		oneMoreCookiePleaseController.getOneMoreCookiePlease().setIsGameStarted(false);

		// calculate the score
		this.oneMoreCookiePleaseController
		.getPlayerController().getPlayer()
		.calculateScore(worldController.getWorld().getTime(),
				worldController.getWorld().getCookieCounter(), gameOver);

		save();

		// Change to game over-screen
		oneMoreCookiePleaseController.getGameOverScreen().gameOver(
				this.oneMoreCookiePleaseController.getPlayerController().getPlayer()
				.getScore(), worldController.getWorld().getTime(),
				gameOver);
		oneMoreCookiePleaseController.setScreen(oneMoreCookiePleaseController.getGameOverScreen());
		if (OneMoreCookiePleaseController.soundEffectsOn()) {
			if (!gameOver) {
				worldController.getSoundController().playVictorySound();
			} else {
				worldController.getSoundController().playGameOverSound();
			}
		}
	}

	/**
	 * Loads the level's map.
	 */
	public void loadMap() {
		try {
			map = new TmxMapLoader().load("worlds/map"
					+ oneMoreCookiePleaseController.getOneMoreCookiePlease().getCurrentLevel()
					+ ".tmx");
		} catch (GdxRuntimeException e) {
			Gdx.app.log("InGameController", "loadMap()", e);
		}
	}
}
