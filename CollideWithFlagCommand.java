package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;

// class that handles collisions with flags
public class CollideWithFlagCommand extends Command{
	private GameWorld gw;
	
	public CollideWithFlagCommand(GameWorld gw) {
		super("Collided with Flag"); // inform user of the collision
		this.gw = gw;
	}
	
	// override actionPerformed method 
	@Override
	public void actionPerformed(ActionEvent evt) {
		TextField flagInput = new TextField(); // create text field
	    Command ok = new Command("OK");
	    Command cancel = new Command("Cancel");
	    // request user to insert the flag number (1-9)
	    Command result = Dialog.show("Flag Collision", BoxLayout.encloseY(new Label("Enter flag number (1-9):"), flagInput), ok, cancel);
		try {
			int flag = Integer.parseInt(flagInput.getText().trim());
			if(flag < 1 || flag > 9 ) // if the user inserts an invalid flag number (!1-9)
				throw new NumberFormatException();
			gw.collideWithFlag(flag);
		} catch(NumberFormatException e) {
		Dialog.show("Error", "Invalid flag number entered. Please try again with a number between 1 - 9", "OK", null); // inform invalid input
	}
	}
}
