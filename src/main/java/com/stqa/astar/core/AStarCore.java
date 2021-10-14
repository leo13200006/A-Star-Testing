package com.stqa.astar.core;

import com.stqa.astar.AStar;
import com.stqa.astar.cell.Cell;
import com.stqa.astar.grid.Grid;

import java.awt.*;
import java.util.ArrayList;

public class AStarCore {
	public static ArrayList<Cell> openSet = new ArrayList<>(),
			closedSet = new ArrayList<>();
 
	public Cell current;

	public boolean startAStar() {
		if (openSet.size() > 0) {
			int winner = 0;
			for (int i = 0; i < openSet.size(); i++) {
				Cell spot = openSet.get(i);
				if (spot.getFCost() < openSet.get(winner).getFCost()) {
					winner = i;
				}
			}
			current = openSet.get(winner);
			if (current.isEnd() && Grid.getEnd().isEnd()) {
				createAndShowPath();
				return false;
			}

			closedSet.add(current);
			openSet.remove(current);

			ArrayList<Cell> neighbors = current.getNeighbors();
			for (Cell neighbor : neighbors) {
				if (!closedSet.contains(neighbor) && !neighbor.isWall()) {
					int tempG = current.getGCost() + 1;
					boolean newPath = false;
					if (openSet.contains(neighbor)) {
						if (tempG < neighbor.getGCost()) {
							neighbor.setGCost(tempG);
							newPath = true;
						}
					} else {
						neighbor.setGCost(tempG);
						newPath = true;
						openSet.add(neighbor);
					}

					if (newPath) {
						neighbor.setHCost(heuristic(neighbor, Grid.getEnd()));
						neighbor.setFCost(neighbor.getGCost() + neighbor.getHCost());
						neighbor.setPrevious(current);
					}
				}
			}
			return true;
		} else {
			System.out.println("No Solution!");
			createAndShowPath();
			return false;
		}
	}

	private void createAndShowPath() {
		ArrayList<Cell> path = new ArrayList<>();
		Cell temp = current;
		path.add(temp);
		while (temp.getPrevious() != null) {
			path.add(temp.getPrevious());
			temp = temp.getPrevious();
		}
		AStar.print(path);
	}
	
	private double distance(double x1, double y1, double x2, double y2) {
		x1 -= x2;
		y1 -= y2;
		return Math.sqrt(x1 * x1 + y1 * y1);
	}

	private int heuristic(Cell neighbor, Cell end) {
		// Return Euclidean Distance for now
		return (int) distance(neighbor.x, neighbor.y, end.x, end.y);
	}
	
	public void setColorsToCell () {
		for (Cell cell : openSet) {
			cell.color = new Color(143, 255, 232);
		}
		
		for (Cell cell : closedSet) {
			cell.color = new Color(251, 168, 255);
		}
		ArrayList<Cell> path = new ArrayList<>();
		Cell temp = current;
		path.add(temp);
		while (temp.previous != null) {
			path.add(temp.previous);
			temp = temp.previous;
		}
		for (Cell cell : path) {
			cell.color = new Color(248, 0, 104);
		}
	}
}
