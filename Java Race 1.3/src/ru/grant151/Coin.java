package ru.grant151;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Coin {
	int x;
	int y;
	int v;
	Image img_coin = new ImageIcon ("res/coin.png").getImage();
	Road road;
	
	public Rectangle getRect() {
		return new Rectangle(x, y, 200, 100);
	}
	
	public Coin(int x, int y, int v, Road road) {
		this.x = x;
		this.y = y;
		this.v = v;
		this.road= road;
		
	}
	
	public void move() {
		x = x - road.p.v + v;
		
	}

}

