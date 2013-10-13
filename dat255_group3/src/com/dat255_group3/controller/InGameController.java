package com.dat255_group3.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.dat255_group3.io.IOHandler;
import com.dat255_group3.model.InGame;
import com.dat255_group3.utils.CoordinateConverter;
import com.dat255_group3.utils.GyroUtils;
import com.dat255_group3.view.InGameView;

public class InGameController implements Screen {

	private InGame inGame;
	private InGameView inGameView;
	private WorldController worldController;
	private MyGdxGameController myGdxGameController;
	private float timeStep = 1.0f / 10.0f;
	private final int velocityIterations = 6;
	private final int positionIterations = 2;
	private TiledMap map;
	private OrthographicCameraController cameraController;
	private Box2DDebugRenderer renderer = new Box2DDebugRenderer(true, true, true, true, true, true);
	private boolean gameOver;

	public InGameController(MyGdxGameController myGdxGameController) {
		this.myGdxGameController = myGdxGameController;
		this.cameraController = new OrthographicCameraController();
		this.cameraController.create();
		IOHandler.readScore();
	}

	@Override
	public void render(float delta) {
		// Shows a white screen
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		/*
		 * Checks whether the backbutton has been pressed. If so, a
		 * pausepop-up-screen will be shown.
		 */

		if (Gdx.input.isKeyPressed(Keys.BACK)) {
			Gdx.input.setCatchBackKey(true);
			myGdxGameController.setScreen(myGdxGameController.getPauseScreen());

		}

		if (hasWon()) {
			// Change to gamewon-screen
			// worldController.getSoundController().playVictorySound();
			// worldController.getSoundController().pauseBackgroundMusic();

			this.gameOver = false;
			gameOver();
		}

		if (this.worldController.getCharacterController().getCharacter()
				.isDead()) {
			this.gameOver = true;
			// worldController.getSoundController().playGameOverSound();
			// worldController.getSoundController().pauseBackgroundMusic();
			gameOver();
		}

		update(delta);

		// check collision with the closest cookie
		worldController.checkNextCookie();

		// draws the world and its components
		this.inGameView.draw(this.worldController.getWorldView(),this.worldController.getCharBody(), 
				this.worldController.getCharacterController().getCharacterView(),
				this.worldController.getCookieController().getCookieView(),
				worldController.getWorld().getTime(), worldController.getWorld().getCookieCounter(), gameOver);

		/*
		 * Checks whether the screen has been touched. If so, a method which
		 * will make the character jump is invoked.
		 */
		if (Gdx.input.isTouched()) {
			worldController.getCharacterController().tryToJump();
		}
		
	}


	@Override
	public void show() {
		if (myGdxGameController.getMyGdxGame().getIsGameStarted() == false) {
			this.cameraController = new OrthographicCameraController();
			this.cameraController.create();
			loadMap();
			this.inGameView = new InGameView(map, cameraController.getCamera());
			this.inGame = new InGame();
			this.worldController = new WorldController(this, inGame.getSpeedM());
			this.gameOver = false;
			myGdxGameController.getMyGdxGame().setIsGameStarted(true);
		}
		this.cameraController.resume();

	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
		myGdxGameController.setScreen(myGdxGameController.getPauseScreen());
		worldController.getSoundController().pauseBackgroundMusic();
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
		map.dispose();
		cameraController.dispose();
		renderer.dispose();
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

	public boolean hasWon() {
		return worldController.getCharacterController().getCharacter()
				.getPosition().x >= worldController.getFinishLineX();
	}


	public void save(){
		if (this.inGame.isNewHighScore("level1", this.myGdxGameController.getPlayerController().getPlayer().getScore())) {
			IOHandler.saveNewHigscore("Level1", this.myGdxGameController.getPlayerController().getPlayer().getScore());
			Gdx.app.log("save new hs InGameController", "");
		}
		if ( ! (IOHandler.contains("level1"))) {
			IOHandler.saveScore("Level1", this.myGdxGameController.getPlayerController().getPlayer().getScore());
			Gdx.app.log("Save in GameController", "Score:" + 
					this.myGdxGameController.getPlayerController().getPlayer().getScore() );
		}

	}

	public void reset() {
		//reset
	}

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
		inGame.setSpeedM(0.5f * GyroUtils.gyroSteering());

		// Updates the speed
		inGame.setSpeedP(CoordinateConverter.meterToPixel(inGame.getSpeedM()
				* delta));
		cameraController.setSpeedP(inGame.getSpeedP());

		// give character speed
		if (this.worldController.getCharBody().getLinearVelocity().x < this.inGame
				.getSpeedM()) {
			this.worldController.getCharBody().applyForceToCenter(
					new Vector2(5, 0), true);
		}

		// update the model position for the character
		this.worldController.uppdatePositions(this.worldController
				.getCharBody(), this.worldController.getCharacterController()
				.getCharacter());

		// Update the position of the death limit
		worldController.getCharacterController().getCharacter()
				.moveDeathLimit(inGame.getSpeedP());
	}

	public void gameOver() {
		Gdx.app.log("Game over:", gameOver + "");
		myGdxGameController.getMyGdxGame().setIsGameStarted(false);
		// Change to gameover-screen

		this.myGdxGameController.getPlayerController().getPlayer().calculateScore(worldController.getWorld().getTime(),
						worldController.getWorld().getCookieCounter(), gameOver);
		myGdxGameController.getGameOverScreen().gameOver(this.myGdxGameController.getPlayerController().getPlayer()
						.getScore(), worldController.getWorld().getTime(), gameOver);
		myGdxGameController.setScreen(myGdxGameController.getGameOverScreen());
	}

	public void loadMap() {
		try {
			map = new TmxMapLoader().load("worlds/map"
					+ myGdxGameController.getMyGdxGame().getCurrentLevel()
					+ ".tmx");
		} catch (GdxRuntimeException e) {
			Gdx.app.log("InGameController", "loadMap()", e);
		}
	}
}
