package me.firelove.dragonscape.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import me.firelove.dragonscape.configuration.Configuration;
import me.firelove.dragonscape.configuration.Methods;

public class MainMenu extends BasicGameState {
	
	   	private int playersChoice = 0;
	   	private int menuChoice = 10;
	   	private Image background = null;
	   	private Rectangle box, box_outline;
	   	private int mouseX, mouseY, text_posY, text_posX;
	   	private static final int MAIN_MENU = 10;
	   	private static final int OPTIONS_MENU = 20;
	   	private static final int CREDITS_MENU = 30;
	    private static final int NOCHOICES = 4;
	    private static final int START = 0;
	    private static final int OPTIONS = 1;
	    private static final int CREDITS = 2;
	    private static final int QUIT = 3;
	    private Image[] playersOptions = new Image[NOCHOICES];
	    private Image[] playersOptions_highlighted = new Image[NOCHOICES];
	    private Color box_color = new Color(140 ,127, 95);
	    private Color box_outline_color = new Color(96 ,81, 51);
	    private Color text_color = new Color(43 ,39, 29);

	@Override
	public void init(GameContainer gc, StateBasedGame sbc) throws SlickException {	
		box = new Rectangle(395, 221, 800, 371);
		text_posY = (int) (box.getY()+15);
		text_posX = (int) (box.getX()+15);
		box_outline = new Rectangle(390, 216, 810, 381);
		background = new Image("assets/mainmenu/background.png");
        playersOptions[0] = new Image("assets/mainmenu/buttons/normal/start.png");
        playersOptions[1] = new Image("assets/mainmenu/buttons/normal/options.png");
        playersOptions[2] = new Image("assets/mainmenu/buttons/normal/credits.png");
        playersOptions[3] = new Image("assets/mainmenu/buttons/normal/exit.png");
        playersOptions_highlighted[0] = new Image("assets/mainmenu/buttons/highlighted/start_highlighted.png");
        playersOptions_highlighted[1] = new Image("assets/mainmenu/buttons/highlighted/options_highlighted.png");
        playersOptions_highlighted[2] = new Image("assets/mainmenu/buttons/highlighted/credits_highlighted.png");
        playersOptions_highlighted[3] = new Image("assets/mainmenu/buttons/highlighted/exit_highlighted.png");
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbc, int delta) throws SlickException {
		Input input = gc.getInput();
		mouseX = input.getMouseX();
		mouseY = input.getMouseY();
		if(menuChoice == MAIN_MENU) {
			if (input.isKeyPressed(Input.KEY_DOWN)) {
	            if (playersChoice == (NOCHOICES - 1)) {
	                playersChoice = 0;
	            } else {
	                playersChoice++;
	            }
	        }
	        if (input.isKeyPressed(Input.KEY_UP)) {
	            if (playersChoice == 0) {
	                playersChoice = NOCHOICES - 1;
	            } else {
	                playersChoice--;
	            }
	        }
	        if (input.isKeyPressed(Input.KEY_ENTER)) {
	            switch (playersChoice) {
	                case QUIT:
	                    System.exit(0);
	                    break;
	                case START:
	                	sbc.enterState(2);
	                	break;
	                case CREDITS:
	                	menuChoice = CREDITS_MENU;
	                	break;
	                case OPTIONS:
	                	menuChoice = OPTIONS_MENU;
	                	break;
	            }
	        }
		} else if(menuChoice == OPTIONS_MENU) {
			if(input.isKeyPressed(Input.KEY_BACK))
				menuChoice = MAIN_MENU;
		} else if(menuChoice == CREDITS_MENU) {
			if(input.isKeyPressed(Input.KEY_BACK))
				menuChoice = MAIN_MENU;
		} else {
			menuChoice = MAIN_MENU;
		}
        
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbc, Graphics g) throws SlickException {
		background.draw(Methods.getCenterX(background), Methods.getCenterY(background));
		g.setColor(box_outline_color);
		g.fill(box_outline);
		g.draw(box_outline);
		g.setColor(box_color);
		g.fill(box);
		g.draw(box);
		g.setColor(text_color);
		g.drawString("Mouse X: "+mouseX+", Mouse Y: "+mouseY, 25, 25);
		for (int i = 0; i < NOCHOICES; i++) {
            if (playersChoice == i) {
            	playersOptions_highlighted[i].drawCentered(200, 250+i*100);
            } else {
            	playersOptions[i].drawCentered(200, 250+i*100);
            }
        }
		if(menuChoice == MAIN_MENU) {
			g.drawString("Changelog", text_posX, text_posY);
			int i = 0;
			for(String changes : Configuration.getChangelog()) {
				i++;
				g.drawString(changes, text_posX, text_posY+i*20);
			}
		} else if(menuChoice == OPTIONS_MENU) {
			
			g.drawString("Options", text_posX, text_posY);
			g.drawString("- Resolution: "+Configuration.getInteger("Width")+"x"+Configuration.getInteger("Height"), text_posX, text_posY+20);
			g.drawString("- FPS Limit: "+Configuration.getInteger("FPS Limit"), text_posX, text_posY+40);
			g.drawString("- Show FPS: "+Configuration.getBoolean("Show FPS"), text_posX, text_posY+60);
			g.drawString("- Enable VSync: "+Configuration.getBoolean("Enable VSync"), text_posX, text_posY+80);
			g.drawString("- Enable Fullscreen: "+Configuration.getBoolean("Enable Fullscreen"), text_posX, text_posY+100);
			g.drawString("Backspace to return", text_posX, text_posY+325);
			
		} else if(menuChoice == CREDITS_MENU) {
			g.drawString("Credits", text_posX, text_posY);
			g.drawString("- Developer: Yagi", text_posX, text_posY+20);
			g.drawString("Backspace to return", text_posX, text_posY+325);
		} else {
			menuChoice = MAIN_MENU;
		}
		
	}

	@Override
	public int getID() {
		return 1;
	}
	
	

}
