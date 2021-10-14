package com.stqa.astar.controllers;

import com.stqa.astar.AStar;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyMouseListener implements MouseListener {
	public static boolean FLAG;
	public static boolean START_CELL;
	public static boolean END_CELL;
	
	@Override
	public void mouseClicked (MouseEvent e) {
		FLAG = true;
		if (KeyBinding.getKEYCODE() == KeyEvent.VK_S) {
			START_CELL = true;
			AStar.setStartCell(e);
		}
		
		if (KeyBinding.getKEYCODE() == KeyEvent.VK_E) {
			END_CELL = true;
			AStar.setEndCell(e);
		}
	}
	
	@Override
	public void mousePressed (MouseEvent e) {}
	
	@Override
	public void mouseReleased (MouseEvent e) {}
	
	@Override
	public void mouseEntered (MouseEvent e) {}
	
	@Override
	public void mouseExited (MouseEvent e) {}
}
