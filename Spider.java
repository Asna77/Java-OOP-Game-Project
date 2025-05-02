package com.mycompany.a3;

import com.codename1.ui.geom.Point;
import com.codename1.ui.Graphics;

import java.util.Random;

// spider class
public class Spider extends Moveable{

	public Spider() {
		// set its attributes
		super(
		        27,
		        new Point(new Random().nextInt(800), new Random().nextInt(600)),
		        0x000000,
		        new Random().nextInt(360),
		        10
		    );

	}
	
	public void draw (Graphics g, Point pCmpRelPrnt) {
		int base = getSize();
		int height = getSize();
		int xCenter = (int)(getLocation().getX()+ pCmpRelPrnt.getX());
		int yCenter = (int)(getLocation().getY() + pCmpRelPrnt.getY());
		
		int[] xPoints = {xCenter - base/2, xCenter + base/2, xCenter};
		int[] yPoints = {yCenter + height/2, yCenter + height/2, yCenter - height/2};
		
		g.setColor(this.getColor());
		g.drawPolygon(xPoints,  yPoints, 3);
	}
	
	public void move(int elapsedTime) {
		super.move(elapsedTime);
	}

	@Override
	public boolean collidesWith(GameObject otherObject) {
		return false;
	}

	@Override
	public void handleCollision(GameObject otherObject) {
		
	}
	
	
	
}
