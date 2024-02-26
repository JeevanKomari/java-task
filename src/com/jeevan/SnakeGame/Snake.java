package com.jeevan.SnakeGame;

import java.awt.Point;
import java.util.LinkedList;

public class Snake {
	
	
	    private static final int GRID_SIZE = 10; // Example grid size
	    private LinkedList<Point> body;
	    private int direction; // 0: up, 1: right, 2: down, 3: left
		private Food food;
		private int countdown = 3; // Initial countdown value
		private int score = 0;
		private boolean gameStarted = false;
		private boolean gameOver = false;
		
	 public Snake(int initialDirection, Food food) 
	 
	    {
		 
		    this.food = food;
	        body = new LinkedList<>();
	        direction = initialDirection; // Start moving to the right

	        // Initialize the snake with a length of 3, starting at position (5, 5)
	        for (int i = 0; i < 3; i++) {
	            body.add(new Point(5 - i, 5));
	        }
	    }
	 
	public LinkedList<Point> getBody() {
		        return body;
		    }
	    
	public int getDirection() {
	        return direction;
	    }
	    
	 
	public void setDirection(int newDirection) {
		
		 // Ensure the new direction is valid and not opposite to the current direction
        if (Math.abs(newDirection - direction) != 2) {
            direction = newDirection;
        }
		
	}
	
	public void move() {
		 
		 if (countdown > 0) {
		        // Display countdown on the screen
		        System.out.println("Countdown: " + countdown);
		        countdown--;

		        // If countdown reaches 0, start the game
		        
		      if (countdown == 0) {
		    	  
		            System.out.println("Game started!");
		            
		            gameStarted = true;
		      
		      }     
		      
		    } else if (!gameStarted) {
		        System.out.println("Game started!");
		        gameStarted = true;
		    } else {
		        moveSnake();
	    }
	}	    		
	
	private void moveSnake() {
		
	    Point head = getHead();
		            
		            if (head != null) {
		                switch (direction) {
		                    case 0: // up
		                        body.addFirst(new Point(head.x, head.y - 1));
		                        break;
		                    case 1: // right
		                        body.addFirst(new Point(head.x + 1, head.y));
		                        break;
		                    case 2: // down
		                        body.addFirst(new Point(head.x, head.y + 1));
		                        break;
		                    case 3: // left
		                        body.addFirst(new Point(head.x - 1, head.y));
		                        break;
		                    default:
		                        // Handle unexpected direction
		                        // For example, you can log an error or throw an exception
		                        System.err.println("Unexpected direction: " + direction);
		                        break;
		                }
		                
		                System.out.println("Head Position: " + head.x + ", " + head.y);
	   
		            } else {
		                    // Handle the case where head is null
		                    // You might want to log an error or take appropriate action
		                    System.err.println("Head is null. Cannot move.");
		                }
		        	 
		          
		 
		 if   (!body.isEmpty()) {
	            body.removeLast();
		            
		    } 
		    	
				if (collidesWithFood(food)) {
					// If the snake collides with food, update the score and generate a new food
					increaseSize();
		             score++;
		             updateScore();
		             food.generateFood();
		         }

		         // Check for collisions with itself or game boundaries
		         if (!gameOver && (collidesWithItself() || collidesWithBoundaries())) {
		             gameOver();
		         }
		         
		    	}	    

       
       
      private void increaseSize() {
    	  
    	  Point tail = body.getLast();
    	  body.addLast(new Point(tail.x, tail.y));
					
				}

	private void gameOver() {
    	  
       System.out.println("Game Over!");
       gameOver = true; // Set the flag to indicate the game is over
 }
		    

	 

	void updateScore() {
	
		 System.out.println("Score:" + score);
		
	}
   
	public Point getHead() {
		
		 return body.isEmpty() ? null : body.getFirst();
		 
	    }
	 
	 
	 public boolean collidesWithFood(Food food) {
		 Point head = getHead();
		    
		 if (head != null && head.equals(food.getPosition())) {
		        // Snake has collided with food
		        increaseSize(); // Call a method to increase the size of the snake
		        return true;
		    }

		    return false;
	    }

	    public boolean collidesWithItself() {
	        for (int i = 1; i < body.size(); i++) {
	            if (getHead().equals(body.get(i))) {
	                return true;
	            }
	        }
	        return false;
	    }

	    public boolean collidesWithBoundaries() {
	    	
	    	 Point head = getHead();
	    	if (head != null) {
	    		int x = head.x;
	            int y = head.y;

	            // Check if the head is outside the grid boundaries
	            return x < 0 || x >= GRID_SIZE || y < 0 || y >= GRID_SIZE;
	            
	        } else {
	            // Handle the case when the body is empty (e.g., game hasn't started yet)
	            return false; // or throw an exception, or handle it as needed
	    }
  }
}
