package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;

// brake command
public class BrakeCommand extends Command {
	 private GameWorld gw;
	    
	    public BrakeCommand(GameWorld gw) {
	        super("Brake"); // display "brake"
	        this.gw = gw;
	    }

	    @Override
	    public void actionPerformed(ActionEvent evt) {
	    	if(((Game) Display.getInstance().getCurrent()).isPaused()) {
	    		return;
	    	}
	        gw.brake(); // action to be invoked = brake
	    }

}
