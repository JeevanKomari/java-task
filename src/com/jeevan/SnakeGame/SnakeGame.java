package com.jeevan.SnakeGame;

import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class SnakeGame extends JFrame implements ActionListener, KeyListener{

	 private static final int GRID_SIZE = 10;
	 private static final int CELL_SIZE = 10;
	    private Snake snake;
	    private Food food;
	    private Timer timer;
	    
	    
	    
	    public SnakeGame() {
		
	        
	        food = new Food();
	        snake = new Snake(1 , food);
	        

	        // Create a timer to trigger the game loop every 200 milliseconds
	        timer = new Timer(1000, this);

	        // Add the SnakeGame panel to the JFrame
	        add(new SnakeGamePanel(snake, food, GRID_SIZE, CELL_SIZE));

	        // Set up JFrame properties
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(GRID_SIZE * CELL_SIZE, GRID_SIZE * CELL_SIZE);
	        setLocationRelativeTo(null);
	        setTitle("Snake Game");
	        setVisible(true);

	        // Register the KeyListener with the JFrame
	        addKeyListener(this);
	        setFocusable(true);

	        // Start the game loop
	        timer.start();
	    }
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyPressed(KeyEvent e) {
			
			 int key = e.getKeyCode();

		        switch (key) {
		            case KeyEvent.VK_UP:
		                snake.setDirection(0); // Up
		                break;
		            case KeyEvent.VK_RIGHT:
		                snake.setDirection(1); // Right
		                break;
		            case KeyEvent.VK_DOWN:
		                snake.setDirection(2); // Down
		                break;
		            case KeyEvent.VK_LEFT:
		                snake.setDirection(3); // Left
		                break;
		        }
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			    snake.move();
		        checkCollisions();
		        checkFood();
		        repaint();
			
		}
	
	
		 private void checkFood() {
			 if (snake.collidesWithFood(food)) {
		            snake.updateScore();
		            food.generateFood();
		        }
		}
		private void checkCollisions() {
			if (snake.collidesWithItself() || snake.collidesWithBoundaries()) {
	            gameOver();
	        }
			
		}
		private void gameOver() {
			
			timer.stop();
		}
		
		public static void main(String[] args) {
		            new SnakeGame();
          }
     

		public static class SnakeGamePanel extends JPanel {

		 
			 private Snake snake;
			 private Food food;
			 private int GRID_SIZE;
			 private int CELL_SIZE;

			public SnakeGamePanel(Snake snake, Food food, int gridSize, int cellSize) {
			        this.snake = snake;
			        this.food = food;
			        this.GRID_SIZE = gridSize;
			        this.CELL_SIZE = cellSize;
			    }

			    @Override
			    protected void paintComponent(Graphics g) {
			        super.paintComponent(g);

			        
			        // Set the size of the panel based on the grid size
			        setPreferredSize(new Dimension(GRID_SIZE * CELL_SIZE,GRID_SIZE * CELL_SIZE));
			        
			        // Draw the game elements here
			        drawGrid(g);
			        drawSnake(g);
			        drawFood(g);
			    }


			    private void drawGrid(Graphics g) {
			    	
			        g.setColor(Color.GRAY);

			        // Draw vertical lines
			        for (int i = 0; i <= GRID_SIZE; i++) {
			            int x = i * CELL_SIZE;
			            g.drawLine(x, 0, x, GRID_SIZE * CELL_SIZE);
			        }

			        // Draw horizontal lines
			        for (int i = 0; i <= GRID_SIZE; i++) {
			            int y = i * CELL_SIZE;
			            g.drawLine(0, y, GRID_SIZE * CELL_SIZE, y);
			            
			        }
			    }
			        
			        
		private int getHeight(int i) {
					// TODO Auto-generated method stub
					return 0;
				}

		private int getWidth(int i) {
					// TODO Auto-generated method stub
					return 0;
				}

		public void drawSnake(Graphics g) {
			
		
			g.setColor(Color.GREEN); // Set snake color
	        for (Point point : snake.getBody()) {
	            int x = (int) (point.getX() * CELL_SIZE); // Assuming CELL_SIZE is a constant
	            int y = (int) (point.getY() * CELL_SIZE);
	            g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
	        }
			
		}
		 
		public void drawFood(Graphics g) {
			
			 g.setColor(Color.RED); // Set food color
		        Point foodPosition = (Point) food.getPosition();
		        int x = (int) (foodPosition.getX() * CELL_SIZE);
		        int y = (int) (foodPosition.getY() * CELL_SIZE);
		        g.fillOval(x, y, CELL_SIZE, CELL_SIZE);
			
		}
	
	}
		
}
