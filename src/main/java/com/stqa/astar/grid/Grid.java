package com.stqa.astar.grid;

import com.stqa.astar.AStar;
import com.stqa.astar.cell.Cell;
import com.stqa.astar.core.AStarCore;

import java.awt.*;

public class Grid {
	public static int width, height;
	public static final int rows = 30, cols = 30;
	private static final Cell[][] grid = new Cell[rows][cols];
	private static Cell start;
	private static Cell end;

	public Grid() {
		Grid.width = AStar.width;
		Grid.height = AStar.height;
		this.setup();
	}

	public void setup() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				grid[i][j] = new Cell(i, j);
			}
		}

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				grid[i][j].addNeighbors(grid);
			}
		}
	}

	public void drawAllCells(Graphics graphics) {
		for (Cell[] cells : grid) {
			for (Cell cell : cells) {
				cell.draw(graphics);
			}
		}
	}

	public static Cell[][] getGrid() {
		return grid;
	}

	public static Cell getStart() {
		return start;
	}

	public static Cell getEnd() {
		return end;
	}

	public void setStart(int i, int j) {
		start = grid[i][j];
		AStarCore.openSet.add(start);
		grid[i][j].color = new Color(120, 178, 179);
		grid[i][j].setStart(true);
	}

	public void setEnd(int i, int j) {
		end = grid[i][j];
		grid[i][j].color = new Color(255, 94, 90);
		grid[i][j].setEnd(true);
	}

	public void setWall(int i, int j) {
		grid[i][j].setWall(true);
		grid[i][j].color = new Color(50, 50, 50);
	}

	public boolean isNotWall(int i, int j) {
		return !grid[i][j].isWall();
	}

	public boolean isNotStart(int i, int j) {
		return !grid[i][j].isStart();
	}

	public boolean isNotEnd(int i, int j) {
		return !grid[i][j].isEnd();
	}
}
