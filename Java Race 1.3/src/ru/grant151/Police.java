package ru.grant151;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Police { 
	
	Player p = new Player();
	
	int x;
	int y;
	int v;
	Image img_police = new ImageIcon ("res/police.png").getImage();
	Road road;
	
	public Rectangle getRect() {
		return new Rectangle(x, y, 200, 100);
	}
	
	public Police(int x, int y, int v, Road road) {
		this.x = x;
		this.y = y;
		this.v = v;
		this.road= road;
		
	}
	
	public void move() {
		x = x - road.p.v + v;
		
	}

}
