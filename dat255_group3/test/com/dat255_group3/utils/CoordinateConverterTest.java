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
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPixelToMeterFloat() {
		float expected = 0.048828125f;
		float input = 5.0f;
		assertEquals(expected,CoordinateConverter.pixelToMeter(input),0.00001f);
	}

	@Test
	public void testPixelToMeterVector2() {
		Vector2 expected = new Vector2();
		Vector2 input = new Vector2();

		expected.x = 0.048828125f;
		expected.y = 0.09765625f;
		input.x = 5.0f;
		input.y = 10.0f;
		assertEquals(expected, CoordinateConverter.pixelToMeter(input));
	}

	@Test
	public void testMeterToPixelFloat() {
		float expected = 512.0f;
		float input = 5.0f;
		assertEquals(expected, CoordinateConverter.meterToPixel(input),0.00001f);
	}

	@Test
	public void testMeterToPixelVector2() {
		Vector2 expected = new Vector2();
		Vector2 input = new Vector2();

		expected.x = 512.0f;
		expected.y = 1024.0f;
		input.x = 5.0f;
		input.y = 10.0f;

		assertEquals(expected, CoordinateConverter.meterToPixel(input));
	}

	@Test
	public void testGetPixelToMeter() {
		float expected = 0.009756f;
		assertEquals(expected, CoordinateConverter.getPixelToMeter(),0.00001f);
	}

	@Test
	public void testGetMeterToPixel() {
		float expected = 102.4f;
		assertEquals(expected, CoordinateConverter.getMeterToPixel(),0.00001f);		
	}

}
