package com.dat255_group3.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Orientation;
import com.badlogic.gdx.input.*;

public class GyroUtils {
	
	/*
	public static float getRotation(){
		//float pitch = Gdx.input.getPitch();
		float pitch = Gdx.input.getpitch();
		//float azimuth = Gdx.input.getAzimuth();
		
		//Gdx.app.log("Viking","Rotation: "+Gdx.input.getRotation()+" | Orienation: "+Gdx.input.getNativeOrientation());
		return pitch;
	}
	*/ 
	
	public static void gyroSteering(){
		float pitch = Gdx.input.getPitch();
		float upHill = 30;
		float downHill = -30;
				
		if (pitch>upHill){
			Gdx.app.log("Viking","Going up hill: "+pitch);
		}else if(pitch<downHill){
			Gdx.app.log("Viking","Going down hill: "+pitch);
		}else{
			Gdx.app.log("Viking","Staying on level "+pitch);
		}
	}
	
	/*
	private static Orientation orientation;

	if (orientation == Input.Orientation.valueOf("Portrait")){
		upHill = 30;
		downHill = -30;
	}else if(orientation == Input.Orientation.valueOf("Landscape")){
		//These values are untested, and intended to be used for tablet devices.
		upHill = 120;
		downHill = 60;
	}else{
		orientation = Gdx.input.getNativeOrientation();
	}
	*/

	
}
