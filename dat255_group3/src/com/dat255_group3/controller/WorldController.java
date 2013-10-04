package com.dat255_group3.controller;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.dat255_group3.model.Character;
import com.dat255_group3.model.World;
import com.dat255_group3.utils.CoordinateConverter;
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
	private ArrayList<Body> solidBodyList;
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

		
		this.worldView = new WorldView();
		this.characterController = new CharacterController(this);

		CoordinateConverter cc = new CoordinateConverter();
		//create character body
		this.charBody = PhysBodyFactory.createCharacter(physicsWorld, new Vector2(worldUtil.getStartPos().x, cc.getScreenHeight() - worldUtil.getStartPos().y), 
				new Vector2(this.characterController.getCharacter().getWidth(), this.characterController.getCharacter().getHeight()));

		
		//create the ground & obstacles
		solidBodyList = new ArrayList<Body>();
		ArrayList<Vector2> solidList = worldUtil.getGroundList().getMapList();
		for(int i=0; i<solidList.size(); i++) {
			solidBodyList.add(PhysBodyFactory.addSolidGround(new Vector2(solidList.get(i).x, cc.getScreenHeight() - solidList.get(i).y),
					worldUtil.getTileSize(), 0.8f, 0f, this.physicsWorld));
		}
		
		
		this.worldView = new WorldView();
		this.characterController = new CharacterController(this);
	}
	/**
	 * Uppdates the model position of the character to the top left corner of the physical body
	 * @param body , physical body 
	 * @param character , character model
	 */
	public void uppdatePositions(Body body, Character character){
		Vector2 posInPixels = CoordinateConverter.meterToPixel(body.getPosition());
		character.setPosition(new Vector2 (posInPixels.x - (character.getWidth()/2), posInPixels.y - (character.getHeight()/2)) );
	}
	
	public boolean isDead() {
		return worldView.getDeathRect().overlaps(characterController.getCharacterView().getRect());
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

	public ArrayList<Body> getSolidBodyList() {
		return solidBodyList;
	}

	public Vector2 getGravity() {
		return gravity;
	}

	public void setGravity(Vector2 gravity) {
		this.gravity = gravity;
	}
	
	
}
