package data;

import static helpers.Artist.*;
import static helpers.Clock.*;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

public class TowerCannon {

	private float x, y, timeSinceLastShot, firingSpeed, angle;
	private int width, height, damage, range;
	private Texture baseTexture, cannonTexture;
	private Tile startTile;
	private ArrayList<Projectile> projectiles;
	private ArrayList<Enemy> enemies;
	private Enemy target;
	private boolean targeted = false;
	
	public TowerCannon(Texture texture, Tile startTile, int damage, int range, ArrayList<Enemy> enemies) {
		this.baseTexture = texture;
		this.cannonTexture = QuickLoad("cannonGun");
		this.startTile = startTile;
		this.width = (int) startTile.getWidth();
		this.height = (int) startTile.getHeight();

		this.x = startTile.getX();
		this.y = startTile.getY();
		
		this.damage = damage;
		this.firingSpeed = 1;
		this.timeSinceLastShot = 0;
		this.range = range;
		this.projectiles = new ArrayList<Projectile>();
		this.enemies = enemies;
		//this.target = acquireTarget();
		//this.angle = calculateAngle();
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
	
	private boolean isRanged(Enemy e) {
		float xDistance = Math.abs(e.getX() - x);
		float yDistance = Math.abs(e.getY() - y);
		if(xDistance < range && yDistance < range) {
			return true;
		}
		return false;
	}
	
	private float findDistance(Enemy e) {
		float xDistance = Math.abs(e.getX() - x);
		float yDistance = Math.abs(e.getY() - y);
		return xDistance + yDistance;
	}
	
	public void updateEnemyList(ArrayList<Enemy> newEnemyList) {
		enemies = newEnemyList;
	}
	
	private float calculateAngle() {
		double angleTemp = Math.atan2(target.getY() - y, target.getX() - x);
		return (float) Math.toDegrees(angleTemp);
	}
	
	private void shoot() {
		timeSinceLastShot=0;
		projectiles.add(new ProjectileIceball(QuickLoad("bullet"), target, x + TILE_SIZE / 4, y + TILE_SIZE / 4, 32, 32, 10000, 10));
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
		DrawQuadTex(baseTexture, x, y, width, height);
		DrawQuadTexRot(cannonTexture, x, y+height/4, width, height/2, angle);
	}
	
}
