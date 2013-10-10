package com.dat255_group3.io;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class IOHandler {
	
	public static void saveScoreNTime(int score, int time, String level){
		try{
			FileHandle handle = Gdx.files.internal("io/io.txt");
			handle.writeString(level + "; Score:" + score + "; Time:" + time + '\n', true);
			Gdx.app.log("IOHandler", "Name: " + handle.name());
		} catch (GdxRuntimeException e){
			Gdx.app.log("IOHandler", "Exception", e);
		}
		
	}
	
	public static void readScoreNTime(){
		try{
			FileHandle handle = Gdx.files.local("ioHandling.txt");
			String text = handle.readString();
			
			String [] levelOne = text.split(";");		
		} catch (GdxRuntimeException e){
			Gdx.app.log("IOHandler", "Exception", e);
		}
		
	}

}
