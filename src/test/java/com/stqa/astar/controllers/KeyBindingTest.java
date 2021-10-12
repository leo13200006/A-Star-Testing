package com.stqa.astar.controllers;

import com.stqa.astar.AStar;
import org.junit.jupiter.api.*;

import java.awt.*;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;
import static org.junit.jupiter.api.Assertions.*;

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
		robot.delay(50);
		robot.keyRelease(VK_S);
		assertEquals(VK_S, KeyBinding.getKEYCODE());
	}
	
	@Test
	@DisplayName("Testing the Creation of End")
	void endPoint() {
		robot.keyPress(VK_E);
		robot.delay(50);
		robot.keyRelease(VK_E);
		assertEquals(VK_E, KeyBinding.getKEYCODE());
	}
	
	@Test
	@DisplayName("Testing the Connection with Enter")
	void runWithEnter() {
		robot.keyPress(VK_ENTER);
		robot.delay(50);
		robot.keyRelease(VK_ENTER);
		assertEquals(VK_ENTER, KeyBinding.getKEYCODE());
	}
	
	@Test
	@DisplayName("Testing the Connection with Escape")
	void resetWithEscape() {
		robot.keyPress(VK_ESCAPE);
		robot.delay(50);
		robot.keyRelease(VK_ESCAPE);
		assertEquals(VK_ESCAPE, KeyBinding.getKEYCODE());
	}
	
	@Test
	@DisplayName("Testing the Connection with R")
	void generateRandomWalls() {
		robot.keyPress(VK_R);
		robot.delay(50);
		robot.keyRelease(VK_R);
		assertEquals(VK_R, KeyBinding.getKEYCODE());
	}
	
	@Test
	@DisplayName("Testing the False case of L key")
	void checkingFalseCaseScenario () {
		robot.keyPress(VK_L);
		robot.delay(50);
		robot.keyRelease(VK_L);
		assertNotEquals(VK_L, KeyBinding.getKEYCODE());
	}
}
