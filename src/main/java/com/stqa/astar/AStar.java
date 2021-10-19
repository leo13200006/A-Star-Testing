package com.stqa.astar;

import com.stqa.astar.cell.Cell;
import com.stqa.astar.controllers.KeyBinding;
import com.stqa.astar.controllers.MyMouseListener;
import com.stqa.astar.controllers.MyMouseMotionListener;
import com.stqa.astar.core.AStarCore;
import com.stqa.astar.grid.Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

public class AStar extends JFrame {
	public static int width;
	public static int height;
	public static JPanel canvas;
	public static Grid grid;
	private static boolean randomCells;
	public static boolean startSet;
	public static boolean endSet;
	private final Font largeFont = new Font("Fira Code", Font.BOLD, 36);
	private final Font smallFont = new Font("Fira Code", Font.BOLD, 22);
	public static boolean beginAStar = false;
	public static AStarCore aStarCore;

	public AStar() {
		super("A Star Pathfinding");
		GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = graphics.getDefaultScreenDevice();
		width = device.getDisplayMode().getWidth();
		height = device.getDisplayMode().getHeight();
		aStarCore = new AStarCore();
		grid = new Grid();

		canvas = new JPanel() {
			public void paint(Graphics g) {
				setBackground(Color.white);
				drawInstructions(g);
				grid.drawAllCells(g);
				if (beginAStar) {
					beginAStar = aStarCore.startAStar();
					aStarCore.draw(g);
					try {
						Thread.sleep(50);
						this.repaint();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		canvas.setFocusable(true);
		canvas.setRequestFocusEnabled(true);
		add(canvas);

		this.addMouseListener(new MyMouseListener());
		this.addMouseMotionListener(new MyMouseMotionListener());

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setBackground(Color.white);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		KeyBinding keyBinding =
				new KeyBinding(canvas.getInputMap(JComponent.WHEN_FOCUSED), canvas.getActionMap());
		keyBinding.setup();
	}

	private void drawInstructions(Graphics g) {
		// Title
		g.setFont(largeFont);
		g.drawString("A* Pathfinding", Cell.w * 31, 50);
		// Legend
		g.setFont(smallFont);
		g.drawString("Legend", Cell.w * 31, 100);

		g.setFont(smallFont.deriveFont(Font.PLAIN, 18));

		// Start
		g.setColor(new Color(120, 178, 179));
		g.fillRect(Cell.w * 31, 130, Cell.w, Cell.w);

		g.setColor(Color.BLACK);
		g.drawString("Start", Cell.w * 33, 150);

		// End
		g.setColor(new Color(255, 94, 90));
		g.fillRect(Cell.w * 37, 130, Cell.w, Cell.w);

		g.setColor(Color.BLACK);
		g.drawString("End", Cell.w * 39, 150);

		// Open Set
		g.setColor(new Color(143, 255, 232));
		g.fillRect(Cell.w * 42, 130, Cell.w, Cell.w);

		g.setColor(Color.BLACK);
		g.drawString("Open Set", Cell.w * 44, 150);

		// Closed Set
		g.setColor(new Color(251, 168, 255));
		g.fillRect(Cell.w * 31, 180, Cell.w, Cell.w);

		g.setColor(Color.BLACK);
		g.drawString("Closed Set", Cell.w * 33, 200);

		// Wall
		g.setColor(new Color(50, 50, 50));
		g.fillRect(Cell.w * 40, 180, Cell.w, Cell.w);

		g.setColor(Color.BLACK);
		g.drawString("Wall", Cell.w * 42, 200);

		g.setFont(smallFont);
		g.drawString("Controls", Cell.w * 31, 250);

		g.setFont(smallFont.deriveFont(Font.PLAIN, 18));

		g.drawString("• Hold S and click on any cell to set start point", Cell.w * 31, 290);
		g.drawString("• Hold E and click on any cell to set end point", Cell.w * 31, 320);
		g.drawString("• Drag on cells to create walls", Cell.w * 31, 350);
		g.drawString("• Press R to generate random walls", Cell.w * 31, 380);
		g.drawString("• Press Enter to start", Cell.w * 31, 410);
		g.drawString("• Press Escape to reset", Cell.w * 31, 440);
	}

	public static void drawWall(MouseEvent e) {
		int[] cell;
		cell = Cell.clickedCell(e.getPoint());
		if (cell != null) {
			int i = cell[0];
			int j = cell[1];
			if (grid.isNotWall(i, j) && grid.isNotStart(i, j) && grid.isNotEnd(i, j)) {
				grid.setWall(i, j);
				canvas.repaint();
			}
		}
	}

	public static void randomWalls() {
		if (!randomCells) {
			Random r = new Random();
			for (int i = 0; i < Grid.rows; i++) {
				for (int j = 0; j < Grid.cols; j++) {
					if (!Grid.getGrid()[i][j].isStart() && !Grid.getGrid()[i][j].isEnd()) {
						if (r.nextDouble() < 0.3) {
							Grid.getGrid()[i][j].setWall(true);
						}
					}
				}
			}
			randomCells = true;
			canvas.repaint();
		}
	}

	public static void reset() {
		grid = new Grid();
		canvas.repaint();
		beginAStar = randomCells = startSet = endSet = false;
		aStarCore = new AStarCore();
	}

	public static void setStartCell(MouseEvent e) {
		if (!startSet) {
			int[] cell;
			cell = Cell.clickedCell(e.getPoint());
			if (cell != null) {
				int i = cell[0];
				int j = cell[1];
				if (grid.isNotWall(i, j) && grid.isNotStart(i, j) && grid.isNotEnd(i, j)) {
					grid.setStart(i, j);
					startSet = true;
					canvas.repaint();
				}
			}
		}
	}

	public static void setEndCell(MouseEvent e) {
		if (!endSet) {
			int[] cell;
			cell = Cell.clickedCell(e.getPoint());
			if (cell != null) {
				int i = cell[0];
				int j = cell[1];
				if (grid.isNotWall(i, j) && grid.isNotStart(i, j) && grid.isNotEnd(i, j)) {
					grid.setEnd(i, j);
					endSet = true;
					canvas.repaint();
				}
			}
		}
	}

	public static void main(String[] args) {
		new AStar();
	}
}
