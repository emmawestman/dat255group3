package com.dat255_group3.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.dat255_group3.model.World;
import com.dat255_group3.view.WorldView;

public class WorldController {

	private InGameController inGameController;
	private World world;
	private WorldView worldView;
	private Vector2 gravity;
	final boolean doSleep;
	private Body groundBody;
	private static com.badlogic.gdx.physics.box2d.World physicsWorld;
	
	public WorldController(InGameController inGameController){
		this.inGameController = inGameController;
		this.world = new World();
		this.worldView = new WorldView();
		
		//create the physics world
		this.setGravity(new Vector2(0.0f, 9.82f));
		this.doSleep = true;
		this.physicsWorld = new com.badlogic.gdx.physics.box2d.World(gravity, doSleep);
		// TODO create the ground
		
	}
	
	//method to create a temporary ground for a character to stand on until we can read from a map. 
	//May also be used in the future to create "solid ground" bodies (obstacles) 
	//(but then we should probably move it move it)
	public static void createGround(final Vector2 pos, final Vector2 size, final float friction, final float restitution) {
		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(size.x, size.y);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = polygonShape;
		fixtureDef.friction = friction;
		fixtureDef.density = 1f;
		fixtureDef.restitution = restitution;

		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(pos);
		bodyDef.type = BodyType.StaticBody;
		bodyDef.fixedRotation = true;

		Body body = physicsWorld.createBody(bodyDef);
		body.createFixture(fixtureDef);
	}

	public World getWorld() {
		return world;
	}

	public WorldView getWorldView() {
		return worldView;
	}

	public com.badlogic.gdx.physics.box2d.World getPhysicsWorld() {
		return physicsWorld;
	}

	public Vector2 getGravity() {
		return gravity;
	}

	public void setGravity(Vector2 gravity) {
		this.gravity = gravity;
	}
	
	
}
