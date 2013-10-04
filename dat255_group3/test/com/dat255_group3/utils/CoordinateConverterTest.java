package com.dat255_group3.utils;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

public class CoordinateConverterTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		CoordinateConverter.setScreenHeight(100);
		CoordinateConverter.setScreenWidth(50);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPixelToMeterFloat() {
		float expected = 1.0f;
		float actual = CoordinateConverter.pixelToMeter(5.0f);
		assertEquals(expected, actual,0.00001f);
	}

	@Test
	public void testPixelToMeterVector2() {
		Vector2 expected = new Vector2();
		expected.x = 1.0f;
		expected.y = 2.0f;
		
		Vector2 input = new Vector2();
		input.x = 5.0f;
		input.y = 10.0f;
		
		Vector2 actual = CoordinateConverter.pixelToMeter(input);
		
		assertEquals(expected, actual);
	}

	@Test
	public void testMeterToPixelFloat() {
		float expected = 25.0f;
		float actual = CoordinateConverter.meterToPixel(5.0f);
		assertEquals(expected, actual,0.00001f);
	}

	@Test
	public void testMeterToPixelVector2() {
		Vector2 expected = new Vector2();
		expected.x = 25.0f;
		expected.y = 50.0f;
		
		Vector2 input = new Vector2();
		input.x = 5.0f;
		input.y = 10.0f;
		
		Vector2 actual = CoordinateConverter.meterToPixel(input);
		
		assertEquals(expected, actual);
	}

	@Test
	public void testGetPixelToMeter() {
		float expected = 0.2f;
		float actual = CoordinateConverter.getPixelToMeter();
		assertEquals(expected, actual,0.00001f);
	}

	@Test
	public void testGetMeterToPixel() {
		
		float expected = 5.0f;
		float actual = CoordinateConverter.getMeterToPixel();
		assertEquals(expected, actual,0.00001f);
	}

}

