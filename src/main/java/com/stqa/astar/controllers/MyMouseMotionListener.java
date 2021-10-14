package com.stqa.astar.controllers;

import com.stqa.astar.AStar;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MyMouseMotionListener implements MouseMotionListener {
	public static boolean FLAG;
	
	@Override
	public void mouseDragged(MouseEvent e) {
		FLAG = true;
		AStar.drawWall(e);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {}
}
