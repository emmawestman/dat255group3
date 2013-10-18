package com.dat255_group3.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class ScreenUtils {
	
	private TextureAtlas atlasRect;
	private TextureAtlas atlasCirc;
	private Skin skinRect;
	private Skin skinCirc;
	private Image gameTitleImage;
	private Texture gameTitleTexture;
	private Image backgroundImage;
	private Texture backgroundTexture;
	private OrthographicCamera camera;
	
	
	public ScreenUtils(){
		try{
		atlasRect = new TextureAtlas(Gdx.files.internal("menuIcons/RectangularIcons.pack"));
		atlasCirc = new TextureAtlas(Gdx.files.internal("menuIcons/CircularIcons.pack"));
		gameTitleTexture = new Texture(Gdx.files.internal("menuIcons/gameTitle.png"));
		backgroundTexture = new Texture(Gdx.files.internal("ui/startBackground1024x512.png"));
		
		} catch (GdxRuntimeException e){
			Gdx.app.log("ScreenUtils", "Exception", e);
		}catch (Exception e) {			
		}
		skinRect = new Skin(atlasRect);
		skinCirc = new Skin(atlasCirc);
		gameTitleImage = new Image(gameTitleTexture);
		backgroundImage = new Image (backgroundTexture);
		camera = new OrthographicCamera(CoordinateConverter.getCameraWidth(), 
				CoordinateConverter.getCameraHeight());
		camera.position.set(CoordinateConverter.getCameraWidth()/2, CoordinateConverter.getCameraHeight()/2, 0);
		camera.update();
		 
		
		
	}
	
	public Skin getRectangularSkin(){
		return skinRect;
	}
	
	public Skin getCircularSkin(){
		return skinCirc;
	}
	
	public Image getGameTitleImage() {
		return gameTitleImage;
	}
	
	public Image getBackgroundImage() {
		return backgroundImage;
	}
	
	public void setCamera(SpriteBatch spriteBatch) {
		spriteBatch.setProjectionMatrix(camera.combined);
	}
	
	public void dispose(){
		try {
		skinRect.dispose();
		skinCirc.dispose();
		atlasRect.dispose();
		atlasCirc.dispose();
		} catch (GdxRuntimeException e){
			Gdx.app.log("ScreenUtils", "Exception", e);
		}catch (Exception e) {			
		}
	}
	

}
