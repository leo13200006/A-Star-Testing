package com.stqa.astar.grid;

import com.stqa.astar.cell.Cell;

import java.awt.*;
import java.util.Random;

public class Grid {
	public static int width, height;
	public static final int rows = 10, cols = 10;
	private static final Cell[][] grid = new Cell[rows][cols];
	private static Cell start;
	private static Cell end;

	public Grid() {
		GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = graphics.getDefaultScreenDevice();
		width = device.getDisplayMode().getWidth();
		height = device.getDisplayMode().getHeight();
	}

	public void setup() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				grid[i][j] = new Cell(i, j);
			}
		}
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				grid[i][j].addNeighbors(grid, rows, cols);
			}
		}
	}

	public void randomWalls() {
		// Cells have a 30% chance of being a wall
		Random r = new Random();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (!(grid[i][j].isStart() || grid[i][j].isEnd())) {
					if (r.nextDouble() < 0.3) {
						grid[i][j].setWall(true);
					}
				}
			}
		}
	}

	public static Cell[][] getGrid() {
		return grid;
	}

	public static Cell getStart() {
		return start;
	}

	public void setStart(int i, int j) {
		start = grid[i][j];
		grid[i][j].setStart(true);
	}

	public static Cell getEnd() {
		return end;
	}

	public void setEnd(int i, int j) {
		end = grid[i][j];
		grid[i][j].setEnd(true);
	}
}
