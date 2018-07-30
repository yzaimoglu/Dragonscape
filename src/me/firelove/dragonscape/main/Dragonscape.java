package me.firelove.dragonscape.main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;

import me.firelove.dragonscape.configuration.Configuration;
import me.firelove.dragonscape.states.Game;
import me.firelove.dragonscape.states.MainMenu;
import me.firelove.dragonscape.states.SplashScreen;

public class Dragonscape extends StateBasedGame {

    public static final int SPLASHSCREEN = 0;
    public static final int MAINMENU     = 1;
    public static final int GAME         = 2;
    
    private static String NAME = "Dragonscape";
    private static String TYPE = "Alpha";
    private static double VERSION = 0.2;
	
	public Dragonscape(String title) {
		super(title);
	}
	
	public static void main(String[] args) throws SlickException {
		
		AppGameContainer app = new AppGameContainer(new Dragonscape(NAME+" "+TYPE+" v"+VERSION));
		
		app.setDisplayMode(Configuration.getInteger("Width"), Configuration.getInteger("Height"), Configuration.getBoolean("Enable Fullscreen"));
		app.setTargetFrameRate(Configuration.getInteger("FPS Limit"));
		app.setShowFPS(Configuration.getBoolean("Show FPS"));
		app.setVSync(Configuration.getBoolean("Enable VSync"));
		app.start();
	}
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
        this.addState(new SplashScreen());
        this.addState(new MainMenu());
        this.addState(new Game());
        this.enterState(0, new FadeInTransition(Color.transparent, 3500), new EmptyTransition());
	}


	
	
	
	

}
