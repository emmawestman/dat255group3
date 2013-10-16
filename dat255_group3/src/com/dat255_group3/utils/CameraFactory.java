package com.dat255_group3.utils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;

public class CameraFactory {
	public static OrthographicCamera Create(){
		// creates the camera
		OrthographicCamera camera = new OrthographicCamera(CoordinateConverter.getCameraWidth(), CoordinateConverter.getCameraHeight());
		
		// Sets the positions of the camera
		camera.position.set(CoordinateConverter.getCameraWidth()/2, CoordinateConverter.getCameraHeight()/2, 0);
		camera.update();
		
		// Sets how much of the map that is shown on the screen
		//TODO might never be used
		Rectangle glViewport = new Rectangle(0, 0, CoordinateConverter.getCameraWidth(), CoordinateConverter.getCameraHeight());
		
		return camera;
	}
}
