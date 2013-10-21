package com.dat255_group3.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.dat255_group3.utils.CoordinateConverter;

/** A view class for the InGame model. 
 * @author The Hans-Gunnar Crew
 *
 */
public class InGameView {
	
	private OrthogonalTiledMapRenderer mapRenderer;
	private OrthographicCamera camera;
	private SpriteBatch spriteBatch;
	private BitmapFont font;
	private CharSequence str;
	private int[] mapLayers = {0, 2};
	private Texture bgImage;
	private Sprite bgSprite;
	private Texture countDownImage;
	private Sprite countDown;
	private int level;
	
	/** A constructor that takes a map. 
	 * @param map
	 */
	
	public InGameView (TiledMap map, OrthographicCamera camera, int level) {
		this.level=level;
		mapRenderer = new OrthogonalTiledMapRenderer(map);
		this.camera = camera;
		this.spriteBatch = new SpriteBatch();
		this.font = new BitmapFont();
		spriteBatch.setProjectionMatrix(camera.combined);
		Gdx.app.log("BG", "loading image...");
		this.bgImage = new Texture(Gdx.files.internal("ui/ingameBackground.png"));
		bgSprite = new Sprite(bgImage);
		Gdx.app.log("BG", "Done!");

		
	}
	
	/** Renders the HUD and background of the game. 
	 * 
	 */
	public void draw(WorldView worldView, Body charBody, CharacterView charView, CookieView cookieView, 
			EnemyView enemy, double time, int cookieCounter, boolean gameOver) {
		//darw bg image
		drawBgImage();
		//Shows selected part of the map
		mapRenderer.setView(camera);
		mapRenderer.render(mapLayers);
		worldView.draw(charView, cookieView, enemy);
		//draw game over text
		if(gameOver) drawGameOver();
		//Draw time
		drawTime(time);
		drawCookieCounter(cookieCounter);

	}
	
	public void drawGameOver(){
		spriteBatch.begin();
		str = "GAME OVER";
		font.setColor(Color.RED);
		font.draw(spriteBatch, str, camera.viewportWidth/2 -40f, camera.viewportHeight/2);
		spriteBatch.end();
	}
	
	public void drawTime(double time) {
		spriteBatch.begin();
		str = "Time: "+ (double)((int)(time*100))/100;
		font.setColor(Color.BLACK);
		font.draw(spriteBatch, str, 100f, camera.viewportHeight-30f);
		spriteBatch.end();
	}
	
	public void drawCookieCounter(int cookieCounter) {
		spriteBatch.begin();
		str = "Cookies: "+ cookieCounter;
		font.setColor(Color.BLACK);
		font.draw(spriteBatch, str, 200f, camera.viewportHeight-30f);
		spriteBatch.end();
	}
	
	public void drawLevelNbr(){
		spriteBatch.begin();
		str = "Level: "+ this.level;
		font.setColor(Color.BLACK);
		font.draw(spriteBatch, str, 300f, camera.viewportHeight-30f);
		spriteBatch.end();
	}
	
	public void drawBgImage(){
		Gdx.app.log("BG", "checking");
		if(this.bgImage != null){
			Gdx.app.log("BG", "Drawing");
			this.spriteBatch.begin();
			this.bgSprite.setPosition(0, 0);
			this.bgSprite.draw(spriteBatch);
			this.spriteBatch.end();
		}

	}
	
	public void drawCountDownNbr(float delayTime) {
		Gdx.app.log("InGameView", "drawCountDownNbr");
		
		try {
		if (delayTime <= 1.0) {
			countDownImage = new Texture(Gdx.files.internal("ui/three.png"));
		}else if (delayTime <= 2.0) {
			countDownImage = new Texture(Gdx.files.internal("ui/two.png"));
		}else {
			countDownImage = new Texture(Gdx.files.internal("ui/one.png"));
		}
		}catch (Exception e) {
			
		}
		
		this.countDown = new Sprite(countDownImage);
		
		spriteBatch.begin();
		countDown.setPosition(CoordinateConverter.getCameraWidth()/2 - 256/2, 
				CoordinateConverter.getCameraHeight()/2 - 256/2);
		countDown.draw(spriteBatch);
		spriteBatch.end();
		
	}
	
}
