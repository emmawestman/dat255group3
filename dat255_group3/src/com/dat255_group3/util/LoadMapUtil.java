package com.dat255_group3.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class LoadMapUtil {
	

	public static InputStream getTmxFile(String name) {
		try {
			return new FileInputStream(name);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null; //to satisfy compiler
	}
}
