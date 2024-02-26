package com.jeevan.SnakeGame;

import java.awt.Point;
import java.util.Random;

public class Food {
      
	
	 private static final int GRID_SIZE = 10; // Example grid size
	    private Point position;
	
	 public Food() {
	        generateFood();
	    }
	 
	public Object getPosition() {
		return position;
	}

	
	public void generateFood() {
        // Generate a new random position for the food
        Random random = new Random();
        int x = random.nextInt(GRID_SIZE); // Assuming GRID_SIZE is a constant representing the game grid size
        int y = random.nextInt(GRID_SIZE);
        position = new Point(x, y);
    }
}
