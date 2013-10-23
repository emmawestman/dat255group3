package com.dat255_group3.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Orientation;
import com.badlogic.gdx.Input.Peripheral;

/**
 * Contains methods for the accelerometer.
 * 
 * @author The Hans-Gunnar Crew
 */
public class GyroUtils {
	private static float maxPitch = 45;
	private static float minPitch = -45;
		
	/**
	 * If the device has a compass it checks the pitch of the device 
	 * and returns the speed as a percentage. Returns zero if the device
	 * doesn't have a compass.
	 * 
	 * @return speed as a percentage, or 0 if the device doesn't have a compass
	 */
	public static float gyroSteering(){
		if (Gdx.input.isPeripheralAvailable(Peripheral.Compass)){
			float pitch = Gdx.input.getPitch();
			
			// Updates the device according to orientation
			pitch = checkDeviceOrientation(pitch);

			// Makes sure the screen doesn't move backwards
			if (pitch > maxPitch) pitch = maxPitch;
			if (pitch < minPitch)  pitch = minPitch;
			return 1.0f-(1.5f*pitch*0.01f);
		}else{
			return 0.0f;
		}
	}

	/**
	 * Takes a pitch and updates it depending on the orientation
	 * of the device.
	 *  
	 * @param pitch the pitch that is to be updated
	 * @return pitch the updated pitch
	 */
	public static float checkDeviceOrientation(float pitch){

		Orientation orientation = Gdx.input.getNativeOrientation();
	
		if(orientation == Input.Orientation.valueOf("Landscape")){
			return pitch - 90;
		}else{
			return pitch;
		}
	}
}	