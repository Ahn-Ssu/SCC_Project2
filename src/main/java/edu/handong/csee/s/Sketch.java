package edu.handong.csee.s;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;



public class Sketch extends JComponent implements MouseMotionListener, MouseListener {
	// 분할한 클래스에서 현재 그리고 있는 캔버스를 같이 이용하기 위함
	// Singletone set up
	static Sketch instance;
	
	private Color nowColor;
	private Color nowInnerColor;
	private int nowMode;
	private int nowModeType;
	private int nowThickness;
	private boolean fillUp;
	
	private int undoCount;
	private int redoCount;
	
	private boolean doNewDraw;
	private boolean doClear;
	private boolean doStack;
	private AlphaComposite alphaComposite;
	
	private Point startPoint;
	private Point endPoint;
	
	private static ToolBarMaker tool = new ToolBarMaker();
	private static MenuBarMaker bar = new MenuBarMaker();
	
	
	private ArrayList<PaintedObject> workedShape = new ArrayList<PaintedObject>(); 
	private ArrayList<PaintedObject> undoRedoBuffer = new ArrayList<PaintedObject>();
	private ArrayList<Point> pointStack = new ArrayList<Point>();

	
	private Sketch() {
		addMouseMotionListener(this);
		addMouseListener(this);

	}
	
	public static Sketch getInstance() {
		if(instance ==null)
			instance = new Sketch();
		return instance;
	}


	Shape hi = new Rectangle2D.Double(1, 2, 3, 4); 
	
	public void doUndo() {
		undoCount = tool.getUndo();
		System.out.println("undo : " + tool.getUndo() + " "+ undoCount);
		System.out.println("work size() : "+workedShape.size());
		
		
		//LIFO, 스택처럼 제일 나중에 들어온 친구가 지워
		if(undoCount>0 && workedShape.size() >0) {
			//undoRedoBuffer에 redo할 경우를 대비, 저장 
			undoRedoBuffer.add(workedShape.get(workedShape.size() - 1)); 
			workedShape.remove(workedShape.size() - 1);
		}
		
		repaint();
	}
	
	public void doRedo() {
		redoCount = tool.getRedo();
		System.out.println("redo : " + tool.getRedo() + " "+ redoCount);
		System.out.println("buffer size() : "+undoRedoBuffer.size());
		
		//LIFO, 스택처럼 동작 : 제일 최근에 그린 그림이  
		if(redoCount>0 && undoRedoBuffer.size()>0) {
			// 다시 작업 저장공간에 리턴 
			workedShape.add(undoRedoBuffer.get(undoRedoBuffer.size()-1));
			undoRedoBuffer.remove(undoRedoBuffer.size()-1);
		}
		repaint();
	}
	
	public void newCanvas() {
		workedShape.clear();
		undoRedoBuffer.clear();
		pointStack.clear();
		tool.resetAll();
		repaint();
	}
	
	public void allClear() {
//	g2.clearRect( , , , ,) 
		doClear = true;
		repaint();
		System.out.println(doClear);
	}


	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		// 현재 사용자가 지정한 "지금" 그릴 정보들을 임시 변수에다가 저장 
		 Color tempColor = nowColor;
		 Color tempInnerColor = nowInnerColor;
		 int tempThickness = nowThickness;
		 
		 int tempMode = nowMode;
		 int tempModeType = nowModeType;
		 boolean tempDoFill = fillUp;
		 Point tempStartPoint = startPoint;
		 Point tempEndPoint = endPoint;
		 ArrayList<Point> tempPointStack = pointStack;
			
		 
		pastSketch(g2);
		
		
		//화면 클리어, 하얀화면(배경화면)을 기준으로 잡고 버튼이 눌렸을때 프레임위에 아주 큰 하얀 상자를 위에 올려 씌움
		// ctrl + A 선택해서 전부 날려도 ^+Z 로 다시 다 생성 가능한 것을 반영, undo / redo rksmd
		if(doClear) {
			
			
			nowColor = Color.WHITE;;
			nowInnerColor = Color.WHITE;
			nowMode = 4;
			nowModeType = 4;
			nowThickness = 4;
			fillUp = true;
			startPoint = new Point(0,0);
			endPoint = new Point(20000,20000);
			g2.setPaint(nowColor);
			g2.setStroke(new BasicStroke(nowThickness));
			sketchUp(g2);
			System.out.println("Paint doClear");
			workedShape.add(new PaintedObject(pointStack ,nowColor,nowInnerColor,nowMode,nowModeType,nowThickness,fillUp,startPoint,endPoint));
			
			doClear = false;
		}
		
		
		//다 그렸으면 임시값을 현재 필드에 다시 저장 
		nowColor = tempColor;
		nowInnerColor = tempInnerColor;
		nowMode = tempMode;
		nowModeType = tempModeType;
		nowThickness = tempThickness;
		fillUp = tempDoFill;
		startPoint = tempStartPoint;
		endPoint = tempEndPoint;
		pointStack = new ArrayList<>(tempPointStack);
		
