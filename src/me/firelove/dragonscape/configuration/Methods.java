package me.firelove.dragonscape.configuration;

import org.newdawn.slick.Image;

public class Methods {
	
	public static int getCenterX(Image img) {
		return (Configuration.getInteger("Width") / 2) - (img.getWidth() / 2);
	}
	
	public static int getCenterY(Image img) {
		return (Configuration.getInteger("Height") / 2) - (img.getHeight() / 2);
	}

}
