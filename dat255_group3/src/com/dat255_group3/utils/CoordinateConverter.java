package com.dat255_group3.utils;

import com.badlogic.gdx.math.Vector2;

public class CoordinateConverter {
	static final float pixelToMeter=0.01f; //TODO change so they depend on screen resolution
	static final float meterToPixel=100f;
	
	public Vector2 pixelToMeter(Vector2 pos){
		pos.x = pos.x*pixelToMeter;
		pos.y = pos.y*pixelToMeter;
	    return pos;
	}
	
	public Vector2  meterToPixel(Vector2 pos){
		pos.x = pos.x*meterToPixel;
		pos.y = pos.y*meterToPixel;
	    return pos;
	}
}
