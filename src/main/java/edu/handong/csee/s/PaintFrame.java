package edu.handong.csee.s;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;




public class PaintFrame extends Frame{
 
	private Color nowColor = Color.BLACK; 
	private ToolBarMaker tool = new ToolBarMaker();
	private CanversMaker canvers = new CanversMaker();
	private MenuBarMaker bar = new MenuBarMaker();
	
	public PaintFrame(String title) {
		// 윈도우 창, frame 세팅 
		JFrame frame = new JFrame(title);
		frame.setLocation(200,100);
		frame.setPreferredSize(new Dimension(900, 700));
		
		
		JPanel colorSlot = new JPanel(new GridLayout(1,1));
		colorSlot.setBackground(Color.GRAY);
		JButton c1 = new JButton("Color ▆");
		c1.setForeground(Color.DARK_GRAY);
		colorSlot.add(c1);
		JPanel temp = tool.getToolBar();
		
		temp.add(colorSlot);		
		
		//인터페이스 구축 
		Container contentPane = frame.getContentPane();
		contentPane.add(temp, BorderLayout.WEST);
		contentPane.add(canvers.getCanvers());
		contentPane.add(bar.getMenuBar(), BorderLayout.NORTH);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}



