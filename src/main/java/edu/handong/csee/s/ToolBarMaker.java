package edu.handong.csee.s;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ToolBarMaker implements ActionListener {
	private JPanel toolPanel ;
	private JButton s1, s2, p1, p2, b1, b2, f1, f2, f3, f4, f5, f6, f7, f8, c1;
	
	private ColorSelector ToolColorEditor = new ColorSelector();
	private Color nowColor=Color.DARK_GRAY;
	//default setting
	private int thickness = 2;
	private int mode = 2;
	private int modeType = 1;
	
	public ToolBarMaker() {
		JPanel tempPanel = new JPanel();
		tempPanel.setBackground(Color.GRAY);
		GridLayout toolLayout = new GridLayout(13,1);
		
		
		JLabel selectionTag = new JLabel("Mouse Mode",SwingConstants.CENTER);
		JPanel selectionSlot = new JPanel(new GridLayout(1,2));
		selectionSlot.setBackground(Color.GRAY);
		s1 = new JButton ("Click");
		s2 = new JButton ("Select");
		s2.setBackground(Color.LIGHT_GRAY);
		s1.setToolTipText("Click mode, can click object");
		s2.setToolTipText("Select mode, can select object");
		selectionSlot.add(s1);selectionSlot.add(s2);
		JLabel penTag = new JLabel("Pen / Eraser",SwingConstants.CENTER);
		JPanel penSlot = new JPanel(new GridLayout(1,2));
		penSlot.setBackground(Color.GRAY);
		p1 = new JButton ("✎");
		p2 = new JButton ("🀆");
		p1.setToolTipText("Pen tool, can draw by the mouse");
		p2.setToolTipText("Eraser tool,can remove by the mouse");
		penSlot.add(p1);penSlot.add(p2);
		JLabel boldTag = new JLabel("Thinkness",SwingConstants.CENTER);
		JPanel boldSlot = new JPanel(new GridLayout(1,2));
		boldSlot.setBackground(Color.GRAY);
		b1 = new JButton ("𝐁+");
		b2 = new JButton ("𝑙-");
		b1.setToolTipText("Be Bold, can make more Bold line or figure");
		b2.setToolTipText("Be thin, can make more thin line or figure");
		boldSlot.add(b1);boldSlot.add(b2);
		JLabel shapeTag = new JLabel("Figure",SwingConstants.CENTER);
		JPanel shapeSlot1 = new JPanel(new GridLayout(1,2));
		JPanel shapeSlot2 = new JPanel(new GridLayout(1,2));
		JPanel shapeSlot3 = new JPanel(new GridLayout(1,2));
		JPanel shapeSlot4 = new JPanel(new GridLayout(1,2));
		shapeSlot1.setBackground(Color.GRAY);
		shapeSlot2.setBackground(Color.GRAY);
		shapeSlot3.setBackground(Color.GRAY);
		shapeSlot4.setBackground(Color.GRAY);
		f1 = new JButton ("⧸");
		f2 = new JButton ("◯");
		f3 = new JButton ("△");
		f4 = new JButton ("▢");
		f5 = new JButton ("◇");
		f6 = new JButton ("▱"); //평행사변형 
		f7 = new JButton ("⬠");
		f8 = new JButton ("✡");//("✩");
		f1.setToolTipText("Drawing straight line by mouse");
		f2.setToolTipText("Drawing circle by mouse");
		f3.setToolTipText("Drawing triangle by mouse");
		f4.setToolTipText("Drawing Rectangle by mouse");
		f5.setToolTipText("Drawing Rhombus by mouse");
		f6.setToolTipText("Drawing parallelogram by mouse");
		f7.setToolTipText("Drawing pentagon by mouse");
		f8.setToolTipText("Drawing star by mouse");
		shapeSlot1.add(f1);shapeSlot1.add(f2);
		shapeSlot2.add(f3);shapeSlot2.add(f4);
		shapeSlot3.add(f5);shapeSlot3.add(f6);
		shapeSlot4.add(f7);shapeSlot4.add(f8);
		JLabel colorTag = new JLabel("Color",SwingConstants.CENTER);
		JPanel colorSlot = new JPanel(new GridLayout(1,1));
		colorSlot.setBackground(Color.GRAY);
		c1 = new JButton("Color ▆");
		c1.setForeground(Color.DARK_GRAY);
		c1.setToolTipText("Changeing the color current color, defualt BLACK");
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
		tempPanel.add(shapeSlot4);
		tempPanel.add(colorTag);
		tempPanel.add(colorSlot);
		
		tempPanel.setLayout(toolLayout);
	
		toolPanel = tempPanel;
		
		//버튼 이벤트 
		s1.addActionListener(this);
		s2.addActionListener(this);
		p1.addActionListener(this);
		p2.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		f1.addActionListener(this);
		f2.addActionListener(this);
		f3.addActionListener(this);
		f4.addActionListener(this);
		f5.addActionListener(this);
		f6.addActionListener(this);
		f7.addActionListener(this);
		f8.addActionListener(this);
		c1.addActionListener(this);
		
	}
	// 툴바 생
	public JPanel getToolBar() {
		return toolPanel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Color ▆") {
			System.out.println("Color ▆");
			ToolColorEditor.ColorSelect(ToolColorEditor.getColor());
			nowColor = ToolColorEditor.getColor();
			c1.setForeground(nowColor);
		}
		else if(e.getActionCommand() == "Click") {
			System.out.println("Click");
			mode = 1;modeType =1;
		}
		else if(e.getActionCommand() == "Select") {
			System.out.println("Select");
			mode = 1;modeType =2;
		}
		else if(e.getActionCommand() == "✎") {
			System.out.println("✎");
			mode = 2;modeType =1;
		}
		else if(e.getActionCommand() == "🀆") {
			System.out.println("🀆");
			mode = 2;modeType =2;
		}
		else if(e.getActionCommand() == "𝐁+") {
			System.out.println("𝐁+");
			if(thickness<8)
				thickness++;
		}
		else if(e.getActionCommand() == "𝑙-") {
			System.out.println("𝑙-");
			if(thickness>1)
				thickness--;
		}
		else if(e.getActionCommand() == "⧸") {
			System.out.println("⧸");
			mode = 4;modeType =1;
		}
		else if(e.getActionCommand() == "◯") {
			System.out.println("◯");
			mode = 4;modeType =2;
		}
		else if(e.getActionCommand() == "△") {
			System.out.println("△");
			mode = 4;modeType =3;
		}
		else if(e.getActionCommand() == "▢") {
			System.out.println("▢");
			mode = 4;modeType =4;
		}
		else if(e.getActionCommand() == "◇") {
			System.out.println("◇");
			mode = 4;modeType =5;
		}
		else if(e.getActionCommand() == "▱") {
			System.out.println("▱");
			mode = 4;modeType =6;
		}
		else if(e.getActionCommand() == "⬠") {
			System.out.println( "⬠");
			mode = 4;modeType =7;
		}
		else if(e.getActionCommand() == "✡") {
			System.out.println("✡");
			mode = 4;modeType =8;
		}
	}
	public int getMode() {
		return mode;
	}
	public int getModeType() {
		return modeType;
	}
	public Color getNowColor() {
		return nowColor;
	}
	public int getThickness() {
		return thickness;
	}
}
