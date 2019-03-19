package data;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

//input class
public class Boot {
	
	public Boot() {
		Display.setTitle("GG Game");
		try {
			Display.setDisplayMode(new DisplayMode(600, 400));
			Display.create();
		} catch(LWJGLException e) {
			e.printStackTrace();
		}
		
		while(!Display.isCloseRequested()) {
			Display.update();
			Display.sync(120);
		}
		
		Display.destroy();
	}
	
	public static void  main(String[] args) {
		new Boot();
	}
	
}
