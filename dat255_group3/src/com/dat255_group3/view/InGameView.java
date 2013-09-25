package com.dat255_group3.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;

public class InGameView {
	public InGameView () {
		
	}
	
	
	
	public void render() {
		//show a green screen
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		//If: Rita världen
		
		//Sköta layouts = lyssnar av olika touch -> 
		
		//If: Vinna -> visa vinna.
	
	}
	
	public void drawJump(){
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	}
}
