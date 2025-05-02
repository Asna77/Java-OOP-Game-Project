package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;

// exitCommand class to handle exit request
public class ExitCommand extends Command {
	public ExitCommand() {
		super("Exit."); // command title
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		// request confirmation from the user for exiting/quitting
		boolean confirm = Dialog.show("Exit", "Are you sure you want to quit?", "Yes", "No");
		if(confirm) {
			Display.getInstance().exitApplication(); // if they select yes, exit application
		}
	}

}
