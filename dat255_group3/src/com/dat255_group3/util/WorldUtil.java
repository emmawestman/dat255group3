package com.dat255_group3.util;

import com.badlogic.gdx.graphics.g2d.tiled.TiledLayer;
import com.badlogic.gdx.graphics.g2d.tiled.TiledMap;
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
		for(int i=0; i<map.layers.size(); i++) {
			TiledLayer currentLayer = map.layers.get(i);
			if(currentLayer.name.equalsIgnoreCase("Solids")) {
				for(int x=0; x<currentLayer.getWidth(); x++) {
					for(int y=0; y<currentLayer.getHeight(); y++) {
						int tile = currentLayer.tiles[x][y];
						if(tile==1) {
							groundList.getMapList().add(new Position(x,y));
						}else if(tile==2) {
							obstacleList.getMapList().add(new Position(x,y));
						}
					}
				}
			}else if(currentLayer.name.equalsIgnoreCase("Positions")) {
				for(int x=0; x<currentLayer.getWidth(); x++) {
					for(int y=0; y<currentLayer.getHeight(); y++) {
						int tile = currentLayer.tiles[x][y];
						if(tile==3) {
							finishLineList.getMapList().add(new Position(x,y));
						}else if(tile==0){
							startPos = new Position(x,y);
						}
					}
				}
			}
		}
	}
}

