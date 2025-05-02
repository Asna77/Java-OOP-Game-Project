package com.mycompany.a3;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import com.codename1.ui.geom.Point;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;

// MapView class to display game information
public class MapView extends Container implements Observer {
	private GameWorld gw;
	private boolean paused = false;
	private boolean dragging = false;
	
	public MapView(GameWorld gw) {
		this.gw = gw;
		this.getAllStyles().setBorder(Border.createLineBorder(3,0xFF0000));
	}
	
	public void update (Observable o, Object arg) {
		this.repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Point origin = new Point(this.getX(), this.getY());
		
		Iterator<GameObject> it = gw.getGameObjects();
		while(it.hasNext()) {
			GameObject obj = it.next();
			if(obj instanceof IDrawable) {
				((IDrawable) obj).draw(g, origin);
			}
		}
	}
	
	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	
	@Override
	public void pointerPressed(int x, int y) {
		if(!paused) {
			return;
		}
		
		Point clickPoint = new Point(x - getX(), y - getY());
		
		Iterator<GameObject> it = gw.getGameObjects();
		while(it.hasNext()) {
			GameObject obj =  it.next();
			
			if(obj instanceof ISelectable) {
				ISelectable sel = (ISelectable) obj;
				if(sel.contains(clickPoint)) {
					sel.setSelected(true);
				}
				else {
					sel.setSelected(false);
				}
			}
		}
		repaint();
	}
	
	@Override
	public void pointerDragged(int x, int y) {
		if(!paused || !dragging) {
			return;
		}
		
		Point p = new Point( x- getX(), y - getY());
		
		Iterator<GameObject> it = gw.getGameObjects();
		while(it.hasNext()) {
			GameObject obj = it.next();
			if(obj instanceof ISelectable && ((ISelectable)obj).isSelected()) {
				obj.setLocation(p);
			}
		}
		repaint();
	}
	
	public void enableDraggingMode() {
		dragging = true;
	}
	
	public void disableDraggingMode() {
		dragging = false;
	}

}
