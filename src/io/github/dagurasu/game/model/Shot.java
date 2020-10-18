package io.github.dagurasu.game.model;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Shot {

	private Image image;
	private int x, y;
	private int height, width;
	private boolean isVisible;

	private static final int WIDTH = 938;
	private static int VELOCITY = 2;

	public Shot(int x, int y) {
		this.x = x;
		this.y = y;
		isVisible = true;
	}

	public void load() {
		ImageIcon reference = new ImageIcon("images\\shot.png");
		image = reference.getImage();

		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
	}

	public void update() {
		this.x += VELOCITY;
		if (this.x > WIDTH) {
			isVisible = false;
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, height, width);
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
