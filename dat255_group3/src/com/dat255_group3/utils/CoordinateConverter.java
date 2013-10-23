package com.dat255_group3.utils;

import com.badlogic.gdx.math.Vector2;
/**
 * A util class containing methods for converting float and Vector2 values 
 * between pixels and meters. The cameraWidth represents 10 meters. 
 * 
 *@author The Hans-Gunnar Crew
 */
public class CoordinateConverter {
	private static float cameraWidth = 1024f;
	private static float CameraHeight = 512f;
	
	/**
	 * Converts pixels into meters, 10 meter = cameraWidth.
	 * 
	 * @param pixels the value in pixels that needs to be converted
	 * @return meters the converted value in meters
	 */
	public static float pixelToMeter(float pixels){
		float pixelToMeter = getPixelToMeter();
		float meters = 0;
		meters = pixels*pixelToMeter;
	    return meters;
	}
	
	/**
	 * Converts a Vector2 with pixel values to meters, 10 meter = cameraWidth.
	 * 
	 * @param pixels the Vector2 object that needs to be converted
	 * @return meters the converted Vector2 in meters
	 */
	public static Vector2 pixelToMeter(Vector2 pixels){
		Vector2 meters = new Vector2 ();
		meters.x =pixelToMeter(pixels.x);
		meters.y = pixelToMeter(pixels.y);
	    return meters;
	}
	
	/**
	 * Converts meters into pixels, 10 meter = cameraWidth.
	 * 
	 * @param meters the value in meters that needs to be converted
	 * @return pixels the converted value in pixels
	 */
	public static float  meterToPixel(float meters){
		float meterToPixel = getMeterToPixel();
		float pixels = 0;
		pixels = meters*meterToPixel;
	    return pixels;
	}
	
	/**
	 * Converts a Vector2 with meter values into pixels, 10 meter = cameraWidth.
	 * 
	 * @param pixels the Vector2 object that needs to be converted
	 * @return meters the converted Vector2 in pixels
	 */
	public static Vector2  meterToPixel(Vector2 meters){
		Vector2 pixels = new Vector2 ();
		pixels.x = meterToPixel(meters.x);
		pixels.y = meterToPixel(meters.y);
	    return pixels;
	}
	
	/**
	 * Returns the number of meters in 1 pixel.
	 * 
	 * @return amount the number of meters/pixel
	 */
	public static float getPixelToMeter(){
		return 10f/(float)CoordinateConverter.cameraWidth;
	}
	
	/**
	 * Returns the number of pixels in 1 meter.
	 * 
	 * @return amount the number of pixels/meter
	 */
	public static float getMeterToPixel(){
		return (float)CoordinateConverter.cameraWidth/10f;
	}
	
	public static float getCameraHeight() {
		return CameraHeight;
	}
	public static void setCameraHeight(float cameraHeight) {
		CameraHeight = cameraHeight;
	}
	public static float getCameraWidth() {
		return cameraWidth;
	}
	public static void setCameraWidth(float cameraWidth) {
		CoordinateConverter.cameraWidth = cameraWidth;
	}
}
