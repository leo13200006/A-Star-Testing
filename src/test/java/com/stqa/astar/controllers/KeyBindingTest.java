package com.stqa.astar.controllers;

import com.stqa.astar.AStar;
import org.junit.jupiter.api.*;

import java.awt.*;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KeyBindingTest {
	
	Robot robot;
	
	@BeforeAll
	static void openWindow() {
		new AStar();
	}
	
	@BeforeEach
	void createRobot() throws AWTException {
		robot = new Robot();
	}
	
	@Test
	@DisplayName("Testing the Creation of Start")
	void startPoint() {
		robot.keyPress(VK_S);
		try {
			Thread.sleep(50);
		} catch (InterruptedException ignored) {
		}
		robot.keyRelease(VK_S);
		assertEquals(VK_S, KeyBinding.getKEYCODE());
	}
	
	@Test
	@DisplayName("Testing the Creation of End")
	void endPoint() {
		robot.keyPress(VK_E);
		try {
			Thread.sleep(50);
		} catch (InterruptedException ignored) {
		}
		robot.keyRelease(VK_E);
		assertEquals(VK_E, KeyBinding.getKEYCODE());
	}
	
	@Test
	@DisplayName("Testing the Connection with Enter")
	void runWithEnter() {
		robot.keyPress(VK_ENTER);
		try {
			Thread.sleep(50);
		} catch (InterruptedException ignored) {
		}
		robot.keyRelease(VK_ENTER);
		assertEquals(VK_ENTER, KeyBinding.getKEYCODE());
	}
	
	@Test
	@DisplayName("Testing the Connection with Escape")
	void resetWithEscape() {
		robot.keyPress(VK_ESCAPE);
		try {
			Thread.sleep(50);
		} catch (InterruptedException ignored) {
		}
		robot.keyRelease(VK_ESCAPE);
		assertEquals(VK_ESCAPE, KeyBinding.getKEYCODE());
	}
	
	@Test
	@DisplayName("Testing the Connection with R")
	void generateRandomWalls() {
		robot.keyPress(VK_R);
		try {
			Thread.sleep(50);
		} catch (InterruptedException ignored) {
		}
		robot.keyRelease(VK_R);
		assertEquals(VK_R, KeyBinding.getKEYCODE());
	}
}
