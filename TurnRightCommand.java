package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;

// turn right command class
public class TurnRightCommand extends Command {
	private GameWorld gw;
	
	public TurnRightCommand(GameWorld gw) {
		super("Turn Right"); // display
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		if(((Game) Display.getInstance().getCurrent()).isPaused()) {
    		return;
    	}
		gw.turnRight();
	}

}
