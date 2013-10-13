package com.dat255_group3.io;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class IOHandler {

	private static FileHandle handle = Gdx.files.local("io/io.txt");
	private static String [] levelData;

	public IOHandler() {
	}

	public static void saveScore(String level, int score){
		try{
			handle.writeString(level + ":" + score + ":", true);
			Gdx.app.log("IOHandler", "Name: " + handle.name());
		} catch (GdxRuntimeException e){
			Gdx.app.log("IOHandler", "Exception", e);
		}
	}

	public static void saveNewHigscore(String level, int score) {
		for(int i = 0; i<levelData.length; i=i+2) { //only every second string will contain a level name therefore increase i by two
			if (levelData[i].contains(level)) {
				levelData[i]= level + ":";
				levelData[i+1] = score + ":";
			}
		}
		String text = "";
		for (int i=0; i<levelData.length; i++) {
			text = text + levelData[i];
		}
		try{
			handle.writeString(text, false);
		} catch (GdxRuntimeException e){

		}
	}

	public static void readScore(){
		try{
			String text = handle.readString();
			levelData = text.split(":");		
		} catch (GdxRuntimeException e){
			Gdx.app.log("IOHandler", "Exception", e);
		}
	}

	public static int getScore(String level) {
		try {
			for(int i = 0; i<levelData.length; i=i+2) { //only every second string will contain a level name therefore increase i by two
				if (levelData[i].contains(level)) {
					return Integer.parseInt(levelData[i+1].trim());
				}
			}
		}
		catch (Exception e) {
			// No old high score
		}
		return 0;
	}
	
	public static boolean contains(String level) {
		boolean foundMatch = false;
		for (int i=0; i<levelData.length; i++) {
			if (levelData[i].contains(level)) {
				foundMatch = true;
			}
		}
		return foundMatch;
	}
	
	public static String[] getLevelData(){
		return levelData;
	}
}
