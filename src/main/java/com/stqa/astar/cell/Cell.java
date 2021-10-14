package com.stqa.astar.cell;

import com.stqa.astar.grid.Grid;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Cell {
	public final int i, j;
	private int fCost, gCost, hCost;
	private final ArrayList<Cell> neighbors = new ArrayList<>();
	public Cell previous;
	private boolean isWall, isStart, isEnd;
	public final int x, y;
	public static int width, height;
	public Color color;
	private static final HashMap<Point, int[]> pointMap = new HashMap<>();
	
	public Cell(int i, int j) {
		this.i = i;
		this.j = j;
		this.fCost = 0;
		this.gCost = 0;
		this.hCost = 0;
		this.isWall = false;
		this.isStart = false;
		this.isEnd = false;
		width = Grid.width / Grid.rows - 10;
		height = Grid.width / Grid.cols - 10;
		this.x = i * width + 5;
		this.y = j * height + 5;
		this.color = Color.WHITE;
		
		int[] c = { i, j };
		for (int k = x; k < x + width; k++)
			for (int l = y; l < y + height; l++)
				pointMap.put(new Point(k, l), c);
	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Stroke s = new BasicStroke(2f);
		g2.setStroke(s);
		g2.setColor(color);
		g2.fillRect(x, y, width, height);
		g2.setColor(Color.BLACK);
		g2.drawRect(x, y, width, height);
 	}
	
	public static int[] clickedCell(Point click) {
		return pointMap.get(click);
	}
 
	public void addNeighbors(Cell[][] grid, int rows, int cols) {
		int i = this.i;
		int j = this.j;
		// * Non-Diagonals
		if (i < cols - 1) {
			this.neighbors.add(grid[i + 1][j]);
		}
		if (i > 0) {
			this.neighbors.add(grid[i - 1][j]);
		}
		if (j < rows - 1) {
			this.neighbors.add(grid[i][j + 1]);
		}
		if (j > 0) {
			this.neighbors.add(grid[i][j - 1]);
		}
		// * Diagonals
		if (i > 0 && j > 0) {
			this.neighbors.add(grid[i - 1][j - 1]);
		}
		if (i < cols - 1 && j > 0) {
			this.neighbors.add(grid[i + 1][j - 1]);
		}
		if (i > 0 && j < rows - 1) {
			this.neighbors.add(grid[i - 1][j + 1]);
		}
		if (i < cols - 1 && j < rows - 1) {
			this.neighbors.add(grid[i + 1][j + 1]);
		}
	}

	public ArrayList<Cell> getNeighbors() {
		return neighbors;
	}

	public int getFCost() {
		return fCost;
	}

	public void setFCost(int fCost) {
		this.fCost = fCost;
	}

	public int getGCost() {
		return gCost;
	}

	public void setGCost(int gCost) {
		this.gCost = gCost;
	}

	public int getHCost() {
		return hCost;
	}

	public void setHCost(int hCost) {
		this.hCost = hCost;
	}

	public boolean isWall() {
		return isWall;
	}

	public void setWall(boolean wall) {
		isWall = wall;
	}

	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean start) {
		isStart = start;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean end) {
		isEnd = end;
	}

	public Cell getPrevious() {
		return previous;
	}

	public void setPrevious(Cell previous) {
		this.previous = previous;
	}
}
