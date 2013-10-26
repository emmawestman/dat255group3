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

/**
 * The view which draws everything in the game screen.
 *  
 * @author The Hans-Gunnar Crew
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

	/**
	 * Constructs a new InGameView with the specified TiledMap and camera.
	 * 
	 * @param map the level's TiledMap
	 * @param camera the camera used to display the game
	 * @param level the current level
	 */
	public InGameView(TiledMap map, OrthographicCamera camera, int level) {
		this.level=level;
		mapRenderer = new OrthogonalTiledMapRenderer(map);
		this.camera = camera;
		this.spriteBatch = new SpriteBatch();
		this.font = new BitmapFont();
		spriteBatch.setProjectionMatrix(camera.combined);
		this.bgImage = new Texture(Gdx.files.internal("ui/background.png"));
		bgSprite = new Sprite(bgImage);
	}

	/**
	 * Draws everything that is displayed in the level.
	 * 
	 * @param worldView the view that draws the character, cookies and enemy
	 * @param charBody the character's physics body
	 * @param charView the view that draws the character
	 * @param cookieView the view that draws the cookies
	 * @param enemy the view that draws the enemy
	 * @param time the time that's being updated in the game
	 * @param cookieCounter keeps track of the number of cookies collected
	 * @param gameOver true if the game is lost
	 */
	public void draw(WorldView worldView, Body charBody, CharacterView charView, CookieView cookieView, 
			EnemyView enemy, double time, int cookieCounter, boolean gameOver) {

		drawBgImage();

		//Shows selected part of the map
		mapRenderer.setView(camera);

		mapRenderer.render(mapLayers);
		worldView.draw(charView, cookieView, enemy);
		drawTime(time);
		drawCookieCounter(cookieCounter);
		drawLevelNbr();

	}

	/**
	 * Draws the time elapsed in the upper left corner.
	 * 
	 * @param time the time that's being updated in the game
	 */
	public void drawTime(double time) {
		spriteBatch.begin();
		str = "Time: "+ (double)((int)(time*100))/100;
		font.setColor(Color.BLACK);
		font.draw(spriteBatch, str, 100f, camera.viewportHeight-30f);
		spriteBatch.end();
	}

	/**
	 * Draws the cookie counter next to the time.
	 * 
	 * @param cookieCounter keeps track of the number of cookies collected
	 */
	public void drawCookieCounter(int cookieCounter) {
		spriteBatch.begin();
		str = "Cookies: "+ cookieCounter;
		font.setColor(Color.BLACK);
		font.draw(spriteBatch, str, 200f, camera.viewportHeight-30f);
		spriteBatch.end();
	}

	/**
	 * Draws the level number next to the cookie counter.
	 */
	public void drawLevelNbr(){
		spriteBatch.begin();
		str = "Level: "+ this.level;
		font.setColor(Color.BLACK);
		font.draw(spriteBatch, str, 300f, camera.viewportHeight-30f);
		spriteBatch.end();
	}

	/**
	 * Draws the background image. This image has a fixed position 
	 * and does not move with the camera.
	 */
	public void drawBgImage(){
		if(this.bgImage != null){
			this.spriteBatch.begin();
			this.bgSprite.setPosition(0, 0);
			this.bgSprite.draw(spriteBatch);
			this.spriteBatch.end();
		}
	}

	/**
	 * Draws the count down numbers at the start of a level.
	 * 
	 * @param delayTime the updated time during the count down
	 */
	public void drawCountDownNbr(float delayTime) {		
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
