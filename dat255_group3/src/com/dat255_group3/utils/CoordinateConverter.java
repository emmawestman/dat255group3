package com.dat255_group3.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class CoordinateConverter {
	private static int ScreenWidth;
	private static int ScreenHeight;
	
	
	
	public Vector2 pixelToMeter(Vector2 pixels){
		float pixelToMeter = 10/this.ScreenWidth;
		Vector2 meters = new Vector2 ();
		meters.x = pixels.x*pixelToMeter;
		meters.y = pixels.y*pixelToMeter;
	    return meters;
	}
	
	public Vector2  meterToPixel(Vector2 meters){
		float meterToPixel=this.ScreenWidth/10;
		Vector2 pixels = new Vector2 ();
		pixels.x = meters.x*meterToPixel;
		pixels.y = meters.y*meterToPixel;
	    return pixels;
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

	
}
