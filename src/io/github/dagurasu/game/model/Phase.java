package io.github.dagurasu.game.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Phase extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Image background;
	private Player player;
	private Timer timer;
	private List<Enemy1> enemy1;
	private List<Stars> stars;
	private boolean inGame;

	public Phase() {
		setFocusable(true);
		setDoubleBuffered(true);

		ImageIcon reference = new ImageIcon("images\\background.png");
		background = reference.getImage();

		player = new Player();
		player.load();

		addKeyListener(new KeyboardAdapter());

		timer = new Timer(5, this);
		timer.start();

		initializesEnemies();
		initializesStars();
		inGame = true;
	}

	public void initializesEnemies() {
		int coordinates[] = new int[40];
		enemy1 = new ArrayList<Enemy1>();

		for (int i = 0; i < coordinates.length; i++) {
			int x = (int) (Math.random() * 8000 + 1024);
			int y = (int) (Math.random() * 650 + 30);
			enemy1.add(new Enemy1(x, y));
		}
	}

	public void initializesStars() {
		int coordinates[] = new int[500];
		stars = new ArrayList<Stars>();

		for (int i = 0; i < coordinates.length; i++) {
			int x = (int) (Math.random() * 1024 + 0);
			int y = (int) (Math.random() * 768 + 0);
			stars.add(new Stars(x, y));
		}

	}

	public void paint(Graphics graphic) {
		Graphics2D graphics = (Graphics2D) graphic;
		if (inGame) {
			graphics.drawImage(background, 0, 0, null);

			for (int p = 0; p < stars.size(); p++) {
				Stars star = stars.get(p);
				star.load();
				graphics.drawImage(star.getImage(), star.getX(), star.getY(), this);
			}

			graphics.drawImage(player.getImage(), player.getX(), player.getY(), this);

			List<Shot> shots = player.getShots();
			for (int i = 0; i < shots.size(); i++) {
				Shot shot = shots.get(i);
				shot.load();
				graphics.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
			}

			for (int o = 0; o < enemy1.size(); o++) {
				Enemy1 enemy = enemy1.get(o);
				enemy.load();
				graphics.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
			}

		} else {
			ImageIcon gameOver = new ImageIcon("images\\gameOver.jpg");
			graphics.drawImage(gameOver.getImage(), 0, 0, null);
		}

		graphic.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player.update();

		if (player.isTurbo()) {
			timer.setDelay(2);
		}

		if (player.isTurbo() == false) {
			timer.setDelay(5);
		}

		for (int p = 0; p < stars.size(); p++) {
			Stars star = stars.get(p);
			if (star.isVisible()) {
				star.update();
			} else {
				stars.remove(p);
			}
		}

		List<Shot> shots = player.getShots();
		for (int i = 0; i < shots.size(); i++) {
			Shot shot = shots.get(i);
			if (shot.isVisible()) {
				shot.update();

				if (player.isTurbo()) {
					shots.get(i).setVELOCITY(-1);
				}

				if (player.isTurbo() == false) {
					shots.get(i).setVELOCITY(2);
				}

			} else {
				shots.remove(i);
			}
		}

		for (int o = 0; o < enemy1.size(); o++) {
			Enemy1 enemy = enemy1.get(o);
			if (enemy.isVisible()) {
				enemy.update();
			} else {
				enemy1.remove(o);
			}
		}
		checkCollisions();
		repaint();
	}

	public void checkCollisions() {
		Rectangle spaceshipShape = player.getBounds();
		Rectangle enemy1Shape;
		Rectangle shotShape;

		for (int i = 0; i < enemy1.size(); i++) {
			Enemy1 temporaryEnemy = enemy1.get(i);
			enemy1Shape = temporaryEnemy.getBounds();
			if (spaceshipShape.intersects(enemy1Shape)) {
				if (player.isTurbo()) {
					temporaryEnemy.setVisible(false);
				} else {
					player.setVisible(false);
					temporaryEnemy.setVisible(false);
					inGame = false;
				}
			}
		}

		List<Shot> shots = player.getShots();
		for (int j = 0; j < shots.size(); j++) {
			Shot temporaryShot = shots.get(j);
			shotShape = temporaryShot.getBounds();
			for (int o = 0; o < enemy1.size(); o++) {
				Enemy1 temporaryEnemy = enemy1.get(o);
				enemy1Shape = temporaryEnemy.getBounds();
				if (shotShape.intersects(enemy1Shape)) {
					temporaryEnemy.setVisible(false);
					temporaryShot.setVisible(false);
				}
			}
		}
	}

	private class KeyboardAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent event) {
			player.keyPressed(event);
		}

		@Override
		public void keyReleased(KeyEvent event) {
			player.keyRelesead(event);
		}
	}

}
