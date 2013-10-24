package com.dat255_group3.utils;

/**
 * An InputStage is a stage which controls the input in general,
 * and manipulates the input of the back key of the android device.
 * 
 * @author The Hans-Gunnar Crew
 */

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class InputStage extends Stage {

	/**
	 * Constructs a new InputStage with the specified viewport.
	 * 
	 * @param width the width of the camera
	 * @param height the height of the camera
	 * @param keepAspectRatio if true, scales the stage to fit the viewport
	 */
	public InputStage(float width, float height, boolean keepAspectRatio) {
		super(width, height, keepAspectRatio);
	}

	@Override
	public boolean keyDown(int keyCode) {
		if (keyCode == Keys.BACK) {
			if (getHardKeyListener() != null)
				getHardKeyListener().onHardKey(keyCode, 1);
		}
		return super.keyDown(keyCode);
	}

	@Override
	public boolean keyUp(int keyCode) {
		if (keyCode == Keys.BACK) {
			if (getHardKeyListener() != null)
				getHardKeyListener().onHardKey(keyCode, 0);
		}
		return super.keyUp(keyCode);
	}


	/**
	 * The hard key listener
	 */

	public interface OnHardKeyListener {

		/**
		 * Is invoked as the player presses a hardkey of the android device
		 * 
		 * @param keyCode the back key (keyCode is one of the constants in Input.Keys)
		 * @param state 1 - key down, 0 - key up
		 */
		public abstract void onHardKey(int keyCode, int state);
	}

	private OnHardKeyListener hardKeyListener = null;

	public void setHardKeyListener(OnHardKeyListener hardKeyListener) {
		this.hardKeyListener = hardKeyListener;
	}

	public OnHardKeyListener getHardKeyListener() {
		return this.hardKeyListener;
	}
}
