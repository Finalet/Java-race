package ru.grant151;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Road extends JPanel implements ActionListener, Runnable {
	
	Timer mainTimer = new Timer(20, this);
	
	Image img = new ImageIcon ("res/road.png").getImage();
	
	Player p = new Player();
	
	Menu m = new Menu();
	
	Lost l = new Lost();
	
	Pause pause = new Pause();
	
	EndGame end = new EndGame();
	
	Thread policeFactory = new Thread(this);
	
	Thread enemiesFactory = new Thread(this);
	
	Thread audioThread = new Thread(new AudioThread()); 
			
	List<Police> police = new ArrayList<Police>();
	
	List<Enemy> enemies = new ArrayList<Enemy>();
	
	
	int z = 3;
	int zz = 3;
	int TH = 3;
	int Win = 3;
	int Score = 0;
	int ShowScore = 3;
	
	public Road() {
		mainTimer.start();
		enemiesFactory.start();
		policeFactory.start();
		audioThread.start();
		addKeyListener(new MyKeyAdapter());
		setFocusable(true);
		
	}
	
	private class MyKeyAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			p.keyPressed(e);
			
		}
		public void keyReleased (KeyEvent e) {
			p.keyReleased(e);
		}
	}

	
	
	@SuppressWarnings("deprecation")
	public void paint(Graphics g) {
		
		
		 g = (Graphics2D) g;
		 g.drawImage(img, p.layer1, 0, null);
		 g.drawImage(img, p.layer2, 0, null);
		 g.drawImage(p.img, p.x, p.y, null);
		 g.drawString("by GOO151", 10, 550);
		 
		 if (p.pause < 2) {
			 g.drawImage(pause.img_pause, 0, 0, null);
			 p.v = 0;
			 p.PEP = 1;
			 p.PEP2 = 1;
			 p.ce = 1;
			 p.knw = 1;
			 if (p.ce < 2) {
				 p.img_e = p.img_clean;
			 }
			 ShowScore = 1;
		 }
		 
		 if (Win < 2) {
			 g.drawImage(end.img_end, 0, 0, null);
			 mainTimer.stop();
			 p.PW = 1;
			 p.img_e = p.img_clean;
		 }


		 if (p.GS > 2) {
			 g.drawImage(m.img_menu, 0, 0, null);
			 p.layer1 = 0;
			 p.layer2 = 1200;
			 p.y = 100;
			 p.v = 0;
			 p.s = 0;
			 p.dv = 0;
			 p.dy = 0;
		 }
		 if (p.YouLost < 2) {
			 g.drawImage(l.img_lost, 0, 0, null);
				p.img_e = p.img_clean;
				p.img_2_e = p.img_clean;
		 }
		 
		 double v = (200/Player.MAX_V) * p.v;
		 g.setColor(Color.WHITE);
		 Font font = new Font("Arial", Font.ITALIC, 20);
		 g.setFont (font);
		 if (p.GS < 2) {
		 	g.drawString("Speed: " + v + " κμ/χ", 50, 40);
		 	if (p.PW > 2) {
		 	g.drawString("Score: " + Score + " points", 470, 40);
		 	}
		 }
		 
		 double s = p.s/1000.0;
		 if (p.GS < 2) {
			 g.drawString("Completed: " + s + "", 870, 40);
			 if (p.s >= 10000) {
				 g.drawString("%", 1040, 40);
			 } else{
				 g.drawString("%", 1030, 40);
			 }
		}
		 if (p.PW < 2) {
			 Font font2 = new Font("Arial", Font.ITALIC, 50);
			 g.setFont (font2);
			 g.drawString("" + Score + " ", 510, 331);
		 }
		 if (ShowScore < 2) {
			 Font font2 = new Font("Arial", Font.ITALIC, 50);
			 g.setFont (font2);
			 g.drawString("" + Score + " ", 510, 298);
			 ShowScore = 3;
		 }
		 if (p.YouLost < 2) {
			 Font font2 = new Font("Arial", Font.ITALIC, 50);
			 g.setFont (font2);
			 g.drawString("" + Score + " ", 510, 298);
			 ShowScore = 3;
		 }
		 
		 Iterator<Enemy> i = enemies.iterator();
		 while(i.hasNext()) {
			 Enemy e = i.next();
			 if (e.x >= 2400 || e.x <= -2400) {
				 i.remove();
			 } else{
				 e.move();
			     g.drawImage(p.img_e, e.x, e.y, null);
			 }
			 

			 Iterator<Police> po = police.iterator();
			 while(po.hasNext()) {
				 Police pol = po.next();
				 if (pol.x >= 2400 || pol.x <= -2400) {
					 po.remove();
					 if (pol.x <= 1150) {
						 if (e.x < 1) {
							 i.remove();
						 } else{
							 
						 }
					 }
				 } else{
					 pol.move();
					 pol.v = p.v -1;
					 if (p.v < 1) {
						 pol.v = 3;
					 }
					 pol.y = p.y;
					 g.drawImage(p.img_police, pol.x, pol.y, null);
					 
						 }
					 if (pol.x < 1100) {
						 z = 0;
				 }
					 if (z < 1) {
						if (pol.x > 1100) {
							po.remove();
						}
					 }
			 }
		 }
	}


	public void actionPerformed(ActionEvent e) {
		p.move();
		repaint();
		Score();
		if (p.PEP2 > 2) {
			testCollisionWithEnemies();
		} 
		BumpPolice();
		testWin();
		mainTimer.start();
	}
	
	private void testWin() {
		if (p.s > 100000) {
			Win = 1;
		}
	}
	

	private void testCollisionWithEnemies() {
		Iterator<Enemy> i = enemies.iterator();
		while (i.hasNext()) {
			Enemy e = i.next();
			if (p.getRect().intersects(e.getRect())) {
				p.YouLost = 1;
				p.knw = 1;
				p.v = 0;
				p.dv = 0;
				p.dy = 0;
				p.img = p.img_b;
				}
			}
		}


	
	private void BumpPolice() {
		Iterator<Police> i = police.iterator();
		while (i.hasNext()) {
			Police pol = i.next();
			if (p.getRect().intersects(pol.getRect())) {
				p.YouLost = 1;
				p.knw = 1;
				p.v = 0;
				p.dv = 0;
				p.dy = 0;
				p.img = p.img_b;
			}
		}
		
	}
	

	@Override
	public void run() {
		
		while(true) {
			Random rand = new Random();
			try {
				Thread.sleep(rand.nextInt(2000));
				enemies.add(new Enemy(1200, rand.nextInt(500), rand.nextInt(60), this));
				if (p.v > 30) {
					Thread.sleep(5000);
					police.add(new Police(1200, rand.nextInt(500), 31, this));
					}
				
			} catch (InterruptedException e) {
				e.printStackTrace();

			}
		}
	}
	public void Score() {
		if (p.v > 75) {
			 Score = Score + 125;
		}
		if (p.v > 50) {
			Score = Score + 25;
		}
		if (p.v > 25) {
			Score = Score + 10;
		}
		if (p.v > 10) {
			Score = Score + 5;
		}
	}
}


			
			

