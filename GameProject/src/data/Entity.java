package data;

public interface Entity {
	public float getX();
	public float getY();
	public int getWidth();
	public int getHeight();
	public void setX(float x);
	public void setY(float y);
	public void setWidth(int x);
	public void setHeight(int y);
	public void update();
	public void draw();
}
