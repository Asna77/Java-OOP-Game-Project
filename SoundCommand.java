package com.mycompany.a3;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

// sound command class 
public class SoundCommand extends Command {
	 private GameWorld gw;
	    
	    public SoundCommand(GameWorld gw) {
	        super("Sound"); // display
	        this.gw = gw;
	    }

	    @Override
	    public void actionPerformed(ActionEvent evt) {
	    	CheckBox soundToggle = new CheckBox("Sound ON");
	    	gw.setSound(!gw.isSoundOn());
	    	
	    }

}
