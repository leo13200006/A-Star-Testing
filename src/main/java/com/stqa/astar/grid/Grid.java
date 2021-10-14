package com.stqa.astar.grid;

import com.stqa.astar.AStar;
import com.stqa.astar.cell.Cell;
import com.stqa.astar.core.AStarCore;

import java.awt.*;
import java.util.HashMap;
import java.util.Random;

public class Grid {
	public static int width, height;
	public static final int rows = 10, cols = 10;
	private static final Cell[][] grid = new Cell[rows][cols];
	private static Cell start;
	private static Cell end;

	public Grid() {
		Grid.width = (int) (AStar.width * 0.40);
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
				grid[i][j].addNeighbors(grid, rows, cols);
			}
		}
		
	}

	public void drawAllCell(Graphics graphics) {
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
		System.out.println("(" + i + "," + j + ")");
		end = grid[i][j];
		grid[i][j].color = new Color(255, 94, 90);
		grid[i][j].setEnd(true);
	}
	
	public void setWall(int i, int j) {
		grid[i][j].setWall(true);
		grid[i][j].color = new Color(50, 50, 50);
	}
	
	public void removeTheWall(int i, int j) {
		grid[i][j].setWall(false);
		grid[i][j].color = Color.WHITE;
	}
	
	public boolean isWall(int i, int j) {
		return grid[i][j].isWall();
	}
	
	public boolean isStart(int i, int j) {
		return grid[i][j].isStart();
	}
	
	public boolean isEnd(int i, int j) {
		return grid[i][j].isEnd();
	}
}
