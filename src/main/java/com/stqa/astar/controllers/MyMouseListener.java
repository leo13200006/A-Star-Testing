package com.stqa.astar.controllers;

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
			// createStartCell(e.getPoint());
		}
		
		if (KeyBinding.getKEYCODE() == KeyEvent.VK_E) {
			END_CELL = true;
			// createEndCell(e.getPoint());
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
