package data;

import org.lwjgl.opengl.Display;

import helpers.Clock;

import static helpers.Artist.*;

public class Boot {
	public Boot() {
		
		BeginSession();
	
		int[][] map = {
				{0, 1, 0, 0, 0, 0, 0, 0, 1, 0}, 
				{0, 1, 0, 0, 0, 0, 0, 0, 1, 0}, 
				{0, 1, 0, 0, 0, 0, 0, 0, 1, 0}, 
				{0, 1, 0, 0, 0, 0, 0, 0, 1, 0}, 
				{0, 1, 0, 0, 0, 0, 0, 0, 1, 0}, 
				{0, 1, 0, 1, 1, 1, 0, 0, 1, 0}, 
				{0, 1, 0, 1, 0, 1, 0, 0, 1, 0}, 
				{0, 1, 0, 1, 0, 1, 0, 0, 1, 0}, 
				{0, 1, 0, 1, 0, 1, 1, 1, 1, 0}, 
				{0, 1, 1, 1, 0, 0, 0, 0, 0, 0}
		};
		
		Game game = new Game(map);
		
		while(!Display.isCloseRequested()) {
			Clock.update();
			
			game.update();
			
			Display.update();
			Display.sync(120);
		}
		
		Display.destroy();
		
	}
	
	public static void  main(String[] args) {
		new Boot();
	}
}
