package com.mycompany.a3;

import com.codename1.ui.geom.Point;
import com.codename1.ui.Graphics;

//ant class
public class Ant extends Moveable {
	// create variables
	private static Ant instance; 
	private GameWorld gw;
	//set levels and speed
	private int maxSpeed = 50;
	private int foodLevel = 100;
	private int foodConsumptionRate = 1;
	private int healthLevel = 10;
	private int lastFlagReached = 1;
	
	// constructor
	private Ant() {
		super(25, new Point(100,100), 0xFF0000, 0, 10);
	}
	
	public void setGameWorld(GameWorld gw) {
		this.gw = gw;
	}
	
	// singleton design pattern
	public static Ant getInstance() {
		if(instance == null) {
			instance = new Ant();} // if an ant instance does not exist
		return instance; // if it exists already, just return that instance
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int x = (int)(getLocation().getX() + pCmpRelPrnt.getX());
		int y = (int)(getLocation().getY()+ pCmpRelPrnt.getY());
		g.setColor(this.getColor());
		g.fillArc(x,  y,  getSize(),  getSize(),  0,  360);
	}
	
	public void move(int elapsedTime) {
		double angle = Math.toRadians(90 - getHeading());
		double distance = (getSpeed()*elapsedTime)/1000.0;
		
		float dx = (float)(Math.cos(angle)*distance);
		float dy = (float)(Math.sin(angle)*distance);
		
		int newX = (int)(getLocation().getX() + dx);
		int newY = (int)(getLocation().getY() + dy);
		
		setLocation(new Point(newX, newY));
		System.out.println("Ant moving: speed=" + getSpeed() + " location=" + getLocation().getX() + "," + getLocation().getY());
	}
	
	public void handleCollision(GameObject otherObject) {
		if(otherObject instanceof Flag) {
			int flagNum = ((Flag)otherObject).getSequenceNumber();
			System.out.println("Collided with Flag #" + flagNum);
			gw.collideWithFlag(flagNum);
		} 
		else if (otherObject instanceof FoodStation) {
			gw.collideWithFood();
		}
		else if(otherObject instanceof Spider) {
			gw.collideWithSpider();
		}
	}
	
	// accelerate method
	public void accelerate() {
			setSpeed(getSpeed()+ 25); // speeds up by +25 points
			System.out.println("Ant accelerated. Speed: " + getSpeed());

		
	}
	
	// brake method
	public void brake() {
		setSpeed(Math.max(0,  getSpeed() - 15)); // decrease by -15 points
	}
	
	// method that declares what happens upon hitting something that would cause damage
	public void takeDamage() {
		healthLevel=Math.max(0,  healthLevel - 1); // decrease health level by -1 point
	}
	
	// get ant's food level
	public int getFoodLevel() { 
		return foodLevel; 
		}
	
	// set the food level
	public void setFoodLevel(int foodLevel) {
	    this.foodLevel = foodLevel;
	}
	
	// get ant's food consumption rate
	public int getFoodConsumptionRate() { 
		return foodConsumptionRate; 
		}
	
	public void setFoodConsumptionRate(int foodConsumptionRate) {
		this.foodConsumptionRate =  foodConsumptionRate;
	}
	
	// get the health level of the ant
	public int getHealthLevel() { 
		return healthLevel; 
		}
	
	public void setHealthLevel(int healthLevel) {
		this.healthLevel = healthLevel;
	}
	
	// method to get the last flag reached by the ant
	public int getLastFlagReached() { 
		return lastFlagReached; 
		} 
	
	// set that flag as the last flag reached
	public void setLastFlagReached(int flag) { 
		lastFlagReached = flag; 
		}
	
	// display the information
	public String toString() {
		return "Ant: " + super.toString() + "maxSpeed = " + maxSpeed + " foodLevel = " + foodLevel + " healthLevel = " + healthLevel;
	}	

}
