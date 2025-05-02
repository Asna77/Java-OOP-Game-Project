package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

// tick command class
public class TickCommand extends Command {
	 private GameWorld gw;
	    
	    public TickCommand(GameWorld gw) {
	        super("Tick"); // display
	        this.gw = gw;
	    }

	    @Override
	    public void actionPerformed(ActionEvent evt) { // action to be executed
	    }

}
