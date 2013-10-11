package com.dat255_group3.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class SoundController {
	private Sound victorySound;
	private Sound cookieSound;
	private Sound gameOverSound;
	private static float volume = 1.0f;
	private static Music backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/backgroundmusic.mp3"));
	
	public SoundController() {
		try {
			victorySound = Gdx.audio.newSound(Gdx.files.internal("sounds/victory.mp3"));
			cookieSound = Gdx.audio.newSound(Gdx.files.internal("sounds/cookiebeep.mp3"));
			gameOverSound = Gdx.audio.newSound(Gdx.files.internal("sounds/fail.mp3"));
		}catch(GdxRuntimeException e){
			Gdx.app.log("SoundController", "constructor", e);
		}
	
	}
	
	public void playVictorySound() {
		victorySound.play(volume);
	}
	
	public void playCookieSound() {
		cookieSound.play(volume);
	}
	
	public void playGameOverSound() {
		gameOverSound.play(volume);
	}
	
	public void setVolume(float volume) {
		SoundController.volume = volume;
	}
	
	public static void playBackgroundMusic() {
		 Gdx.app.log("Sound", "backgroundMusic: played");
//		backgroundMusic.setLooping(true);
		backgroundMusic.setVolume(volume);
		backgroundMusic.play();
	}

	public static void pauseBackgroundMusic() {
		 Gdx.app.log("Sound", "backgroundMusic: paused");
		if(backgroundMusic.isPlaying()){
			backgroundMusic.pause();
		}
	}
	
	public static boolean backgroundMusicIsPlaying() {
		return backgroundMusic.isPlaying();
	}
}