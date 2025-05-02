package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

// class to handle collisions with food
public class CollideWithFoodCommand extends Command {
	private GameWorld gw;
	
	public CollideWithFoodCommand(GameWorld gw) {
		super("Collide with Food"); // display
		this.gw = gw;
	}
	
	// override actionPerformed method
	@Override
	public void actionPerformed(ActionEvent evt) {
		System.out.println("Collided with Food!"); // inform
	}

}
