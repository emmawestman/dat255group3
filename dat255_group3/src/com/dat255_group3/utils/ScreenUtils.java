package com.dat255_group3.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class ScreenUtils {
	
	private TextureAtlas atlasRect;
	private TextureAtlas atlasCirc;
	private Skin skinRect;
	private Skin skinCirc;
	
	public ScreenUtils(){
		try{
		atlasRect = new TextureAtlas(Gdx.files.internal("menuIcons/RectangularIcons.pack"));
		atlasCirc = new TextureAtlas(Gdx.files.internal("menuIcons/CircularIcons.pack"));
		} catch (GdxRuntimeException e){
			Gdx.app.log("ScreenUtils", "Exception", e);
		}catch (Exception e) {			
		}
		skinRect = new Skin(atlasRect);
		skinCirc = new Skin(atlasCirc);
		
	}
	
	public Skin getRectangularSkin(){
		return skinRect;
	}
	
	public Skin getCircularSkin(){
		return skinCirc;
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
