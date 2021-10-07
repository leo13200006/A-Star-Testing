package com.stqa.astar;

import com.stqa.astar.cell.Cell;
import com.stqa.astar.core.AStarCore;
import com.stqa.astar.grid.Grid;

import java.util.ArrayList;

public class AStar {

	public static void main(String[] args) {
		AStarCore aStarCore = new AStarCore();
		Grid grid = new Grid();
		grid.setup();
		grid.setStart(0, 0);
		aStarCore.openSet.add(Grid.getStart());

		grid.setEnd(Grid.rows - 1, Grid.cols - 1);
		grid.randomWalls();
		boolean beginAStar = true;
		while (beginAStar) {
			beginAStar = aStarCore.startAStar();
		}
	}

	public static void print(ArrayList<Cell> path) {
		String[][] grid = new String[Grid.rows][Grid.cols];
		for (Cell[] row : Grid.getGrid()) {
			for (Cell cell: row) {
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
			if (!(cell.isStart() || cell.isEnd()))
				grid[cell.i][cell.j] = "\u001B[33m*\u001B[0m";
		}

		for (String[] row : grid) {
			for (String cell: row) {
				System.out.print(cell + " ");
			}
			System.out.println();
		}

	}
}
