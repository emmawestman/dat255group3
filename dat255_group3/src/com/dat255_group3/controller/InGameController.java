package com.dat255_group3.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.dat255_group3.model.InGame;
import com.dat255_group3.utils.CoordinateConverter;
import com.dat255_group3.view.InGameView;

public class InGameController implements Screen{

	private InGame inGame;
	private InGameView inGameView;
	private WorldController worldController;
	private float timeStep = 1.0f / 10.0f;
	private final int velocityIterations = 6;
	private final int positionIterations = 2;
	private TiledMap map;
	private OrthographicCameraController cameraController;
	private Box2DDebugRenderer renderer = new Box2DDebugRenderer(true, true, true, true, true, true);
	private Matrix4 matrix = new Matrix4();
	private float time;


	public InGameController(MyGdxGameController myGdxGameController){
		matrix.setToOrtho2D(0, 0, 480, 320);
		this.cameraController = new OrthographicCameraController();
		this.cameraController.create();
		map = new TmxMapLoader().load("worlds/test5.tmx");
		this.inGameView = new InGameView(map, cameraController.getCamera());
		this.inGame = new InGame();
		this.worldController = new WorldController(this, inGame.getSpeedM());
		this.time = 0;
		

	}
	
	
	@Override
	public void render(float delta) {
		if(delta>0){
			this.timeStep = delta;
		}
		if(!hasWon()) {
			//for testing
			Gdx.app.log("position", "character position: "+this.worldController.getCharacterController().getCharacter().getPosition());
			
			if(this.worldController.getCharacterController().getCharacter().isDead()){
				Gdx.app.log("Game over", "game is over!");
			}
			//update the time
			this.time = time+delta;
			
			// Shows a white screen
			Gdx.gl.glClearColor(1, 1, 1, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			this.inGameView.draw(this.worldController.getWorldView(), this.worldController.getCharBody(), this.worldController.getCharacterController().getCharacterView(), time);

			// Updates the speed
			inGame.setSpeedP(CoordinateConverter.pixelToMeter(inGame.getSpeedM()*delta*1000));
			cameraController.setSpeedP(inGame.getSpeedP());
		
			
			// update the physics
			this.worldController.getPhysicsWorld().step(this.timeStep, this.velocityIterations, this.positionIterations);

			// update the model position for the character
			this.worldController.uppdatePositions(this.worldController.getCharBody(), this.worldController.getCharacterController().getCharacter());

			this.worldController.getPhysicsWorld().step(this.timeStep, this.velocityIterations, this.positionIterations);

			// Update the position of the camera
			cameraController.render();

			// Update the position of the finish line
			worldController.moveFinishLine(inGame.getSpeedP());

			/*
			 * Checks whether the screen has been touched. 
			 * If so, a method which will make the character jump is invoked.
			 */
			if(Gdx.input.isTouched()){
				worldController.getCharacterController().tryToJump(); 	
			}

			//Draw physics bodies, for debugging
			renderer.render(worldController.getPhysicsWorld(), matrix);
//			Gdx.app.log("Physics", "x: "+worldController.getCharBody().getPosition().x+ "y: "+
//					worldController.getCharBody().getPosition().y + " massa: "+ worldController.getCharBody().getMass());
		}else{
			Gdx.app.log("FinishLine","At finish line");
		}

	}



	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

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
	/**
	 * Update so that the character model has the same position (x,y) as the physical body
	 * @param body , the physical body of the character
	 * @param character , the character model with the position
	 */


	public InGame getInGame() {
		return inGame;
	}

	public InGameView getInGameView() {
		return inGameView;
	}

	public TiledMap getMap() {
		return map;
	}

	public boolean hasWon() {
//		Gdx.app.log("FinishLine", "Finish line: x: " + worldController.getFinishLineX() + "Start: x: " + worldController.getStartPos().x);
			if(worldController.getCharacterController().getCharacter().getPosition().x >= worldController.getFinishLineX()) {
				return true;
			}else{
				return false;
			}
	}


}
