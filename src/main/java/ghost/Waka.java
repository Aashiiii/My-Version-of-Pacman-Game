package ghost;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PFont;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.lang.Math;

/**
 *  Represents waka object in game.
 */

public class Waka {

// Variable to store the starting x-axis position of waka.
private long startx;

// Variable to store the starting y-axis position of waka.
private long starty;

// Variable to store the x-axis position of waka.
private long xpos;

// Variable to store the y-axix position of waka.
private long ypos;

// Variable to store the number of lives that waka has.
private long lives;

// Variable to store the speed of waka.
private long speed;

// Variable to store whether the waka is alive or not.
private boolean alive;

// Variable to store whether the waka is moving or not.
private boolean isMoving;

// Variable to store whether waka should have a closed mouth or not.
private boolean closedOrNot;

// Variable to store the PImage of waka with closed mouth.
private PImage playerClosed;

// Variable to store the PImage of waka when facing downwards.
private PImage playerDown;

// Variable to store the PImage of waka when facing left.
private PImage playerLeft;

// Variable to store the PImage of waka when facing right.
private PImage playerRight;

// Variable to store the PImage of waka when facing upwards.
private PImage playerUp;

// Variable to store the PImage of waka that needs to be drawn on the screen.
private PImage spirit;

// Variable to store the name of the map text file.
private String mapfile;

// Variable to store the direction in which the waka is moving.
private String movingDirection;

// Variable to store the x-axis position, followed by y-axis position of down left 
// grid blocks.
private ArrayList<Integer> downL;

// Variable to store the x-axis position, followed by y-axis position of down right 
// grid blocks.
private ArrayList<Integer> downR;

// Variable to store the x-axis position, followed by y-axis position of up left 
// grid blocks.
private ArrayList<Integer> upL;

// Variable to store the x-axis position, followed by y-axis position of up right 
// grid blocks.
private ArrayList<Integer> upR;

// Variable to store the x-axis position, followed by y-axis position of horizontal 
// grid blocks.
private ArrayList<Integer> hor;

// Variable to store the x-axis position, followed by y-axis position of vertical 
// grid blocks.
private ArrayList<Integer> ver;

// Variable to store the list of moves made by waka.
private ArrayList<Integer> moves;

// Variable to store the x-axis position, followed by y-axis position of fruits on map.
private ArrayList<Integer> fruitsList;

// Variable to store the x-axis position, followed by y-axis position of super fruits on map.
private ArrayList<Integer> superFruitsList;

// Variable of type Fruit.
private Fruit fruits;

// Variable of type Config.
private Config config;

/**
 *  Constructs a new Waka object.
 * 
 * @param playerClosed PImage of waka with closed mouth.
 * @param playerDown PImage of waka facing downwards.
 * @param playerLeft PImage of waka facing towards the left.
 * @param playerRight PImage of waka facing towards the right.
 * @param playerUp PImage of waka facing upwards.
 * @param fruits Object of type Fruit.
 * @param config Object of type Config.
 * 
 */

// Constructor.
public Waka (PImage playerClosed, PImage playerDown, PImage playerLeft,  PImage playerRight, 
                        PImage playerUp, Fruit fruits, Config config ) {
        
        this.playerClosed = playerClosed;
        this.playerDown = playerDown;
        this.playerLeft = playerLeft;
        this.playerRight = playerRight;
        this.playerUp = playerUp;
        this.spirit = playerClosed;

        this.alive = true;
        this.isMoving = false;
        this.fruits = fruits;
        this.fruitsList = this.fruits.getFruits ();
        this.superFruitsList = this.fruits.getSuperFruits ();
        this.movingDirection = "start";

        this.config = config;
        this.lives = config.getLives ();
        this.speed = config.getSpeed ();

        this.hor = config.getHor ();
        this.ver = config.getVer ();
        this.downL = config.getDownL ();
        this.downR = config.getDownR ();
        this.upL = config.getUpL ();
        this.upR = config.getUpR ();

        this.moves = new ArrayList<Integer> ();

        this.startx = config.getWakaStartX ();
        this.starty = config.getWakaStartY ();

        this.xpos = startx;
        this.ypos = starty;

        this.closedOrNot = false;
}

/**
 *  Returns the current x-axis position of waka.
 *  @return x-axis position of waka.
 */

// Getter method.
public long getX () {
        return startx;
}

/**
 *  Returns the current y-axis position of waka.
 *  @return y-axis position of waka.
 */

// Getter method.
public long getY () {
        return starty;
}

/**
 *  Returns the direction in which the waka is moving.
 *  @return Direction of waka.
 */

// Getter method.
public String getMovingDirection () {
        return movingDirection;
}

/**
 *  Method to move the waka based on the keycode of the key pressed by the player.
 * 
 *  @param code Keycode
 *  @param frameCount Frame count
 */

public void move (int code, int frameCount) {

        // We add keycode of a move only once before it changes.
        if (moves.size () != 0) {
                if ( (int) moves.get (moves.size () - 1) != code) {
                        moves.add ( (Integer) code);
                }
        } else {
                moves.add ( (Integer) code);
        }

        // The current keycode is the last code in the list of moves.
        int keyCode = (int) moves.get (moves.size () - 1);

        // This function moves the waka in the particular direction, and checkes if it 
        // is colliding with some fruit, or superfruit, or not.
        boolean result = direction (keyCode);

        // Result stores false if the waka is colliding when trying to move in the 
        // direction specified by the key pressed by user.
        if (result == false && moves.size () > 2) {

                // In the case when waka is colliding, waka moves in the direction of 
                // the previous keycode, instead of stopping.
                keyCode = (int) moves.get (moves.size () - 2);

                // Moving waka in the direction of previous keycode.
                Boolean res = direction (keyCode);

                // If waka is colliding for the previous keycode also, then we remove 
                // that keycode from the list.
                // Waka tries to move using the previous keycodes if possible, 
                // otherwise stops.
                if (res == false) {

                        moves.remove (moves.size () - 2);

                }

        } else if (result == true && moves.size () > 2) {

                // If the previous move was possible then we remove moves from the moves 
                // list accordingly.
                moves.remove (moves.remove (moves.size () - 2) );
        }

        // Waka changes from open to colsed to open every 8 frames.
        // Calculating based on the frame count, that when waka needs to change from 
        // open to close and then back to open.
        if ( frameCount % 8 == 0) {

                if ( ( frameCount / 8 ) % 2 == 0 ){

                        closedOrNot = false;

                } else {

                        closedOrNot = true;

                }
        }

        // If the condition is true, then it calls the close function to change the 
        // waka image to closed.
        if (closedOrNot == true){

                close();
        
        }

}

/**
 * This function checks and returns whether waka can move in the direction specified 
 * by keycode or not. It also checks if waka is eating any fruit, or superfruit when 
 * moving in that direction or not.
 * It returns true if waka can move in that direction, else it returns false.
 * 
 * @param keyCode Key code of the key pressed.
 * 
 * @return Outcome of trying to move waka in that direction.
 */

public boolean direction (int keyCode) {

        boolean result;
        if (keyCode == 37) {
                result = moveLeft ();
                isCollidingFruitsLeft (fruitsList, "simple");
                isCollidingFruitsLeft (superFruitsList, "super");

        } else if (keyCode == 38) {
                result = moveUp ();
                isCollidingFruitsUp (fruitsList, "simple");
                isCollidingFruitsUp (superFruitsList, "super");

        } else if (keyCode == 39) {
                result = moveRight ();
                isCollidingFruitsRight (fruitsList, "simple");
                isCollidingFruitsRight (superFruitsList, "super");

        } else if (keyCode == 40) {
                result = moveDown ();
                isCollidingFruitsDown (fruitsList, "simple");
                isCollidingFruitsDown (superFruitsList, "super");

        } else {
                result = false;
        }

        return result;
}


/**
 *  This function tries to move waka upwards. It returns false if waka collided with 
 *  wall when doing so, else it returns true. 
 *  
 *  @return Whether waka can move upwards or not.
 */


public boolean moveUp () {

        this.starty -= this.speed;

        if (isCollidingUp () == true) {
                this.starty += this.speed;
                return false;
        } else {
                this.movingDirection = "up";
                this.spirit = this.playerUp;
        }

        return true;
}

/**
 *  This function tries to move waka downwards. It returns false if waka collided with 
 *  wall when doing so, else it returns true. 
 *  
 *  @return Whether waka can move downwards or not.
 */

public boolean moveDown () {

        this.starty += this.speed;

        if (isCollidingDown () == true) {
                this.starty -= this.speed;
                return false;
        } else {
                this.movingDirection = "down";
                this.spirit = this.playerDown;
        }

        return true;
}

/**
 *  This function tries to move waka towards right. It returns false if waka collided 
 *  with wall when doing so, else it returns true. 
 *  
 *  @return Whether waka can move right or not.
 */

public boolean moveRight () {

        this.startx += this.speed;

        if (isCollidingRight () == true) {
                this.startx -= this.speed;
                return false;
        } else {
                this.spirit = this.playerRight;
                this.movingDirection = "right";
                return true;
        }
}


/**
 *  This function tries to move waka towards left. It returns false if waka collided 
 *  with wall when doing so, else it returns true. 
 *  
 *  @return Whether waka can move left or not.
 */

public boolean moveLeft () {

        this.startx -= this.speed;

        if (isCollidingLeft () == true) {
                this.startx += this.speed;
                return false;
        } else {
                this.movingDirection = "left";
                this.spirit = this.playerLeft;
        }

        return true;
}

/**
 *  This function is called when waka dies. It resets the position, number of lives, 
 *  and the initial starting PImage of waka.
 */

public void die () {

        // Number of lives of waka decreases.
        this.lives -= 1;

        // The starting position of waka is reset.
        this.startx = xpos;
        this.starty = ypos;

        // Waka always starts with closed mouth.
        this.spirit = playerClosed;
}

/**
 *  Checking if waka is alive or not. Waka is alive if it has lives left. It returns 
 *  true if waka has lives left, else it returns false.
 * 
 *  @return Whether waka is alive or not.
 */

public boolean isAlive () {

        if (this.lives == 0) {
                return false;
        } else {
                return true;
        }

}


/**
 *  Checking for different top, bottom, left, and right values of waka, and wall that 
 *  they are colliding or not when waka is trying to move upwards.
 *  It returns true if the waka collides with wall, when it tries to move upwards, 
 *  else it returns false.
 * 
 * 
 *  @param wallTop       Coordinates of the top of wall grid.
 *  @param wallBottom    Coordinates of the bottom of wall grid.
 *  @param wallLeft      Coordinates of the left side of wall grid.
 *  @param wallRight     Coordinates of the right of wall grid.
 *  @param wakaTop      Coordinates of the top of waka.
 *  @param wakaBottom   Coordinates of the bottom of waka.
 *  @param wakaLeft     Coordinates of the left side of waka.
 *  @param wakaRight    Coordinates of the right side of waka.
 * 
 *  @return              Outcome of waka trying to move upwards.
 */


public boolean collisonUp (long wallTop, long wallBottom, long wallLeft, long wallRight, 
                                long wakaTop, long wakaBottom, long wakaLeft, long wakaRight) {
        
        if (wakaTop < wallBottom && wakaTop > wallTop && wakaRight < wallRight 
                        && wakaRight > wallLeft) {
                
                                return true;
        }

        if (wakaTop < wallBottom && wakaTop > wallTop && wakaLeft < wallRight 
                        && wakaLeft > wallLeft) {
                
                                return true;
        }

        if (wakaTop < wallBottom && wakaTop > wallTop && wallRight < wakaRight 
                        && wallRight > wakaLeft) {
                
                                return true;
        }

        if (wallTop > wakaTop && wallTop < wakaBottom && wakaRight < wallRight 
                        && wakaRight > wallLeft) {
                
                                return true;
        }

        if (wallTop > wakaTop && wallTop < wakaBottom && wakaLeft < wallRight 
                        && wakaLeft > wallLeft) {
                
                                return true;
        }

        if (wallTop > wakaTop && wallTop < wakaBottom && wallRight < wakaRight 
                        && wallRight > wakaLeft) {
                
                                return true;
        }

        return false;
}


/**
 *  Checking for different top, bottom, left, and right values of waka, and wall 
 *  that they are colliding or not when waka is trying to move downwards.
 *  It returns true if the waka collides with wall, when it tries to move downwards, 
 *  else it returns false.
 * 
 * 
 *  @param wallTop       Coordinates of the top of wall grid.
 *  @param wallBottom    Coordinates of the bottom of wall grid.
 *  @param wallLeft      Coordinates of the left side of wall grid.
 *  @param wallRight     Coordinates of the right of wall grid.
 *  @param wakaTop      Coordinates of the top of waka.
 *  @param wakaBottom   Coordinates of the bottom of waka.
 *  @param wakaLeft     Coordinates of the left side of waka.
 *  @param wakaRight    Coordinates of the right side of waka.
 * 
 *  @return              Outcome of waka trying to move downwards.
 */

public boolean collisonDown (long wallTop, long wallBottom, long wallLeft, long wallRight, 
                                long wakaTop, long wakaBottom, long wakaLeft, long wakaRight) {

        if (wallTop > wakaTop && wallTop < wakaBottom && wakaRight < wallRight 
                        && wakaRight > wallLeft) {
                
                                return true;
        }

        if (wallTop > wakaTop && wallTop < wakaBottom && wakaLeft < wallRight 
                        && wakaLeft > wallLeft) {
                
                                return true;
        }

        if (wallTop > wakaTop && wallTop < wakaBottom && wallRight < wakaRight 
                        && wallRight > wakaLeft) {
                
                                return true;
        }

        if (wakaBottom < wallBottom && wakaBottom > wallTop && wakaRight < wallRight 
                        && wakaRight > wallLeft) {
                
                                return true;
        }

        if (wakaBottom < wallBottom && wakaBottom > wallTop && wakaLeft < wallRight 
                        && wakaLeft > wallLeft) {
                
                                return true;
        }

        return false;
}


/**
 *  Checking for different top, bottom, left, and right values of waka, and wall that 
 *  they are colliding or not when waka is trying to move towards right.
 *  It returns true if the waka collides with wall, when it tries to move right, 
 *  else it returns false.
 * 
 * 
 *  @param wallTop       Coordinates of the top of wall grid.
 *  @param wallBottom    Coordinates of the bottom of wall grid.
 *  @param wallLeft      Coordinates of the left side of wall grid.
 *  @param wallRight     Coordinates of the right of wall grid.
 *  @param wakaTop      Coordinates of the top of waka.
 *  @param wakaBottom   Coordinates of the bottom of waka.
 *  @param wakaLeft     Coordinates of the left side of waka.
 *  @param wakaRight    Coordinates of the right side of waka.
 * 
 *  @return              Outcome of waka trying to move right.
 */

public boolean collisonRight (long wallTop, long wallBottom, long wallLeft, long wallRight, 
                                long wakaTop, long wakaBottom, long wakaLeft, long wakaRight) {

        if (wakaTop > wallTop && wakaTop < wallBottom && wakaRight < wallRight 
                && wakaRight > wallLeft) {
                
                        return true;
        }

        if (wakaBottom > wallTop && wakaBottom < wallBottom && wakaRight < wallRight 
                && wakaRight > wallLeft) {
                
                        return true;
        }

        if (wakaTop < wallTop && wakaBottom > wallTop && wakaRight > wallRight 
                && wallRight > wakaLeft) {
                
                        return true;
        }

        if (wakaBottom > wallTop && wakaBottom < wallBottom && wallRight < wakaRight 
                && wallRight > wakaLeft) {
                
                        return true;
        }

        if (wakaTop > wallTop && wakaTop < wallBottom && wallLeft < wakaRight 
                && wallLeft > wakaLeft) {
                
                        return true;
        }

        return false;
}


/**
 *  Checking for different top, bottom, left, and right values of waka, and wall 
 *  that they are colliding or not when waka is trying to move towards left.
 *  It returns true if the waka collides with wall, when it tries to move left, 
 *  else it returns false.
 * 
 * 
 *  @param wallTop       Coordinates of the top of wall grid.
 *  @param wallBottom    Coordinates of the bottom of wall grid.
 *  @param wallLeft      Coordinates of the left side of wall grid.
 *  @param wallRight     Coordinates of the right of wall grid.
 *  @param wakaTop      Coordinates of the top of waka.
 *  @param wakaBottom   Coordinates of the bottom of waka.
 *  @param wakaLeft     Coordinates of the left side of waka.
 *  @param wakaRight    Coordinates of the right side of waka.
 * 
 *  @return              Outcome of waka trying to move left.
 */

public boolean collisonLeft1 (long wallTop, long wallBottom, long wallLeft, 
                                        long wallRight, long wakaTop, long wakaBottom, 
                             long wakaLeft, long wakaRight) {

        if (wakaTop < wallTop && wakaBottom > wallTop && wakaRight > wallRight 
                && wallRight > wakaLeft) {
                
                        return true;
        }

        if (wakaBottom > wallTop && wakaBottom < wallBottom && wallRight < wakaRight 
                && wallRight > wakaLeft) {
                
                        return true;
        }

        if (wakaTop > wallTop && wakaTop < wallBottom && wallLeft < wakaRight 
                && wallLeft > wakaLeft) {
                
                        return true;
        }

        return false;
}

/**
 *  Checking for different top, bottom, left, and right values of waka, and wall 
 *  that they are colliding or not when waka is trying to move towards left.
 *  It returns true if the waka collides with wall, when it tries to move left, 
 *  else it returns false.
 * 
 * 
 *  @param wallTop       Coordinates of the top of wall grid.
 *  @param wallBottom    Coordinates of the bottom of wall grid.
 *  @param wallLeft      Coordinates of the left side of wall grid.
 *  @param wallRight     Coordinates of the right of wall grid.
 *  @param wakaTop      Coordinates of the top of waka.
 *  @param wakaBottom   Coordinates of the bottom of waka.
 *  @param wakaLeft     Coordinates of the left side of waka.
 *  @param wakaRight    Coordinates of the right side of waka.
 * 
 *  @return              Outcome of waka trying to move left.
 */

public boolean collisonLeft2 (long wallTop, long wallBottom, long wallLeft, 
                                        long wallRight, long wakaTop, long wakaBottom, 
                             long wakaLeft, long wakaRight) {

        if (wakaTop > wallTop && wakaTop < wallBottom && wakaLeft < wallRight && 
                wakaLeft > wallLeft) {
                
                        return true;
        }

        if (wakaBottom > wallTop && wakaBottom < wallBottom && wakaLeft < wallRight && 
                wakaLeft > wallLeft) {
                
                        return true;
        }

        return false;
}

/**
 * This method calculates the top, bottom, left, and right position of wall, and 
 * waka, and checks if it is colliding when waka tries to move upwards or not.
 * It returns true if the waka is able to move upwards without colliding, else it 
 * returns false.
 * 
 * @return  Whether waka can move upwards or not.
 */


public boolean isCollidingUp () {

        // Checking if waka is colliding with a horizontal grid space when trying 
        // to move upwards or not.
        for (int i = 0; i < hor.size () - 1; i += 2) {

                long wallTop = hor.get (i + 1);
                long wallBottom = hor.get (i + 1) + 8;
                long wallLeft = hor.get (i);
                long wallRight = hor.get (i) + 16;

                long wakaTop = this.starty;
                long wakaBottom = this.starty + this.spirit.height - 3;

                long wakaLeft = this.startx +2;
                long wakaRight = this.startx + this.spirit.width -2;

                if (collisonUp (wallTop, wallBottom, wallLeft, wallRight, wakaTop, 
                                        wakaBottom, wakaLeft, wakaRight) == true) {
                        
                                                return true;
                }

        }

        // Checking if waka is colliding with an up right grid space when trying to 
        // move upwards or not.
        for (int i = 0; i < upR.size () - 1; i += 2) {

                long wallTop = upR.get (i + 1);
                long wallBottom = upR.get (i + 1) + 8;
                long wallLeft = upR.get (i) + 8;
                long wallRight = upR.get (i) + 16;

                long wakaTop = this.starty;
                long wakaBottom = this.starty + this.spirit.height - 3;

                long wakaLeft = this.startx +2;
                long wakaRight = this.startx + this.spirit.width -2;

                if (collisonUp (wallTop, wallBottom, wallLeft, wallRight, wakaTop, 
                                        wakaBottom, wakaLeft, wakaRight) == true) {
                        
                                                return true;
                }
        }

        // Checking if waka is colliding with an up left grid space when trying to 
        // move upwards or not.
        for (int i = 0; i < upL.size () - 1; i += 2) {

                long wallTop = upL.get (i + 1);
                long wallBottom = upL.get (i + 1) + 8;
                long wallLeft = upL.get (i);
                long wallRight = upL.get (i) + 8;

                long wakaTop = this.starty;
                long wakaBottom = this.starty + this.spirit.height - 3;

                long wakaLeft = this.startx +2;
                long wakaRight = this.startx + this.spirit.width -2;

                if (collisonUp (wallTop, wallBottom, wallLeft, wallRight, wakaTop, 
                                        wakaBottom, wakaLeft, wakaRight) == true) {
                        
                                                return true;
                }
        }

        return false;

}


/**
 * This method calculates the top, bottom, left, and right position of wall, and 
 * waka, and checks if it is colliding when waka tries to move downwards or not.
 * It returns true if the waka is able to move downwards without colliding, else 
 * it returns false.
 * 
 * @return  Whether waka can move downwards or not.
 */

public boolean isCollidingDown () {


        // Checking if waka is colliding with a horizontal grid space when trying to 
        // move downwards or not.
        for (int i = 0; i < hor.size () - 1; i += 2) {

                long wallTop = hor.get (i + 1);
                long wallBottom = hor.get (i + 1) + 8;
                long wallLeft = hor.get (i);
                long wallRight = hor.get (i) + 16;

                long wakaTop = this.starty + 3;
                long wakaBottom = this.starty + this.spirit.height - 3 -1;

                long wakaLeft = this.startx + 2 -1;
                long wakaRight = this.startx + this.spirit.width - 2;

                if (collisonDown (wallTop, wallBottom, wallLeft, wallRight, wakaTop, 
                                        wakaBottom, wakaLeft, wakaRight) == true) {
                        
                                                return true;
                }
        }

        // Checking if waka is colliding with a down right grid space when trying to 
        // move downwards or not.
        for (int i = 0; i < downR.size () - 1; i += 2) {

                long wallTop = downR.get (i + 1) + 8;
                long wallBottom = downR.get (i + 1) + 16;
                long wallLeft = downR.get (i) + 8;
                long wallRight = downR.get (i) + 16;

                long wakaTop = this.starty + 3;
                long wakaBottom = this.starty + this.spirit.height - 4;

                long wakaLeft = this.startx + 1;
                long wakaRight = this.startx + this.spirit.width - 2;

                if (collisonDown (wallTop, wallBottom, wallLeft, wallRight, wakaTop, 
                                        wakaBottom, wakaLeft, wakaRight) == true) {
                        
                                                return true;
                }
        }

        // Checking if waka is colliding with a down left grid space when trying to 
        // move downwards or not.
        for (int i = 0; i < downL.size () - 1; i += 2) {

                long wallTop = downL.get (i + 1) + 8;
                long wallBottom = downL.get (i + 1) + 16;
                long wallLeft = downL.get (i) - 1;
                long wallRight = downL.get (i) + 8;

                long wakaTop = this.starty + 3;
                long wakaBottom = this.starty + this.spirit.height - 3 -1;

                long wakaLeft = this.startx + 2;
                long wakaRight = this.startx + this.spirit.width - 2;

                if (collisonDown (wallTop, wallBottom, wallLeft, wallRight, wakaTop, 
                                        wakaBottom, wakaLeft, wakaRight) == true) {
                        
                                                return true;
                }
        }
        
        return false;
}


/**
 * This method calculates the top, bottom, left, and right position of wall, and 
 * waka, and checks if it is colliding when waka tries to move towards right or not.
 * It returns true if the waka is able to move towards right without colliding, else 
 * it returns false.
 * 
 * @return  Whether waka can move right or not.
 */

public boolean isCollidingRight () {

        // Checking if waka is colliding with a vertical grid space when trying to 
        // move right or not.
        for (int i = 0; i < ver.size () - 1; i += 2) {

                long wallTop = ver.get (i + 1);
                long wallBottom = ver.get (i + 1) + 16;
                long wallLeft = ver.get (i) + 8;
                long wallRight = ver.get (i) + 16;

                long wakaTop = this.starty + 3;
                long wakaBottom = this.starty + this.spirit.height - 3;

                long wakaLeft = this.startx + 2;
                long wakaRight = this.startx + this.spirit.width;

                if (collisonRight (wallTop, wallBottom, wallLeft, wallRight, wakaTop, 
                                        wakaBottom, wakaLeft, wakaRight) == true) {
                        
                                                return true;
                }


        }

        // Checking if waka is colliding with a down left grid space when trying to
        // move right or not.
        for (int i = 0; i < downL.size () - 1; i += 2) {

                long wallTop = downL.get (i + 1) + 8;
                long wallBottom = downL.get (i + 1) + 16;
                long wallLeft = downL.get (i) + 8;
                long wallRight = downL.get (i) + 16;

                long wakaTop = this.starty + 3;
                long wakaBottom = this.starty + this.spirit.height - 3;

                long wakaLeft = this.startx + 2;
                long wakaRight = this.startx + this.spirit.width;


                if (collisonRight (wallTop, wallBottom, wallLeft, wallRight, wakaTop, 
                                        wakaBottom, wakaLeft, wakaRight) == true) {
                        
                                                return true;
                }
        }

        // Checking if waka is colliding with a down right grid space when trying to 
        // move right or not.
        for (int i = 0; i < downR.size () - 1; i += 2) {

                long wallTop = downR.get (i + 1) + 8;
                long wallBottom = downR.get (i + 1) + 16;
                long wallLeft = downR.get (i) + 8;
                long wallRight = downR.get (i) + 16;

                long wakaTop = this.starty + 3;
                long wakaBottom = this.starty + this.spirit.height - 3;

                long wakaLeft = this.startx + 2;
                long wakaRight = this.startx + this.spirit.width;


                if (collisonRight (wallTop, wallBottom, wallLeft, wallRight, wakaTop, 
                                wakaBottom, wakaLeft, wakaRight) == true) {
                        
                                        return true;
                }
        }

        // Checking if waka is colliding with an up left grid space when trying to 
        // move right or not.
        for (int i = 0; i < upL.size () - 1; i += 2) {

                long wallTop = upL.get (i + 1);
                long wallBottom = upL.get (i + 1) + 8;
                long wallLeft = upL.get (i) + 8;
                long wallRight = upL.get (i) + 16;

                long wakaTop = this.starty + 3;
                long wakaBottom = this.starty + this.spirit.height - 3;

                long wakaLeft = this.startx + 2;
                long wakaRight = this.startx + this.spirit.width;

                if (collisonRight (wallTop, wallBottom, wallLeft, wallRight, wakaTop, 
                                wakaBottom, wakaLeft, wakaRight) == true) {
                        
                                        return true;
                }
        }

        // Checking if waka is colliding with an up right grid space when trying to 
        // move right or not.
        for (int i = 0; i < upR.size () - 1; i += 2) {

                long wallTop = upR.get (i + 1);
                long wallBottom = upR.get (i + 1) + 8;
                long wallLeft = upR.get (i) + 8;
                long wallRight = upR.get (i) + 16;

                long wakaTop = this.starty + 3;
                long wakaBottom = this.starty + this.spirit.height - 3;

                long wakaLeft = this.startx + 2;
                long wakaRight = this.startx + this.spirit.width;

                if (collisonRight (wallTop, wallBottom, wallLeft, wallRight, wakaTop, 
                                        wakaBottom, wakaLeft, wakaRight) == true) {
                        
                                                return true;
                }
        }

        return false;
}


/**
 * This method calculates the top, bottom, left, and right position of wall, and waka, 
 * and checks if it is colliding when waka tries to move towards left or not.
 * It returns true if the waka is able to move towards left without colliding, else 
 * it returns false.
 * 
 * @return  Whether waka can move left or not.
 */


public boolean isCollidingLeft () {

        // Checking if waka is colliding with a vertical grid space when trying to move
        // left or not.
        for (int i = 0; i < ver.size () - 1; i += 2) {

                long wallTop = ver.get (i + 1);
                long wallBottom = ver.get (i + 1) + 16;
                long wallLeft = ver.get (i) + 8;
                long wallRight = ver.get (i) + 16;

                long wakaTop = this.starty + 3;
                long wakaBottom = this.starty + this.spirit.height - 3;

                long wakaLeft = this.startx + 2;
                long wakaRight = this.startx + this.spirit.width - 2;

                if (collisonLeft1 (wallTop, wallBottom, wallLeft, wallRight, wakaTop, 
                                        wakaBottom, wakaLeft, wakaRight) == true) {
                        
                                                return true;
                }

                wallLeft = ver.get (i);
                wallRight = ver.get (i) + 8;

                if (collisonLeft2 (wallTop, wallBottom, wallLeft, wallRight, wakaTop, 
                                        wakaBottom, wakaLeft, wakaRight) == true) {
                        
                                                return true;
                }

        }

        // Checking if waka is colliding with an up left grid space when trying to 
        // move left or not.
        for (int i = 0; i < upL.size () - 1; i += 2) {

                long wallTop = upL.get (i + 1);
                long wallBottom = upL.get (i + 1) + 8;
                long wallLeft = upL.get (i) + 8;
                long wallRight = upL.get (i) + 16;

                long wakaTop = this.starty + 3;
                long wakaBottom = this.starty + this.spirit.height - 3;

                long wakaLeft = this.startx + 2;
                long wakaRight = this.startx + this.spirit.width - 2;

                if (collisonLeft1 (wallTop, wallBottom, wallLeft, wallRight, wakaTop, 
                                        wakaBottom, wakaLeft, wakaRight) == true) {
                        
                                                return true;
                }

                wallTop = upL.get (i + 1);
                wallBottom = upL.get (i + 1) + 8;
                wallLeft = upL.get (i);
                wallRight = upL.get (i) + 8;

                if (collisonLeft2 (wallTop, wallBottom, wallLeft, wallRight, wakaTop, 
                                        wakaBottom, wakaLeft, wakaRight) == true) {
                        
                                                return true;
                }
        }

        // Checking if waka is colliding with an up right grid space when trying to 
        // move left or not.
        for (int i = 0; i < upR.size () - 1; i += 2) {

                long wallTop = upR.get (i + 1);
                long wallBottom = upR.get (i + 1) + 8;
                long wallLeft = upR.get (i) + 8;
                long wallRight = upR.get (i) + 16;

                long wakaTop = this.starty + 3;
                long wakaBottom = this.starty + this.spirit.height - 3;

                long wakaLeft = this.startx + 2;
                long wakaRight = this.startx + this.spirit.width - 2;

                if (collisonLeft1 (wallTop, wallBottom, wallLeft, wallRight, wakaTop, 
                                        wakaBottom, wakaLeft, wakaRight) == true) {
                        
                                                return true;
                }

                wallTop = upR.get (i + 1);
                wallBottom = upR.get (i + 1) + 8;
                wallLeft = upR.get (i);
                wallRight = upR.get (i) + 8;

                if (collisonLeft2 (wallTop, wallBottom, wallLeft, wallRight, wakaTop, 
                                        wakaBottom, wakaLeft, wakaRight) == true) {
                        
                                                return true;
                }

        }

        // Checking if waka is colliding with a down left grid space when trying to 
        // move left or not.
        for (int i = 0; i < downL.size () - 1; i += 2) {

                long wallTop = downL.get (i + 1) + 8;
                long wallBottom = downL.get (i + 1) + 16;
                long wallLeft = downL.get (i) + 8;
                long wallRight = downL.get (i) + 16;

                long wakaTop = this.starty + 3;
                long wakaBottom = this.starty + this.spirit.height - 3;

                long wakaLeft = this.startx + 2;
                long wakaRight = this.startx + this.spirit.width - 2;


                if (collisonLeft1 (wallTop, wallBottom, wallLeft, wallRight, wakaTop, 
                                        wakaBottom, wakaLeft, wakaRight) == true) {
                        
                                                return true;
                }

                wallTop = downL.get (i + 1) + 8;
                wallBottom = downL.get (i + 1) + 16;
                wallLeft = downL.get (i);
                wallRight = downL.get (i) + 8;

                if (collisonLeft2 (wallTop, wallBottom, wallLeft, wallRight, wakaTop, 
                                        wakaBottom, wakaLeft, wakaRight) == true) {
                        
                                                return true;
                }

        }

        // Checking if waka is colliding with a down right grid space when trying to 
        // move left or not.
        for (int i = 0; i < downR.size () - 1; i += 2) {

                long wallTop = downR.get (i + 1) + 8;
                long wallBottom = downR.get (i + 1) + 16;
                long wallLeft = downR.get (i) + 8;
                long wallRight = downR.get (i) + 16;

                long wakaTop = this.starty + 3;
                long wakaBottom = this.starty + this.spirit.height - 3;

                long wakaLeft = this.startx + 2;
                long wakaRight = this.startx + this.spirit.width - 2;

                if (collisonLeft1 (wallTop, wallBottom, wallLeft, wallRight, wakaTop, 
                                        wakaBottom, wakaLeft, wakaRight) == true) {
                        
                                                return true;
                }

                wallTop = downR.get (i + 1) + 8;
                wallBottom = downR.get (i + 1) + 16;
                wallLeft = downR.get (i);
                wallRight = downR.get (i) + 8;

                if (collisonLeft2 (wallTop, wallBottom, wallLeft, wallRight, wakaTop, 
                                        wakaBottom, wakaLeft, wakaRight) == true) {
                        
                                                return true;
                }
        }

        return false;
}


/**
 *  This method calculates the centre coordinates of waka, and checks if it collides 
 *  with any fruit in the fruits, and super fruits list when waka is trying to move left. 
 *  It decides whether to remove from fruits list, or super fruits list based on the 
 *  type parameter. type parameter "simple" refers to simple fruits list, while 
 *  "super" refers to super fruits list.
 * 
 * @param fruitsListt    List of fruits, or superfruits with which colliding needs to be checked.
 * @param type     String telling the type of fruit.
 */

public void isCollidingFruitsLeft (ArrayList<Integer> fruitsListt, String type) {

        for (int i = 0; i < fruitsListt.size () - 1; i += 2) {

                int fruitsx = fruitsListt.get (i) + 2;
                int fruitsy = fruitsListt.get (i + 1) + 2;

                long wakaTop = this.starty;
                long wakaBottom = this.starty + 16;
                long wakaLeft = this.startx;
                long wakaRight = this.startx + 16 - 7;

                long wakaCentrex = (wakaLeft + wakaRight) / 2;
                long wakaCentrey = (wakaTop + wakaBottom) / 2;

                long diffx = Math.abs (fruitsx - wakaCentrex);
                long diffy = Math.abs (fruitsy - wakaCentrey);

                if (fruitsy > wakaTop && fruitsy < wakaBottom 
                        && wakaRight - fruitsx >= 0 && wakaRight - fruitsx < 3) {

                        fruits.removeFruits (fruitsListt.get (i), 
                                                fruitsListt.get (i + 1), type);
                }
        }
}


/**
 * This method calculates the centre coordinates of waka, and checks if it collides with any fruit in the fruits, and super fruits list when waka is trying to move right. 
 *  It decides whether to remove from fruits list, or super fruits list based on the type parameter. type parameter "simple" refers to simple fruits list,
 *  while "super" refers to super fruits list.
 * 
 * 
 * @param fruitsListt    List of fruits, or superfruits with which colliding needs to be checked.
 * @param type     String telling the type of fruit.
 */

public void isCollidingFruitsRight (ArrayList<Integer> fruitsListt, String type) {

        for (int i = 0; i < fruitsListt.size () - 1; i += 2) {

                int fruitsx = fruitsListt.get (i) + 2;
                int fruitsy = fruitsListt.get (i + 1) + 2;

                long wakaTop = this.starty;
                long wakaBottom = this.starty + 16;
                long wakaLeft = this.startx;
                long wakaRight = this.startx + 16; 

                long wakaCentrex = (wakaLeft + wakaRight) / 2;
                long wakaCentrey = (wakaTop + wakaBottom) / 2;

                long diffx = Math.abs (fruitsx - wakaCentrex);
                long diffy = Math.abs (fruitsy - wakaCentrey);

                if (fruitsy > wakaTop && fruitsy < wakaBottom 
                                && fruitsx - wakaLeft >= 0 && fruitsx - wakaLeft < 3) {
                        
                                fruits.removeFruits (fruitsListt.get (i), 
                                                        fruitsListt.get (i + 1), type);
                }
        }
}


/**
 * This method calculates the centre coordinates of waka, and checks if it collides 
 * with any fruit in the fruits, and super fruits list when waka is trying to move 
 * downwards. 
 * It decides whether to remove from fruits list, or super fruits list based on the 
 * type parameter. type parameter "simple" refers to simple fruits list, while
 * "super" refers to super fruits list.
 * 
 * 
 * @param fruitsListt    List of fruits, or superfruits with which colliding needs to be checked.
 * @param type     String telling the type of fruit.
 */

public void isCollidingFruitsDown (ArrayList<Integer> fruitsListt, String type) {

        for (int i = 0; i < fruitsListt.size () - 1; i += 2) {

                int fruitsx = fruitsListt.get (i) + 2;
                int fruitsy = fruitsListt.get (i + 1) + 2;

                long wakaTop = this.starty;
                long wakaBottom = this.starty + 16;
                long wakaLeft = this.startx;
                long wakaRight = this.startx + this.spirit.width;

                long wakaCentrex = (wakaLeft + wakaRight) / 2;
                long wakaCentrey = (wakaTop + wakaBottom) / 2;

                long diffx = Math.abs (fruitsx - wakaCentrex);
                long diffy = Math.abs (fruitsy - wakaCentrey);

                if (fruitsx > wakaLeft && fruitsx < wakaRight ) {

                        if (wakaBottom - fruitsy >= 0 && wakaBottom - fruitsy < 16) {
                                fruits.removeFruits (fruitsListt.get (i), 
                                                        fruitsListt.get (i + 1), type);
                        }

                }
        }
}


/**
 *  This method calculates the centre coordinates of waka, and checks if it collides 
 *  with any fruit in the fruits, and super fruits list when waka is trying to move upwards. 
 *  It decides whether to remove from fruits list, or super fruits list based on the 
 *  type parameter. type parameter "simple" refers to simple fruits list, while
 * "super" refers to super fruits list.
 * 
 * 
 * @param fruitsListt    List of fruits, or superfruits with which colliding needs to be checked.
 * @param type     String telling the type of fruit.
 */

public void isCollidingFruitsUp (ArrayList<Integer> fruitsListt, String type) {

        for (int i = 0; i < fruitsListt.size () - 1; i += 2) {

                int fruitsx = fruitsListt.get (i) + 2;
                int fruitsy = fruitsListt.get (i + 1) + 2;

                long wakaTop = this.starty;
                long wakaBottom = this.starty + this.spirit.height;
                long wakaLeft = this.startx;
                long wakaRight = this.startx + this.spirit.width;

                long wakaCentrex = (wakaLeft + wakaRight) / 2;
                long wakaCentrey = (wakaTop + wakaBottom) / 2;

                long diffx = Math.abs (fruitsx - wakaCentrex);
                long diffy = Math.abs (fruitsy - wakaCentrey);

                if (fruitsx > wakaLeft && fruitsx < wakaRight ) {

                        if (fruitsy - wakaTop >= 0 && fruitsy - wakaTop < 16) {
                                fruits.removeFruits (fruitsListt.get (i), 
                                                        fruitsListt.get (i + 1), type);
                        }

                }
        }
}

/**
 *  This method changes the PImage of waka to closed.
 */

public void close () {
        this.spirit = this.playerClosed;
}


/**
 *  It is the draw method which is called sixty (frame rate) times per second.
 */

public void tick () {
}


/**
 *  It is the draw method which is called sixty (frame rate) times per second. 
 * It draws the image of waka on the screen at its position using the required PImage.
 * 
 * @param app PApplet app object.
 */


// Draw method.
public void draw (PApplet app) {

        // This loop draws waka based on the number of lives left, on the bottom 
        // left of the screen, below the game grid.
        for (long i = 0; i < lives - 1; i++) {
                app.image (this.playerRight, 5 + i * 30, 548);
        }

        // It draws the waka based on its position, using the PImage required for 
        // that case.
        app.image (this.spirit, startx, starty);
}

}

