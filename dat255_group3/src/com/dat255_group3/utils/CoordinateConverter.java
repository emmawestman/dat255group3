package com.dat255_group3.utils;

import com.badlogic.gdx.math.Vector2;
/**
 * A util class containing methds for converting float and Vector2 values 
 * between pixels and meters. The cameraWidth represents 10 meters. 
 * 
 *
 */
public class CoordinateConverter {
	private static float cameraWidth = 1024f;
	private static float CameraHeight = 512f;
	
	/**
	 * converts a pixels into meters, 10 meter = cameraWidth
	 * @param pixels
	 * @return meters
	 */
	public static float pixelToMeter(float pixels){
		float pixelToMeter = getPixelToMeter();
		float meters = 0;
		meters = pixels*pixelToMeter;
	    return meters;
	}
	/**
	 * converts a Vector2 of pixels to meters, 10 meter = cameraWidth
	 * @param pixels
	 * @return meters
	 */
	public static Vector2 pixelToMeter(Vector2 pixels){
		Vector2 meters = new Vector2 ();
		meters.x =pixelToMeter(pixels.x);
		meters.y = pixelToMeter(pixels.y);
	    return meters;
	}
	/**
	 * converts meters into pixels, 10 meter = cameraWidth
	 * @param meters
	 * @return pixels
	 */
	public static float  meterToPixel(float meters){
		float meterToPixel = getMeterToPixel();
		float pixels = 0;
		pixels = meters*meterToPixel;
	    return pixels;
	}
	/**
	 * converts a Vector2 of meters into pixels, 10 meter = cameraWidth
	 * @param meters
	 * @return pixels
	 */
	public static Vector2  meterToPixel(Vector2 meters){
		Vector2 pixels = new Vector2 ();
		pixels.x = meterToPixel(meters.x);
		pixels.y = meterToPixel(meters.y);
	    return pixels;
	}
	
	/**
	 * get the number of meters in 1 pixel
	 * @return amount
	 */
	public static float getPixelToMeter(){
		return 10f/(float)CoordinateConverter.cameraWidth;
	}
	/**
	 * get the number of pixels in 1 meter
	 * @return amount
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
