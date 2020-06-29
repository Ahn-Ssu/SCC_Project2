package edu.handong.csee.s;

import java.io.*;
import java.util.ArrayList;


public class FileIOHelper {

	private ArrayList<PaintedObject> saveTarget;
	private ArrayList<PaintedObject> loadTarget;
	
	public void save(ArrayList<PaintedObject> getTartget) throws FileNotFoundException {
		saveTarget = getTartget;
	    PrintWriter pw = new PrintWriter(new FileOutputStream("save"));
	    for (PaintedObject PaintedInfo : saveTarget)
	         pw.println(PaintedInfo); // call toString() on club, like club.toString()
	    pw.close();
	}
	
	public void load() throws FileNotFoundException {
		
	    PrintWriter pw = new PrintWriter(new FileOutputStream("save"));
	    for (PaintedObject PaintedInfo : saveTarget)
	         pw.println(PaintedInfo); // call toString() on club, like club.toString()
	    pw.close();
	}
	
	
	
}
