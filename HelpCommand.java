package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
// help command class
public class HelpCommand extends Command {
	 private GameWorld gw;
	    
	    public HelpCommand(GameWorld gw) {
	        super("Help"); // display
	        this.gw = gw;
	    }

	    @Override
	    public void actionPerformed(ActionEvent evt) {
	    	// action to be performed when invoked - dialog box with key information
	    	String helpDisplay = "Keys:\n" + "'a' = Accelerate\n" + "'b' = Brake\n" + "'l' = Turn Left\n" + "'r' = Turn Right\n" + "'c' = Collide with Flag\n" + "'f' = Collide with Food\n" + "'g' = Collide with Spider\n" + "'t' = Tick (increment time)" ;
	    	Dialog.show("Help", helpDisplay, "Ok", null);
	    }

}
