package data;

public class Checkpoint {

	private Tile tile;
	private int xDirection, yDirection;
	
	public Checkpoint(Tile tile, int xDirection, int yDirection) {
		this.tile = tile;
		this.xDirection = xDirection;
		this.yDirection = yDirection;
	}

	public Tile getTile() {
		return tile;
	}

	public float getXDirection() {
		return xDirection;
	}

	public float getYDirection() {
		return yDirection;
	}
	
	public int[] getPreferDirection() {
		int[] prefer_direction = new int[2];
		prefer_direction[0] = xDirection;
		prefer_direction[1] = yDirection;
		return prefer_direction;
	}
}
