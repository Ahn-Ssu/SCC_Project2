package edu.handong.csee.s;


import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBarMaker {
	
	private JMenuBar paintMenu;
	private JMenu fileMenu;
	private JMenuItem createNew;
	private JMenu backColorMenu;
	private JMenuItem changeColor;
	
	
	public MenuBarMaker() {
		
		this.createNew = new JMenuItem("새로 만들기 / Create new Canvers");
		this.fileMenu = new JMenu("파일 / File ");
		this.paintMenu = new JMenuBar();
		backColorMenu = new JMenu("배경색 / Background Color");
		changeColor = new JMenuItem("배경색 설정 / set Background Color");
		
		fileMenu.add(createNew);
		backColorMenu.add(changeColor);

		paintMenu.add(fileMenu);
		paintMenu.add(backColorMenu);
	}

	public JMenuBar getMenuBar() {
		return this.paintMenu;
	}
}
