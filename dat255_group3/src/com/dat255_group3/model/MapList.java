package com.dat255_group3.model;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

/**
 * A class describing a list for maps.
 * @author The Hans-Gunnar Crew
 *
 */
public class MapList {
	private ArrayList<Vector2> mapList;

	/**
	 * Constructs a MapList.
	 */
	public MapList() {
		mapList = new ArrayList<Vector2>();		
	}

	/**
	 * A method which returns the list of maps as it is invoked.
	 * @return
	 * 		The maplist
	 */
	public ArrayList<Vector2> getMapList() {
		return mapList;
	}

}
