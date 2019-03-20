package data;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import helpers.Clock;

import static helpers.Artist.*;

import java.util.ArrayList;

public class Player {
	private TileGrid grid;
	private TileType[] types;
	private WaveManager waveManager;
	private ArrayList<Tower> towerList;
	private boolean leftMouseButtenDown;
	
	public Player(TileGrid grid, WaveManager waveManager) {
		this.grid = grid;
		this.types = new TileType[3];
		this.types[0] = TileType.Grass;
		this.types[1] = TileType.Dirt;
		this.types[2] = TileType.Water;
		this.waveManager = waveManager;
		this.towerList = new ArrayList<Tower>();
		this.leftMouseButtenDown = false;
	}
 	
	public void update(){
		for(Tower t : towerList) {
			t.updateEnemyList(waveManager.getCurrentWave().getEnemyList());
			t.update();
		}
		
		//Handle Mouse Input
		if(Mouse.isButtonDown(0) && !leftMouseButtenDown) {
			towerList.add(new TowerCannonBlue(TowerType.CannonRed, grid.getTile(Mouse.getX()/TILE_SIZE, (HEIGHT - Mouse.getY()-1)/TILE_SIZE), waveManager.getCurrentWave().getEnemyList()));
			//SetTile();
		}
		
		leftMouseButtenDown = Mouse.isButtonDown(0);
		
		//Handle Keyboard Input
		while (Keyboard.next()) {
			if(Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {
				//moveIndex();
				Clock.ChangeMultiplier(0.2f);
			}
			if(Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState()) {
				//moveIndex();
				Clock.ChangeMultiplier(-0.2f);
			}
		}
	}
}
