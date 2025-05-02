package com.mycompany.a3;

import com.codename1.ui.*;

//import com.codename1.ui.events.ActionEvent;
//import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.*;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
//import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.UITimer;

public class Game extends Form implements Runnable{
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	
	private boolean paused = false;
	private UITimer timer;
	
	// constructor
	public Game() {
		// Set title
		super(new BorderLayout());
		setTitle("Avoid-It Game");
		
		// import gameWorld, mapView, and scoreView
		gw = new GameWorld();
		mv = new MapView(gw);
		sv = new ScoreView();
		
		gw.addObserver(mv); //observers of Game
		gw.addObserver(sv);
		
		// set layout for game
		setLayout(new BorderLayout());
		add(BorderLayout.NORTH, sv);
		add(BorderLayout.CENTER, mv);
		
		gw.init();
		gw.createSounds();
		
		// set up the graphical user interface with the appropriate commands and menus
		setupCommands();
		setupGUI();
		setupKeyBindings();
	    setupSideMenu();
	
	    this.revalidate();
		
		timer = new UITimer(this);
		timer.schedule(500, true, this);
		this.show();
		
		if (gw.isSoundOn()) {
		    gw.playBackgroundSound();
		}
	}
	
	
	// method to set up all of the commands
@SuppressWarnings("deprecation")
	private void setupCommands() {
		Command accelerateCmd = new AccelerateCommand(gw);
		Command brakeCmd = new BrakeCommand(gw);
		Command turnLeftCmd = new TurnLeftCommand(gw);
		Command turnRightCmd = new TurnRightCommand(gw);
		Command soundCmd = new SoundCommand(gw);
		Command aboutCmd = new AboutCommand(gw);
		Command helpCmd = new HelpCommand(gw);
		
	}
	
	// method to set up the graphical user interface
	public void setupGUI() {
		// layout
		setupBottomButtons();
		setupSideButtons();
	}
	
	// method to set up buttons needed on the side: accelerate, turn left, brake, and turn right	
	private void setupSideButtons() {
		    String[][] sideButtons = { // display their names/titles
		        { "Accelerate", "Left" },
		        { "Brake", "Right" }
		    };

		    // Left container
		    Container leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		    for (String label : sideButtons[0]) {
		        Button button = createStyledButton(label);
		        if(label.equals("Accelerate")) {
		        	button.setCommand(new AccelerateCommand(gw));
		        }
		        else if(label.equals("Left")) {
		        	button.setCommand(new TurnLeftCommand(gw));
		        }
		        leftContainer.add(button);
		    }
		    
		    // right container
		    Container rightContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		    for(String label : sideButtons[1]) {
		    	Button button = createStyledButton(label);
		    	if(label.equals("Brake")) {
		    		button.setCommand(new BrakeCommand(gw));
		    	}
		    	else if(label.equals("Right")) {
		    		button.setCommand(new TurnRightCommand(gw));
		    	}
		    	
		    	rightContainer.add(button);
		    }

		    add(BorderLayout.WEST, leftContainer); // add to the left side
		    add(BorderLayout.EAST, rightContainer); // add to the right side
		}

