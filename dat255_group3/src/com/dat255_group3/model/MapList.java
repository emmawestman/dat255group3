package com.dat255_group3.model;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

/**
 * A MapList is used for storing specific tiles' positions.
 * It contains an ArrayList with Vector2 objects.
 * 
 * @author The Hans-Gunnar Crew
 *
 */
public class MapList {
	private ArrayList<Vector2> mapList;

	/**
	 * Constructs a new MapList and creates an ArrayList with 
	 * Vector2 objects.
	 */
	public MapList() {
		mapList = new ArrayList<Vector2>();		
	}

	public ArrayList<Vector2> getMapList() {
		return mapList;
	}

}
