package com.dat255_group3;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.dat255_group3.controller.MyGdxGameController;

public class MainActivity extends AndroidApplication {

	 private MyGdxGameController MGGC;
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
        
        Log.d("Viking","Width: "+width+" Height: "+height);
       
        
        MGGC = new MyGdxGameController();
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;
        initialize(MGGC, cfg);
        
        
    }
    
    public void onStart(){
    	Log.d("Screen", "NO SWAG");
    	SettingScreen SS = new SettingScreen(MGGC, width, height);
    	Log.d("Screen","Width: "+SS.getScreenWidth()+" Height: "+SS.getScreenHeight());
    }
    
}