package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

// about-command class
public class AboutCommand extends Command {
	 private GameWorld gw; // in GameWorld
	    
	    public AboutCommand(GameWorld gw) {
	        super("About"); // display "about"
	        this.gw = gw;
	    }

	    @Override
	    public void actionPerformed(ActionEvent evt) {
	    	// action to be performed when invoked - display my information
	    	Dialog.show(
	    			"About", "Name: Asna Farooq\nCourse: CSC 133 - Object-Oriented Programming\nGame: Avoid-It\nSemester: Spring 2025", "Ok", null);
	    	
	    }

}
