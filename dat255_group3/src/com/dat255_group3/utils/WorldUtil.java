package com.dat255_group3.utils;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.dat255_group3.model.Character;
import com.dat255_group3.model.MapList;


/**
 * Contains methods to detect positions from the tmx map.
 * 
 * @author The Hans-Gunnar Crew
 */
public class WorldUtil {

	private static Vector2 startPos;
	private TiledMap map;
	private static MapList groundList;
	private float finishLineX;
	private static MapList obstacleList;
	private Vector2 tileSize;
	private MapList cookieList;

	/**
	 * Constructs a new WorldUtil and creates MapLists for the ground,
	 * obstacle and cookie positions.
	 * 
	 * @param tiledMap the level's TiledMap
	 */
	public WorldUtil(TiledMap tiledMap) {
		this.map = tiledMap;
		groundList = new MapList();
		obstacleList = new MapList();
		cookieList = new MapList();
		findTileSize();
		addToLists();
	}


	/**
	 * Finds the size of the tiles.
	 */
	public void findTileSize() {
		TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(0);
		tileSize = new Vector2(layer.getTileHeight(), layer.getTileWidth());
	}

	public Vector2 getTileSize() {
		return tileSize;
	}

	public static MapList getObstacleList() {
		return obstacleList;
	}

	public static Vector2 getStartPos() {
		return startPos;
	}

	public static MapList getGroundList() {
		return groundList;
	}

	public float getFinishLineX() {
		return finishLineX;
	}

	public MapList getCookieList() {
		return cookieList;
	}

	/**
	 * Loops through the map layers and creates lists of different 
	 * kinds of positions.
	 */
	private void addToLists() {
		for(int i=0; i<map.getLayers().getCount(); i++) {
			TiledMapTileLayer currentLayer = (TiledMapTileLayer)map.getLayers().get(i);
			if(currentLayer.getName().equalsIgnoreCase("Solids")) {
				for(int x=0; x<currentLayer.getWidth(); x++) {
					for(int y=0; y<currentLayer.getHeight(); y++) {
						if(currentLayer.getCell(x, y) != null) {
							TiledMapTile tile = currentLayer.getCell(x, y).getTile();
							if(tile.getProperties().containsKey("Ground")) {
								groundList.getMapList().add(new Vector2((x*tileSize.x + tileSize.x/2), y*tileSize.y + tileSize.y/2));
							}else if(tile.getProperties().containsKey("Obstacle")) {
								obstacleList.getMapList().add(new Vector2((x*tileSize.x + tileSize.x/2), y*tileSize.y + tileSize.y/2));
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
								finishLineX = x*tileSize.x;
							}else if(tile.getProperties().containsKey("StartPosition")) {
								startPos = new Vector2((x*tileSize.x) - Character.getRadius()/2,y*tileSize.y + Character.getRadius()/2 + 30);
							}else if(tile.getProperties().containsKey("Cookie")) {
								cookieList.getMapList().add(new Vector2((x*tileSize.x), y*tileSize.y));
							}
						}
					}
				}
			}
		}
	}
}

