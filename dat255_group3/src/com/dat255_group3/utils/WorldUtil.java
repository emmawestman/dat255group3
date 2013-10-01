package com.dat255_group3.utils;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.dat255_group3.model.MapList;
import com.dat255_group3.model.Position;

public class WorldUtil {

	private Vector2 startPos;
	private TiledMap map;
	private MapList groundList;
	private MapList obstacleList;
	private MapList finishLineList;
	private Vector2 tileSize;

	public WorldUtil(TiledMap tiledMap) {
		this.map = tiledMap;
		groundList = new MapList();
		obstacleList = new MapList();
		finishLineList = new MapList();
		setTileSize();
		addToLists();

	}
	
	public void setTileSize() {
		TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(0);
		tileSize = new Vector2(layer.getTileHeight(), layer.getTileWidth());
	}
	
	public Vector2 getTileSize() {
		return tileSize;
	}

	public Vector2 getStartPos() {
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
	
	public void placePhysObject() {
		
	}

	private void addToLists() {
		for(int i=0; i<map.getLayers().getCount(); i++) {
			TiledMapTileLayer currentLayer = (TiledMapTileLayer)map.getLayers().get(i);
			if(currentLayer.getName().equalsIgnoreCase("Solids")) {
				for(int x=0; x<currentLayer.getWidth(); x++) {
					for(int y=0; y<currentLayer.getHeight(); y++) {
						if(currentLayer.getCell(x, y) != null) {
							TiledMapTile tile = currentLayer.getCell(x, y).getTile();
							if(tile.getProperties().containsKey("Ground")) {
								groundList.getMapList().add(new Position(x,currentLayer.getHeight()-(y+1)));
							}else if(tile.getProperties().containsKey("Obstacle")) {
								obstacleList.getMapList().add(new Position(x,currentLayer.getHeight()-(y+1)));
							}
						}
					}

				}
			}else if(currentLayer.getName().equalsIgnoreCase("Positions")) {
				for(int x=0; x<currentLayer.getWidth(); x++) {
					for(int y=0; y<currentLayer.getHeight(); y++) {
						if(currentLayer.getCell(x, y) != null) {
							TiledMapTile tile = currentLayer.getCell(x, y).getTile();
							if(tile.getProperties().containsKey("FinishLine")) {
								finishLineList.getMapList().add(new Position(x,currentLayer.getHeight()-(y+1)));
							}else if(tile.getProperties().containsKey("StartPosition")) {
								startPos = new Vector2(x,currentLayer.getHeight()-(y+1));
							}
						}
					}
				}
			}
		}
	}
}

