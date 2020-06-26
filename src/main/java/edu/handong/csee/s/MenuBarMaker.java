package edu.handong.csee.s;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBarMaker implements ActionListener {
	
	private JMenuBar paintMenu;
	
	private JMenu fileMenu;
	private JMenuItem createNew;
	
//	private JMenu backColorMenu;
//	private JMenuItem changeColor;
	
	private JMenu helpMenu;
	private JMenuItem help;
	
//	private ColorSelector barColorEditor = new ColorSelector();
//	private Color barColor;
	
	
	public MenuBarMaker() {
		paintMenu = new JMenuBar();
		
		createNew = new JMenuItem("새로 만들기 / Create new Canvers");
		fileMenu = new JMenu("파일 / File ");
		

//		changeColor = new JMenuItem("배경색 설정 / set Background Color");
//		backColorMenu = new JMenu("배경색 / Background Color");
		
		help = new JMenuItem("도움말 / Help Contects");
		helpMenu = new JMenu("그 외 / Help");
		
		fileMenu.add(createNew);
//		backColorMenu.add(changeColor);
		helpMenu.add(help);

		paintMenu.add(fileMenu);
//		paintMenu.add(backColorMenu);
		paintMenu.add(helpMenu);
		
		
		createNew.addActionListener(this);
//		changeColor.addActionListener(this);
		help.addActionListener(this);
	}

	public JMenuBar getMenuBar() {
		return this.paintMenu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println(e.getActionCommand());
		
		if(e.getActionCommand() == "새로 만들기 / Create new Canvers") {
			System.out.println("new");
		}/*
		else if(e.getActionCommand() == "배경색 설정 / set Background Color") {
			System.out.println("back color");
			barColorEditor.ColorSelect(barColorEditor.getColor());
			barColor = barColorEditor.getColor();
			pf.setCanvers(barColor);
		}*/
		else if(e.getActionCommand() == "도움말 / Help Contects") {
			System.out.println("Help");
		}
		else {}
	}


}
