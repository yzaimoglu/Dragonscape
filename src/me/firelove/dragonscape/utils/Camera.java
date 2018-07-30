package me.firelove.dragonscape.utils;

import org.newdawn.slick.geom.Rectangle;

import me.firelove.dragonscape.configuration.Configuration;

public class Camera {
	
	private float x, y;
	private Rectangle rect;
	
	public Camera(float x, float y) {
		this.setX(x);
		this.setY(y);
		this.setRect(new Rectangle(x, y, Configuration.getInteger("Width"), Configuration.getInteger("Height")));
	}
	
	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}

	public float getY() {
		return y;
	}

	public void setY(float y2) {
		this.y = y2;
	}

	public float getX() {
		return x;
	}

	public void setX(float x2) {
		this.x = x2;
	}

}
