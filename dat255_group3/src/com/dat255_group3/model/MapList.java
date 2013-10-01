package com.dat255_group3.model;

import java.util.ArrayList;

/**
 * A class describing a list for maps.
 * @author The Hans-Gunnar Crew
 *
 */
public class MapList {
	private ArrayList<Position> mapList;

	/**
	 * Constructs a MapList.
	 */
	public MapList() {
		mapList = new ArrayList<Position>();		
	}

	/**
	 * A method which returns the list of maps as it is invoked.
	 * @return
	 * 		The maplist
	 */
	public ArrayList<Position> getMapList() {
		return mapList;
	}

}
