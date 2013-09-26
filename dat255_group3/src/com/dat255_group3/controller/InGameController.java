package com.dat255_group3.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.dat255_group3.model.InGame;
import com.dat255_group3.view.InGameView;

public class InGameController implements Screen{
	
	private MyGdxGameController myGdxGameController;
	private InGame inGame;
	private InGameView inGameView;
	private WorldController worldController;
	private float timeStep = 1.0f / 60.0f;
	private final int velocityIterations = 6;
	private final int positionIterations = 2;
	private Box2DDebugRenderer renderer = new Box2DDebugRenderer(true, true, true, true, true, true);
	private Matrix4 matrix = new Matrix4();
	
	public InGameController(MyGdxGameController myGdxGameController){
		this.myGdxGameController = myGdxGameController;
		this.inGameView = new InGameView();
		this.inGame = new InGame();
		this.worldController = new WorldController(this);
		
		matrix.setToOrtho2D(0, 0, 480, 320);
	}
	
	@Override
	public void render(float delta) {
		
		//Shows a white screen
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		
		//update the physics in the world... belongs in a update method?
		if(delta > 0) {
			this.timeStep = 1; //(float) delta / 1000f * 4; //4 is for getting a good speed, may change
		}
		this.worldController.getPhysicsWorld().step(this.timeStep, this.velocityIterations, this.positionIterations);
	
		
		/*
		 * Checks whether the screen has been touched. 
		 * If so, a method which will make the character jump is invoked.
		 */
//		if(Gdx.input.isTouched()){
//			worldController.getCharacterController().jump(); 	
//		}
		
		//Draw physics bodies
		renderer.render(worldController.getPhysicsWorld(), matrix);
		Gdx.app.log("Physics", "x: "+worldController.getCharBody().getPosition().x+ "y: "+
				worldController.getCharBody().getPosition().y + " massa: "+ worldController.getCharBody().getMass());
	
	}
	
	

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		//show a yellow screen
		Gdx.gl.glClearColor(1, 1, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
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

	public InGame getInGame() {
		return inGame;
	}

	public InGameView getInGameView() {
		return inGameView;
	}


	
}
