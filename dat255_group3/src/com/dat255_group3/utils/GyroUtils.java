package com.dat255_group3.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Orientation;

/**
 * @author TheHansGunnarCrew
 * A utility class that handles methods to do with the accelerometer
 */
public class GyroUtils {
	private static float upHill = 30;
	private static float downHill = -30;
		
	/**
	 * Checks the pitch of the device and 
	 * updates game speed according to it. 
	 */
	public static void gyroSteering(){
		float pitch = Gdx.input.getPitch();
				
		if (pitch>upHill){
			Gdx.app.log("Viking","Going up hill: "+pitch);
		}else if(pitch<downHill){
			Gdx.app.log("Viking","Going down hill: "+pitch);
		}else{
			Gdx.app.log("Viking","Staying on level "+pitch);
		}
	}

	/**
	 * Checks the native orientation of the device
	 * and updates the steering according to it.
	 */
	public void checkDeviceOrientation(){

		Orientation orientation = Gdx.input.getNativeOrientation();
	
		if (orientation == Input.Orientation.valueOf("Portrait")){
			upHill = 30;
			downHill = -30;
		}else if(orientation == Input.Orientation.valueOf("Landscape")){
			//These values are untested, 
			//and are intended to be used for tablet devices.
			upHill = 120;
			downHill = 60;
		}
	
	}
}