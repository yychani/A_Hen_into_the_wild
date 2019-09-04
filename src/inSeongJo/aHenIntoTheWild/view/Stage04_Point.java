package inSeongJo.aHenIntoTheWild.view;

public class Stage04_Point {
	private int x;
	private int y;

	public Stage04_Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void move() {
		y += 5;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
