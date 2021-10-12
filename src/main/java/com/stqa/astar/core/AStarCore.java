package com.stqa.astar.core;

import com.stqa.astar.cell.Cell;
import com.stqa.astar.grid.Grid;

import java.awt.*;
import java.util.ArrayList;

public class AStarCore {
	public static ArrayList<Cell> openSet = new ArrayList<>(),
			closedSet = new ArrayList<>();

	public Cell current;

	public AStarCore() {
		openSet = new ArrayList<>();
		closedSet = new ArrayList<>();
	}

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
			return false;
		}
	}

	private int heuristic(Cell a, Cell b) {
		Point p = a.getPoint();
		Point q = b.getPoint();
		return (int) Point.distance(p.x, p.y, q.x, q.y);
	}

	public void draw(Graphics g) {
		for (Cell cell : openSet) {
			cell.color = new Color(143, 255, 232);
			cell.draw(g);
		}

		for (Cell cell : closedSet) {
			cell.color = new Color(251, 168, 255);
			cell.draw(g);
		}

		ArrayList<Cell> path = new ArrayList<>();
		Cell temp = current;
		path.add(temp);
		while (temp.getPrevious() != null) {
			if (!temp.isStart() || !temp.isEnd()) {
				path.add(temp.getPrevious());
				temp = temp.getPrevious();
			}
		}

		for (Cell cell : path) {
			if (cell.isStart())
				cell.color = new Color(120, 178, 179);
			else if (cell.isEnd())
				cell.color = new Color(255, 94, 90);
			else cell.color = new Color(160, 32, 240);
			cell.draw(g);
		}
	}
}
