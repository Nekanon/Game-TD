package data;

public enum TileType {
	Grass("green", true), Dirt("brown", false), Water("water", false), NULL("water", false);
	
	String textureName;
	boolean buildable;
	
	TileType(String textureName, boolean buildable) {
		this.textureName = textureName;
		this.buildable = buildable;
	}
}
