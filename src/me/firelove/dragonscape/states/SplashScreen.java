package me.firelove.dragonscape.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import me.firelove.dragonscape.configuration.Methods;

public class SplashScreen extends BasicGameState {
	
	private Image splash;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbc) throws SlickException {
		gc.getInput().initControllers();
		splash = new Image("assets/splashscreen/Splash.png");
		splash.setImageColor(100, 255, 255);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbc, int delta) throws SlickException {
		sbc.enterState(1, new FadeOutTransition(Color.transparent, 3500), new EmptyTransition());
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbc, Graphics g) throws SlickException {
		g.setBackground(Color.darkGray);
		splash.draw(Methods.getCenterX(splash), Methods.getCenterY(splash));
	}

	@Override
	public int getID() {
		return 0;
	}

	
	

}
