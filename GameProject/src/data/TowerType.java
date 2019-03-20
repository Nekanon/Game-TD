package data;

import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;

public enum TowerType {
	
	CannonRed(new Texture[] {QuickLoad("tower"), QuickLoad("cannonGun")}, 10, 1000, 1),
	CannonBlue(new Texture[] {QuickLoad("tower2"), QuickLoad("cannonGun")}, 30, 1000, 2),
	CannonIce(new Texture[] {QuickLoad("tower"), QuickLoad("cannonGun")}, 30, 1000, 3);
	
	Texture[] textures;
	int damage, range;
	float firingSpeed;
	
	TowerType(Texture[] textures, int damage, int range, float firingSpeed) {
		this.textures = textures;
		this.damage = damage;
		this.range = range;
		this.firingSpeed = firingSpeed;
	}
}
