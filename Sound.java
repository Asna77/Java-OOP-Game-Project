package com.mycompany.a3;

import java.io.InputStream;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class Sound {
	private Media sound;
	
	public Sound(String fileName) {
		try {
	        System.out.println("Loading sound: " + fileName); // DEBUG LINE
	        InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/resources" + fileName);
	        if (is == null) {
	            System.out.println("Sound file not found: " + fileName);
	        }
	        sound = MediaManager.createMedia(is, "audio/wav");
	    } catch (Exception e) {
	        System.out.println("Error loading sound: " + fileName);
	        e.printStackTrace();}
		

	}
	
	public void play() {
		if(sound != null) {
			sound.setTime(0);
			sound.play();
		}
	}

}
