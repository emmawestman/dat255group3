package com.dat255_group3.model;

/**
 * A Position is an object containing an x- and y-coordinate.
 * 
 * 
 * @author The Hans-Gunnar Crew
 *
 */

public class Position {
	
	/** The x-coordinate. */
	private int x;
	
	/** The y-coordinate. */
	private int y;
	
	/**
	 * Class constructor specifying the coordinates.
	 * 
	 * @param x the x-coordinate
	 * @param y the y-coordinate
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns an int with the value of the x-coordinate.
	 * 
	 * @return the x-coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the x-coordinate to the specified value.
	 * 
	 * @param x the new x-coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Returns an int with the value of the y-coordinate.
	 * 
	 * @return the y-coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the y-coordinate to the specified value.
	 * 
	 * @param y the new y-coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean equals(Object o) {
		if(o != null && o.getClass().equals(this.getClass())) {
			Position pos = (Position) o;
			return pos.getX() == this.getX() && pos.getY() == this.getY();
		}else{
			return false;
		}
	}
}

