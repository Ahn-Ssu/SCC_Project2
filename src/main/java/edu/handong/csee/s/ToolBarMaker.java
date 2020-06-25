package edu.handong.csee.s;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class ToolBarMaker {
	private JPanel toolPanel ;
	
	public ToolBarMaker() {
		JPanel tempPanel = new JPanel();
		tempPanel.setBackground(Color.GRAY);
		GridLayout toolLayout = new GridLayout(12,1);
		
		
		JLabel selectionTag = new JLabel("Mouse Mode",SwingConstants.CENTER);
		JPanel selectionSlot = new JPanel(new GridLayout(1,2));
		selectionSlot.setBackground(Color.GRAY);
		JButton s1 = new JButton ("Click");
		JButton s2 = new JButton ("Select");
		s2.setBackground(Color.LIGHT_GRAY);
		selectionSlot.add(s1);selectionSlot.add(s2);
		JLabel penTag = new JLabel("Pen / Eraser",SwingConstants.CENTER);
		JPanel penSlot = new JPanel(new GridLayout(1,2));
		penSlot.setBackground(Color.GRAY);
		JButton p1 = new JButton ("✎");
		JButton p2 = new JButton ("🀆");
		penSlot.add(p1);penSlot.add(p2);
		JLabel boldTag = new JLabel("Thinkness",SwingConstants.CENTER);
		JPanel boldSlot = new JPanel(new GridLayout(1,2));
		boldSlot.setBackground(Color.GRAY);
		JButton b1 = new JButton ("𝐁+");
		JButton b2 = new JButton ("𝑙-");
		boldSlot.add(b1);boldSlot.add(b2);
		JLabel shapeTag = new JLabel("Figure",SwingConstants.CENTER);
		JPanel shapeSlot1 = new JPanel(new GridLayout(1,2));
		JPanel shapeSlot2 = new JPanel(new GridLayout(1,2));
		JPanel shapeSlot3 = new JPanel(new GridLayout(1,2));
		shapeSlot1.setBackground(Color.GRAY);
		shapeSlot2.setBackground(Color.GRAY);
		shapeSlot3.setBackground(Color.GRAY);
		JButton f1 = new JButton ("⧸");
		JButton f2 = new JButton ("◯");
		JButton f3 = new JButton ("△");
		JButton f4 = new JButton ("▢");
		JButton f5 = new JButton ("◇");
		JButton f6 = new JButton ("☆");
		shapeSlot1.add(f1);shapeSlot1.add(f2);
		shapeSlot2.add(f3);shapeSlot2.add(f4);
		shapeSlot3.add(f5);shapeSlot3.add(f6);
		JLabel colorTag = new JLabel("Color",SwingConstants.CENTER);
/*		JPanel colorSlot = new JPanel(new GridLayout(1,1));
		colorSlot.setBackground(Color.GRAY);
		JButton c1 = new JButton("Color");
		c1.setForeground(Color.CYAN);
		colorSlot.add(c1);
*/
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
//		tempPanel.add(colorSlot);
		
		tempPanel.setLayout(toolLayout);
	
		toolPanel = tempPanel;
	}
	// 툴바 생
	public JPanel getToolBar() {
		return toolPanel;
	}
}
