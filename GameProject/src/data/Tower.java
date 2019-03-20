package data;

import org.newdawn.slick.opengl.Texture;
import static helpers.Artist.DrawQuadTex;
import static helpers.Artist.DrawQuadTexRot;
import static helpers.Artist.QuickLoad;
import static helpers.Artist.TILE_SIZE;
import static helpers.Clock.Delta;

import java.util.ArrayList;

public abstract class Tower implements Entity {

	private float x, y, timeSinceLastShot, firingSpeed, angle;
	private int width, height, damage, range;
	private Enemy target;
	private Texture[] textures;
	private ArrayList<Enemy> enemies;
	private ArrayList<Projectile> projectiles;
	
	public Tower(TowerType type, Tile startTile, ArrayList<Enemy> enemies) {
		this.textures = type.textures;
		this.damage = type.damage;
		this.range = type.range;
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = startTile.getWidth();
		this.height = startTile.getHeight();
		this.enemies = new ArrayList<Enemy>();
		this.timeSinceLastShot = 0;
		this.projectiles = new ArrayList<Projectile>();
		this.firingSpeed = type.firingSpeed;
		this.angle = 0f;
	}

	private Enemy acquireTarget() {
		Enemy potential = null;
		float closestDistance = 10000;
		for(Enemy e : enemies) {
			if(e.isAlive() && isRanged(e) && findDistance(e)<closestDistance) {
				closestDistance = findDistance(e);
				potential = e;
			}
		}
		return potential;
	}

	private float findDistance(Enemy e) {
		float xDistance = Math.abs(e.getX() - x);
		float yDistance = Math.abs(e.getY() - y);
		return xDistance + yDistance;
	}
	
	private float calculateAngle() {
		double angleTemp = Math.atan2(target.getY() - y, target.getX() - x);
		return (float) Math.toDegrees(angleTemp);
	}
	
	public void shoot() {
		timeSinceLastShot=0;
		projectiles.add(new ProjectileBasic(QuickLoad("bullet"), target, x + TILE_SIZE / 4, y + TILE_SIZE / 4, 32, 32, 10000, 10));
	}
	
	private boolean isRanged(Enemy e) {
		float xDistance = Math.abs(e.getX() - x);
		float yDistance = Math.abs(e.getY() - y);
		if(xDistance < range && yDistance < range) {
			return true;
		}
		return false;
	}
	
	public void updateEnemyList(ArrayList<Enemy> newEnemyList) {
		enemies = newEnemyList;
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setX(float x) {
		this.x = x;
		
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setWidth(int x) {
		this.width = x;
	}

	public void setHeight(int y) {
		this.height = y;
	}

	public void update() {
		target = acquireTarget();
		
		timeSinceLastShot += Delta();
		if(target!=null) {
			if(timeSinceLastShot > firingSpeed) 
				shoot();
				
			for(Projectile p : projectiles )
				p.update();
				
			angle = calculateAngle();
		}
		
		draw();
	}

	public void draw() {
		DrawQuadTex(textures[0], x, y, width, height);
		if(textures.length > 1)
			for (int i = 1; i < textures.length; i++)
				DrawQuadTexRot(textures[i], x, y, width, height, angle);
	}
	
	public Enemy getTarget() {
		return target;
	}
}
