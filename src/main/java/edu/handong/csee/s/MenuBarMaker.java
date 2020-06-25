package edu.handong.csee.s;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBarMaker {
	
	private JMenuBar paintMenu;
	private JMenu fileMenu;
	private JMenuItem createNew;
	
	
	public MenuBarMaker() {
		
		this.createNew = new JMenuItem("새로 만들기 / Create new Canvers");
		this.fileMenu = new JMenu("파일 / File ");
		this.paintMenu = new JMenuBar();
		
		fileMenu.add(createNew);
		paintMenu.add(fileMenu);
	}

	public JMenuBar getMenuBar() {
		return this.paintMenu;
	}
}
