package data;

import org.newdawn.slick.opengl.Texture;
import org.w3c.dom.css.Counter;

import static helpers.Artist.*;
import static helpers.Clock.*;

import java.util.ArrayList;

public class Enemy implements Entity{
	private int width, height, currentCheckpoint;
	private float x, y, speed, startHealth, health;
	private Texture texture, healthBackground, healthBorder, healthForeground;
	private Tile startTile;
	private boolean first = true;
	private TileGrid grid;
	private boolean alive = true;
	
	private ArrayList<Checkpoint> checkpoints;
	private int[] directions;
	
	public Enemy(Texture texture, Tile startTile, TileGrid grid, int width, int height, float speed, float health) {
		this.texture = texture;
		this.healthBackground = QuickLoad("healthBackground");//healthBackground
		//this.healthBorder = QuickLoad("healthBorder");//healthBorder
		this.healthForeground = QuickLoad("healthForeground");//healthBorder
		this.startTile = startTile;
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.grid = grid;
		this.startHealth = health;
		this.health = health;
		
		this.checkpoints = new ArrayList<Checkpoint>();
		this.directions = new int[2];
		//X direction
		this.directions[0] = 0;
		//Y direction
		this.directions[1] = 0;
		directions = findNextD(startTile, null);
		this.currentCheckpoint = 0;
		populateCheckpointList();
	}
	
	public void update() {
		if(first)
			first = false;
		else {
			if(checkpointReached()) {
				if(currentCheckpoint + 1 >= checkpoints.size()) {
					die();
					System.out.println("Enemy reached end");
				}
				else
					currentCheckpoint++;
			} else {
				x += Delta() * checkpoints.get(currentCheckpoint).getXDirection() * speed;
				y += Delta() * checkpoints.get(currentCheckpoint).getYDirection() * speed;
			}
		}
		//(x, y) = f(stage)
		//(x1, y1), (x2, x2), (x3, y3) - point trajectory
	}
	
	//build roadmap
	private boolean checkpointReached() {
		boolean reached = false;
		Tile t = checkpoints.get(currentCheckpoint).getTile();
		if(x > t.getX() - 3 && x < t.getX() + 3 && y > t.getY() - 3 && y < t.getY() + 3) {
			reached = true;
			x = t.getX();
			y = t.getY();
		}
		return reached; // TO DO
	}
	
	private void populateCheckpointList() {
		checkpoints.add(findNextC(startTile, directions = findNextD(startTile, null)));
		
		int counter = 0;
		boolean cont = true;
		while (cont) {
			int[] currentD = findNextD(checkpoints.get(counter).getTile(), 
					checkpoints.get(counter).getPreferDirection());
			if (currentD[0] == 2 || counter == 20) {
				cont = false;
			} else {
				checkpoints.add(findNextC(checkpoints.get(counter).getTile(), 
						directions = findNextD(checkpoints.get(counter).getTile(), 
								checkpoints.get(counter).getPreferDirection())));
			}
			counter++;
		}
	}
	
	private Checkpoint findNextC(Tile s, int[] dir) {
		Tile next = null;
		Checkpoint c = null;
		
		//Boolean to decide of next checkpoint is fund
		boolean found = false;
		
		//Integer to increment each loop
		int counter = 1;
		
		while(!found) {
			if(s.getXPlace() + dir[0] * counter == grid.getTilesWide() || 
					s.getYPlace() + dir[1] * counter == grid.getTilesHigh() || 
					s.getType() != 
					grid.getTile( s.getXPlace() + dir[0] * counter, 
							s.getYPlace() + dir[1] * counter).getType()) {
				found = true;
				counter -=1;
				next = grid.getTile(s.getXPlace() + dir[0] * counter, 
							s.getYPlace() + dir[1] * counter);
			}
			
			counter++;
		}
		
		c = new Checkpoint(next, dir[0], dir[1]);
		return c;
	}
	
	private int[] findNextD(Tile s, int[] dir_pre) {
		int[] dir = new int[2];
		if(dir_pre==null) {
			dir_pre = new int[2];
			dir_pre[0] = 0;
			dir_pre[1] = 0;
		}
		Tile u = grid.getTile(s.getXPlace(), s.getYPlace() - 1 );	//top
		Tile r = grid.getTile(s.getXPlace() + 1, s.getYPlace() );	//right
		Tile d = grid.getTile(s.getXPlace(), s.getYPlace() + 1 );	//bottom
		Tile l = grid.getTile(s.getXPlace() - 1, s.getYPlace() );	//left
		
		if(s.getType() == u.getType() && dir_pre[1]!=1) {
			dir[0] = 0;
			dir[1] = -1;
		} else if(s.getType()==r.getType() && dir_pre[0]!=-1) {
			dir[0] = 1;
			dir[1] = 0;
		} else if(s.getType()==d.getType() && dir_pre[1]!=-1) {
			dir[0] = 0;
			dir[1] = 1;
		} else if(s.getType()==l.getType() && dir_pre[0]!=1) {
			dir[0] = -1;
			dir[1] = 0;
		} else {
			//dir[0] = -1;
			//dir[1] = -1;
			//System.out.println("NO DIRECTION!");
			//to do
			dir[0] = 2;//-dir_pre[0];
			dir[1] = 2;//-dir_pre[1];
		}
		
		return dir;
	}
	
	
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
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

	public void setY(float y) {
		this.y = y;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Tile getStartTile() {
		return startTile;
	}

	public void setStartTile(Tile startTile) {
		this.startTile = startTile;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}
	
	public void damage(int amount) {
		health -= amount;
		if (health <= 0)
			die();
	}
	
	private void die() {
		alive = false;
	}

	public boolean isAlive() {
		return alive;
	}
	
	public void draw() {
		float healthPercentage = health / startHealth;
		DrawQuadTex(texture, x, y, width, height);
		DrawQuadTex(healthBackground, x, y - 16, width, 8);
		DrawQuadTex(healthForeground, x, y - 16, TILE_SIZE * healthPercentage, 8);
		//DrawQuadTex(healthBorder, x, y - 16, width, 8);
	}
	
	public TileGrid getTileGrid() {
		return grid;
	}

}
