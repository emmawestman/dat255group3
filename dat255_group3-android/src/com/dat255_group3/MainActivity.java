package com.dat255_group3;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.dat255_group3.controller.MyGdxGameController;

public class MainActivity extends AndroidApplication {
	private int width;
	private int height;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
        
       
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = true;
        
        MyGdxGameController gdx = new MyGdxGameController();
        
        initialize(gdx, cfg);
        
        Log.d("Viking","Width: "+width+" Height: "+height);
        gdx.setHeight(height);
        gdx.setWidth(width);
    }
    
    public int getScreenWidth(){
    	return width;
    }
    
    public int getScreenHeight(){
    	return height;
    }
}