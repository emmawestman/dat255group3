package com.dat255_group3.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class WorldUtilTest {
	private WorldUtil wu;

	@Test
	public void isMapFoundTest() {
		try {
			wu = new WorldUtil(new TmxMapLoader().load("worlds/test1.tmx"));
		}catch(Exception e){
			e.printStackTrace();
		}
		assertTrue(wu != null);
	}
}
