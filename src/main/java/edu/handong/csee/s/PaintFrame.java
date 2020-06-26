//package edu.handong.csee.s;
//
//import java.awt.BorderLayout;
//import java.awt.BasicStroke;
//import java.awt.Color;
//import java.awt.Container;
//import java.awt.Dimension;
//import java.awt.Frame;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.GridLayout;
//import java.awt.Point;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.event.MouseMotionListener;
//import java.awt.geom.GeneralPath;
//import java.awt.image.BufferedImage;
//
//import javax.swing.JComponent;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//
//public class PaintFrame extends JComponent {
//	// 그림 그릴 때 필요한 색, 모드, 모드 내 타입, 굵기 정도, 시작점
//	private Color nowColor = Color.DARK_GRAY;
//	private int nowMode;
//	private int nowModeType;
//	private int nowThickness;
//	private Point startPoint;
//	private Point endPoint;
//	private BufferedImage image; 
//	private ToolBarMaker tool = new ToolBarMaker();
//	private MenuBarMaker bar = new MenuBarMaker();
//
//	public PaintFrame(String title) {
//		
//		
//		// 윈도우 창, frame 세팅
//		JFrame frame = new JFrame(title);
//		frame.setLocation(200, 100);
//		frame.setPreferredSize(new Dimension(900, 700));
//
//		// 인터페이스 구축
//		Container contentPane = frame.getContentPane();
//
//		contentPane.add(tool.getToolBar(), BorderLayout.WEST);
//		contentPane.add(bar.getMenuBar(), BorderLayout.NORTH);
//		contentPane.setBackground(Color.WHITE);
//		contentPane.addMouseListener(this);
//		contentPane.addMouseMotionListener(this);
//
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.pack();
//		frame.setVisible(true);
//	}
// private class mouseHandler implements MouseListener, MouseMotionListener {
//
//	@Override
//	public void mouseDragged(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseMoved(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mousePressed(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseEntered(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseExited(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//	 
// }
////	@Override
////	public void mouseClicked(MouseEvent e) {
////		System.out.println("Click");
////
////	}
////
////	@Override
////	public void mousePressed(MouseEvent e) {
////		// TODO Auto-generated method stub
////		System.out.println("Pressed");
////
////		nowMode = tool.getMode();
////		nowModeType = tool.getModeType();
////		nowColor = tool.getNowColor();
////		startPoint = e.getPoint();
////		nowThickness = tool.getThickness();
////		System.out.println("mode "+nowMode);
////		System.out.println("mode type " + nowModeType);
////		System.out.println("color " +nowColor);
////		System.out.println("x :" + startPoint.x);
////		System.out.println("y :" + startPoint.y);
////		System.out.println("Thickness "+nowThickness);
////		repaint();
////	}
////
////	@Override
////	public void mouseReleased(MouseEvent e) {
////		// TODO Auto-generated method stub
////		System.out.println("Released");
////		endPoint = e.getPoint();
////		repaint();
////	}
////
////	@Override
////	public void mouseDragged(MouseEvent e) {
////		// TODO Auto-generated method stub
////		System.out.println("drag");
////		repaint();
////	}
////
////	@Override
////	public void mouseEntered(MouseEvent e) {
////		// TODO Auto-generated method stub
////
////	}
////
////	@Override
////	public void mouseExited(MouseEvent e) {
////		// TODO Auto-generated method stub
////
////	}
////
////	@Override
////	public void mouseMoved(MouseEvent e) {
////		// TODO Auto-generated method stub
////
////	}
////
////	public void paintComponent(Graphics g) {
////		super.paintComponent(g);
////		Graphics2D g2 = (Graphics2D) g;
////		if( image == null) {
////			image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
////		}
////		g2.setPaint(nowColor);
////		g2.setStroke(new BasicStroke(nowThickness));
////		g2.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
////		System.out.println("g2");
////		g2.drawImage(image, 0, 0, null);
////
////	}
//}
