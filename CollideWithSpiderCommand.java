package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

// class to handle collision with spider
public class CollideWithSpiderCommand extends Command {
	private GameWorld gw;
	public CollideWithSpiderCommand(GameWorld gw) {
		super ("Collide with Spider."); // display
		this.gw = gw;
	}
	
	// override actionPerformed method
	@Override
	public void actionPerformed(ActionEvent evt) {
		System.out.println("Collided with Spider!");
	}

}
