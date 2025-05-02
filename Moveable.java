package com.mycompany.a3;

import com.codename1.ui.Display;
//import com.codename1.charts.models.Point;
import com.codename1.ui.geom.Point;

//abstract class MoveAble for objects that can move
public abstract class Moveable extends GameObject{
	// local/private variables for speed and heading
	private int heading;
	private int speed;
	
	// constructor
	public Moveable(int size, Point location, int color, int heading, int speed) {
		super(size, location, color);
		this.heading=heading;
		this.speed=speed;
	}
	
	public void setLocation(Point p) {
		super.setLocation(p);
	}
	
	// get the objects' speed
	 public int getSpeed() { 
		 return speed; 
		 }
	 
	 // set the speed
	 public void setSpeed(int speed) { 
		 int capped = Math.min(speed, 200);
	    	this.speed = Math.max(0,  capped);
	    	}

	 // get the objects' heading
	 public int getHeading() { 
		 return heading; 
		 }
	 
	 // set the heading
	 public void setHeading(int heading) { 
		 this.heading = heading;
		 }
	
	 // method to move the object(s)
	public void move(int elapsedTime) {
	    double angle = Math.toRadians(90 - heading);
	    double distance = (speed * elapsedTime) / 1000.0;

	    float dx = (float)(Math.cos(angle) * distance);
	    float dy = (float)(Math.sin(angle) * distance);

	    float newX = getLocation().getX() + dx;
	    float newY = getLocation().getY() + dy;

	    // Screen boundaries 
	    int left = 0;
	    int right = Display.getInstance().getDisplayWidth();
	    int top = 0;
	    int bottom = Display.getInstance().getDisplayHeight();

	    boolean bounced = false;

	    // Bounce off left/right
	    if (newX < left || newX > right) {
	        setHeading(180 - heading); // reflect horizontally
	        bounced = true;
	       	    
	    }

	    // Bounce off top/bottom
	    if (newY < top || newY > bottom) {
	        setHeading(360 -heading); // reflect vertically
	        bounced = true;
	    }

	    if (bounced) {
	        // recalculate angle after bounce
	        angle = Math.toRadians(90 - heading);
	        dx = (float)(Math.cos(angle) * distance);
	        dy = (float)(Math.sin(angle) * distance);
	        newX = getLocation().getX() + dx;
	        newY = getLocation().getY() + dy;
	    }

	    newX = Math.max(left + 1, Math.min(newX,  right -1));
	    newY = Math.max(top + 1, Math.min(newY,  bottom -1));
	    
	    setLocation(new Point((int)newX, (int)newY));
  
	}
	
	// display information
	public String toString() {
		return super.toString() + " heading="+ heading + " speed= " + speed;
	}

}
