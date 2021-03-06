package data;

import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;
import static helpers.Clock.*;

public abstract class Projectile implements Entity {
	
	private Texture texture;
	private float x, y, speed, xVelocity, yVelocity;
	private int damage, width, height;
	private Enemy target;
	private boolean alive;
	
	public Projectile(Texture texture, Enemy target, float x, float y, int width, int height, float speed, int damage) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.damage = damage;
		this.target = target;
		this.alive = true;
		this.xVelocity = 0f;
		this.yVelocity = 0f;
		calculateDirection();
	}
	
	private void calculateDirection() {
		float totalAllowedMovement = 1.0f;
		float xDistanceFromTarget = Math.abs(target.getX() - x + TILE_SIZE / 4);
		float yDistanceFromTarget = Math.abs(target.getY() - y + TILE_SIZE / 4);
		float totalDistanceTarget = xDistanceFromTarget + yDistanceFromTarget;
		float xPercentOfMovement = xDistanceFromTarget / totalDistanceTarget;
		xVelocity = xPercentOfMovement;
		yVelocity = totalAllowedMovement - xPercentOfMovement;
		if(target.getX() < x)
			xVelocity *= -1;
		if(target.getY() < y)
			yVelocity *= -1;
	
	}
	
	public void damage() {
		target.damage(damage);
		alive = false;
	}
	
	public void update() {
		if(alive) {
			x += xVelocity * speed * Delta();
			y += yVelocity * speed * Delta();
			if( CheckCollision(x, y, width, height, target.getX(), target.getY(), (float)target.getWidth(), (float)target.getHealth()) ) {
				damage();
			}
			draw();
		}
	} 
	
	public void draw() {
		DrawQuadTex(texture, x, y, 32, 32);
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
	
	public Enemy getTarget() {
		return target;
	}
	
	public void setAlive(boolean status) {
		alive = status;
	}
}
