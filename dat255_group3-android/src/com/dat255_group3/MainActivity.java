package com.dat255_group3;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.dat255_group3.controller.MyGdxGameController;

public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        
        Log.d("Viking","Width: "+width+" Height: "+height);

        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;
        
        initialize(new MyGdxGameController(), cfg);
    }
}