package com.noteslookup.githubreader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

public class PropertiesFileGenerator {

	public void startFileCreationProcess(List<String> lines, String filePathName) {
		Properties prop = new Properties();
		File file = new File(filePathName);
		
		try (InputStream in = new FileInputStream(file)) {
			prop.load(in);
			
			prop.setProperty("key", "value");

			OutputStream out = new FileOutputStream(file);
			prop.store(out, "some comment");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
