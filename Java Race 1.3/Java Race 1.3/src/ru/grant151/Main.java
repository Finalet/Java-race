package ru.grant151;

import javax.swing.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		JFrame f = new JFrame("Burn Your Tyre");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1100, 600);
		f.setResizable(false);
		f.add(new Road());
		f.setVisible(true);
	}

}
