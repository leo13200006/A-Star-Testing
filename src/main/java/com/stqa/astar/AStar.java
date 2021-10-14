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
import java.util.ArrayList;
import java.util.Random;

public class AStar extends JFrame {
	public static int width;
	public static int height;
	public static JPanel canvas;
	public static Grid grid;
	private final Font largeFont = new Font("Fira Code", Font.BOLD, 36);
	private final Font smallFont = new Font("Fira Code", Font.BOLD, 22);
	public static boolean beginAStar = false;
	public AStarCore aStarCore;
	
	public AStar() throws HeadlessException {
		super("A Star Pathfinding");
		GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = graphics.getDefaultScreenDevice();
		width = device.getDisplayMode().getWidth();
		height = device.getDisplayMode().getHeight();
		aStarCore = new AStarCore();
		grid = new Grid();
		canvas = new JPanel() {
					public void paint(Graphics g) {
						setBackground(Color.WHITE);
						drawInstructions(g);
						grid.drawAllCell(g);
						
						if (beginAStar) {
							beginAStar = aStarCore.startAStar();
							aStarCore.setColorsToCell();
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
		
		setSize(width, height);
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
		g.drawString("A* Pathfinding", Grid.width + 31, 50);
		// Legend
		g.setFont(smallFont);
		g.drawString("Legend", Grid.width + 31, 100);
		
		g.setFont(smallFont.deriveFont(Font.PLAIN, 18));
		
		// Start
		g.setColor(new Color(120, 178, 179));
		g.fillRect(Grid.width + 31, 130, 20, 20);
		
		g.setColor(Color.BLACK);
		g.drawString("Start", Grid.width + 55, 147);
		
		// End
		g.setColor(new Color(255, 94, 90));
		g.fillRect(Grid.width + 110, 130, 20, 20);
		
		g.setColor(Color.BLACK);
		g.drawString("End", Grid.width + 135, 147);
		
		// Open Set
		g.setColor(new Color(143, 255, 232));
		g.fillRect(Grid.width + 175, 130, 20, 20);
		
		g.setColor(Color.BLACK);
		g.drawString("Open Set", Grid.width + 200, 147);
		
		// Closed Set
		g.setColor(new Color(251, 168, 255));
		g.fillRect(Grid.width + 31, 180, 20, 20);
		
		g.setColor(Color.BLACK);
		g.drawString("Closed Set", Grid.width + 55, 197);
		
		// Wall
		g.setColor(new Color(50, 50, 50));
		g.fillRect(Grid.width + 160, 180, 20, 20);
		g.setColor(Color.BLACK);
		g.drawString("Wall", Grid.width + 185, 197);
		
		g.setFont(smallFont);
		g.drawString("Controls", Grid.width + 31, 250);
		
		g.setFont(smallFont.deriveFont(Font.PLAIN, 18));
		
		g.drawString("~ Hold S and click on any cell to set start point", Grid.width + 31, 290);
		g.drawString("~ Hold E and click on any cell to set end point", Grid.width + 31, 320);
		g.drawString("~ Drag on cells to create walls", Grid.width + 31, 350);
		g.drawString("~ Press R to generate random walls", Grid.width + 31, 380);
		g.drawString("~ Press Enter to start", Grid.width + 31, 410);
		g.drawString("~ Press Escape to reset", Grid.width + 31, 440);
	}
	
	public static void drawWall(MouseEvent e) {
		int[] cell;
		cell = Cell.clickedCell(e.getPoint());
		if (cell != null) {
			int i = cell[0];
			int j = cell[1];
			if (!grid.isWall(i, j) && !grid.isStart(i, j) && !grid.isEnd(i, j)) {
				grid.setWall(i, j);
				canvas.repaint();
			}
		}
	}
	
	public static void randomWalls() {
		Random r = new Random();
		for (int i = 0; i < Grid.rows; i++) {
			for (int j = 0; j < Grid.cols; j++) {
				if (grid.isWall(i, j)) {
					grid.removeTheWall(i, j);
				}
			}
		}
		
		for (int i = 0; i < Grid.rows; i++) {
			for (int j = 0; j < Grid.cols; j++) {
				if (!(grid.isStart(i, j) || grid.isEnd(i, j))) {
					if (r.nextDouble() < 0.3) {
						grid.setWall(i, j);
					}
				}
			}
		}
		canvas.repaint();
	}
	
	public static void reset() {
		grid = new Grid();
		canvas.repaint();
	}
	
	public static void setStartCell(MouseEvent e) {
		int[] cell;
		cell = Cell.clickedCell(e.getPoint());
		if (cell != null) {
			int i = cell[0];
			int j = cell[1];
			if (!grid.isWall(i, j) && !grid.isStart(i, j) && !grid.isEnd(i, j)) {
				grid.setStart(i, j);
				canvas.repaint();
			}
		}
	}
	
	public static void setEndCell(MouseEvent e) {
		int[] cell;
		cell = Cell.clickedCell(e.getPoint());
		if (cell != null) {
			int i = cell[0];
			int j = cell[1];
			if (!grid.isWall(i, j) && !grid.isStart(i, j) && !grid.isEnd(i, j)) {
				grid.setEnd(i, j);
				canvas.repaint();
			}
		}
	}
	
	public static void main(String[] args) {
		new AStar();
	}
	
	public static void print(ArrayList<Cell> path) {
		String[][] grid = new String[Grid.rows][Grid.cols];
		for (Cell[] row : Grid.getGrid()) {
			for (Cell cell : row) {
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
			if (!(cell.isStart() || cell.isEnd())) grid[cell.i][cell.j] = "\u001B[33m*\u001B[0m";
		}
		
		for (String[] row : grid) {
			for (String cell : row) {
				System.out.print(cell + " ");
			}
			System.out.println();
		}
	}
}
