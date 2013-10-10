package com.dat255_group3.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.dat255_group3.model.Character;
import com.dat255_group3.utils.WorldUtil;

/**  A view class for the character Model. 
 * @author The Hans-Gunnar Crew
 * 
 */
public class CharacterView {
	
	private Rectangle characterRect;
	private Character character;
	private ShapeRenderer shape = new ShapeRenderer();
	private OrthographicCamera camera;
	
	/** A constructor that takes a character class.
	 * 
	 * @param character
	 */
	public CharacterView (Character character, OrthographicCamera camera) {
		this.character = character;
		characterRect = new Rectangle();
		characterRect.height = Character.getHeight();
		characterRect.width =  Character.getWidth();
		this.camera = camera;
	}
	
	
	/**
	 * A method which draws the character (which is currently just a turquoise rectangle)
	 * The Characters appearance will change with time. This is only to test.
	 */
	public void draw(){
		camera.update();
		shape.setProjectionMatrix(camera.combined);
		shape.begin(ShapeType.Filled);
		shape.setColor(Color.CYAN);
		shape.rect(character.getPosition().x, character.getPosition().y, characterRect.width, characterRect.height);
		shape.end();
		
		//fullosning ritar ut forsta hindret
		shape.setProjectionMatrix(camera.combined);
		shape.begin(ShapeType.Filled);
		shape.setColor(Color.RED);
		shape.rect(WorldUtil.getObstacleList().getMapList().get(0).x+16, WorldUtil.getObstacleList().getMapList().get(0).y-16, 32, 32);
		shape.end();
		
		//fullosning ritar ut en marktile
				shape.setProjectionMatrix(camera.combined);
				shape.begin(ShapeType.Filled);
				shape.setColor(Color.GREEN);
				shape.rect(WorldUtil.getGroundList().getMapList().get(10).x+16, WorldUtil.getGroundList().getMapList().get(0).y-16, 32, 32);
				shape.end();

	}
}
