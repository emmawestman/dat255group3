package com.dat255_group3.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Orientation;
import com.badlogic.gdx.input.*;

public class GyroUtils {
	private static Orientation orientation;
	
	public static float getRotation(){

		//float pitch = Gdx.input.getPitch();
		float roll = Gdx.input.getRoll();
		//float azimuth = Gdx.input.getAzimuth();
		
		//Gdx.app.log("Viking","Rotation: "+Gdx.input.getRotation()+" | Orienation: "+Gdx.input.getNativeOrientation());
		return roll;
		
	}
	
	public static void gyroSteering(){
		if (orientation == Input.Orientation.valueOf("Portrait")){
			
		}else if(orientation == Input.Orientation.valueOf("Landscape")){
			
		}else{
			orientation = Gdx.input.getNativeOrientation();
		}
	}
	
}
