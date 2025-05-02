package com.mycompany.a3;

import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

//foodStation class
public class FoodStation extends Fixed implements ISelectable {
	private int capacity; // variable to handle the capacity for each food station
	private boolean selected = false;
	
	// constructor
	public FoodStation(int size, com.codename1.ui.geom.Point point) {
		super(30, point, 0x00FF00);
		this.capacity = size;
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int size = getSize();
		int x = (int)(getLocation().getX()+pCmpRelPrnt.getX() - size/2);
		int y = (int)(getLocation().getY()+pCmpRelPrnt.getY() - size/2);
		
		g.setColor(getColor());
		g.fillRect(x, y, size, size);
		g.setColor(0x000000);
		g.drawString(Integer.toString(getCapacity()), x+getSize()/4, y+getSize()/4);
		
		if(isSelected()) {
			g.setColor(ColorUtil.CYAN);
			g.drawRect(x, y, size, size);
		}
	}
	
	// get the food station's capacity
	public int getCapacity() {
		return capacity;
	}
	
	// if empty
	public void empty() {
		capacity =0;
	}
	
	// display information
	public String toString() {
		return "FoodStation: " + super.toString() + " capacity = " + capacity;
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
		
		return (pPtrRelPrnt.getX()>= x - size/2 &&
				pPtrRelPrnt.getX() <= x + size/2 &&
				pPtrRelPrnt.getY() >= y - size/2 && 
				pPtrRelPrnt.getY() <= y + size/2);
	}

}
