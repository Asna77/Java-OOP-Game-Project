package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;

// turn left command class
public class TurnLeftCommand extends Command {
	 private GameWorld gw;
	    
	    public TurnLeftCommand(GameWorld gw) {
	        super("Turn Left"); // display 
	        this.gw = gw;
	    }

	    @Override
	    public void actionPerformed(ActionEvent evt) {
	    	if(((Game) Display.getInstance().getCurrent()).isPaused()) {
	    		return;
	    	}
	        gw.turnLeft(); // method to be called from gameworld
	    }

}
