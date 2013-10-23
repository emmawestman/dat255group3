package com.dat255_group3;

import android.os.Bundle;
import android.view.WindowManager;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.dat255_group3.controller.OneMoreCookiePleaseController;

public class MainActivity extends AndroidApplication {
	private int width;
	private int height;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = true;
        
        OneMoreCookiePleaseController oneMoreCookiePleaseController = new OneMoreCookiePleaseController();
        
        initialize(oneMoreCookiePleaseController, cfg);
    }
    
    public int getScreenWidth(){
    	return width;
    }
    
    public int getScreenHeight(){
    	return height;
    }
}