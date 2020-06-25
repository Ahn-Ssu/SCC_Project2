package edu.handong.csee.s;

import java.awt.Color;

import javax.swing.JColorChooser;

public class ColorSelector {

	
	private Color color = Color.BLACK;
	
	public Color ColorSelect(Color color) {
		this.color = JColorChooser.showDialog(null, "Choose a Color", color);
		return this.color;
		}

}
