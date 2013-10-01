package com.dat255_group3.controller;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Mesh.VertexDataType;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;

/**
 * A controller class for the camera
 * @author Group 3
 *
 */
public class OrthographicCameraController implements ApplicationListener {

	private Mesh mesh;
	private Rectangle glViewport;
	private float speed;
	private OrthographicCamera camera;
	private int width;
	private int height;
	private boolean pause = false;


	/**
	 * Creates the controller
	 */
	@Override
	public void create() {
		//Sets the size and form of the whole map
		this.mesh = new Mesh(VertexDataType.VertexArray, false, 4, 6, 
				new VertexAttribute(VertexAttributes.Usage.Position, 3, "attr_position"));
		mesh.setVertices(new float[] {
				-1024f, -512f, 0,
				1024f, -512f, 0,
				1024f, 512f, 0,
				-1024f, 512f, 0
		});
		mesh.setIndices(new short[] {0, 1, 2, 2, 3, 0});

		//creates the camera
		camera = new OrthographicCamera(width, height);

		//Sets the positions of the camera
		camera.position.set(width/2, height/2, 0);
		camera.update();

		//Sets how much of the map that is shown on the screen
		glViewport = new Rectangle(0, 0, width, height);

		//Sets how fast the camera moves
		this.speed = 0.8f;
	}

	/**
	 * Creates the controller with the width and height of the screen
	 * @param width
	 * 			The widht of the screen
	 * @param height
	 * 			The height of the screen
	 */
	public void create(int width, int height) {
		//The width and height of the screen
		this.width = width;
		this.height = height; 
		create();
	}

	/**
	 * A method to resize how much the camera is showing.
	 */
	@Override
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
		glViewport.setSize(width, height);


	}
	/**
	 * A method to update the camera and move it sideways.
	 */

	@Override
	public void render() {
		if(!pause) {
			camera.translate(speed, 0, 0);
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





}
