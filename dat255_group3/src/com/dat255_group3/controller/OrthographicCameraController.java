package com.dat255_group3.controller;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Mesh.VertexDataType;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.math.Rectangle;
import com.dat255_group3.utils.CoordinateConverter;

/**
 * A controller class for the camera
 * @author Group 3
 *
 */
public class OrthographicCameraController implements ApplicationListener {

	private Mesh mesh;
	private Rectangle glViewport;
	private float speedP;
	private OrthographicCamera camera;
	private boolean pause = false;


	/**
	 * Creates the controller
	 */
	@Override
	public void create() {
		
		// Sets the size and form of the whole map
		this.mesh = new Mesh(VertexDataType.VertexArray, false, 4, 6, 
				new VertexAttribute(VertexAttributes.Usage.Position, 3, "attr_position"));
		float heightConverter = CoordinateConverter.getScreenHeight()/512;
		float widthConverter = CoordinateConverter.getScreenWidth()/1024;
		mesh.setVertices(new float[] {
				-1024f*widthConverter, -512f*heightConverter, 0,
				1024f*widthConverter, -512f*heightConverter, 0,
				1024f*widthConverter, 512f*heightConverter, 0,
				-1024f*widthConverter, 512f*heightConverter, 0
		});
		mesh.setIndices(new short[] {0, 1, 2, 2, 3, 0});

		// creates the camera
//		camera = new OrthographicCamera(CoordinateConverter.getScreenWidth(), CoordinateConverter.getScreenHeight());
		camera = new OrthographicCamera(800, 512);

		// Sets the positions of the camera
//		camera.position.set(CoordinateConverter.getScreenWidth()/2, CoordinateConverter.getScreenHeight()/2, 0);
		camera.position.set(800/2, 512/2, 0);
		camera.update();

		// Sets how much of the map that is shown on the screen
//		glViewport = new Rectangle(0, 0, CoordinateConverter.getScreenWidth(), CoordinateConverter.getScreenHeight());
		glViewport = new Rectangle(0, 0, 800, 512);

	}


	/**
	 * A method to resize how much the camera is showing.
	 */
	@Override
	public void resize(int width, int height) {
		glViewport.setSize(width, height);


	}
	/**
	 * A method to update the camera and move it sideways.
	 */

	@Override
	public void render() {
		if(!pause) {
			camera.translate(speedP, 0, 0);
			camera.update();
		}

	}

	/**
	 * A method to pause the cameras movement
	 */
	@Override
	public void pause() {
		pause = true;

	}

	/**
	 * A method to resume the paused camera
	 */
	@Override
	public void resume() {
		pause = false;

	}

	/**
	 * The method that is called right before the application is destroyed.
	 * Calls the pause method
	 */
	@Override
	public void dispose() {
		pause();

	}

	/**
	 * A method to get the camera
	 * @return
	 * 		The camera
	 */
	public OrthographicCamera getCamera() {
		return camera;
	}

	/**
	 * A method to set the speed in pixels.
	 * @param speedP
	 * 			The speed in pixels.
	 */
	public void setSpeedP(float speedP) {
		this.speedP = speedP;
	}
	
	/**
	 * A method to get the current speed in pixels.
	 * @return
	 * 		The current speed in pixels.
	 */
	public float getSpeedP() {
		return speedP;
	}




}
