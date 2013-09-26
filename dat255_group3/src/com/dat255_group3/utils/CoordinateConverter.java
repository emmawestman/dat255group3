package com.dat255_group3.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class CoordinateConverter {
	static final float pixelToMeter=0.01f; //TODO change so they depend on screen resolution
	static final float meterToPixel=100f;
	private static int ScreenWidth;
	private static int ScreenHeight;
	
	public Vector2 pixelToMeter(Vector2 pos){
		pos.x = pos.x*pixelToMeter;
		pos.y = pos.y*pixelToMeter;
	    return pos;
	}
	
	public int getScreenWidth() {
		return ScreenWidth;
	}

	public static void setScreenWidth(int screenWidth) {
		ScreenWidth = screenWidth;
		Gdx.app.log("Viking", "screenWidth: "+screenWidth);
		
	}

	public int getScreenHeight() {
		return ScreenHeight;
		
	}

	public static void setScreenHeight(int screenHeight) {
		ScreenHeight = screenHeight;
		Gdx.app.log("Viking", "screenHeight: "+screenHeight);
	}

	public Vector2  meterToPixel(Vector2 pos){
		pos.x = pos.x*meterToPixel;
		pos.y = pos.y*meterToPixel;
	    return pos;
	}
}
