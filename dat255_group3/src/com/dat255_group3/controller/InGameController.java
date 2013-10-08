package com.dat255_group3.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.dat255_group3.model.InGame;
import com.dat255_group3.utils.CoordinateConverter;
import com.dat255_group3.view.GameOverScreen;
import com.dat255_group3.view.InGameView;
import com.dat255_group3.view.PauseScreen;

public class InGameController implements Screen{

	private InGame inGame;
	private InGameView inGameView;
	private WorldController worldController;
	private MyGdxGameController myGdxGameController;
	private float timeStep = 1.0f / 10.0f;
	private final int velocityIterations = 6;
	private final int positionIterations = 2;
	private TiledMap map;
	private OrthographicCameraController cameraController;
	private Box2DDebugRenderer renderer = new Box2DDebugRenderer(true, true, true, true, true, true);
	private Matrix4 matrix = new Matrix4();
	private float time;
	private boolean gameOver;


	public InGameController(MyGdxGameController myGdxGameController){
		matrix.setToOrtho2D(0, 0, 480, 320);
		this.cameraController = new OrthographicCameraController();
		this.cameraController.create();
		map = new TmxMapLoader().load("worlds/test5.tmx");
		this.myGdxGameController = new MyGdxGameController();
		this.inGameView = new InGameView(map, cameraController.getCamera());
		this.inGame = new InGame();
		this.worldController = new WorldController(this, inGame.getSpeedM());
		this.time = 0;
		this.gameOver = false;


	}


	@Override
	public void render(float delta) {
		if(delta>0){
			this.timeStep = delta;
		}
		
		/*
		 * Checks whether the backbutton has been pressed.
		 * If so, a pausepop-up-screen will be shown.
		 */
		if (Gdx.input.isKeyPressed(Keys.BACK)){
			Gdx.input.setCatchBackKey(true);

			myGdxGameController.setScreen(new PauseScreen(myGdxGameController));
			
			//TODO: Show a popupscreen instead of a new screen
			/*
			Stage stage = new Stage();
			Skin skin = new Skin(Gdx.files.internal("ui/dialog.json"),new TextureAtlas(Gdx.files.internal("ui/button.pack")));
			
			Dialog popup = new Dialog("Paus", skin);
			popup.setPosition(0, 0);
			popup.fadeDuration = 1;
			stage.addActor(popup);
			stage.act(delta);
			stage.draw();*/
			Gdx.input.setCatchBackKey(true);
			myGdxGameController.setScreen(new PauseScreen(myGdxGameController));
		}
		
		
		if(!hasWon()) {
			if(this.worldController.getCharacterController().getCharacter().isDead()){
				Gdx.app.log("Game over", "game is over!");
				this.gameOver = true;
				//this.inGameView.draw(this.worldController.getWorldView(), this.worldController.getCharBody(), this.worldController.getCharacterController().getCharacterView(), time, gameOver);
				
				int timeint = (int) (time);
				//Change to gameover-screen
				myGdxGameController.setScreen(new GameOverScreen(myGdxGameController, 10, timeint, gameOver));
			} else {
			//update the time
			this.time = time+delta;

			// Shows a white screen
			Gdx.gl.glClearColor(1, 1, 1, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			this.inGameView.draw(this.worldController.getWorldView(), this.worldController.getCharBody(), this.worldController.getCharacterController().getCharacterView(), time);

			// Updates the speed
			inGame.setSpeedP(CoordinateConverter.pixelToMeter(inGame.getSpeedM()*delta*1000));
			cameraController.setSpeedP(inGame.getSpeedP());

			//Testing
			//Gdx.app.log("Obstacles", "Position character: x: " + worldController.getCharBody().getPosition().x);
			for(int i = 0; i<worldController.getObstacleBodyList().size(); i++) {
				//Gdx.app.log("Obstacles", "Position obstacle: x: " + worldController.getObstacleBodyList().get(i).getPosition().x);
			}

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
			}
		}else{
			//Change to gamewon-screen
			int timeint = (int) (time);
			//myGdxGameController.setScreen(new GameOverScreen(myGdxGameController, 10, timeint, true));
			//Gdx.app.log("FinishLine","At finish line");
		}
	}

	


	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
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
		map.dispose();
		cameraController.dispose();
		renderer.dispose();
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
	
	public OrthographicCamera getCamera() {
		return cameraController.getCamera();
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
