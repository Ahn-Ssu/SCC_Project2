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
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;



public class Sketch extends JComponent implements MouseMotionListener, MouseListener {
	
	private Color nowColor;
	private int nowMode;
	private int nowModeType;
	private int nowThickness;
	private boolean fillUp;
	
	
	private Point startPoint;
	private Point endPoint;
	
	private static ToolBarMaker tool = new ToolBarMaker();
	private static MenuBarMaker bar = new MenuBarMaker();
	
	GeneralPath poly = new GeneralPath();
	
	private ArrayList<PaintedObject> workedShape = new ArrayList<PaintedObject>(); 
	
	public Sketch() {
		addMouseMotionListener(this);
		addMouseListener(this);
		poly.moveTo(300, 300);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
//		g2.setPaint(nowColor);
//		g2.setStroke(new BasicStroke(nowThickness));
		
//		System.out.println("Paint");
		
		// 현재 사용자가 지정한 "지금" 그릴 정보들을 임시 변수에다가 저장 
		 Color tempColor = nowColor;
		 int tempThickness = nowThickness;
		 
		 int tempMode = nowMode;
		 int tempModeType = nowModeType;
		 boolean tempDoFill = fillUp;
		 Point tempStartPoint = startPoint;
		 Point tempEndPoint = endPoint;
		
		 
		 //반복문을 통해 이전 작업 결과 다시 그리기 
		for(int queueNumber = 0 ; queueNumber <workedShape.size() ; queueNumber++) {
			//g2에 그렸었던 색정보와 선 굵기 정도를 가져옴 
			g2.setPaint(workedShape.get(queueNumber).theColor);
			g2.setStroke(new BasicStroke(workedShape.get(queueNumber).theThickness));
			//나머지 정보를 현재 필드에다가 저장해놓고 메소드 호출
			nowMode = workedShape.get(queueNumber).theMode;
			nowModeType = workedShape.get(queueNumber).theModeType;
			fillUp = workedShape.get(queueNumber).doFill;
			startPoint = workedShape.get(queueNumber).theStartPoint;
			endPoint = workedShape.get(queueNumber).theEndPoint;
		
			// 셋업된 정보로 그리기
			sketchUp(g2);
		}
		
		
		//다 그렸으면 임시값을 현재 필드에 다시 저장 
		nowColor = tempColor;
		nowMode = tempMode;
		nowModeType = tempModeType;
		nowThickness = tempThickness;
		fillUp = tempDoFill;
		startPoint = tempStartPoint;
		endPoint = tempEndPoint;
		
		g2.setPaint(nowColor);
		g2.setStroke(new BasicStroke(nowThickness));
		
		
		sketchUp(g2);		
	}
	
