package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

// ScoreView class to display game scores
class ScoreView extends Container implements Observer{
	// create private labels for whatever needs to be displayed
	private Label livesLabel, clockLabel, lastFlagLabel, foodLabel, healthLabel, soundLabel;
	
	// constructor
	public ScoreView() {
		setLayout(new FlowLayout(Component.CENTER));
		
		getAllStyles().setPadding(Component.TOP, 4);
		getAllStyles().setPadding(Component.BOTTOM, 4);
		
		com.codename1.ui.Font font = com.codename1.ui.Font.createSystemFont(
				com.codename1.ui.Font.FACE_SYSTEM, 
				com.codename1.ui.Font.STYLE_PLAIN, 
				com.codename1.ui.Font.SIZE_MEDIUM);
		
		// what the labels will show
		livesLabel = createStyledLabel("Lives: 3", font);
		clockLabel = createStyledLabel("Time: 0", font);
		lastFlagLabel = createStyledLabel("Last Flag: 1", font);
		foodLabel = createStyledLabel("Food Level: 100", font);
		healthLabel = createStyledLabel("Health: 100", font);
		soundLabel = createStyledLabel("Sound: OFF", font);
		
		// add labels
		add(livesLabel);
		add(clockLabel);
		add(lastFlagLabel);
		add(foodLabel);
		add(healthLabel);
		add(soundLabel);
	}
	
	private Label createStyledLabel(String text, com.codename1.ui.Font font) {
		Label label = new Label(text);
		label.getAllStyles().setFgColor(0x0000FF);
		label.getAllStyles().setFont(font);
		label.getAllStyles().setMargin(Component.RIGHT, 6);
		return label;
	}
	
	// update method
	public void update(Observable o, Object arg) {
		GameWorld gw = (GameWorld) o;
		livesLabel.setText("Lives: "+ ((GameWorld)o).getLives());
		clockLabel.setText("Time: " + ((GameWorld)o).getTime());
		lastFlagLabel.setText("Last Flag: " + ((GameWorld)o).getLastFlag());
		foodLabel.setText("Food Level: " + ((GameWorld)o).getFoodLevel());
		healthLabel.setText("Health: " + ((GameWorld)o).getHealth());
		soundLabel.setText("Sound: " + (gw.isSoundOn()? "ON" : "OFF" ));
		
		this.revalidate();  // Refresh UI
        
		
	}

}
