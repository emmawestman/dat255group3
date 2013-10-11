package com.dat255_group3.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class SoundController {
	private Sound victorySound;
	private Sound cookieSound;
	private Sound gameOverSound;
	private float volume;
	private Music backgroundMusic;
	
	public SoundController() {
		try {
			victorySound = Gdx.audio.newSound(Gdx.files.internal("sounds/victory.mp3"));
			cookieSound = Gdx.audio.newSound(Gdx.files.internal("sounds/cookiebeep.mp3"));
			gameOverSound = Gdx.audio.newSound(Gdx.files.internal("sounds/fail.mp3"));
			backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/backgroundmusic.mp3"));
			volume = 1.0f;
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
		this.volume = volume;
	}
	
	public void playBackgroundMusic() {
		backgroundMusic.setLooping(true);
		backgroundMusic.setVolume(volume);
		backgroundMusic.play();
	}

	public void pauseBackgroundMusic() {
		backgroundMusic.pause();
	}
}
