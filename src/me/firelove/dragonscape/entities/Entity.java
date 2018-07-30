package me.firelove.dragonscape.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import me.firelove.dragonscape.utils.Camera;

public class Entity {
	
	private Image sprite;
	private String name;
	private int type, health, exp, level, hitbox_size_x, hitbox_size_y;
	private float speed, x, y;
	private Rectangle hitbox;
	
	public Entity(Image sprite, String name, int type, float x, float y, float speed, int hitbox_size_y, int hitbox_size_x, int health, int exp, int level) {
		this.setSprite(sprite);
		this.setName(name);
		this.setType(type);
		this.setX(x);
		this.setY(y);
		this.setSpeed(speed);
		this.hitbox = new Rectangle(x, y, hitbox_size_x, hitbox_size_y);
		this.hitbox_size_x = hitbox_size_x;
		this.hitbox_size_y = hitbox_size_y;
		this.setHealth(health);
		this.setExp(exp);
		this.setLevel(level);
	}
	
	public void update(Camera cam) {
		this.hitbox.setBounds(getX() + cam.getX(), getY() + cam.getY()+10, getHitbox_size_x()+20, getHitbox_size_y()-20);
	}
	
	public void render(GameContainer gc, Camera cam) {
		gc.getGraphics().drawImage(getSprite(), getX() + cam.getX(), getY()+ cam.getY());
		//gc.getGraphics().draw(getHitbox()); 		// FOR DEBUG
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y2) {
		this.y = y2;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
	
	public int getHitbox_size_x() {
		return hitbox_size_x;
	}

	public void setHitbox_size_x(int hitbox_size_x) {
		this.hitbox_size_x = hitbox_size_x;
	}

	public int getHitbox_size_y() {
		return hitbox_size_y;
	}

	public void setHitbox_size_y(int hitbox_size_y) {
		this.hitbox_size_y = hitbox_size_y;
	}
	

}
