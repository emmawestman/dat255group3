package com.dat255_group3.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Orientation;

/**
 * @author TheHansGunnarCrew
 * A utility class that handles methods to do with the accelerometer
 */
public class GyroUtils {
	private static float upHill = 25;
	private static float downHill = -25;
	private static float maxPitch = 45;
	private static float minPitch = -45;
		
	/**
	 * Checks the pitch of the device and returns speed in as a percentage. 
	 * @return speed as a percentage
	 */
	public static float gyroSteering(){
		float pitch = Gdx.input.getPitch();
		// Updates the device according to orientation
		pitch = checkDeviceOrientation(pitch);

		// Makes sure the screen doesn't move backwards
		if (pitch > maxPitch) pitch = maxPitch;
		if (pitch < minPitch)  pitch = minPitch;
		return 1.0f-(2*pitch*0.01f);
	}

	/**
	 * Takes a pitch and updates it depending on the orientation
	 * of the device. 
	 * @param pitch
	 * @return pitch
	 */
	public static float checkDeviceOrientation(float pitch){

		Orientation orientation = Gdx.input.getNativeOrientation();
	
		if(orientation == Input.Orientation.valueOf("Landscape")){
			//These values are untested, 
			//and are intended to be used for tablet devices.
			return pitch - 90;
		}else{
			return pitch;
		}
	
	}
}	