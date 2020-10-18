package io.github.dagurasu.game.model;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Stars {

	private Image image;
	private int x, y;
	private int height, width;
	private boolean isVisible;

	// private static final int WIDTH = 938;
	private static int VELOCITY = 2;

	public Stars(int x, int y) {
		this.x = x;
		this.y = y;
		isVisible = true;
	}

	public void load() {
		ImageIcon reference = new ImageIcon("images\\star.png");
		image = reference.getImage();

		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
	}

	public void update() {
		if (this.x < 0) {
			this.x = width;
			Random random = new Random();
			int m = random.nextInt(500);
			this.x = m + 1024;

			Random random2 = new Random();
			int a = random2.nextInt(768);
			this.y = a;
		} else {
			this.x -= VELOCITY;
		}

	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public static int getVELOCITY() {
		return VELOCITY;
	}

	public static void setVELOCITY(int vELOCITY) {
		VELOCITY = vELOCITY;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImage() {
		return image;
	}

}
