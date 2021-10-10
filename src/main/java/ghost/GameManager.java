package ghost;

import processing.core.PApplet;
import processing.core.PImage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.text.ParseException;
import java.util.*;

/**
 *  Manages different components of the game.
 */

public class GameManager {

// Variable of type Waka.
private Waka waka;

// Variable of type Ghost.
private Ghost ghost;

// Variable of type Fruit.
private Fruit fruit;

// Variable to store the current time.
private long timeNow;

// Variable to store the length of the frightened ghost mode.
private long frightenedLength;


/**
 *  Constructs a new GameManager object.
 * 
 * @param waka Object type of Waka.
 * @param ghost Object of type Ghost.
 * @param fruit Object of type Fruit.
 */

// Constructor
public GameManager (Waka waka, Ghost ghost, Fruit fruit) {
        this.waka = waka;
        this.ghost = ghost;
        this.fruit = fruit;
        frightenedLength = 0;
} 



/**
 *  Returns the length of frightened mode for the ghost.
 *  @return Frightened mode length.
 */

// Getter method.
public long getFrightenedLength () {
        return frightenedLength;
}



/**
 *  It returns an array list of coordinates. 
 *  The first element refers to the current x-axis position of ghost, 
 *  second refers to the current y-axis position of the ghost, the third
 *  element refers to the target x-axis position of ghost, and 
 *  fourth element represents the target y-axis position of ghost. 
 * 
 *  The type of ghost is determined with the help of the parameter. 
 *  The integer value 0 refers to the ambusher ghost, 
 *  1 refers to the ignorant ghost, 2 refers to the chaser ghost, and 
 *  3 refers to the whim ghost.
 * 
 * 
 *  @param k Number corresponding to the ghost type.
 * 
 *  @return Coordinates of ghost, and its target positions.
 */

// This method returns an array list which stores the x-axis, and y-axix position of 
// the ghost, and its targets.
public ArrayList <Long> getCoordinatesForDebug (int k) {

        ArrayList <Long> coordinates = new ArrayList<Long> ();

        if (k == 0 && ghost.isAmbusherAlive () == true) { //ambusher
                
                if(ghost.getTargetsAmbusher ().size () != 0) {

                        // Adding the x-axis position of the ghost.
                        coordinates.add (ghost.getTargetsAmbusher ().get (0) + 14);

                        // Adding the y-axis position of the ghost.
                        coordinates.add (ghost.getTargetsAmbusher ().get (1) + 14);

                        // Adding the x-axis position of the ghost's target.
                        coordinates.add (ghost.getTargetxAmbusher () + 12);

                        // Adding the y-axis position of the ghost's target.
                        coordinates.add (ghost.getTargetyAmbusher () + 12);

                }
        }

        if (k == 1 && ghost.isIgnorantAlive () == true) { //ignorant

                if(ghost.getTargetsIgnorant ().size () != 0) {

                        // Adding the x-axis position of the ghost.
                        coordinates.add (ghost.getTargetsIgnorant ().get (0) + 14);

                        // Adding the y-axis position of the ghost.
                        coordinates.add (ghost.getTargetsIgnorant ().get (1) + 14);

                        // Adding the x-axis position of the ghost's target.
                        coordinates.add (ghost.getTargetxIgnorant () + 12);

                        // Adding the y-axis position of the ghost's target.
                        coordinates.add (ghost.getTargetyIgnorant () + 12);

                }
        }

        if (k == 2 && ghost.isChaserAlive () == true) { //chaser

                if(ghost.getTargetsChaser ().size () != 0) {

                        // Adding the x-axis position of the ghost.
                        coordinates.add (ghost.getTargetsChaser ().get (0) + 14);

                        // Adding the y-axis position of the ghost.
                        coordinates.add (ghost.getTargetsChaser ().get (1) + 14);

                        // Adding the x-axis position of the ghost's target.
                        coordinates.add (ghost.getTargetxChaser () + 12);

                        // Adding the y-axis position of the ghost's target.
                        coordinates.add (ghost.getTargetyChaser ()  + 12);

                }
        }

        if (k == 3 && ghost.isWhimAlive () == true) { //whim

                if(ghost.getTargetsWhim ().size () != 0) {

                        // Adding the x-axis position of the ghost.
                        coordinates.add (ghost.getTargetsWhim ().get (0) + 14);

                        // Adding the y-axis position of the ghost.
                        coordinates.add (ghost.getTargetsWhim ().get (1) + 14);

                        // Adding the x-axis position of the ghost's target.
                        coordinates.add (ghost.getTargetxWhim () + 12);

                        // Adding the y-axis position of the ghost's target.
                        coordinates.add (ghost.getTargetyWhim () + 12);

                }
        }

        return coordinates;
}




/**
*  It is called sixty (frame rate) times per second. 
*  It performs all the necessary calculations for GameManager objects.
*  It finds the frightened mode start time, adds it to the list of frightened time, 
*  and from the current time it decides whether the ghost is in frightened mode or not.
*/

public void tick () {
        
        // Stores the current time in a variable.
        timeNow = System.currentTimeMillis() / 1000;

        // Stores the length of ghost frightened mode.
        frightenedLength = ghost.getFrightenedLength ();

        // Stores the time at which the frightened mode started.
        long frightenedStartTime = fruit.getFrightenedStartTime ();
        
        // If the start time is zero, then that means no super fruit has been eaten yet.
        if (frightenedStartTime != 0) {
                ghost.addFrightTime (frightenedStartTime);
        }
        
        // If the current time is less than the time when the frightened mode is 
        // supposed to end that is frightened start time + frightened mode length, 
        // then set the ghost to be in frightened mode.
        if (timeNow <= frightenedStartTime + frightenedLength) {
                ghost.setFrightened (true);
        } else {

                // If the condition is false, then the ghost is no more in frightened mode.
                ghost.setFrightened (false);
        }

}



/**
*  It is called sixty (frame rate) times per second. 
*  @param app PApplet app object.
*/

// Draw method.
public void draw (PApplet app) {
}

}