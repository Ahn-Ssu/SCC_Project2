package edu.handong.csee.s;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

public class PaintedObject {

	public ArrayList<Point> thePointStack;
	public Color theColor;
	public Color theInnerColor;
	public int theMode;
	public int theModeType;
	public int theThickness;
	public boolean doFill;
	public Point theStartPoint;
	public Point theEndPoint;
	
	
	public PaintedObject(ArrayList<Point> pointStack, Color theColor,Color theInnerColor, int theMode, int theModeType, int theThickness, boolean doFill, Point theStartPoint, Point theEndPoint ) {
		this.thePointStack = pointStack;
		this.theColor = theColor;
		this.theInnerColor = theInnerColor;
		this.theMode = theMode;
		this.theModeType = theModeType;
		this.theThickness = theThickness;
		this.doFill = doFill;
		this.theStartPoint = theStartPoint;
		this.theEndPoint = theEndPoint;
	}
	
}
