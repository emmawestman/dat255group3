package com.dat255_group3.io;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class IOHandler {
	
	private static FileHandle handle;
	private static String [] levelData;
	
	public IOHandler() {
		handle = Gdx.files.local("io/io.txt");
	}
	
	public static void saveScoreNTime(int score, int time, String level){
		try{
			handle.writeString(level + "; Score:" + score + "; Time:" + time + '\n', true);
			Gdx.app.log("IOHandler", "Name: " + handle.name());
		} catch (GdxRuntimeException e){
			Gdx.app.log("IOHandler", "Exception", e);
		}
	}
	
	public static void readScoreNTime(){
		try{
			String text = handle.readString();
			levelData = text.split(";");		
		} catch (GdxRuntimeException e){
			Gdx.app.log("IOHandler", "Exception", e);
		}
	}
	
	public static int getScore(String level) {
		for(int i = 0; i<levelData.length; i++) {
			if (levelData[i].contains(level)) {
				String score[] = levelData[i+1].split(":");
				return Integer.parseInt(score[1].trim());
			}
		}
		return 0;
	}
	
	public static int getTime(String level) {
		for(int i = 0; i<levelData.length; i++) {
			if (levelData[i].contains(level)) {
				String time[] = levelData[i+2].split(":");
				return Integer.parseInt(time[1].trim());
			}
		}
		return 0;
	}
}
