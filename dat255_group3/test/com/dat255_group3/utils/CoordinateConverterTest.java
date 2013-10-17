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
		//CoordinateConverter.setScreenHeight(100);
		//CoordinateConverter.setScreenWidth(50);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPixelToMeterFloat() {
		float expected = 1.0f;
		float input = 5.0f;
		assertEquals(expected,CoordinateConverter.pixelToMeter(input),0.00001f);

		//CoordinateConverter.setScreenHeight(0);
		//CoordinateConverter.setScreenWidth(0);
		expected = Float.MAX_VALUE;
		input = 5.0f;
		assertEquals(expected,CoordinateConverter.pixelToMeter(input),0.00001f);
	}

	@Test
	public void testPixelToMeterVector2() {
		Vector2 expected = new Vector2();
		Vector2 input = new Vector2();

		expected.x = 1.0f;
		expected.y = 2.0f;
		input.x = 5.0f;
		input.y = 10.0f;
		assertEquals(expected, CoordinateConverter.pixelToMeter(input));

		//CoordinateConverter.setScreenHeight(0);
		//CoordinateConverter.setScreenWidth(0);
		expected.x = Float.MAX_VALUE;
		expected.y = Float.MAX_VALUE;
		input.x = 5.0f;
		input.y = 10.0f;
		assertEquals(expected, CoordinateConverter.pixelToMeter(input));
	}

	@Test
	public void testMeterToPixelFloat() {
		float expected = 25.0f;
		float input = 5.0f;
		assertEquals(expected, CoordinateConverter.meterToPixel(input),0.00001f);

		//CoordinateConverter.setScreenHeight(0);
		//CoordinateConverter.setScreenWidth(0);
		expected = 0.0f;
		input = 5.0f;
		assertEquals(expected, CoordinateConverter.meterToPixel(input),0.00001f);
	}

	@Test
	public void testMeterToPixelVector2() {
		Vector2 expected = new Vector2();
		Vector2 input = new Vector2();

		expected.x = 25.0f;
		expected.y = 50.0f;
		input.x = 5.0f;
		input.y = 10.0f;

		assertEquals(expected, CoordinateConverter.meterToPixel(input));

		//CoordinateConverter.setScreenHeight(0);
		//CoordinateConverter.setScreenWidth(0);
		expected.x = 0.0f;
		expected.y = 0.0f;
		input.x = 5.0f;
		input.y = 10.0f;

		assertEquals(expected, CoordinateConverter.meterToPixel(input));
	}

	@Test
	public void testGetPixelToMeter() {
		float expected = 0.2f;
		assertEquals(expected, CoordinateConverter.getPixelToMeter(),0.00001f);

		//CoordinateConverter.setScreenHeight(0);
		//CoordinateConverter.setScreenWidth(0);
		expected = Float.MAX_VALUE;
		assertEquals(expected, CoordinateConverter.getPixelToMeter(),0.00001f);
	}

	@Test
	public void testGetMeterToPixel() {
		float expected = 5.0f;
		assertEquals(expected, CoordinateConverter.getMeterToPixel(),0.00001f);

		//CoordinateConverter.setScreenHeight(0);
		//CoordinateConverter.setScreenWidth(0);
		expected = 0.0f;
		assertEquals(expected, CoordinateConverter.getMeterToPixel(),0.00001f);
	}

}