		 if(doNewDraw) {
			g2.setPaint(nowColor);
			g2.setStroke(new BasicStroke(nowThickness));
			g2.setComposite(alphaComposite);
			undoRedoBuffer.clear();
			sketchUp(g2);
		}
		
	}
	
	public void pastSketch(Graphics2D g2) {
		 //반복문을 통해 이전 작업 결과 다시 그리기 
		for(int queueNumber = 0 ; queueNumber <workedShape.size() ; queueNumber++) {
			//정보를 현재 필드에다가 저장해놓고 메소드 호출
			nowColor = workedShape.get(queueNumber).theColor;
			nowInnerColor = workedShape.get(queueNumber).theInnerColor;
			nowThickness = workedShape.get(queueNumber).theThickness;
			nowMode = workedShape.get(queueNumber).theMode;
			nowModeType = workedShape.get(queueNumber).theModeType;
			fillUp = workedShape.get(queueNumber).doFill;
			startPoint = workedShape.get(queueNumber).theStartPoint;
			endPoint = workedShape.get(queueNumber).theEndPoint;
			//참조타입이여서 얘는 딥카
			pointStack = new ArrayList<>(workedShape.get(queueNumber).thePointStack);
			System.out.println(pointStack.hashCode());
			for(Point p : pointStack) {
				System.out.println(p);
			}
			//System.out.println(poly.hashCode());
			//g2에 그렸었던 색정보와 선 굵기 정도를 가져옴 
			g2.setPaint(nowColor);
			g2.setStroke(new BasicStroke(nowThickness));
			
			
			// 셋업된 정보로 그리기
			sketchUp(g2);
		}
	}
	

	@Override
	public void mouseDragged(MouseEvent e) {
		// endPoint 최신화 
		endPoint = e.getPoint();
		// 그림이 그려지는 흔적을 마우스 드래그할떄 따라오게끔 
		doNewDraw = true;
		doStack = true;
		alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3F);
		
		// 자유곡선 모드에 사용할 점 좌표 저
		if(nowMode == 2 && nowModeType == 1 ) {
				pointStack.add(new Point(e.getX(), e.getY()));
				repaint();
		}
		else if(nowMode == 4) { // 도형 입력일때 
			repaint();				
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		pointStack.clear();
		// 그림그릴 때 사용할 변수들 세
		nowMode = tool.getMode();
		nowModeType = tool.getModeType();
		nowColor = tool.getNowColor();
		nowInnerColor = tool.getNowInnerColor();
		startPoint = e.getPoint();
		endPoint = e.getPoint();
		nowThickness = tool.getThickness();
		fillUp = tool.isFillOrEmpty();
		
		
	
		System.out.println("mode "+nowMode + ", mode type " + nowModeType);
		System.out.println("color " +nowColor+", inner color " + nowColor);
		System.out.println("Start - x :" + startPoint.x +", y :" + startPoint.y);
		System.out.println("Thickness "+nowThickness);
		System.out.println("Fill up? :"+ fillUp);
		System.out.println("stack size :"+pointStack.size());
		System.out.println("worked size :"+workedShape.size());
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		//endPoint 종결,투명도 0
		endPoint = e.getPoint();
		alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F);
		
		
		if(nowMode == 2 && nowModeType == 1) {
			pointStack.add(new Point(e.getX(), e.getY()));
			repaint();
		}
		else if(nowMode == 4) { // 도형 입력일때 
			repaint();			
		}

		
		workedShape.add(new PaintedObject(pointStack,nowColor,nowInnerColor,nowMode,nowModeType,nowThickness,fillUp, startPoint,endPoint));
		doNewDraw = false;

		System.out.println("stack size :"+pointStack.size());	
		
	}
	@Override
	public void mouseClicked(MouseEvent e) { 	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {	}
	@Override
	public void mouseMoved(MouseEvent e) {	}
	
	public void sketchUp(Graphics2D g2) {
		
		if(nowMode == 2 && nowModeType == 1) { // 펜 자유곡선 
			int[] stackedX = new int[pointStack.size()];
			int[] stackedY = new int[pointStack.size()];
			for(int i=0;i<pointStack.size(); i++){
				stackedX[i] = pointStack.get(i).x;
				stackedY[i] = pointStack.get(i).y;
			}
			g2.drawPolyline(stackedX, stackedY, pointStack.size());
			
		}
		else {
			if(nowMode == 4) { // 도형 입력일때 
				if(nowModeType == 1) { // 직선 
					
					g2.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
				}
				else if(nowModeType == 2) { // 원 
					if(fillUp) {
						g2.setColor(nowInnerColor);
						g2.fillOval(
								Math.min(endPoint.x,startPoint.x),
								Math.min(endPoint.y,startPoint.y), 
								Math.abs(endPoint.x-startPoint.x),
								Math.abs(endPoint.y-startPoint.y));
						if(nowColor!=nowInnerColor) {
							g2.setColor(nowColor);
							g2.drawOval(
									Math.min(endPoint.x,startPoint.x),
									Math.min(endPoint.y,startPoint.y), 
									Math.abs(endPoint.x-startPoint.x),
									Math.abs(endPoint.y-startPoint.y));
						}
						
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
						g2.setColor(nowInnerColor);
						g2.fillPolygon(
								new int[] {startPoint.x, (startPoint.x+endPoint.x)/2,endPoint.x},
								new int[] {endPoint.y, startPoint.y, endPoint.y},
								3);
						if(nowColor!=nowInnerColor) {
							g2.setColor(nowColor);
							g2.drawPolygon(
									new int[] {startPoint.x, (startPoint.x+endPoint.x)/2,endPoint.x},
									new int[] {endPoint.y, startPoint.y, endPoint.y},
									3);
							}
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
						g2.setColor(nowInnerColor);
						g2.fillRect(
								Math.min(endPoint.x,startPoint.x),
								Math.min(endPoint.y,startPoint.y), 
								Math.abs(endPoint.x-startPoint.x),
								Math.abs(endPoint.y-startPoint.y));
						if(nowColor!=nowInnerColor) {
							g2.setColor(nowColor);
							g2.drawRect(
									Math.min(endPoint.x,startPoint.x),
									Math.min(endPoint.y,startPoint.y), 
									Math.abs(endPoint.x-startPoint.x),
									Math.abs(endPoint.y-startPoint.y));
							}
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
						g2.setColor(nowInnerColor);
						g2.fillPolygon(
								new int[] {startPoint.x,(startPoint.x+endPoint.x)/2, endPoint.x,(startPoint.x+endPoint.x)/2},
								new int[] {(startPoint.y+endPoint.y)/2, startPoint.y,  (startPoint.y+endPoint.y)/2, endPoint.y},
										4);
						if(nowColor!=nowInnerColor) {
							g2.setColor(nowColor);
							g2.drawPolygon(
									new int[] {startPoint.x,(startPoint.x+endPoint.x)/2, endPoint.x,(startPoint.x+endPoint.x)/2},
									new int[] {(startPoint.y+endPoint.y)/2, startPoint.y,  (startPoint.y+endPoint.y)/2, endPoint.y},
											4);
							}
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
						g2.setColor(nowInnerColor);
						g2.fillPolygon(
								new int[] {(int)(startPoint.x+(endPoint.x-startPoint.x)*0.2),endPoint.x, (int)(startPoint.x +(endPoint.x-startPoint.x)*0.8),startPoint.x},
								new int[] {startPoint.y, startPoint.y, endPoint.y,  endPoint.y},
										4);
						if(nowColor!=nowInnerColor) {
							g2.setColor(nowColor);
							g2.drawPolygon(
									new int[] {(int)(startPoint.x+(endPoint.x-startPoint.x)*0.2),endPoint.x, (int)(startPoint.x +(endPoint.x-startPoint.x)*0.8),startPoint.x},
									new int[] {startPoint.y, startPoint.y, endPoint.y,  endPoint.y},
											4);
							}
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
						g2.setColor(nowInnerColor);
						g2.fillPolygon(
								new int[] {(startPoint.x+endPoint.x)/2, startPoint.x, (int)(startPoint.x+(endPoint.x - startPoint.x)*0.19),(int)(startPoint.x+(endPoint.x - startPoint.x)*0.81), endPoint.x },
								new int[] {startPoint.y,(int)(startPoint.y+(endPoint.y - startPoint.y)*0.38), endPoint.y,endPoint.y,(int)(startPoint.y+(endPoint.y - startPoint.y)*0.38)}
										,5);
						if(nowColor!=nowInnerColor) {
							g2.setColor(nowColor);
							g2.drawPolygon(
									new int[] {(startPoint.x+endPoint.x)/2, startPoint.x, (int)(startPoint.x+(endPoint.x - startPoint.x)*0.19),(int)(startPoint.x+(endPoint.x - startPoint.x)*0.81), endPoint.x },
									new int[] {startPoint.y,(int)(startPoint.y+(endPoint.y - startPoint.y)*0.38), endPoint.y,endPoint.y,(int)(startPoint.y+(endPoint.y - startPoint.y)*0.38)}
											,5);
							}
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
						g2.setColor(nowInnerColor);
						g2.fillPolygon(
								new int[] {startPoint.x, (startPoint.x+endPoint.x)/2,endPoint.x},
								new int[] {(int)(startPoint.y + (endPoint.y-startPoint.y)*0.75), startPoint.y, (int)(startPoint.y + (endPoint.y-startPoint.y)*0.75)},
								3);
						g2.fillPolygon(
								new int[] {startPoint.x, (startPoint.x+endPoint.x)/2,endPoint.x},
								new int[] {(int)(endPoint.y - (endPoint.y-startPoint.y)*0.75), endPoint.y, (int)(endPoint.y - (endPoint.y-startPoint.y)*0.75)},
								3);
						if(nowColor!=nowInnerColor) {
							g2.setColor(nowColor);
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
			
	}
	
	
	public static void main(String[] args) {
		JFrame f = new JFrame("Studio A");
		Container c = f.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(tool.getToolBar(), BorderLayout.WEST);
		c.add(Sketch.getInstance(), BorderLayout.CENTER);
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
