package edu.handong.csee.s;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

public class ColorSelector {

	
	private Color color = Color.BLACK;
	
	public void ColorSelect(Color color) {
		this.color = JColorChooser.showDialog(null, "Choose a Color", color);
		}

	public Color getColor() {
		return color;
	}

	

}
