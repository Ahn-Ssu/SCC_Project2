package edu.handong.csee.s;

import java.awt.Color;

import javax.swing.JPanel;

public class CanversMaker {

	private JPanel canversPanel;
	public CanversMaker() {
		JPanel tempPanel = new JPanel();
		tempPanel.setBackground(Color.WHITE);
		canversPanel = tempPanel;
		}

	
	public JPanel getCanvers() {
		
		return canversPanel;
	}
	
}
