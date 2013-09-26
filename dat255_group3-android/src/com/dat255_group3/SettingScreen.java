package com.dat255_group3;

import com.dat255_group3.controller.MyGdxGameController;

public class SettingScreen {
	private int width;
	private int height;

	public SettingScreen(MyGdxGameController MGGC, int x, int y){
		  this.width = x;
		  this.height = y;
		
		  MGGC.getMyGdxGame().setWidth(x);
		  MGGC.getMyGdxGame().setHeight(y);
	}
	
    public int getScreenWidth(){
    	return width;
    }
    
    public int getScreenHeight(){
    	return height;
    }
	
}
