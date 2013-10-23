package com.dat255_group3.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.GdxRuntimeException;

/**
 * Handles the sound.
 * 
 * @author The Hans-Gunnar Crew
 */

public class SoundController {
	private Sound victorySound;
	private Sound cookieSound;
	private Sound gameOverSound;
	private static float volume = 1.0f; //maximum volume
	private Music backgroundMusic;
	
	/**
	 * Constructs a new SoundController and loads all sound files.
	 */
	public SoundController() {
		try {
			victorySound = Gdx.audio.newSound(Gdx.files.internal("sounds/victory.mp3"));
			cookieSound = Gdx.audio.newSound(Gdx.files.internal("sounds/cookiebeep.mp3"));
			gameOverSound = Gdx.audio.newSound(Gdx.files.internal("sounds/fail.mp3"));
			backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/backgroundmusic.mp3"));
		}catch(GdxRuntimeException e){
			Gdx.app.log("SoundController", "constructor", e);
		}
	
	}
	
	/**
	 * Plays the victory sound at the maximum volume.
	 */
	public void playVictorySound() {
		victorySound.play(volume);
	}
	
	/**
	 * Plays the cookie sound at the maximum volume.
	 */
	public void playCookieSound() {
		cookieSound.play(volume);
	}
	
	/**
	 * Plays the game over sound at the maximum volume.
	 */
	public void playGameOverSound() {
		gameOverSound.play(volume);
	}
	
	public void setVolume(float volume) {
		SoundController.volume = volume;
	}
	
	/**
	 * Plays the background music and loops it.
	 */
	public void playBackgroundMusic() {
		backgroundMusic.play();
		backgroundMusic.setVolume(volume);
		backgroundMusic.setLooping(true);

	}

	/**
	 * Pauses the background music.
	 */
	public void pauseBackgroundMusic() {
		if(backgroundMusic.isPlaying()){ //borde inte backgroundMusicIsPlaying anropas här?
			backgroundMusic.pause();
		}
	}
	
	/**
	 * Checks if the background music is playing.
	 * 
	 * @return true if the background music is playing
	 */
	public boolean backgroundMusicIsPlaying() {
		return backgroundMusic.isPlaying();
	}
}
