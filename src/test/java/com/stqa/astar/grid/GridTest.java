package com.stqa.astar.grid;

import com.stqa.astar.cell.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GridTest {
	private final Grid grid = new Grid();
	private final Random random = new Random();

	@BeforeEach
	void setup() {
		grid.setup();
		System.out.println("Setup Complete");
	}

	@Test
	@DisplayName("Grid Setup and Cell Initialization")
	void checkCellNotNull() {
		// * Pick 5 random cells from grid to test if null
		for (int i = 0; i < 5; i++) {
			int g_i = random.nextInt(Grid.rows);
			int g_j = random.nextInt(Grid.cols);
			System.out.println("Checking grid[" + g_i + "][" + g_j + "]");
			assertNotNull(Grid.getGrid()[g_i][g_j]);
		}
	}

	@Test
	@DisplayName("Neighbors assignment test")
	void checkCellHasNeighbors() {
		System.out.println("Checking Neighbors");
		for (Cell[] row : Grid.getGrid()) {
			for (Cell cell : row) {
				assertTrue(cell.getNeighbors().size() > 0);
			}
		}
	}

	@Test
	void checkGetStart() {
		grid.setStart(0, 0);
		Cell cell = Grid.getStart();
		assertTrue(cell.isStart());
	}

	@Test
	void checkSetStart() {
		int i = random.nextInt(Grid.rows);
		int j = random.nextInt(Grid.cols);
		grid.setStart(i, j);
		assertTrue(Grid.getGrid()[i][j].isStart());
	}

	@Test
	void checkGetEnd() {
		grid.setEnd(Grid.rows - 1, Grid.cols - 1);
		Cell cell = Grid.getEnd();
		assertTrue(cell.isEnd());
	}

	@Test
	void checkSetEnd() {
		int i = random.nextInt(Grid.rows);
		int j = random.nextInt(Grid.cols);
		grid.setEnd(i, j);
		assertTrue(Grid.getGrid()[i][j].isEnd());
	}
}
