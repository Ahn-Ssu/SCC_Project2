package edu.handong.csee.s;

import java.awt.Color;

import javax.swing.JPanel;

public class CanvasMaker {

	private JPanel canvasPanel;
	public CanvasMaker() {
		JPanel tempPanel = new JPanel();
		tempPanel.setBackground(Color.WHITE);
		canvasPanel = tempPanel;
		}

	
	public JPanel getCanvers() {
		return canvasPanel;
	}
	
	
}
