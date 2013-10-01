package com.dat255_group3.controller;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.dat255_group3.model.MapList;
import com.dat255_group3.model.Position;
import com.dat255_group3.model.World;
import com.dat255_group3.utils.PhysBodyFactory;
import com.dat255_group3.utils.WorldUtil;
import com.dat255_group3.view.WorldView;

public class WorldController {

	private InGameController inGameController;
	private World world;
	private WorldView worldView;
	private CharacterController characterController;
	private Vector2 gravity;
	final boolean doSleep;
	private Body groundBody;
	private Body charBody;
	private static com.badlogic.gdx.physics.box2d.World physicsWorld;
	private WorldUtil worldUtil;
	
	public WorldController(InGameController inGameController){
		this.inGameController = inGameController;
		this.world = new World();
		this.worldView = new WorldView();
		this.characterController = new CharacterController(this);
		this.worldUtil = new WorldUtil(inGameController.getMap());

		//create the physics world
		this.setGravity(new Vector2(0.0f, -10f));
		this.doSleep = true;
		this.physicsWorld = new com.badlogic.gdx.physics.box2d.World(gravity, doSleep);
		
		
		//create the ground
		ArrayList<Position> groundList = worldUtil.getGroundList().getMapList();
		for(int i=0; i<groundList.size(); i++) {
			PhysBodyFactory.addSolidGround(new Vector2(groundList.get(i).getX(),groundList.get(i).getY()),
					worldUtil.getTileSize(), 0.8f, 0f, this.physicsWorld);
		}
		
		//create obstacles
		ArrayList<Position> obstacleList = worldUtil.getGroundList().getMapList();
		for(int i=0; i<obstacleList.size(); i++) {
			PhysBodyFactory.addSolidGround(new Vector2(obstacleList.get(i).getX(),obstacleList.get(i).getY()),
					worldUtil.getTileSize(), 0.8f, 0f, this.physicsWorld);
		}
		
		
//		groundBody = PhysBodyFactory.addSolidGround(new Vector2(240f, 0f), new Vector2(240f,10f), 0.8f, 0f, this.physicsWorld);
		this.worldView = new WorldView();
		this.characterController = new CharacterController(this);
		//create character body
		this.charBody = PhysBodyFactory.createCharacter(physicsWorld, new Vector2(worldUtil.getStartPos().x, worldUtil.getStartPos().y-worldUtil.getTileSize().y), new Vector2(5f, 10f));
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

	public Body getCharBody() {
		return charBody;
	}



	public Vector2 getGravity() {
		return gravity;
	}

	public void setGravity(Vector2 gravity) {
		this.gravity = gravity;
	}
	
	
}
