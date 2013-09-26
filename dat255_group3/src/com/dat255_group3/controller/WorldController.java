package com.dat255_group3.controller;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.dat255_group3.model.World;
import com.dat255_group3.utils.PhysBodyFactory;
import com.dat255_group3.view.WorldView;

public class WorldController {

	private InGameController inGameController;
	private World world;
	private WorldView worldView;
	private CharacterController characterController;
	private Vector2 gravity;
	final boolean doSleep;
	private Body groundBody;
	private static com.badlogic.gdx.physics.box2d.World physicsWorld;
	
	public WorldController(InGameController inGameController){
		this.inGameController = inGameController;
		this.world = new World();
		//create the physics world
		this.setGravity(new Vector2(0.0f, 9.82f));
		this.doSleep = true;
		this.physicsWorld = new com.badlogic.gdx.physics.box2d.World(gravity, doSleep);
		groundBody = PhysBodyFactory.addSolidGround(new Vector2(240f, 0f), new Vector2(240f,10f), 0.8f, 0f, this.physicsWorld);
		this.worldView = new WorldView(physicsWorld);
		this.characterController = new CharacterController(this);
		
		// TODO create the ground
		
		
	}
	


	public World getWorld() {
		return world;
	}

	public WorldView getWorldView() {
		return worldView;
	}
	
	public CharacterController getCharacterController(){
		return characterController;
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
