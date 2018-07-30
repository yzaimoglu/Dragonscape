package me.firelove.dragonscape.entities;

import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import me.firelove.dragonscape.utils.Camera;

public class Player extends Entity {
	
	private Animation player_anim, left, right, forwards, backwards;
	private int[] animationDuration = {200, 200};
	@SuppressWarnings("unused")
	private boolean steady = true, collision = false;
	private int collision_type_x = -1, collision_type_y = -1, RIGHT = 0, LEFT = 1, UP = 2, DOWN = 3;

	public Player(Image sprite, String name, int type, float x, float y, float speed, int hitbox_size_x, int hitbox_size_y, int health, int exp, int level) {
		super(sprite, name, type, x, y, speed, hitbox_size_x, hitbox_size_y, health, exp, level);
	}
	
	public void init(GameContainer gc) throws SlickException {
		Image[] walkLeft = { new Image("assets/entities/player/player_left1.png").getScaledCopy(2), new Image("assets/entities/player/player_left2.png").getScaledCopy(2) };
        Image[] walkRight = { new Image("assets/entities/player/player_right1.png").getScaledCopy(2), new Image("assets/entities/player/player_right2.png").getScaledCopy(2) };
		Image[] walkForwards = { new Image("assets/entities/player/player_forward1.png").getScaledCopy(2), new Image("assets/entities/player/player_forward2.png").getScaledCopy(2) };
        Image[] walkBackwards = { new Image("assets/entities/player/player_backward1.png").getScaledCopy(2), new Image("assets/entities/player/player_backward2.png").getScaledCopy(2) };
        left = new Animation(walkLeft, animationDuration, false);
        right = new Animation(walkRight, animationDuration, false);
        forwards = new Animation(walkForwards, animationDuration, false);
        backwards = new Animation(walkBackwards, animationDuration, false);
        player_anim = forwards;
	}
	
	public void update(GameContainer gc, int delta, float loc_x, float loc_y, float speed, Camera cam, List<Entity> allentitites) {
		getHitbox().setBounds(getX() + cam.getX() + 551 - (getHitbox_size_x()-5) / 3 / 2, getY() + cam.getY() + 350- (getHitbox_size_y()-145) / 3 / 2, (player_anim.getCurrentFrame().getWidth()-2), (player_anim.getCurrentFrame().getHeight()-23));
		float camX = cam.getX();
		float camY = cam.getY();
		Input input = gc.getInput();
		for(Entity entity : allentitites) {
			if(getHitbox().intersects(entity.getHitbox())) {
				steady = true;
				collision = true;
				if(entity.getHitbox().getX() > getHitbox().getX()) {
					collision_type_x = RIGHT;
					loc_x -= speed * delta;
					camX += speed * delta;
					setX(loc_x);
					cam.setX(camX);
				} else if(entity.getHitbox().getX() < getHitbox().getX()) {
					collision_type_x = LEFT;
					loc_x += speed * delta;
					camX -= speed * delta;
					setX(loc_x);
					cam.setX(camX);
				}
				if(entity.getHitbox().getY() < getHitbox().getY()) {
					collision_type_y = DOWN;
					loc_y -= speed * delta;
					camY += speed * delta;
					setY(loc_y);
					cam.setY(camY);
				} else if(entity.getHitbox().getY() > getHitbox().getY()) {
					collision_type_y = UP;
					loc_y += speed * delta;
					camY -= speed * delta;
					setY(loc_y);
					cam.setY(camY);
				}
			} else {
				collision = false;
			}
		}
		if(input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT) || input.isControlPressed(0)) {
			if(collision_type_x != LEFT && collision_type_x != RIGHT) {
				steady = false;
				player_anim = left;
				loc_x -= speed * delta;
				camX += speed * delta;
				setX(loc_x);
				cam.setX(camX);
			} else if(collision_type_x == RIGHT) {
				steady = false;
				player_anim = left;
				loc_x -= speed * delta;
				camX += speed * delta;
				setX(loc_x);
				cam.setX(camX);
				collision_type_x = -1;
				collision_type_y = -1;
			}
		} else if(input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN) || input.isControlPressed(3)) {
			if(collision_type_y != DOWN && collision_type_y != UP) {
				steady = false;
				player_anim = forwards;
				loc_y += speed * delta;
				camY -= speed * delta;
				setY(loc_y);
				cam.setY(camY);
			} else if(collision_type_y == UP) {
				steady = false;
				player_anim = forwards;
				loc_y += speed * delta;
				camY -= speed * delta;
				setY(loc_y);
				cam.setY(camY);
				collision_type_x = -1;
				collision_type_y = -1;
			}
		} else if(input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT) || input.isControlPressed(1)) {
			if(collision_type_x != RIGHT && collision_type_x != LEFT) {
				steady = false;
				player_anim = right;
				loc_x += speed * delta;
				camX -= speed * delta;
				setX(loc_x);
				cam.setX(camX);
			} else if(collision_type_x == LEFT) {
				steady = false;
				player_anim = right;
				loc_x += speed * delta;
				camX -= speed * delta;
				setX(loc_x);
				cam.setX(camX);
				collision_type_x = -1;
				collision_type_y = -1;
			}
		} else if(input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP) || input.isControlPressed(2)) {
			if(collision_type_y != UP && collision_type_y != DOWN) {
				steady = false;
				player_anim = backwards;
				loc_y -= speed * delta;
				camY += speed * delta;
				setY(loc_y);
				cam.setY(camY);
			} else if(collision_type_y == DOWN) {
				steady = false;
				player_anim = backwards;
				loc_y -= speed * delta;
				camY += speed * delta;
				setY(loc_y);
				cam.setY(camY);
				collision_type_x = -1;
				collision_type_y = -1;
			}
		} else {
			steady = true;
		}
		player_anim.update(delta);
	}
	
	public void render(GameContainer gc, float cameraX, float cameraY, TiledMap tm, List<Entity> allentities) {
		//float playerCenterX = getX() + getSprite().getWidth() / 2;
		//float playerCenterY = getY() + getSprite().getHeight() / 2;
		
		tm.render((int)cameraX, (int)cameraY);
		if(!steady) {
			gc.getGraphics().setColor(Color.white);
			//gc.getGraphics().draw(getHitbox());    // FOR DEBUG
			gc.getGraphics().drawAnimation(player_anim, getX() + cameraX + 550, getY() + cameraY + 350);
		} else {
			gc.getGraphics().setColor(Color.white);
			//gc.getGraphics().draw(getHitbox());     // FOR DEBUG
			gc.getGraphics().drawImage(player_anim.getImage(0), getX() + cameraX + 550, getY() + cameraY + 350);
		}
		//gc.getGraphics().drawString("Hitbox Player X: "+getHitbox().getX()+", Hitbox Player Y: "+getHitbox().getY(), 100, 100);
		//gc.getGraphics().drawString("Hitbox Entity X: "+allentities.get(0).getHitbox().getX()+", Hitbox Entity Y: "+allentities.get(0).getHitbox().getY(), 100, 120);
		// gc.getGraphics().drawImage(getSprite().getScaledCopy(getSprite().getWidth() / 3, getSprite().getHeight() / 3), getX() + cameraX + 550, getY() + cameraY + 350);
		// getSprite().draw(getX() + cameraX + 500, getY() + cameraY + 350);
	}

}
