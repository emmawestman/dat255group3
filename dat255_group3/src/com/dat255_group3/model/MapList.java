package com.dat255_group3.model;

import java.util.ArrayList;

/**
 * A class describing a list for maps.
 * @author Grupp 3
 *
 */
public class MapList {
	private ArrayList<Position> mapList;

	/**
	 * Construcs a MapList.
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
