package io.github.dagurasu.game.model;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Player implements ActionListener {

	private int x, y;
	private int dx, dy;
	private Image image;
	private int height, width;
	private List<Shot> shots;
	private boolean isVisible, isTurbo;
	private Timer timer;

	public Player() {
		this.x = 100;
		this.y = 100;
		isVisible = true;
		isTurbo = false;

		shots = new ArrayList<Shot>();

		timer = new Timer(5000, this);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (isTurbo) {
			turbo();
			isTurbo = false;
		}

		if (isTurbo == false) {
			load();
		}
	}

	public void load() {
		ImageIcon reference = new ImageIcon("images\\spaceship.png");
		image = reference.getImage();

		height = image.getHeight(null);
		width = image.getWidth(null);

	}

	public void update() {
		x += dx;
		y += dy;
	}

	public void simpleShot() {
		this.shots.add(new Shot(x + height, y + (width / 2)));
	}

	public void turbo() {
		isTurbo = true;
		ImageIcon reference = new ImageIcon("images\\spaceshipTurbo.png");
		image = reference.getImage();
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, height, width);
	}

	public void keyPressed(KeyEvent event) {
		int code = event.getKeyCode();

		if (code == KeyEvent.VK_SPACE) {
			turbo();
		}
		if (code == KeyEvent.VK_A) {
			if (isTurbo == false) {
				simpleShot();
			}
		}
		if (code == KeyEvent.VK_UP) {
			dy = -3;
		}
		if (code == KeyEvent.VK_DOWN) {
			dy = 3;
		}
		if (code == KeyEvent.VK_LEFT) {
			dx = -3;
		}
		if (code == KeyEvent.VK_RIGHT) {
			dx = 3;
		}

	}

	public void keyRelesead(KeyEvent event) {
		int code = event.getKeyCode();

		if (code == KeyEvent.VK_UP) {
			dy = 0;
		}
		if (code == KeyEvent.VK_DOWN) {
			dy = 0;
		}
		if (code == KeyEvent.VK_LEFT) {
			dx = 0;
		}
		if (code == KeyEvent.VK_RIGHT) {
			dx = 0;
		}

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

	public List<Shot> getShots() {
		return shots;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public boolean isTurbo() {
		return isTurbo;
	}

	public void setTurbo(boolean isTurbo) {
		this.isTurbo = isTurbo;
	}

}
