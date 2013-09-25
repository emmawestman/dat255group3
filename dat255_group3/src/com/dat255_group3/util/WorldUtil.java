package com.dat255_group3.util;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.dat255_group3.model.MapList;
import com.dat255_group3.model.Position;

public class WorldUtil {

	private Position startPos;
	private TiledMap map;
	private MapList groundList;
	private MapList obstacleList;
	private MapList finishLineList;

	public WorldUtil(TiledMap tiledMap) {
		this.map = tiledMap;
		groundList = new MapList();
		obstacleList = new MapList();
		finishLineList = new MapList();
		addToLists();

	}

	public Position getStartPos() {
		return startPos;
	}

	public MapList getGroundList() {
		return groundList;
	}

	public MapList getObstacleList() {
		return obstacleList;
	}

	public MapList getFinishLineList() {
		return finishLineList;
	}

	private void addToLists() {
		for(int i=0; i<map.getLayers().getCount(); i++) {
			TiledMapTileLayer currentLayer = (TiledMapTileLayer)map.getLayers().get(i);
			if(currentLayer.getName().equalsIgnoreCase("Solids")) {
				for(int x=0; x<currentLayer.getHeight(); x++) {
					for(int y=0; y<currentLayer.getWidth(); y++) {
						TiledMapTile tile = currentLayer.getCell(x, y).getTile();
						if(tile.getId()==1) {
							groundList.getMapList().add(new Position(x,y));
						}else if(tile.getId()==2) {
							obstacleList.getMapList().add(new Position(x,y));
						}
					}
				}
			}else if(currentLayer.getName().equalsIgnoreCase("Positions")) {
				for(int x=0; x<currentLayer.getHeight(); x++) {
					for(int y=0; y<currentLayer.getWidth(); y++) {
						TiledMapTile tile = currentLayer.getCell(x, y).getTile();
						if(tile.getId()==3) {
							finishLineList.getMapList().add(new Position(x,y));
						}else if(tile.getId()==0) {
							startPos = new Position(x,y);
						}
					}
				}
			}
		}
	}
}

