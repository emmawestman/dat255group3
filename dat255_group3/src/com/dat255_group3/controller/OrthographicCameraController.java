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
 * The controller class for the camera.
 * 
 * @author The Hans-Gunnar Crew
 *
 */
public class OrthographicCameraController implements ApplicationListener {

	private Mesh mesh;
	private Rectangle glViewport;
	private float speedP;
	private OrthographicCamera camera;
	private boolean pause = false;

	@Override
	public void create() {
		
		// Sets the size and form of the whole map
		this.mesh = new Mesh(VertexDataType.VertexArray, false, 4, 6, 
				new VertexAttribute(VertexAttributes.Usage.Position, 3, "attr_position"));

		mesh.setVertices(new float[] {
				-8192f, -512f, 0,
				8192f, -512f, 0,
				8192f, 512f, 0,
				-8192f, 512f, 0
		});
		mesh.setIndices(new short[] {0, 1, 2, 2, 3, 0});

		// creates the camera
		camera = new OrthographicCamera(CoordinateConverter.getCameraWidth(), CoordinateConverter.getCameraHeight());

		// Sets the positions of the camera
		camera.position.set(CoordinateConverter.getCameraWidth()/2, CoordinateConverter.getCameraHeight()/2, 0);
		camera.update();

		// Sets how much of the map that is shown on the screen
		//TODO might never be used
		glViewport = new Rectangle(0, 0, CoordinateConverter.getCameraWidth(), CoordinateConverter.getCameraHeight());
		
	}

	@Override
	public void resize(int width, int height) {
		glViewport.setSize(width, height);


	}

	@Override
	public void render() {
		if(!pause) {
			camera.translate(speedP, 0, 0); //moves the camera sideways
			camera.update();
		}

	}

	@Override
	public void pause() {
		pause = true;

	}

	@Override
	public void resume() {
		pause = false;

	}

	@Override
	public void dispose() {
		pause();

	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public void setSpeedP(float speedP) {
		this.speedP = speedP;
	}
	
	public float getSpeedP() {
		return speedP;
	}
}
