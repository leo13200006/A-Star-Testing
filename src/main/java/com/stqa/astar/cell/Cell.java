package com.stqa.astar.cell;

import com.stqa.astar.AStar;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Cell {
	private final int x;
	private final int y;
	public static int w, h;
	private int fCost;
	private int gCost;
	private int hCost;
	public final int i, j;
	private final ArrayList<Cell> neighbors = new ArrayList<>();
	private Cell previous = null;
	private boolean wall, start, end;
	private static final HashMap<Point, int[]> pointMap = new HashMap<>();
	public Color color;

	private final int rows = 30;
	private final int cols = 30;

	public ArrayList<Cell> getNeighbors() {
		return neighbors;
	}

	public Cell(int i, int j) {
		this.i = i;
		this.j = j;
		this.fCost = 0;
		this.gCost = 0;
		this.hCost = 0;
		this.wall = false;
		this.start = false;
		this.end = false;

		int width = AStar.width;

		w = width / rows;
		h = width / cols;

		w *= 0.55;
		h *= 0.55;

		this.x = i * w;
		this.y = j * h;

		int[] c = {i, j};
		for (int k = x; k < x + w; k++)
			for (int l = y; l < y + h; l++)
				pointMap.put(new Point(k, l), c);

		color = Color.WHITE;
	}


	public boolean isWall() {
		return wall;
	}

	public boolean isStart() {
		return start;
	}

	public boolean isEnd() {
		return end;
	}

	public void setWall(boolean wall) {
		this.wall = wall;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	/**
	 * @return int[]
	 */
	public static int[] clickedCell(Point click) {
		return pointMap.get(click);
	}

	/**
	 * @return Point
	 */
	public Point getPoint() {
		return new Point(i * w, j * h);
	}

	/**
	 *
	 */
	public void addNeighbors(Cell[][] grid) {
		int i = this.i;
		int j = this.j;

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

	/**
	 *
	 */
	public void draw(Graphics g_) {
		Graphics2D g = (Graphics2D) g_;
		Stroke s = new BasicStroke(2f);
		g.setStroke(s);
		g.setColor(this.color);
		g.fillRect(x, y, w, h);
		if (this.wall) {
			g.setColor(new Color(50, 50, 50));
			g.fillRect(x, y, w, h);
		}
		g.setColor(Color.BLACK);
		g.drawRect(x, y, w, h);
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

	public void setPrevious(Cell previous) {
		this.previous = previous;
	}

	public Cell getPrevious() {
		return previous;
	}
}
