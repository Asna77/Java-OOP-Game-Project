package com.mycompany.a3;

import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
//import com.codename1.charts.models.Point;
import com.codename1.ui.geom.Point;

import java.util.Iterator;
import java.util.Observable;
import com.codename1.*;

// GameWorld class
public class GameWorld extends Observable {
	private int lives = 3;
	private float time = 0;
	private GameObjectCollection gameObjects; // bring in objects
	private boolean soundOn = false;
	
	// add sounds
	private Sound spiderSound;
	private Sound foodSound;
	private Sound flagSound;
	private BGSound bgSound;
	
	// public method for game objects in game world
	public void init() {
		
		gameObjects = new GameObjectCollection();
		
		// insert flags
		gameObjects.add(new Flag(1, new Point(200, 200)));
        gameObjects.add(new Flag(2, new Point(100, 300)));
        gameObjects.add(new Flag(3, new Point(400, 300)));
        gameObjects.add(new Flag(4, new Point(500, 200)));
        
        // ant is singleton
        Ant ant = Ant.getInstance();
        ant.setGameWorld(this);
        ant.setLocation(new Point(100,100));
        ant.setHealthLevel(10);
        ant.setFoodLevel(350);
        ant.setFoodConsumptionRate(1);
        ant.setSpeed(0);
        ant.setHeading(0);
        ant.setLastFlagReached(0);
        gameObjects.add(ant);
        
        
        // Create spiders
        Spider spider1 = new Spider();
        Spider spider2 = new Spider();
        
        gameObjects.add(spider1);
        gameObjects.add(spider2);

        // Create food stations
        gameObjects.add(new FoodStation(20, new Point(150, 150)));
        gameObjects.add(new FoodStation(30, new Point(400, 350)));

        setChanged();
        notifyObservers();
	}
	
	public void createSounds() {
		spiderSound = new Sound("spider_collision.wav");
		foodSound = new Sound("food_collision.wav");
		flagSound = new Sound("flag_reached.wav");
		bgSound = new BGSound("background_music.wav");
		
	}
	
	public void playBackgroundSound() {
		if(bgSound != null && soundOn) {
			bgSound.play();
		}
			
	}
	
	public void pauseBackgroundSound() {
		if(bgSound != null) {
			bgSound.pause();
		}
	}
	
	// method for accelerating the ant then setting change
	public void accelerate() {
		Ant.getInstance().accelerate();
		setChanged();
		notifyObservers();
	}
	
	// method for braking upon user input and acknowledging the change
	public void brake() {
        Ant.getInstance().brake();
        setChanged();
        notifyObservers();
    }
	
	// method for turning left
	public void turnLeft() {
        Ant.getInstance().setHeading(Ant.getInstance().getHeading() - 5);
        setChanged();
        notifyObservers();
    }

	// method for turning right
    public void turnRight() {
        Ant.getInstance().setHeading(Ant.getInstance().getHeading() + 5);
        setChanged();
        notifyObservers();
    }

    // method for handling flag collisions
    public void collideWithFlag(int flagNumber) {
        Ant ant = Ant.getInstance();
        if (flagNumber == ant.getLastFlagReached() + 1) { // if the flag number is 1 number bigger than the last one reached
            ant.setLastFlagReached(flagNumber); // set this one as the last flag reached
            if(soundOn && flagSound != null) {
            	flagSound.play();
            }
        
        
        int lastFlag = 4;
        if(flagNumber == lastFlag) {
        	Dialog.show("Great Job!", "You've reached all the flags!\nYou win!", "OK", null);
        	System.exit(0);
        }
        // set changes and notify mv/sv
        setChanged();
        notifyObservers();
    }
    }

