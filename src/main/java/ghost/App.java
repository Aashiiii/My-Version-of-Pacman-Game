package ghost;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PFont;
import java.lang.System.*;
import java.util.*;

/**
 *  Manages, and draws the whole game on screen.
 */

public class App extends PApplet {

/**
*  Sets the width size of the screen.
*/ 

public static final int WIDTH = 448;

/**
*  Sets the hieght size of the screen.
*/ 

public static final int HEIGHT = 576;

/**
*  Map object.
*/ 

// Variable of type Map.
public Map map;

/**
*  Waka object.
*/ 

// Variable of type Waka.
public Waka waka;

/**
*  Fruit object.
*/ 

// Variable of type Fruit.
public Fruit fruits;

/**
*  Ghost object.
*/ 

// Variable of type Ghost.
public Ghost ghost;

/**
*  GameManager object.
*/ 

// Variable of type GameManager.
public GameManager gameManager;

/**
*  Config object.
*/ 

// Variale of type Config.
public Config config;

/**
*  Variable telling the time at which the game started.
*/ 


// Variable  to store the time at which game was started.
public long time; 

// Variable of type PFont.
private PFont font;

// Counter to store the number of times draw has been called when game is over, or 
// player has won.
private int counter; 

/**
*  Variable to store the number of times debug is called to decide when to activate, and deactivate it.
*/ 

// Counter keeping a track of whether we need to activate debug mode, or deactivate.
public int debug; 

// Variable to store the list of keycode of all the keys pressed.
private ArrayList<Integer> keyCodesList; 

/**
*  Constructs a new App object.
*/ 

// Constructor.
public App() {

}


/**
*  It creates all the game objects, sets up the frame rate, and loads all the images.
*/ 

public void setup() {

        // Set frame rate to 60 frames per second.
        frameRate (60); 

        // Counter to store number of frames when game is won, or game ends
        counter = 0; 

        // To keep a count of when debug mode has been activated, and when deactivated.
        debug = 0; 

        // Stores the list of keycode of the keys pressed by the player.
        this.keyCodesList = new ArrayList<Integer> (); 

        // Stores the current time.
        this.time = System.currentTimeMillis() / 1000;

        //Loading images, and creating objects.
        this.config = new Config("config.json"); 

        //Loading images, and creating objects.

        this.map = new Map (this.loadImage("src/main/resources/downLeft.png"), 
                                this.loadImage("src/main/resources/downRight.png"), 
                                this.loadImage("src/main/resources/upLeft.png"), 
                                this.loadImage("src/main/resources/upRight.png"), 
                                this.loadImage("src/main/resources/horizontal.png"), 
                                this.loadImage("src/main/resources/vertical.png"), 
                            this.config); 

        this.fruits = new Fruit (this.loadImage("src/main/resources/fruit.png"), 
                                        this.loadImage("src/main/resources/SuperFruit.png"), 
                                this.config); 

        this.waka = new Waka (this.loadImage("src/main/resources/playerClosed.png"), 
                                        this.loadImage("src/main/resources/playerDown.png"), 
                                        this.loadImage("src/main/resources/playerLeft.png"), 
                                        this.loadImage("src/main/resources/playerRight.png"), 
                                        this.loadImage("src/main/resources/playerUp.png"),
                                this.fruits,this.config);

        this.ghost = new Ghost (this.loadImage("src/main/resources/ambusher.png"), 
                                        this.loadImage("src/main/resources/ignorant.png"),
                                        this.loadImage("src/main/resources/chaser.png"),
                                        this.loadImage("src/main/resources/whim.png"),
                                        this.waka, this.loadImage("src/main/resources/frightened.png"), 
                                this.config); 

        this.gameManager = new GameManager(this.waka, this.ghost, this.fruits);

}

/**
*  It sets the size of the screen
*/ 

public void settings () {

        size (WIDTH, HEIGHT);

}


/**
*  It is called the frame rate number of times per second, and 
*  it drwas all the game components on the screen by calling their draw function.
*/ 

public void draw () { 

        // Checking if waka is alive, and the game has not been won yet.
        if (this.waka.isAlive () == true && this.fruits.hasWon () == false) { 

                //Moving the waka.
                this.waka.move (keyCode, frameCount); 

                // Moving the ghost.
                this.ghost.move (time); 

                this.map.tick ();
                this.fruits.tick ();
                this.ghost.tick ();
                this.waka.tick ();
                this.gameManager.tick ();

                background (0, 0, 0);

                this.map.draw (this);
                this.fruits.draw (this);
                this.ghost.draw (this);
                this.waka.draw (this);
                this.gameManager.draw (this);

                // Checking if space key is pressed.
                if (keyCode == 32) { 

                        // Checking if the last pressed key was not space.
                        if (keyCodesList.get (keyCodesList.size () - 1) != 32) { 

                                // In that case, we increase the counter by one.
                                debug += 1; 

                        }
                }

                // If debug is an odd number, then we need to activate debug mode, 
                // else deactivate it.
                if (debug%2 != 0) { 

                        for (int k = 0; k < 4; k++) {

                                ArrayList<Long> coordinates = new ArrayList<Long> ();

                                // Getting the coordinates of the ghost, and its targets.
                                coordinates = this.gameManager.getCoordinatesForDebug (k); 

                                if (coordinates.size () != 0) {

                                        // Changing the colour of the line.
                                        stroke (204); 

                                        // Drawing the line joining the ghost to its 
                                        // target position.
                                        line (coordinates.get (0), coordinates.get (1), 
                                                coordinates.get (2), 
                                             coordinates.get (3)); 
                                }   

                        }

                }

                keyCodesList.add (keyCode); 

        } else if (this.waka.isAlive () == false) { 
                // Checking if waka is not alive, in that case the game ends.

                counter += 1;
                this.font = createFont ("src/main/resources/PressStart2P-Regular", 32);
                textFont (font);
                background (0, 0, 0);

                // Displaying the game over text on screen.
                text ("GAME OVER", 130, 270); 

                // If this condition is true, then it means that ten seconds have passed
                if (counter > 600) { 

                        // In that case reset the game conditions, and restart the game.
                        keyCode = 0; 
                        setup();

                }

        } else { 

                // The code reaches this block when the game has been won.
                counter += 1;
                this.font = createFont ("src/main/resources/PressStart2P-Regular", 32);
                textFont (font);
                background (0, 0, 0);

                // Displaying the game won text on screen.
                text (" YOU WIN ", 130, 270); 

                // If this condition is true, then it means that ten seconds have passed.
                if (counter > 600) { 

                        // In that case reset the game conditions, and restart the game.
                        keyCode = 0; 
                        setup();
                }

        }
}

/**
*  Main method of app class.
*/ 

public static void main (String[] args) {

        PApplet.main ("ghost.App");

 }
}


