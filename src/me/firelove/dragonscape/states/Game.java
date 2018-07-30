package me.firelove.dragonscape.states;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import me.firelove.dragonscape.entities.Entity;
import me.firelove.dragonscape.entities.EntityType;
import me.firelove.dragonscape.entities.Player;
import me.firelove.dragonscape.utils.Camera;

public class Game extends BasicGameState {
	
	private Image player_sprite;
	private List<Entity> entities;
	private Player player;
	private Entity test, test2;
	private Camera camera;
	private TiledMap tiledmap;
	private float cameraX, cameraY;
	private boolean paused = false;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbc) throws SlickException {
		entities = new ArrayList<>();
		camera = new Camera(0, 0);
		player_sprite = new Image("assets/entities/player/player_forward1.png");
		player = new Player(player_sprite, "PLAYER", EntityType.PLAYER, 0, 0, 0.3f, player_sprite.getScaledCopy(2).getHeight(), player_sprite.getScaledCopy(2).getWidth() / 3, 10, 0, 1);
		test = new Entity(player_sprite.getScaledCopy(2), "Test Mob", EntityType.PLAYER, 100, 100, 0.0f, player_sprite.getScaledCopy(2).getHeight(), player_sprite.getScaledCopy(2).getWidth() / 3, 10, 0, 0);
		test2 = new Entity(player_sprite.getScaledCopy(2), "Test Mob", EntityType.PLAYER, 200, 200, 0.0f, player_sprite.getScaledCopy(2).getHeight(), player_sprite.getScaledCopy(2).getWidth() / 3, 10, 0, 0);
		tiledmap = new TiledMap("assets/maps/Test.tmx");
		player.init(gc);
		setEntities();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbc, int delta) throws SlickException {
		if(paused) {
			
		} else {
			player.update(gc, delta, player.getX(), player.getY(), player.getSpeed(), camera, entities);			
			for(Entity entity : entities) {
				entity.update(camera);
			}
			cameraX = camera.getX();
			cameraY = camera.getY();
		}
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbc, Graphics g) throws SlickException {
		if(paused) {
			
		} else {
			player.render(gc, cameraX, cameraY, tiledmap, entities);
			for(Entity entity : entities) {
				entity.render(gc, camera);
			}
			g.drawString("X: "+player.getX(), 50, 20);
			g.drawString("Y: "+player.getY(), 50, 35);
			g.drawString("Mouse X: "+gc.getInput().getMouseX(), 50, 50);
			g.drawString("Mouse Y: "+gc.getInput().getMouseY(), 50, 65);
		}
	}

	@Override
	public int getID() {
		return 2;
	}
	
	private void setEntities() {
		entities.add(test);
		entities.add(test2);
	}
	
	
}
