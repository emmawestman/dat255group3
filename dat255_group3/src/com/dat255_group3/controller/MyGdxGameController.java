package com.dat255_group3.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.dat255_group3.model.MyGdxGame;
import com.dat255_group3.screen.ExitPopUpScreen;
import com.dat255_group3.screen.GameCompletedScreen;
import com.dat255_group3.screen.GameOverScreen;
import com.dat255_group3.screen.HighScoreScreen;
import com.dat255_group3.screen.LevelScreen;
import com.dat255_group3.screen.PauseScreen;
import com.dat255_group3.screen.StartScreen;
import com.dat255_group3.utils.ScreenUtils;




public class MyGdxGameController extends Game {
	private MyGdxGame myGdxGame;
	private ScreenUtils screenUtils;
	private InGameController inGameController;
	private PlayerController playerController;
	private StartScreen startScreen;
	private LevelScreen levelScreen;
	private GameOverScreen gameOverScreen;
	private PauseScreen pauseScreen;
	private ExitPopUpScreen exitPopUpScreen;
	private HighScoreScreen highScoreScreen;
	private GameCompletedScreen gameCompletedScreen;
	private SoundController soundController;

	private static boolean soundEffectsOn = true;

	@Override
	public void create() {
		// create other the scenes and the player and the gameModel
		this.myGdxGame = new MyGdxGame();
		this.screenUtils = new ScreenUtils();
		this.playerController = new PlayerController();
		this.inGameController = new InGameController(this);
		this.startScreen = new StartScreen(this);
		this.levelScreen = new LevelScreen(this);
		this.gameOverScreen = new GameOverScreen(this);
		this.pauseScreen = new PauseScreen(this);
		this.gameCompletedScreen = new GameCompletedScreen(this);
		this.exitPopUpScreen = new ExitPopUpScreen(this);
		this.highScoreScreen = new HighScoreScreen(this);
		this.soundController = new SoundController();
		this.soundController.playBackgroundMusic();

		// go to the first screen
		setScreen(startScreen);
	}

	@Override
	public void dispose() {
		super.dispose();
		inGameController.dispose();
		screenUtils.dispose();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	public MyGdxGame getMyGdxGame() {
		return myGdxGame;
	}
	
	public ScreenUtils getScreenUtils(){
		return screenUtils;
	}

	public InGameController getInGameController() {
		return inGameController;
	}

	public PlayerController getPlayerController() {
		return this.playerController;
	}

	public StartScreen getStartScreen() {
		return startScreen;
	}

	public LevelScreen getLevelScreen() {
		return this.levelScreen;
	}

	public PauseScreen getPauseScreen() {
		return this.pauseScreen;
	}
	
	public ExitPopUpScreen getExitPopUpScreen() {
		return this.exitPopUpScreen;
	}
	
	public HighScoreScreen getHighScoreScreen() {
		return this.highScoreScreen;
	}

	public GameOverScreen getGameOverScreen() {
		return this.gameOverScreen;
	}

	public GameCompletedScreen getUnlockedScreen() {
		return gameCompletedScreen;
	}

	public void soundEffectsOn(boolean soundOn) {
		soundEffectsOn = soundOn;
	}

	public static boolean soundEffectsOn() {
		return soundEffectsOn;
	}

	public void save() {
		inGameController.save();
		Gdx.app.log("MyGdx", "Save");
	}
	
	public SoundController getSoundController() {
		return soundController;
	}

}
