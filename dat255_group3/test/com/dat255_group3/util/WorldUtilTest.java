package com.dat255_group3.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.tiled.TiledLoader;
import com.badlogic.gdx.graphics.g2d.tiled.TiledMap;

public class WorldUtilTest {
	private WorldUtil wu;

	@Test
	public void isMapFoundTest() {
		try {
			TiledMap map = TiledLoader.createMap("res/worlds/test1.tmx");
			wu = new WorldUtil(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		assertTrue(wu != null);
	}
}
