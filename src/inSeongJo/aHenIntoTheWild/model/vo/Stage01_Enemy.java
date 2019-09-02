package inSeongJo.aHenIntoTheWild.model.vo;

public class Stage01_Enemy {
	private int x;
	private int y;

	int speed;

	public Stage01_Enemy(int x, int y, int speed ) {
		this.x = x;
		this.y = y;
		this.speed = speed;
	}
	public void move(){ 
		x -= speed;
		System.out.println("speed1 = " + speed);
	}
	public void move2() {
		x += speed;
		System.out.println("speed2 = " + speed);
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
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
}