	public void sketchUp(Graphics2D g2) {
		if(nowMode == 2 && nowModeType == 1) {
			g2.draw(poly);
		}
		else if(nowMode == 4) { // 도형 입력일때 
			if(nowModeType == 1) { // 직선 
				g2.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
			}
			else if(nowModeType == 2) { // 원 
				if(fillUp) {
					g2.fillOval(
							Math.min(endPoint.x,startPoint.x),
							Math.min(endPoint.y,startPoint.y), 
							Math.abs(endPoint.x-startPoint.x),
							Math.abs(endPoint.y-startPoint.y));
				}
				else {
					g2.drawOval(
							Math.min(endPoint.x,startPoint.x),
							Math.min(endPoint.y,startPoint.y), 
							Math.abs(endPoint.x-startPoint.x),
							Math.abs(endPoint.y-startPoint.y));
				}
				
			}
			else if(nowModeType == 3) { // 세모 
				if(fillUp) {
					g2.fillPolygon(
							new int[] {startPoint.x, (startPoint.x+endPoint.x)/2,endPoint.x},
							new int[] {endPoint.y, startPoint.y, endPoint.y},
							3);
				}
				else {
					g2.drawPolygon(
							new int[] {startPoint.x, (startPoint.x+endPoint.x)/2,endPoint.x},
							new int[] {endPoint.y, startPoint.y, endPoint.y},
							3);
				}
				
			}
			else if(nowModeType == 4) { // 네모 
				if(fillUp) {
					g2.fillRect(
							Math.min(endPoint.x,startPoint.x),
							Math.min(endPoint.y,startPoint.y), 
							Math.abs(endPoint.x-startPoint.x),
							Math.abs(endPoint.y-startPoint.y));
				}
				else {
					g2.drawRect(
							Math.min(endPoint.x,startPoint.x),
							Math.min(endPoint.y,startPoint.y), 
							Math.abs(endPoint.x-startPoint.x),
							Math.abs(endPoint.y-startPoint.y));
				}
				
			}
			else if(nowModeType == 5) { //  마름모  
				if(fillUp) {
					g2.fillPolygon(
							new int[] {startPoint.x,(startPoint.x+endPoint.x)/2, endPoint.x,(startPoint.x+endPoint.x)/2},
							new int[] {(startPoint.y+endPoint.y)/2, startPoint.y,  (startPoint.y+endPoint.y)/2, endPoint.y},
									4);
				}
				else {
					g2.drawPolygon(
							new int[] {startPoint.x,(startPoint.x+endPoint.x)/2, endPoint.x,(startPoint.x+endPoint.x)/2},
							new int[] {(startPoint.y+endPoint.y)/2, startPoint.y,  (startPoint.y+endPoint.y)/2, endPoint.y},
									4);
				}
				
			}
			else if(nowModeType == 6) { // 평행사변
				if(fillUp) {
					g2.fillPolygon(
							new int[] {(int)(startPoint.x+(endPoint.x-startPoint.x)*0.2),endPoint.x, (int)(startPoint.x +(endPoint.x-startPoint.x)*0.8),startPoint.x},
							new int[] {startPoint.y, startPoint.y, endPoint.y,  endPoint.y},
									4);
				}
				else {
					g2.drawPolygon(
							new int[] {(int)(startPoint.x+(endPoint.x-startPoint.x)*0.2),endPoint.x, (int)(startPoint.x +(endPoint.x-startPoint.x)*0.8),startPoint.x},
							new int[] {startPoint.y, startPoint.y, endPoint.y,  endPoint.y},
									4);
				}
				
			}
			else if(nowModeType == 7){ //  오각
				if(fillUp) {
					g2.fillPolygon(
							new int[] {(startPoint.x+endPoint.x)/2, startPoint.x, (int)(startPoint.x+(endPoint.x - startPoint.x)*0.19),(int)(startPoint.x+(endPoint.x - startPoint.x)*0.81), endPoint.x },
							new int[] {startPoint.y,(int)(startPoint.y+(endPoint.y - startPoint.y)*0.38), endPoint.y,endPoint.y,(int)(startPoint.y+(endPoint.y - startPoint.y)*0.38)}
									,5);
				}
				else {
					g2.drawPolygon(
							new int[] {(startPoint.x+endPoint.x)/2, startPoint.x, (int)(startPoint.x+(endPoint.x - startPoint.x)*0.19),(int)(startPoint.x+(endPoint.x - startPoint.x)*0.81), endPoint.x },
							new int[] {startPoint.y,(int)(startPoint.y+(endPoint.y - startPoint.y)*0.38), endPoint.y,endPoint.y,(int)(startPoint.y+(endPoint.y - startPoint.y)*0.38)}
									,5);
				}
				
			}
			else if(nowModeType == 8) { // 삼각교차별 
				if(fillUp) {
					g2.fillPolygon(
							new int[] {startPoint.x, (startPoint.x+endPoint.x)/2,endPoint.x},
							new int[] {(int)(startPoint.y + (endPoint.y-startPoint.y)*0.75), startPoint.y, (int)(startPoint.y + (endPoint.y-startPoint.y)*0.75)},
							3);
					g2.fillPolygon(
							new int[] {startPoint.x, (startPoint.x+endPoint.x)/2,endPoint.x},
							new int[] {(int)(endPoint.y - (endPoint.y-startPoint.y)*0.75), endPoint.y, (int)(endPoint.y - (endPoint.y-startPoint.y)*0.75)},
							3);
				}
				else {
					g2.drawPolygon(
							new int[] {startPoint.x, (startPoint.x+endPoint.x)/2,endPoint.x},
							new int[] {(int)(startPoint.y + (endPoint.y-startPoint.y)*0.75), startPoint.y, (int)(startPoint.y + (endPoint.y-startPoint.y)*0.75)},
							3);
					g2.drawPolygon(
							new int[] {startPoint.x, (startPoint.x+endPoint.x)/2,endPoint.x},
							new int[] {(int)(endPoint.y - (endPoint.y-startPoint.y)*0.75), endPoint.y, (int)(endPoint.y - (endPoint.y-startPoint.y)*0.75)},
							3);
				}
				
			}

			} // end of draw if statement
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
		fillUp = tool.isFillOrEmpty();
		
		System.out.println("mode "+nowMode);
		System.out.println("mode type " + nowModeType);
		System.out.println("color " +nowColor);
		System.out.println("x :" + startPoint.x);
		System.out.println("y :" + startPoint.y);
		System.out.println("Thickness "+nowThickness);
		System.out.println("Fill up? :"+ fillUp);
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		endPoint = e.getPoint();
		workedShape.add(new PaintedObject(nowColor,nowMode,nowModeType,nowThickness,fillUp,startPoint,endPoint));
		
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
		f.setSize(1100, 700);
		f.setJMenuBar(bar.getMenuBar());
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		f.setVisible(true);
	}

}