    // method to handle food collisions
    public void collideWithFood() {
    	Ant ant = Ant.getInstance();
        // find a random food station and refill Ant
        for (GameObject obj : gameObjects) {
            if (obj instanceof FoodStation) {
                FoodStation fs = (FoodStation) obj;
                if (fs.getCapacity() > 0 && ant.collidesWith(fs)) {
                    ant.setFoodLevel(ant.getFoodLevel() + fs.getCapacity());
                    fs.empty();
                    if(soundOn && foodSound != null) {
                		foodSound.play();
                	}
                    break;
                }
            }
        }
        // set changes and notify mv/sv
        setChanged();
        notifyObservers();
    }

    // method to handle collisions with spider
    public void collideWithSpider() {
    	System.out.println("Ant collided with a spider");
        Ant.getInstance().takeDamage();
        if(soundOn && spiderSound != null) {
        	spiderSound.play();
        }
        setChanged();
        notifyObservers();
    }

    // tick method
    public void tick(int elapsedTime) {
        time+= elapsedTime/1000.0f; // increment time
        
       Ant ant = Ant.getInstance(); // get ant instance
        ant.move(elapsedTime); // move ant
        float loss = ant.getFoodConsumptionRate() * elapsedTime / 1000.0f; // deltaTime is in ms
        ant.setFoodLevel((int)(ant.getFoodLevel() - loss));
        System.out.println("Time: " + time + " | Food: " + ant.getFoodLevel() + " | Health: " + ant.getHealthLevel());

        for (GameObject obj : gameObjects) {
            if (obj instanceof Spider) {
            	Spider s = (Spider) obj;
            	 // move spider(s)
            	s.move(elapsedTime);
            }
            }
        
        for(GameObject obj1: gameObjects) {
        	for(GameObject obj2: gameObjects) {
        		if(obj1 != obj2 && obj1 instanceof ICollider && obj2 instanceof ICollider) {
        			ICollider collider1 = (ICollider) obj1;
        			ICollider collider2 = (ICollider) obj2;
        			
        			boolean hasCollided = collider1.collidesWith(obj2);
        			boolean alreadyCollided = obj1.getCollisionVector().contains(obj2);
        			
        			if(hasCollided && !alreadyCollided) {
        				collider1.handleCollision(obj2);
        				obj1.getCollisionVector().add(obj2);
        				obj2.getCollisionVector().add(obj1);
        			}
        			else if(!hasCollided && alreadyCollided) {
        				obj1.getCollisionVector().remove(obj2);
        				obj2.getCollisionVector().remove(obj1);
        			}
        			
        		}
        	}
        }        

       if (ant.getFoodLevel() <= 0 || ant.getHealthLevel() <= 0) {
            lives--; // decrement lives
            System.out.println("Ant died. Lives left: " + lives);
            if (lives == 0) { // if ant runs out of lives/dies
                System.out.println("Game over, you failed!"); // display
                Dialog.show("Game Over", "Ant does not have any lives left...:(", "OK", null);
                Display.getInstance().exitApplication();
            }
            else {
            System.out.println("Minus 1 Life! Resetting..."); //reset game
            init();
        }
       }

        // set changes and notify mv/sv
        setChanged();
        notifyObservers();
    }
    

    // get ant's lives
    public int getLives() { 
    	return lives; 
    	}
    
    // get the current time
    public float getTime() { 
    	return time; 
    	}
    
    // method for checking sound 
    public boolean isSoundOn() { 
    	return soundOn; 
    	}
    
    public void setSound(boolean sound) {
    	this.soundOn = sound;
    	setChanged();
    	notifyObservers();
    }
    
    // get the last flag 
    public int getLastFlag() { 
    	return Ant.getInstance().getLastFlagReached(); 
    	}
    
    // get the ant's food level
    public int getFoodLevel() { 
    	return Ant.getInstance().getFoodLevel(); 
    	}
    
    // get the ant's health level
    public int getHealth() { 
    	return Ant.getInstance().getHealthLevel(); 
    	}
    
    public Iterator<GameObject> getGameObjects(){
    	return gameObjects.iterator();
    }
}
