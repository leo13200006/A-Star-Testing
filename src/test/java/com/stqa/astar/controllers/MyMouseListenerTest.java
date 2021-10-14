package com.stqa.astar.controllers;

import com.stqa.astar.AStar;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static org.junit.jupiter.api.Assertions.*;

class MyMouseListenerTest {
	private static AStar aStar;
	Robot robot;
	
	@BeforeAll
	static void openWindow() {
		aStar = new AStar();
	}
	
	@BeforeEach
	void createRobot() throws AWTException {
		robot = new Robot();
	}
 
	@Test
	void areClickedAreRegistering () {
		robot.mouseMove(aStar.width/2, aStar.height/2);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(20);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(50);
		assertTrue(MyMouseListener.FLAG);
	}
	
	@Test
	void creatingStartCell () {
		robot.keyPress(KeyEvent.VK_S);
		robot.delay(20);
		robot.mouseMove(aStar.width/2, aStar.height/2);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(20);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.keyRelease(KeyEvent.VK_S);
		robot.delay(50);
		assertTrue(MyMouseListener.START_CELL);
	}
	
	@Test
	void creatingEndCell () {
		robot.keyPress(KeyEvent.VK_E);
		robot.delay(20);
		robot.mouseMove(aStar.width/2, aStar.height/2);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(20);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.keyRelease(KeyEvent.VK_E);
		robot.delay(50);
		assertTrue(MyMouseListener.END_CELL);
	}
}
