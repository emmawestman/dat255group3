package com.dat255_group3.controller;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundController {
	private Audio audio;
	private Sound victorySound;
	private Sound cookieSound;
	private Sound gameOverSound;
	private float volume;
	
	public SoundController() {
		victorySound = audio.newSound(Gdx.files.internal("sounds/victory.mp3"));
		cookieSound = audio.newSound(Gdx.files.internal("sounds/cookieBeep.mp3"));
		gameOverSound = audio.newSound(Gdx.files.internal("sounds/fail.mp3"));
		volume = 1;
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

}
