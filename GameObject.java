package com.mycompany.a3;

import com.codename1.ui.geom.Point;
import com.codename1.ui.Graphics;
import java.util.Vector;

//GameObject class 
public abstract class GameObject implements IDrawable, ICollider {
	private int size;
	private com.codename1.ui.geom.Point location;
	private int color;
	
	// constructor
	public GameObject(int size, com.codename1.ui.geom.Point point, int color) {
		// set the objects' size, location, and color
		this.size=size;
		this.location=point;
		this.color=color;
	}
	
	public abstract void draw(Graphics g, Point pCmpRelPrnt);
	
	private Vector<GameObject> collisionVector = new Vector<>();
	
	public Vector<GameObject> getCollisionVector(){
		return collisionVector;
	}
	
	public boolean collidesWith(GameObject other) {
		float thisX = this.getLocation().getX();
		float thisY = this.getLocation().getY();
		float otherX =  other.getLocation().getX();
		float otherY = other.getLocation().getY();
		
		int thisSize = this.getSize();
		int otherSize = other.getSize();
		
		float thisLeft = thisX - thisSize/2;
		float thisRight = thisX + thisSize/2;
		float thisTop = thisY + thisSize/2;
		float thisBottom = thisY - thisSize/2;
		
		float otherLeft = otherX - otherSize/2;
		float otherRight = otherX + otherSize/2;
		float otherTop = otherY + otherSize/2;
		float otherBottom = otherY - otherSize/2;
		
		boolean xOverlap = thisRight >= otherLeft && thisLeft <= otherRight;
		boolean yOverlap = thisTop >=otherBottom && thisBottom <= otherTop;
		
		return xOverlap && yOverlap;
		
	}
	
	// get the size
	public int getSize() {
		return size;
	}
	
	// get the location
	public com.codename1.ui.geom.Point getLocation() {
		return location;
	}
	
	public void setLocation(Point location) {
		System.out.println(this.getClass().getSimpleName() + " setLocation to " + location.getX() + ", " + location.getY());
		this.location = location;
	}
	
	// get the color
	public int getColor() {
		return color;
	}
	
	// display information
	public String toString() {
		return "loc=" + location.getX() + ", " + location.getY() + "size= " + size;
	}

}
