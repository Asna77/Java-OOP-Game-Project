package com.mycompany.a3;

import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

//Flag object
public class Flag extends Fixed implements ISelectable{
	private int sequenceNumber; // variable to handle the different flag numbers
	private boolean selected;
	
	// flag constructor
	public Flag(int sequenceNumber, com.codename1.ui.geom.Point point) {
		super (26, point, 0x0000FF);
		this.sequenceNumber = sequenceNumber;
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int base = getSize();
		int height = getSize();
		int xCenter = (int)(getLocation().getX()+pCmpRelPrnt.getX());
		int yCenter = (int)(getLocation().getY()+pCmpRelPrnt.getY());
		
		int[] xPoints = {xCenter, xCenter - base/2, xCenter + base/2};
		int[] yPoints = {yCenter - height/2, yCenter + height/2, yCenter + height/2};
		
		g.setColor(getColor());
		g.fillPolygon(xPoints, yPoints, 3);
		g.setColor(0x000000);
		g.drawString("" + getSequenceNumber(), xCenter - 5, yCenter -5);
		
		if(isSelected()) {
			g.setColor(ColorUtil.CYAN);
			g.drawRect(xCenter - getSize()/2,  yCenter - getSize()/2,  getSize(), getSize());
		}
	}
	
	// get the number for the flag
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	
	// display information
	public String toString() {
		return "Flag: " + super.toString() + " sequenceNumber = " + sequenceNumber;
	}

	@Override
	public boolean collidesWith(GameObject otherObject) {
		return false;
	}

	@Override
	public void handleCollision(GameObject otherObject) {
		
	}

	@Override
	public boolean isSelected() {
		return selected;
	}

	@Override
	public void setSelected(boolean selected) {
		this.selected = selected;
		
	}

	@Override
	public boolean contains(Point pPtrRelPrnt) {
		int x = (int) getLocation().getX();
		int y = (int) getLocation().getY();
		int size = getSize();
		
		return (pPtrRelPrnt.getX() >= x - size/2 &&
				pPtrRelPrnt.getX()<= x + size/2 &&
				pPtrRelPrnt.getY() >= y - size/2 &&
				pPtrRelPrnt.getY() <= y + size/2);
	}

}
