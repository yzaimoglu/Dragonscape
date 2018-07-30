package me.firelove.dragonscape.configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Configuration {

	private static int WINDOWS = 0, MACOS = 1/*, LINUX = 2*/;
	private static int OPERATING_SYSTEM = MACOS;
	
	@SuppressWarnings({ "unused", "unchecked" })
	public static JSONObject getConfig() {
		String os = "NONE";
		if(OPERATING_SYSTEM == WINDOWS) {
			os = "C://Dragonscape/config.json";
		} else if(OPERATING_SYSTEM == MACOS) {
			os = "/Users/apple/Documents/config.json";
		}
		File file = new File(os);
		if(!file.exists()) {
			file = new File(os);
			JSONObject obj = new JSONObject();
			obj.put("Width", 800);
			obj.put("Height", 600);
			obj.put("Enable Fullscreen", false);
			obj.put("Show FPS", true);
			obj.put("Enable VSync", true);
			obj.put("FPS Limit", 60);
		    try (FileWriter filewriter = new FileWriter(os)) {

			    filewriter.write(obj.toJSONString());
			    filewriter.flush();

		    } catch (IOException e) {
		    	e.printStackTrace();
		    }
		}
		
		
		JSONParser parser = new JSONParser();
		JSONObject config = null;
		try {
			config = (JSONObject) parser.parse(new FileReader(os));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return config;
		
	}
	
	public static String getString(String param) {
		JSONObject obj = getConfig();
		return obj.get(param).toString();
	}
	
	public static int getInteger(String param) {
		JSONObject obj = getConfig();
		return Integer.valueOf(obj.get(param).toString());
	}
	
	public static float getFloat(String param) {
		JSONObject obj = getConfig();
		return Float.valueOf(obj.get(param).toString());
	}
	
	public static boolean getBoolean(String param) {
		JSONObject obj = getConfig();
		return Boolean.valueOf(obj.get(param).toString());
	}
	
	public static List<String> getChangelog() {
		List<String> changes = new ArrayList<>();
		changes.add("- Added a main menu");
		changes.add("- Added the player entity");
		changes.add("- Added player movement");
		changes.add("- Added entities");
		changes.add("- Added entity-player collision");
		return changes;
	}
}
