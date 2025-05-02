package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class BGSound {
	private Media bgMedia;
	
	public BGSound(String fileName) {
		try {
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
			bgMedia = MediaManager.createMedia(is, "audio/wav");
		} catch (Exception e) {
			System.out.println("Error loading sound: " + fileName);
			e.printStackTrace();
		}
	}
	
	public void play() {
		if(bgMedia != null) {
			bgMedia.setTime(0);
			bgMedia.play();
		}
	}
	
	public void pause() {
		if(bgMedia != null) {
			bgMedia.pause();
		}
	}

}
