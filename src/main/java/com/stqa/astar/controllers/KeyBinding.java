package com.stqa.astar.controllers;

import com.stqa.astar.AStar;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static com.stqa.astar.controllers.constants.Keys.*;
import static java.awt.event.KeyEvent.*;

public record KeyBinding(InputMap inputMap, ActionMap actionMap) {
	private static int KEYCODE;

	public void setup() {
		this.inputMap.put(KeyStroke.getKeyStroke(VK_S, 0, false), S_KEY);
		this.inputMap.put(KeyStroke.getKeyStroke(VK_E, 0, false), E_KEY);
		this.inputMap.put(KeyStroke.getKeyStroke(VK_ENTER, 0, false), Enter_KEY);
		this.inputMap.put(KeyStroke.getKeyStroke(VK_ESCAPE, 0, false), ESC_KEY);
		this.inputMap.put(KeyStroke.getKeyStroke(VK_R, 0, false), R_KEY);

		this.actionMap.put(S_KEY, startPoint());
		this.actionMap.put(E_KEY, endPoint());
		this.actionMap.put(Enter_KEY, runWithEnter());
		this.actionMap.put(ESC_KEY, resetWithEscape());
		this.actionMap.put(R_KEY, generateRandomWalls());
	}

	public AbstractAction startPoint() {
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KEYCODE = VK_S;
			}
		};
	}

	public AbstractAction endPoint() {
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KEYCODE = VK_E;
			}
		};
	}

	public AbstractAction runWithEnter() {
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (AStar.startSet && AStar.endSet && !AStar.beginAStar) {
					KEYCODE = VK_ENTER;
					AStar.beginAStar = true;
					AStar.canvas.repaint();
				}
			}
		};
	}

	public AbstractAction resetWithEscape() {
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KEYCODE = VK_ESCAPE;
				AStar.reset();
			}
		};
	}

	public AbstractAction generateRandomWalls() {
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KEYCODE = VK_R;
				AStar.randomWalls();
			}
		};
	}

	public static int getKEYCODE() {
		return KEYCODE;
	}
}
