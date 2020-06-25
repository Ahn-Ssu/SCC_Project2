package edu.handong.csee.s;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ToolBarMaker {
	private JPanel toolPanel ;
	
	public ToolBarMaker() {
		JPanel tempPanel = new JPanel();
		GridLayout toolLayout = new GridLayout(12,1);
		
		JLabel selectionTag = new JLabel("Select",SwingConstants.CENTER);
		JPanel selectionSlot = new JPanel(new GridLayout(1,2));
		JButton s1 = new JButton ("s1");
		JButton s2 = new JButton ("s2");
		selectionSlot.add(s1);selectionSlot.add(s2);
		JLabel penTag = new JLabel("Pen / Eraser",SwingConstants.CENTER);
		JPanel penSlot = new JPanel(new GridLayout(1,2));
		JButton p1 = new JButton ("p1");
		JButton p2 = new JButton ("p2");
		penSlot.add(p1);penSlot.add(p2);
		JLabel boldTag = new JLabel("Thinkness",SwingConstants.CENTER);
		JPanel boldSlot = new JPanel(new GridLayout(1,2));
		JButton b1 = new JButton ("b1");
		JButton b2 = new JButton ("b2");
		boldSlot.add(b1);boldSlot.add(b2);
		JLabel shapeTag = new JLabel("Figure",SwingConstants.CENTER);
		JPanel shapeSlot1 = new JPanel(new GridLayout(1,2));
		JPanel shapeSlot2 = new JPanel(new GridLayout(1,2));
		JPanel shapeSlot3 = new JPanel(new GridLayout(1,2));
		JButton f1 = new JButton ("f1");
		JButton f2 = new JButton ("f2");
		JButton f3 = new JButton ("f3");
		JButton f4 = new JButton ("f4");
		JButton f5 = new JButton ("f5");
		JButton f6 = new JButton ("f6");
		shapeSlot1.add(f1);shapeSlot1.add(f2);
		shapeSlot2.add(f3);shapeSlot2.add(f4);
		shapeSlot3.add(f5);shapeSlot3.add(f6);
		JLabel colorTag = new JLabel("Color",SwingConstants.CENTER);
		JPanel colorSlot = new JPanel(new GridLayout(1,1));
		JButton c1 = new JButton("Color");
		colorSlot.add(c1);
		
		tempPanel.add(selectionTag);
		tempPanel.add(selectionSlot);
		tempPanel.add(penTag);
		tempPanel.add(penSlot);
		tempPanel.add(boldTag);
		tempPanel.add(boldSlot);
		tempPanel.add(shapeTag);
		tempPanel.add(shapeSlot1);
		tempPanel.add(shapeSlot2);
		tempPanel.add(shapeSlot3);
		tempPanel.add(colorTag);
		tempPanel.add(colorSlot);
		
		tempPanel.setLayout(toolLayout);
		
		toolPanel = tempPanel;
	}
	// 툴바 생
	public JPanel getToolBar() {
		return toolPanel;
	}
}
