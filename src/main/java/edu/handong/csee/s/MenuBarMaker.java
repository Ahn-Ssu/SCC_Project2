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
	
	private JMenu EditMenu;
	private JMenuItem removeAll;
	
	private JMenu backColorMenu;
	private JMenuItem changeColor;
	
	private JMenu helpMenu;
	private JMenuItem help;
	
	private ColorSelector barColorEditor = new ColorSelector();
	private Color barColor;
	
	
	public MenuBarMaker() {
		paintMenu = new JMenuBar();
		
		createNew = new JMenuItem("새로 만들기 / Create new Canvers");
		fileMenu = new JMenu("파일 / File ");
		
		removeAll = new JMenuItem("모두 지우기 / Clear All");
		EditMenu = new JMenu("편집 / Edit");		

		changeColor = new JMenuItem("배경색 설정 / set Background Color");
		backColorMenu = new JMenu("배경색 / Background Color");
		
		help = new JMenuItem("도움말 / Help Contects");
		helpMenu = new JMenu("그 외 / Help");
		
		fileMenu.add(createNew);
		EditMenu.add(removeAll);
		backColorMenu.add(changeColor);
		helpMenu.add(help);

		paintMenu.add(fileMenu);
		paintMenu.add(EditMenu);
//		paintMenu.add(backColorMenu);
		paintMenu.add(helpMenu);
		
		
		createNew.addActionListener(this);
		removeAll.addActionListener(this);
		changeColor.addActionListener(this);
		help.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println(e.getActionCommand());
		
		if(e.getActionCommand() == "새로 만들기 / Create new Canvers") {
			System.out.println("new");
			Sketch.getInstance().newCanvas();
		}
		else if(e.getActionCommand() == "모두 지우기 / Clear All") {
			System.out.println("clear");
			Sketch.getInstance().allClear();
		}
//		else if(e.getActionCommand() == "배경색 설정 / set Background Color") {
//			System.out.println("back color");
//			barColorEditor.ColorSelect(barColorEditor.getColor());
//			barColor = barColorEditor.getColor();
//			Sketch.getInstance().changeCanvaseColor();
//		}
		else if(e.getActionCommand() == "도움말 / Help Contects") {
			System.out.println("Help");
		}
		else {}
	}

	public JMenuBar getMenuBar() {
		return this.paintMenu;
	}

	public Color getBarColor() {
		return barColor;
	}

	

}
