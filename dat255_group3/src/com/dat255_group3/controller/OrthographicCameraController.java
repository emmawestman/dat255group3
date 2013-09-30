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

public class OrthographicCameraController implements ApplicationListener {
	
	private Mesh mesh;
	private Rectangle glViewport;
	private float speed;
	private OrthographicCamera camera;
	private int width;
	private int height;

	@Override
	public void create() {
		this.mesh = new Mesh(VertexDataType.VertexArray, false, 4, 6, 
				new VertexAttribute(VertexAttributes.Usage.Position, 3, "attr_position"));
		mesh.setVertices(new float[] {
				-1024f, -512f, 0,
				1024f, -512f, 0,
				1024f, 512f, 0,
				-1024f, 512f, 0
		});
		mesh.setIndices(new short[] {0, 1, 2, 2, 3, 0});
		camera = new OrthographicCamera(width, height);
		camera.position.set(width/2, height/2, 0);
		camera.update();
		glViewport = new Rectangle(0, 0, width, height);
		this.speed = 0.8f;
	}
	
	public void create(int width, int height) {
		this.width = width;
		this.height = height; 
		create();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		camera.translate(speed, 0, 0);
		camera.update();
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	public OrthographicCamera getCamera() {
		return camera;
	}


		
	

}
