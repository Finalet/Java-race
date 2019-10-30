package ru.grant151;

import java.awt.Image;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class Player {
	
	public static final int MAX_V = 100;
	public static final int MAX_TOP = 70;
	public static final int MAX_BOTTOM = 450;
	
	
	Image img = new ImageIcon ("res/player.png").getImage();
	Image img_3 = new ImageIcon ("res/player.png").getImage();
	Image img_l = new ImageIcon ("res/igrokleft.png").getImage();
	Image img_l_3 = new ImageIcon ("res/igrokleft.png").getImage();
	Image img_r = new ImageIcon ("res/igrokright.png").getImage();
	Image img_r_3 = new ImageIcon ("res/igrokright.png").getImage();
	Image img_b = new ImageIcon ("res/player_b.png").getImage();
	Image img_b_3 = new ImageIcon ("res/player_b.png").getImage();
	Image img_2 = new ImageIcon ("res/player_2.png").getImage();
	Image img_2_r = new ImageIcon ("res/player_2_right.png").getImage();
	Image img_2_l = new ImageIcon ("res/player_2_left.png").getImage();
	Image img_2_b = new ImageIcon ("res/player_2_b.png").getImage();
	Image img_e = new ImageIcon ("res/protivnik.png").getImage();
	Image img_e_3 = new ImageIcon ("res/protivnik.png").getImage();
	Image img_2_e = new ImageIcon ("res/protivnik_2.png").getImage();
	Image img_police = new ImageIcon ("res/police.png").getImage();
	Image img_clean = new ImageIcon ("res/clean.png").getImage();
	Image img_c = img;
	
	public Rectangle getRect() {
		return new Rectangle(x, y, 202, 100);
	}
	int v = 0;
	int dv = 0;
	int s = 0;
	
	int w= 0;
	int w2 = 3;
	int pause = 3;
	int GS = 3;
	int PW = 3;
	int PEP = 3;
	int PEP2 = 3;
	int ce = 3;
	int knw = 3;
	int YouLost = 3;

	int x = 50;
	int y = 100;
	int dy = 0;
	
	int layer1 = 0;
	int layer2 = 1200;
	
	public void move() {
		s += v;
		v += dv;
		if (v <= 0) v = 0;
		if (v >= MAX_V) v = MAX_V;
		y -= dy;
		if (y <= MAX_TOP) y = MAX_TOP;
		if (y >= MAX_BOTTOM) y = MAX_BOTTOM;
		if (layer2 - v <= 0) {
			layer1 = 0;
			layer2 = 1200;
		} else {
		  layer1 -= v;
		  layer2 -= v;
		}

	}
	

	

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT || key  == KeyEvent.VK_D) {
			if (knw > 2) {
			dv = 1;
			}
		}
		if (key == KeyEvent.VK_LEFT || key  == KeyEvent.VK_A) {
			if (knw > 2) {
			dv = -1;
			}
		}
		if (key == KeyEvent.VK_UP || key  == KeyEvent.VK_W) {
			if (knw > 2) {
			dy = 17;
			img = img_l;
			}
		}
		if (key == KeyEvent.VK_DOWN || key  == KeyEvent.VK_S) {
			if (knw > 2) {
			dy = -17;
			img = img_r;
			}	
		}
		if (key == KeyEvent.VK_ESCAPE) {
			if (GS > 2) {
				System.exit(0);
			}
			if (PW < 2) {
				System.exit(0);
			} else{
				pause = 1;
				
			}
		}
		if (key == KeyEvent.VK_SPACE) {
			if (knw > 2) {
				if (GS < 2) {
					if (w < 1) {
						img = img_2;
						img_c = img_2;
						img_r = img_2_r;
						img_l = img_2_l;
						img_b = img_2_b;
						img_e = img_2_e;
						w = 2;
						w2 = 1;
					} else{
						w = 0;
						img = img_3;
						img_c = img_3;
						img_r = img_r_3;
						img_l = img_l_3;
						img_b = img_b_3;
						img_e = img_e_3;
						w2 = 3;
					}
				} 
			}
		}
			if (key == KeyEvent.VK_ENTER) {
				if (PEP < 2) {
					pause = 3;
					PEP2 = 3;
					ce = 3;
					if (w2 > 2) {
					img_e = img_e_3;
					} else{
						img_e = img_2_e;
					}
					knw = 3;
				}
				GS = 1;
			}
			if (key == KeyEvent.VK_Q) {
				if (YouLost < 2) {
					System.exit(0);
				}
			}
		}


			
	
	
	
     public void keyReleased(KeyEvent e) {
    	 int key = e.getKeyCode();
    	 if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT || key  == KeyEvent.VK_D || key  == KeyEvent.VK_A) {
 			dv = 0;
 		}
    	 if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN || key  == KeyEvent.VK_S || key  == KeyEvent.VK_W) {
  			dy = 0;
  			img = img_c;
    	 }
    	 if (key == KeyEvent.VK_ESCAPE) {
    	 }
    	 
	}
	
}
