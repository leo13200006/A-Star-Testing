package com.stqa.astar;

import com.stqa.astar.cell.Cell;
import com.stqa.astar.controllers.KeyBinding;
import com.stqa.astar.core.AStarCore;
import com.stqa.astar.grid.Grid;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AStar extends JFrame {
	public int width, height;
	public JPanel canvas;
	public AStar() throws HeadlessException {
		super("A Star Pathfinding");
		
		this.canvas = new JPanel() {
					public void paint(Graphics g) {
						super.paint(g);
						setBackground(Color.WHITE);
					}
				};
		
		this.canvas.setFocusable(true);
		this.canvas.setRequestFocusEnabled(true);
		add(this.canvas);
		
		GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = graphics.getDefaultScreenDevice();
		width = device.getDisplayMode().getWidth();
		height = device.getDisplayMode().getHeight();
		setSize(width, height);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		KeyBinding keyBinding = new KeyBinding(canvas.getInputMap(JComponent.WHEN_FOCUSED),
				canvas.getActionMap());
		keyBinding.setup();
	}
	
	public static void main(String[] args) {
		new AStar();
	}
	
	public static void print(ArrayList<Cell> path) {
		String[][] grid = new String[Grid.rows][Grid.cols];
		for (Cell[] row : Grid.getGrid()) {
			for (Cell cell : row) {
				if (cell.isStart()) {
					grid[cell.i][cell.j] = "\u001B[32mS\u001B[0m";
				} else if (cell.isEnd()) {
					grid[cell.i][cell.j] = "\u001B[31mE\u001B[0m";
				} else if (cell.isWall()) {
					grid[cell.i][cell.j] = "\u001B[35m#\u001B[0m";
				} else {
					grid[cell.i][cell.j] = "\u001B[36m.\u001B[0m";
				}
			}
		}
		
		for (Cell cell : path) {
			if (!(cell.isStart() || cell.isEnd())) grid[cell.i][cell.j] = "\u001B[33m*\u001B[0m";
		}
		
		for (String[] row : grid) {
			for (String cell : row) {
				System.out.print(cell + " ");
			}
			System.out.println();
		}
	}
}
