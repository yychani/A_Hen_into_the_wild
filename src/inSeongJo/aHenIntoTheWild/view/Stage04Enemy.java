package inSeongJo.aHenIntoTheWild.view;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Stage04Enemy {
	private int x;
	private int y;

	public Stage04Enemy(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void move() {
		x -= 15;
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
