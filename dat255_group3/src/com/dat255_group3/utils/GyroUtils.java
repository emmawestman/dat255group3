package com.dat255_group3.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.*;

public class GyroUtils {
	
	public float GetRotation(){
		int rotation = Gdx.input.getRotation();
		
		float x = Gdx.input.getAccelerometerX();
		float y = Gdx.input.getAccelerometerY();
		float z = Gdx.input.getAccelerometerZ();
		
		Gdx.app.log("Viking","Rotation: "+rotation);
		Gdx.app.log("Viking","X: "+x);
		Gdx.app.log("Viking","Y: "+y);
		Gdx.app.log("Viking","Z: "+z);
		return 0;
		
	}
	
}
