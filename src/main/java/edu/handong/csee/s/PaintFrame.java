package edu.handong.csee.s;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JFrame;




public class PaintFrame extends Frame{
 
	private Color nowColor = Color.BLACK; 
	private ToolBarMaker tool = new ToolBarMaker();
	
	public PaintFrame(String title) {
		// 윈도우 창, frame 세팅 
		JFrame frame = new JFrame(title);
		frame.setLocation(200,100);
		frame.setPreferredSize(new Dimension(900, 700));
		
		
		//툴바 만들기 
		Container contentPane = frame.getContentPane();
		contentPane.add(tool.toolBar(), BorderLayout.WEST);
		
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}