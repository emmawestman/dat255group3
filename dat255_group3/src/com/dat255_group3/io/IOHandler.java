package com.dat255_group3.io;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;

/**
 * Saves and reads the highscore.
 * 
 * @author The Hans-Gunnar Crew
 */
public class IOHandler {

	private static FileHandle handle = Gdx.files.local("highScores.txt");
	private static String [] levelData;

	/**
	 * Saves the player's high scores in a .txt file.
	 * 
	 * @param highScores the player's high scores
	 */
	public static void saveHighScores(List<Integer> highScores){
		String text = "";
		for (int i=0; i<highScores.size(); i++) {
			text = text + highScores.get(i) + ":";
		}
		try{
			handle.writeString(text, false);
		} catch (GdxRuntimeException e){
			Gdx.app.log("IOHandler", "Exception", e);
		}catch (Exception e) {			
		}
	
	}

	/**
	 * Reads the player's high scores.
	 */
	public static void readScore(){
		try{
			String text = handle.readString();
			levelData = text.split(":");		
		} catch (GdxRuntimeException e){
			Gdx.app.log("IOHandler", "Exception", e);
		}
	}
	
	public static String[] getLevelData(){
		return levelData;
	}
}
