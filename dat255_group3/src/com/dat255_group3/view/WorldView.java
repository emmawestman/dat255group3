package com.dat255_group3.view;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class WorldView {
	
	World physicsWorld;
	
	public WorldView (World physicsWorld) {
		this.physicsWorld = physicsWorld;
		
	}
	
	public void render() {
		//Draws characterview
		Box2DDebugRenderer renderer = new Box2DDebugRenderer(true, true, true, true, true, true);
		Matrix4 matrix = new Matrix4();
		matrix.setToOrtho2D(0, 0, 480, 320);
		renderer.render(physicsWorld, matrix);
	}
}