		// method to add the buttons needed at the bottom of the GUI
      private void setupBottomButtons() {
          String[] buttonLabels = { "Pause", "Position" };

          Container bottomContainer = new Container(new FlowLayout(Component.CENTER)); // new container
          bottomContainer.setPreferredH(Display.getInstance().convertToPixels(50));

          
          for (String label : buttonLabels) {
            
              if(label.equals("Pause")) {
              	CheckBox pauseButton = new CheckBox("Pause");
              	pauseButton.setToggle(true);
              	
              	// pause button styling
              	Style pauseStyle = pauseButton.getAllStyles();
              	pauseStyle.setBgColor(0x0000FF);
              	pauseStyle.setFgColor(0xFFFFFF);
              	pauseStyle.setBgTransparency(255);
              	pauseStyle.setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
              	pauseStyle.setPadding(Component.TOP, 6);
              	pauseStyle.setPadding(Component.BOTTOM, 6);
              	pauseStyle.setPadding(Component.LEFT, 12);
              	pauseStyle.setPadding(Component.RIGHT, 12);
              	
              	
              	pauseStyle.setBorder(Border.createLineBorder(1, 0x000000));
              	
              	 Style pausePressed = pauseButton.getPressedStyle();
                 pausePressed.setBgColor(pauseStyle.getBgColor());
                 pausePressed.setFgColor(pauseStyle.getFgColor());
                 pausePressed.setFont(pauseStyle.getFont());
                 pausePressed.setBgTransparency(pauseStyle.getBgTransparency());
                 pausePressed.setBorder(pauseStyle.getBorder());
                 pausePressed.setPadding(Component.TOP, 6);
                 pausePressed.setPadding(Component.BOTTOM, 6);
                 pausePressed.setPadding(Component.LEFT, 12);
                 pausePressed.setPadding(Component.RIGHT, 12);
              	
              	
              	
              	pauseButton.addActionListener(e-> togglePause());
              	bottomContainer.add(pauseButton);
              }
              else {
            	  Button button = createStyledButton(label);
              
              if(label.equals("Position")) {
              	button.addActionListener(e -> {
              		if(paused) {
              			mv.enableDraggingMode();
              		}
              	});
              }
              
              Style unselected = button.getUnselectedStyle();
              Style pressed = button.getPressedStyle();
              pressed.setBgColor(unselected.getBgColor());
              pressed.setFgColor(unselected.getFgColor());
              pressed.setFont(unselected.getFont());
              pressed.setBgTransparency(unselected.getBgTransparency());
              pressed.setBorder(unselected.getBorder());
              pressed.setPadding(Component.TOP, 6);
              pressed.setPadding(Component.BOTTOM, 6);
              pressed.setPadding(Component.LEFT, 12);
              pressed.setPadding(Component.RIGHT, 12);

     
              bottomContainer.add(button);
          }
          }
          // add the container to the bottom of the form
          add(BorderLayout.SOUTH, bottomContainer);
		
	}
      // method to create customization for the buttons - used in the method that sets up the buttons
      private Button createStyledButton(String text) {
  		Button button = new Button(text); // name
  	    Style buttonStyle = button.getAllStyles();
  	    buttonStyle.setBgColor(0x0000FF); // background color
  	    buttonStyle.setBgTransparency(255); // background transparency
  	    buttonStyle.setFgColor(0xFFFFFF); 
  	    
  	    buttonStyle.setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD,Font.SIZE_MEDIUM));
  	    
  	    // button size
  	    buttonStyle.setPadding(Component.TOP, 6);
  	    buttonStyle.setPadding(Component.BOTTOM, 6);
  	    buttonStyle.setPadding(Component.LEFT, 12);
  	    buttonStyle.setPadding(Component.RIGHT, 12);
  	    
  	    // borders
  	    buttonStyle.setMargin(Component.TOP, 4);
  	    buttonStyle.setMargin(Component.BOTTOM, 4);
  	    buttonStyle.setBorder(Border.createLineBorder(1, 0x000000));
  	    buttonStyle.setAlignment(Component.CENTER);
  	    
  	    button.setPressedStyle(button.getUnselectedStyle());
  	    
  	    return button;
  	      		
  	}
  	

	// method to set up the menus on the side for the commands/buttons
	 private void setupSideMenu() {
	        // using tool bar
		 Toolbar tb = getToolbar();
		 if (tb == null) {
			    tb = new Toolbar();
			    setToolbar(tb);
			}
	     // Add sound toggle
	        CheckBox soundToggle = new CheckBox("Sound"); // sound is to be implemented as a check box
	       // default to "off"
	        soundToggle.setSelected(gw.isSoundOn());
	        soundToggle.addActionListener(e -> {
	        	gw.setSound(soundToggle.isSelected());
	        });

			tb.addComponentToSideMenu(soundToggle); // add the sound toggle
	        
	        // add the rest of the commands to the side menu
	        tb.addCommandToSideMenu(new AccelerateCommand(gw));
	        tb.addCommandToSideMenu(new SoundCommand(gw));
	        tb.addCommandToSideMenu(new AboutCommand(gw));
	        
	        // help command should be on the right
	        tb.addCommandToRightBar(new HelpCommand(gw));

	        tb.addCommandToOverflowMenu(new ExitCommand()); // exit menu to quit
	    }
	
	 // method to set up key bindings where each character key is associated with a specific action to be executed
	public void setupKeyBindings() {
		addKeyListener('a', new AccelerateCommand(gw));
		addKeyListener('b', new BrakeCommand(gw));
		addKeyListener('l', new TurnLeftCommand(gw));
		addKeyListener('r', new TurnRightCommand(gw));
		addKeyListener('c', new CollideWithFoodCommand(gw));
		addKeyListener('f', new CollideWithFoodCommand(gw));
		addKeyListener('g', new CollideWithSpiderCommand(gw));
		addKeyListener('t', new TickCommand(gw));
	}
	
	// method for further customization of the components
	private void copyStyle(Style source, Style target) {
	    target.setBgColor(source.getBgColor());
	    target.setBgTransparency(source.getBgTransparency());
	    target.setFgColor(source.getFgColor());
	    target.setPadding(
	        source.getPadding(false, Component.TOP),
	        source.getPadding(false, Component.BOTTOM),
	        source.getPadding(false, Component.LEFT),
	        source.getPadding(false, Component.RIGHT)
	    );
	    target.setMargin(
	        source.getMargin(false, Component.TOP),
	        source.getMargin(false, Component.BOTTOM),
	        source.getMargin(false, Component.LEFT),
	        source.getMargin(false, Component.RIGHT)
	    );
	    target.setBorder(source.getBorder());
	    target.setAlignment(source.getAlignment());
	    target.setFont(source.getFont());
	}
	
	public boolean isPaused() {
		return paused;
	}
	
	private void togglePause() {
		paused = !paused;
		
		if(paused) {
			pauseGame();
		}
		else {
			resumeGame();
			mv.disableDraggingMode();
		}
	}
	
	private void pauseGame() {
		timer.cancel();
		gw.pauseBackgroundSound();
		mv.setPaused(true);
	}

	private void resumeGame() {

		timer.schedule(500, true, this);
		if(gw.isSoundOn()) gw.playBackgroundSound();
		mv.setPaused(false);
	}

	@Override
	public void run() {
		if(!paused) {
		gw.tick(20);
		}
		
	}

}
