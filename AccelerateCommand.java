package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;

//accelerate command for game world
public class AccelerateCommand extends Command {
	 private GameWorld gw;
	    
	    public AccelerateCommand(GameWorld gw) {
	        super("Accelerate"); // display
	        this.gw = gw;
	    }

	    @Override
	    public void actionPerformed(ActionEvent evt) {
	    	if(((Game) Display.getInstance().getCurrent()).isPaused()) {
	    		return;
	    	}
	        gw.accelerate(); // action invoked = accelerate
	    }

}