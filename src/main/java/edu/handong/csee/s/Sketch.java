package edu.handong.csee.s;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JFrame;



public class Sketch extends JComponent implements MouseMotionListener, MouseListener {
	
	private Color nowColor = Color.DARK_GRAY;
	private int nowMode;
	private int nowModeType;
	private int nowThickness;
	
	
	private Point startPoint;
	private Point endPoint;
	private static ToolBarMaker tool = new ToolBarMaker();
	private static MenuBarMaker bar = new MenuBarMaker();
	GeneralPath poly = new GeneralPath();
	
	public Sketch() {
		addMouseMotionListener(this);
		addMouseListener(this);
		poly.moveTo(300, 300);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(nowColor);
		g2.setStroke(new BasicStroke(nowThickness));
		
		System.out.println("Paint");
		
		if(nowMode == 2 && nowModeType == 1) {
			g2.draw(poly);
		}
		else if(nowMode == 4) { // 도형 입력일때 
			if(nowModeType == 1) { // 직선 
				g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
			}
			else if(nowModeType == 2) { // 원 
				g2.drawOval(
						Math.min(endPoint.x,startPoint.x),
						Math.min(endPoint.y,startPoint.y), 
						Math.abs(endPoint.x-startPoint.x),
						Math.abs(endPoint.y-startPoint.y));
			}
			else if(nowModeType == 3) { // 세모 
				g2.drawPolygon(
						new int[] {startPoint.x, (startPoint.x+endPoint.x)/2,endPoint.x},
						new int[] {endPoint.y, startPoint.y, endPoint.y},
						3);
			}
			else if(nowModeType == 4) { // 네모 
				g2.drawRect(
						Math.min(endPoint.x,startPoint.x),
						Math.min(endPoint.y,startPoint.y), 
						Math.abs(endPoint.x-startPoint.x),
						Math.abs(endPoint.y-startPoint.y));
			}
			else if(nowModeType == 5) { //  마름모  
				g2.drawPolygon(
						new int[] {startPoint.x,(startPoint.x+endPoint.x)/2, endPoint.x,(startPoint.x+endPoint.x)/2},
						new int[] {(startPoint.y+endPoint.y)/2, startPoint.y,  (startPoint.y+endPoint.y)/2, endPoint.y},
								4);
			}
			else if(nowModeType == 6) { // 평행사변
				g2.drawPolygon(
						new int[] {(int)(startPoint.x+(endPoint.x-startPoint.x)*0.2),endPoint.x, (int)(startPoint.x +(endPoint.x-startPoint.x)*0.8),startPoint.x},
						new int[] {startPoint.y, startPoint.y, endPoint.y,  endPoint.y},
								4);
			}
			else if(nowModeType == 7){ //  오각
				g2.drawPolygon(
				new int[] {(startPoint.x+endPoint.x)/2, startPoint.x, (int)(startPoint.x+(endPoint.x - startPoint.x)*0.19),(int)(startPoint.x+(endPoint.x - startPoint.x)*0.81), endPoint.x },
				new int[] {startPoint.y,(int)(startPoint.y+(endPoint.y - startPoint.y)*0.38), endPoint.y,endPoint.y,(int)(startPoint.y+(endPoint.y - startPoint.y)*0.38)}
						,5);
			}
			else if(nowModeType == 8) { // 삼각교차별 
				g2.drawPolygon(
						new int[] {startPoint.x, (startPoint.x+endPoint.x)/2,endPoint.x},
						new int[] {(int)(startPoint.y + (endPoint.y-startPoint.y)*0.75), startPoint.y, (int)(startPoint.y + (endPoint.y-startPoint.y)*0.75)},
						3);
				g2.drawPolygon(
						new int[] {startPoint.x, (startPoint.x+endPoint.x)/2,endPoint.x},
						new int[] {(int)(endPoint.y - (endPoint.y-startPoint.y)*0.75), endPoint.y, (int)(endPoint.y - (endPoint.y-startPoint.y)*0.75)},
						3);
			}
			
//			{ //  오각
//				g2.drawPolygon(
//						new int[] {(startPoint.x+endPoint.x)/2, startPoint.x, (int)(startPoint.x+(endPoint.x - startPoint.x)*0.19),(int)(startPoint.x+(endPoint.x - startPoint.x)*0.81), endPoint.x },
//						new int[] {startPoint.y,(int)(startPoint.y+(endPoint.y - startPoint.y)*0.38), endPoint.y,endPoint.y,(int)(startPoint.y+(endPoint.y - startPoint.y)*0.38)}
//								,5);
//			}
			}
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		endPoint = e.getPoint();
		
		if(nowMode == 2 && nowModeType == 1) {
			poly.lineTo(e.getX(), e.getY());
			repaint();
		}
		else if(nowMode == 4) { // 도형 입력일때 
			if(nowModeType == 1) { // 직선 
				poly.lineTo(e.getX(), e.getY());
				repaint();
			}
			else if(nowModeType == 2) { // 원 
				repaint();
			}
			else if(nowModeType == 3) { // 세모 
				repaint();
			}
			else if(nowModeType == 4) { // 네모 
				repaint();
			}
			else if(nowModeType == 5) { //  마름모  
				repaint();
			}
			else if(nowModeType == 6) { //  평행사변
				repaint();
			}
			else if(nowModeType == 7) { //  오각형   
				repaint();
			}
			else if(nowModeType == 8) { //  별 
				repaint();
			}
						
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		poly.moveTo(e.getX(), e.getY());
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		nowMode = tool.getMode();
		nowModeType = tool.getModeType();
		nowColor = tool.getNowColor();
		startPoint = e.getPoint();
		endPoint = e.getPoint();
		nowThickness = tool.getThickness();
		System.out.println("mode "+nowMode);
	System.out.println("mode type " + nowModeType);
	System.out.println("color " +nowColor);
	System.out.println("x :" + startPoint.x);
	System.out.println("y :" + startPoint.y);
	System.out.println("Thickness "+nowThickness);
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		endPoint = e.getPoint();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		JFrame f = new JFrame("Studio A");
		Container c = f.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(tool.getToolBar(), BorderLayout.WEST);
		c.add(new Sketch(), BorderLayout.CENTER);
		c.setBackground(Color.WHITE);
		f.setSize(600, 622);
		f.setJMenuBar(bar.getMenuBar());
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		f.setVisible(true);
	}

}
