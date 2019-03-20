package data;

import static helpers.Artist.QuickLoad;
import static helpers.Artist.TILE_SIZE;

public class Game {
	
	private TileGrid grid;
	private Player player; 
	private WaveManager waveManager;
	private TowerCannonBlue blue;
	
	public Game(int[][] map) {
		grid = new TileGrid(map);
		waveManager = new WaveManager(new Enemy(QuickLoad("enemy2"), grid.getTile(1, 0), grid, TILE_SIZE, TILE_SIZE, 70, 25), 1, 10);
		player = new Player(grid, waveManager); 
		
		//blue = new TowerCannonBlue(QuickLoad("tower2"), 128, 128, 64, 64);
	}
	
	public void update() {
		
		
		grid.draw();
		//blue.draw();
		waveManager.update();
		player.update();
	}
}
