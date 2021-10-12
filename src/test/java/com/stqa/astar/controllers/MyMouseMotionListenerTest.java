package com.stqa.astar.controllers;

import com.stqa.astar.AStar;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.InputEvent;

import static org.junit.jupiter.api.Assertions.*;

class MyMouseMotionListenerTest {
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
	void isMouseDragged () {
		robot.mouseMove(aStar.width/2, aStar.height/2);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(20);
		robot.mouseMove(aStar.width/2, aStar.height/2+200);
		robot.delay(20);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(50);
		
		assertTrue(MyMouseMotionListener.FLAG);
	}
}
