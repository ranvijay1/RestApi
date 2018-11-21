package com.epam.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Base {

	public static Properties prop;
	public static Properties pros;
	public static int Res_code_200 = 200;
	public static int Res_code_201 = 201;
	public static int Res_code_404 = 404;
	
	public static Properties getUserProperty() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/resource/java/com/epam/config/config.properties");
			prop.load(ip);
		} catch(FileNotFoundException fe) {
			fe.printStackTrace();
		}catch(IOException io) {
			io.printStackTrace();
		}
		
		return prop;
		
	}
}
